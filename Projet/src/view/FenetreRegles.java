package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Fenetre regles.
 */
public class FenetreRegles extends FenetrePrincipale {

    private String sourcePrincipale = "/fxml/FenetrePrincipale.fxml";
    private int widthScene = 720;
    private int heightScene = 480;

    /**
     * Retour.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void retour(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sourcePrincipale));
        loader.load();
        FenetrePrincipale fenetrePrincipal = loader.getController();
        fenetrePrincipal.setTheme(this.theme);
        fenetrePrincipal.setmusicMP3(mon_mediaPlayer,mon_volume);
        Parent root = loader.getRoot();
        Scene sc = new Scene(root, widthScene, heightScene);
        stage.setScene(sc);
    }

}
