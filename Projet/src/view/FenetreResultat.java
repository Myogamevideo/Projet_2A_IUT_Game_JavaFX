package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

/**
 * The type Fenetre resultat.
 */
public class FenetreResultat extends FenetrePrincipale {

    @FXML
    private TextField textpseudo;
    @FXML
    private TextField textnbvague;
    @FXML
    private TextField textnbtour;
    @FXML
    private TextField texttemps;
    @FXML
    private ListView<Joueur> listViewJoueur;
    @FXML
    private ListView<Joueur> listeViewJoueurVague;
    @FXML
    private ListView<Joueur> listeViewJoueurNbtour;
    @FXML
    private ListView<Joueur> listeViewJoueurTempsJ;
    @FXML
    private Button boutonSave;

    private Classement classementAfficher;
    private String pseudo;
    private String vague;
    private String nbtour;
    private String temps;
    private Joueur joueur;
    private String filename = "classement.json";
    private String sourcePrincipale = "/fxml/FenetrePrincipale.fxml";
    private int widthScene = 720;
    private int heightScene = 480;

    /**
     * Initialize.
     *
     * @throws IOException the io exception
     */
    public void initialize() throws IOException {
        chargerListViews();
    }

    /**
     * Charger list views.
     *
     * @throws IOException the io exception
     */
    public void chargerListViews() throws IOException {
        classementAfficher = LoadJSON.load(filename);
        listViewJoueur.itemsProperty().bind(classementAfficher.joueursProperty());

        listViewJoueur.setCellFactory(__ -> new ListCell<Joueur>() {
            @Override
            protected void updateItem(Joueur joueur, boolean empty) {
                super.updateItem(joueur, empty);
                if (!empty) {
                    textProperty().bind(joueur.pseudoProperty());
                } else {
                    textProperty().unbind();
                    setText("");
                }
            }
        });

        listeViewJoueurVague.setCellFactory(__ -> new ListCell<Joueur>() {
                    @Override
                    protected void updateItem(Joueur joueur, boolean empty) {
                        super.updateItem(joueur, empty);
                        if (!empty) {
                            textProperty().bind(joueur.vagueProperty().asString());
                        } else {
                            textProperty().unbind();
                            setText("");
                        }
                    }
                }
        );

        listeViewJoueurNbtour.setCellFactory(__ -> new ListCell<Joueur>() {
                    @Override
                    protected void updateItem(Joueur joueur, boolean empty) {
                        super.updateItem(joueur, empty);
                        if (!empty) {
                            textProperty().bind(joueur.nbtourProperty().asString());
                        } else {
                            textProperty().unbind();
                            setText("");
                        }
                    }
                }
        );

        listeViewJoueurTempsJ.setCellFactory(__ -> new ListCell<>() {
                    @Override
                    protected void updateItem(Joueur joueur, boolean empty) {
                        super.updateItem(joueur, empty);
                        if (!empty) {
                            textProperty().bind(joueur.tempsJProperty().asString());
                        } else {
                            textProperty().unbind();
                            setText("");
                        }
                    }
                }
        );
    }

    /**
     * Accueil.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void accueil(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sourcePrincipale));
        loader.load();
        FenetrePrincipale fenetrePrincipal = loader.getController();
        fenetrePrincipal.setTheme(this.theme);
        fenetrePrincipal.setmusicMP3(mon_mediaPlayer, mon_volume);
        Parent root = loader.getRoot();
        Scene sc = new Scene(root, widthScene, heightScene);
        stage.setScene(sc);
    }

    /**
     * Sets score.
     *
     * @param j the j
     */
    public void setScore(Joueur j) {
        this.joueur = j;
        pseudo = j.getPseudo();
        vague = String.valueOf(j.getVague());
        nbtour = String.valueOf(j.getNbtour());
        temps = String.valueOf(j.getTempsJ());
        textpseudo.setText(pseudo);
        textnbvague.setText(vague);
        textnbtour.setText(nbtour);
        texttemps.setText(temps);
    }

    /**
     * Save joueur.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void saveJoueur(ActionEvent actionEvent) throws IOException {
        Joueur j = new Joueur(joueur.getPseudo() ,joueur.getVague(), joueur.getNbtour(),joueur.getTempsJ());
        Classement classement = LoadJSON.load(filename);
        classement.addJoueur(j);
        ISave sauvegarde = new SaveJSON();
        sauvegarde.save(filename, classement);
        this.boutonSave.setDisable(true);
        chargerListViews();
    }

}

