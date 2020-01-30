package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 * The type Launch.
 */
public class Launch extends Application {

    private int widthScene = 720;
    private int heightScene = 480;
    private String sourcePrincipale = "/fxml/FenetrePrincipale.fxml";
    private String nomFenetrePrincipale = "Dofus";
    private String imagePrincipale = "/image/logo.png";

    /**
     * Start.
     *
     * @param primaryStage the primary stage
     * @throws Exception the exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sourcePrincipale));
            Parent root = loader.load();
            Scene sc = new Scene(root, widthScene, heightScene);
            primaryStage.setTitle(nomFenetrePrincipale);
            primaryStage.getIcons().add(new Image(imagePrincipale));
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
