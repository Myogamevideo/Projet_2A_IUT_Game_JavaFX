package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Instru;
import model.Personnage;

import java.io.File;
import java.io.IOException;

/**
 * The type Fenetre principal.
 */
public class FenetrePrincipale extends Parent {
    /**
     * The Theme.
     */
    protected String theme = "Dark";
    /**
     * The Mon file.
     */
    protected String mon_pathmusic = "projet06_javafx_2019\\Projet\\ressource\\music\\sonore.mp3";;

    /**
     * The Mon file.
     */
    protected File mon_file  = new File(mon_pathmusic);;
    /**
     * The Mon instru.
     */
    protected Instru mon_instru = new Instru();;
    /**
     * The Mon sound.
     */
    protected Media mon_sound = new Media(mon_file.toURI().toString());
    /**
     * The Mon pathmusic.
     */
    /**
     * The Typepersonnage.
     */
    protected Personnage typepersonnage;
    /**
     * The Vboxback.
     */
    protected MediaPlayer mon_mediaPlayer = new MediaPlayer(mon_sound);
    /**
     * The Mon volume.
     */
    protected float mon_volume;

    /**
     * The Vboxback.
     */
    @FXML
    protected VBox vboxback;

    private String sourceChoixPersonnage = "/fxml/FenetreChoixPersonnages.fxml";
    private String sourceRegles = "/fxml/FenetreRegles.fxml";
    private String sourceOptions = "/fxml/FenetreOptions.fxml";
    private String sourceClassement = "/fxml/FenetreClassement.fxml";
    private int widthScene = 900;
    private int heightScene = 600;
    private int heightScenebis = 480;


    /**
     * Afficher jouer.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void afficherJouer(ActionEvent actionEvent) throws IOException {
        mon_instru.note_on(65);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sourceChoixPersonnage));
        loader.load();
        FenetreChoixPersonnages fenetreJouer = loader.getController();
        fenetreJouer.setTheme(this.theme);
        fenetreJouer.setmusicMP3(this.mon_mediaPlayer,this.mon_volume);
        Parent root = loader.getRoot();
        Scene sc = new Scene(root, widthScene, heightScene);
        stage.setScene(sc);
    }

    /**
     * Afficher regles.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void afficherRegles(ActionEvent actionEvent) throws IOException {
        mon_instru.note_on(64);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sourceRegles));
        loader.load();
        FenetreRegles fenetreRegles = loader.getController();
        fenetreRegles.setTheme(this.theme);
        fenetreRegles.setmusicMP3(this.mon_mediaPlayer,this.mon_volume);
        Parent root = loader.getRoot();
        Scene sc = new Scene(root, widthScene, heightScenebis);
        stage.setScene(sc);
    }

    /**
     * Afficher option.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void afficherOption(ActionEvent actionEvent) throws IOException {
        mon_instru.note_on(63);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sourceOptions));
        loader.load();
        FenetreOptions fenetreOption = loader.getController();
        fenetreOption.setTheme(this.theme);
        fenetreOption.setmusicMP3(this.mon_mediaPlayer,this.mon_volume);
        Parent root = loader.getRoot();
        Scene sc = new Scene(root, widthScene, heightScenebis);
        stage.setScene(sc);
    }

    /**
     * Afficher classement.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void afficherClassement(ActionEvent actionEvent) throws IOException {
        mon_instru.note_on(62);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sourceClassement));
        loader.load();
        FenetreClassement fenetreClassement = loader.getController();
        fenetreClassement.setTheme(this.theme);
        fenetreClassement.setmusicMP3(this.mon_mediaPlayer,this.mon_volume);
        Parent root = loader.getRoot();
        Scene sc = new Scene(root, widthScene, heightScenebis);
        stage.setScene(sc);
    }

    /**
     * Quitter.
     *
     * @param actionEvent the action event
     */
    public void quitter(ActionEvent actionEvent){
        mon_instru.note_on(61);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Sets theme.
     *
     * @param theme the theme
     */
    public void setTheme(String theme) {
        this.theme = theme;
        vboxback.getStylesheets().add("/css/" + this.theme + ".css");
        vboxback.getStyleClass().add("background");
    }

    /**
     * Setmusic mp 3.
     *
     * @param Player the player
     * @param volume the volume
     */
    public void setmusicMP3(MediaPlayer Player,float volume){
        this.mon_volume = volume;
        this.mon_mediaPlayer = Player;
        this.mon_mediaPlayer.setAutoPlay(false);
        this.mon_mediaPlayer.setVolume(this.mon_volume);
        this.mon_mediaPlayer.play();
    }

    /**
     * Initialize.
     *
     * @throws Exception the exception
     */
    public void initialize() throws Exception {
        vboxback.getStylesheets().add("/css/" + this.theme + ".css");
        vboxback.getStyleClass().add("background");

        setmusicMP3(mon_mediaPlayer,0);
    }

    /**
     * Show alert.
     *
     * @param texte the texte
     */
    public void showAlert(String texte) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(texte);
        alert.showAndWait();
    }
}