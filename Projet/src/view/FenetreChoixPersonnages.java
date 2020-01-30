package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Archer;
import model.Guerrier;

import java.io.IOException;

/**
 * The type Fenetre jouer.
 */
public class FenetreChoixPersonnages extends FenetrePrincipale {
    @FXML
    private Label labelpseudo;
    @FXML
    private TextField inputpseudo;
    @FXML
    private Label labelpersonnage;
    @FXML
    private TextArea labelarcher;
    @FXML
    private TextArea labelguerrier;
    @FXML
    private RadioButton radio0;
    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML
    private RadioButton radio3;
    @FXML
    private RadioButton radio4;
    @FXML
    private RadioButton radio5;

    private Guerrier guerrier = new Guerrier(null, 0, 0);
    private Archer archer = new Archer(null, 0, 0);
    private String personnage;
    private String pseudo;
    private int position = 0;

    private int widthScene = 720;
    private int heightScene = 480;
    private int widthSceneJeu = 1280;
    private int heightSceneJeu = 720;
    private String sourceJeu = "/fxml/FenetreJeu.fxml";
    private String sourcePrincipale = "/fxml/FenetrePrincipale.fxml";

    /**
     * Valider.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void valider(ActionEvent actionEvent) throws IOException {
        pseudo = inputpseudo.getText();
        if (this.personnage == null || pseudo.isEmpty()) {
            showAlert("Personnage non choisie ou pseudo non renseigné");
        } else {
            if (this.personnage.equals("guerrier")) {
                this.typepersonnage = new Guerrier(pseudo, 0, position);
            }
            if (this.personnage.equals("archer")) {
                this.typepersonnage = new Archer(pseudo, 0, position);
            }
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(sourceJeu));
            loader.load();
            FenetreJeu fenetreJeux = loader.getController();
            //fenetreJeux.setPersonnage(typepersonnage);
            //fenetreJeux.startHandler();
            fenetreJeux.setPartie(typepersonnage);
            fenetreJeux.setTheme(this.theme);
            fenetreJeux.setmusicMP3(mon_mediaPlayer,mon_volume);
            Parent root = loader.getRoot();
            Scene sc = new Scene(root, widthSceneJeu, heightSceneJeu);
            stage.setMaximized(true);
            stage.setScene(sc);
        }
    }

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

    /**
     * Sets personnage guerrier.
     */
    public void setPersonnageGuerrier() {
        this.personnage = "guerrier";
        labelpersonnage.setText(this.personnage);
    }

    /**
     * Sets personnage archer.
     */
    public void setPersonnageArcher() {
        this.personnage = "archer";
        labelpersonnage.setText(this.personnage);
    }

    /**
     * Initialize.
     */
    public void initialize() {
        labelarcher.setText("Points de vie : " + archer.getPts_vie() + "\nPoints de mouvement : " + archer.getPts_mouvement() + "\nDistance des attaques : " + archer.getDistance() + "\nPoints d'action : " + archer.getPts_action() + "\nNombre de soin : " + archer.getNb_potion() + "");
        labelguerrier.setText("Points de vie : " + guerrier.getPts_vie() + " \nPoints de mouvement : " + guerrier.getPts_mouvement() + " \nDistance des attaques : " + guerrier.getDistance() + " \nPoints d'action : " + guerrier.getPts_action() + "\nNombre de soin : " + archer.getNb_potion() + "");
        labelpseudo.textProperty().bindBidirectional(inputpseudo.textProperty());
    }

    /**
     * Positionselect.
     *
     * @param actionEvent the action event
     */
    public void positionselect(ActionEvent actionEvent) {
        toutdisable();
        ((RadioButton) actionEvent.getSource()).setSelected(true);
        position = Integer.parseInt((((RadioButton) actionEvent.getSource()).getText()).trim());
    }

    private void toutdisable() {
        radio0.setSelected(false);
        radio1.setSelected(false);
        radio2.setSelected(false);
        radio3.setSelected(false);
        radio4.setSelected(false);
        radio5.setSelected(false);
    }
}


