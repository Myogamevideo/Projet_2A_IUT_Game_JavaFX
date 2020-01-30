package handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Ennemi;

/**
 * The type Ennemi handler.
 */
public class EnnemiHandler {

    private EventHandler<ActionEvent> ennemiHandler;
    private Ennemi ennemi;

    /**
     * Instantiates a new Ennemi handler.
     *
     * @param ennemi the ennemi
     */
    public EnnemiHandler(Ennemi ennemi) {
        this.ennemi=ennemi;
    }

    /**
     * Gets ennemi handler.
     *
     * @return the ennemi handler
     */
    public EventHandler<ActionEvent> getEnnemiHandler() {
        return ennemiHandler;
    }

    /**
     * Handle.
     */
    public void handle(){
        ennemiHandler = event -> {
            String type;
            type = ((((Button) event.getSource()).getId().trim()));
            if (type.contains("enemie")) {
                Stage stage = new Stage();
                Label titleLabel = new Label("Type : " + ennemi.getClass().getSimpleName());
                Label vieLabel = new Label("Point de vie : " + ennemi.getPts_vie());
                Label actionLabel = new Label("Point d'action : " + ennemi.getPts_action());
                Label mouvementLabel = new Label("Point de mouvement : " + ennemi.getPts_mouvement());
                Label distanceLabel = new Label("Distance : " + ennemi.getDistance());
                Button closeButton = new Button("Fermer");
                closeButton.setOnAction(e -> stage.close());
                VBox root = new VBox();
                root.getChildren().addAll(titleLabel, vieLabel, actionLabel, mouvementLabel, distanceLabel, closeButton);
                Scene scene = new Scene(root, 150, 150);
                stage.setScene(scene);
                stage.show();
            }
        };
    }
}
