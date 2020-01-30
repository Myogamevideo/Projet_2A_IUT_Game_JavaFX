package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Fenetre option.
 */
public class FenetreOptions extends FenetrePrincipale {

    @FXML
    private Slider sonSlider;

    @FXML
    private ProgressIndicator sonIndicator;

    @FXML
    private VBox vboxback;

    @FXML
    private ComboBox comBox;

    private float volume;

    private String sourcePrincipale = "/fxml/FenetrePrincipale.fxml";
    private int widthScene = 720;
    private int heightScene = 480;

    /**
     * Valider.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void valider(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sourcePrincipale));
        loader.load();
        FenetrePrincipale fenetrePrincipal = loader.getController();
        fenetrePrincipal.setTheme(comBox.getValue().toString());
        fenetrePrincipal.setmusicMP3(mon_mediaPlayer,this.volume);
        Parent root = loader.getRoot();
        Scene sc = new Scene(root, widthScene, heightScene);
        stage.setScene(sc);
    }

    /**
     * Gets theme.
     */
    public void getTheme() {
        vboxback.getStylesheets().add("/css/" + comBox.getValue().toString() + ".css");
        vboxback.getStyleClass().add("background");
    }

    /**
     * Initialize.
     */
    public void initialize() {
        System.out.println(this.mon_volume);
        sonSlider.valueProperty().addListener((observable, oldValue, newValue) -> volume = (newValue.floatValue()) / 100);
        sonSlider.valueProperty().addListener((observable, oldValue, newValue) -> setmusicMP3(mon_mediaPlayer,(newValue.floatValue()) / 100));

        comBox.getItems().add("Dark");
        comBox.getItems().add("Light");
        comBox.getSelectionModel().selectFirst();

        sonIndicator.progressProperty().bind(sonSlider.valueProperty().divide(100.0));
    }
}
