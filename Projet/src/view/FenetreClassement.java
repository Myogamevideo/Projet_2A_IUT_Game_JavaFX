package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Classement;
import model.Joueur;
import model.LoadJSON;

import java.io.IOException;

/**
 * The type Fenetre classement.
 */
public class FenetreClassement extends FenetrePrincipale {

    @FXML
    private ListView<Joueur> listViewJoueur;
    @FXML
    private ListView<Joueur> listeViewJoueurVague;
    @FXML
    private ListView<Joueur> listeViewJoueurNbtour;
    @FXML
    private ListView<Joueur> listeViewJoueurTempsJ;
    private Classement classementAfficher;

    private String filename ="classement.json";
    private String sourcePrincipale = "/fxml/FenetrePrincipale.fxml";
    private int widthScene = 720;
    private int heightScene = 480;

    /**
     * Initialize.
     */
    public void initialize() {
        classementAfficher = LoadJSON.load(filename);
        listViewJoueur.itemsProperty().bind(classementAfficher.joueursProperty());
        listViewJoueur.setCellFactory(__ -> new ListCell<>() {
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

        listeViewJoueurVague.setCellFactory(__ -> new ListCell<>() {
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

        listeViewJoueurNbtour.setCellFactory(__ -> new ListCell<>() {
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
