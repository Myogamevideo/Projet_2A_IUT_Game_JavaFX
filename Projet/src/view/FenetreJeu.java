package view;

import handler.GameHandler;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Optional;

/**
 * The type Fenetre jeux.
 */
public class FenetreJeu extends FenetrePrincipale {

    @FXML
    private Label labelpseudo;
    @FXML
    private Label labelvague;
    @FXML
    private Label labelnbtour;
    @FXML
    private Label labelptmouv;
    @FXML
    private Label labelptact;
    @FXML
    private Label labelvie;
    @FXML
    private GridPane  gridplateau;
    @FXML
    private TextArea textinfo;
    @FXML
    private Button buttonsoin;
    @FXML
    private Button buttoncompetence;

    private Personnage personnage;
    private Ennemi ennemi = new Ennemi(getRandom(1, 3), getRandom(1, 3), getRandom(40, 60), getRandom(0, 11), getRandom(0, 5), getRandom(1, 2),0);
    private EventHandler<ActionEvent> deplacementHandler;
    private EventHandler<ActionEvent> actionHandler;
    private EventHandler<ActionEvent> enemieHandler;
    private EventHandler<ActionEvent> flechefinieHandler;
    private int vague = 0;
    private int nbtour = 1;
    private Image imagefleche = new Image("/image/fleche.png",500,500,true,false);
    private ImageView imageViewfleche = new ImageView(imagefleche);
    private int temps = 0;
    private Timer timer;

    private int widthScene = 1200;
    private int heightScene = 480;
    private String sourceResultat = "/fxml/FenetreResultat.fxml";

    /**
     * Gets random.
     *
     * @param min the min
     * @param max the max
     * @return the random
     */
    public static int getRandom(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    /**
     * Instantiates a new Fenetre jeux.
     */
    public FenetreJeu(){
        timer = createTimer ();
        timer.start ();
    }

    /**
     * Initialize.
     */
    public void initialize() {

        deplacementHandler = event -> {
            Personnage joueur = personnage;;
            String position = ((((Button) event.getSource()).getText()).trim());
            joueur = joueur.deplacementPersonnage(joueur,position);
            if (joueur.getPts_mouvement() > -1) {
                setPartie(joueur);
            }
            personnage = joueur;
        };

        actionHandler = event -> {
            Personnage joueur = personnage;
            Ennemi bot = ennemi;
            String distance;
            distance = ((((Button) event.getSource()).getText()).trim());
            joueur = joueur.actionPersonnage(joueur);
            bot = bot.enemieSubisDegat(bot,distance,personnage);
            if (joueur.getPts_action() > -1) {
                setPartie(joueur);
            }
            if (joueur.getPts_action() == 0) {
                setPartie(joueur);
            }
            personnage = joueur;
            ennemi = bot;

            System.out.println("Enemie a perdu : -" + ennemi.getDegat() + " et il lui reste " + ennemi.getPts_vie() + " PV");
            textinfo.appendText("Enemie a perdu : -" +  ennemi.getDegat() + " et il lui reste " + ennemi.getPts_vie() + " PV \n");
        };

        enemieHandler = event -> {
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

        flechefinieHandler = event -> {
            Ennemi bot = ennemi;
            bot = bot.enemieSubisCompetence(bot);
            ennemi = bot;
            personnage.setPts_action(0);
            personnage.setPts_mouvement(0);

            vboxback.getChildren().remove(imageViewfleche);
            buttoncompetence.setDisable(true);
            System.out.println("Utilisation de votre capacité special : "+personnage.getCompetence());
            textinfo.appendText("Utilisation de votre capacité special : "+personnage.getCompetence() +"\n");
            System.out.println("Enemie a perdu : -" + 50 + " et il lui reste " + ennemi.getPts_vie() + " PV");
            textinfo.appendText("Enemie a perdu : -" + 50 + " et il lui reste " + ennemi.getPts_vie() + " PV \n");
        };

    }

    /**
     * Start handler.
     */
    public void startHandler(){
        GameHandler gameHandler = new GameHandler();

        gameHandler.initComposants(labelpseudo, labelvague, labelnbtour, labelptmouv, labelptact, labelvie, gridplateau, textinfo, buttonsoin, buttoncompetence, vboxback);
        gameHandler.setPartie(this.personnage);
    }

    /**
     * Sets personnage.
     *
     * @param personnage the personnage
     */
    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }

    /**
     * Sets personnage.
     *
     * @param personnage the personnage
     */
    public void setPartie(Personnage personnage) {
        Node node = gridplateau.getChildren().get(0);
        gridplateau.getChildren().clear();
        gridplateau.getChildren().add(0, node);
        this.personnage = personnage;
        labelpseudo.setText(this.personnage.getPseudo() + " (LVL:" + this.personnage.getLvl() + ")");
        labelvague.setText(String.valueOf(this.vague));
        labelnbtour.setText("Nombre de tour jouer : " + this.nbtour);
        labelptmouv.setText("Points de mouvement : " + this.personnage.getPts_mouvement());
        labelptact.setText("Points d'action : " + this.personnage.getPts_action());
        labelvie.setText(this.personnage.getPts_vie() + " / " + this.personnage.getVietotal() + " PV");
        buttonsoin.setText("Soin ("+this.personnage.getNb_potion()+")");
        buttoncompetence.setText(this.personnage.getCompetence());
        imagejoueur(this.personnage);
        imageenemie(this.ennemi);
        vague(this.vague,this.personnage , this.ennemi);
    }

    /**
     * Imagejoueur.
     *
     * @param personnage the personnage
     */
    public void imagejoueur(Personnage personnage) {
        generatorimage("/image/" + personnage.getClass().getSimpleName().toLowerCase() + ".png", personnage.getPositionX(), personnage.getPositionY());
    }

    /**
     * Imageenemie.
     *
     * @param ennemi the enemie
     */
    public void imageenemie(Ennemi ennemi) {
        generatorimage("/image/" + ennemi.getClass().getSimpleName().toLowerCase() + ".png", ennemi.getPositionX(), ennemi.getPositionY());
    }

    /**
     * Vague.
     *
     * @param vague      the vague
     * @param personnage the personnage
     * @param enemie     the enemie
     */
    public void vague(int vague, Personnage personnage, Ennemi enemie) {
        for (int i = 0; i < vague + 1; i++) {
            tonneau(personnage, enemie);
        }
    }

    /**
     * Tonneau.
     *
     * @param personnage the personnage
     * @param enemie     the enemie
     */
    public void tonneau(Personnage personnage, Ennemi enemie) {
        int positionX = getRandom(2, 11);
        int positionY = getRandom(0, 5);
        while ((positionX == personnage.getPositionX() && positionY == personnage.getPositionY()) || (positionY == enemie.getPositionY() && positionX == enemie.getPositionX())) {
            positionX = getRandom(2, 11);
        }
        while ((positionX == personnage.getPositionX() && positionY == personnage.getPositionY()) || (positionY == enemie.getPositionY() && positionX == enemie.getPositionX())) {
            positionY = getRandom(0, 5);
        }
        generatorimage("/image/tonneau.png", positionX, positionY);
    }

    /**
     * Generatorimage.
     *
     * @param url    the url
     * @param column the column
     * @param row    the row
     */
    public void generatorimage(String url, int column, int row) {
        Image image = new Image(url, gridplateau.getColumnConstraints().get(column).getPrefWidth(), gridplateau.getRowConstraints().get(row).getPrefHeight(), true, false);
        ImageView imageView = new ImageView(image);
        Button button = new Button();
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.0); -fx-border-color: rgba(0, 0, 0, 0.0)");
        button.setId(url);
        button.setOnAction(enemieHandler);
        gridplateau.add(button, column, row);
    }

    /**
     * Boutondistance.
     *
     * @param positionX the position x
     * @param positionY the position y
     * @param distance  the distance
     */
    public void boutondistance(int positionX, int positionY, int distance) {
        int i;
        for (i = -distance; i < (distance + 1); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY < gridplateau.getRowCount() && positionY > -1 && positionX + i > -1 && positionX + i != positionX) {
                generatorboutondist(positionX + i, positionY);
            }
        }

        for (i = -distance + 1; i < (distance); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY - 1 < gridplateau.getRowCount() && positionY - 1 > -1 && positionX + i > -1) {
                generatorboutondist(positionX + i, positionY - 1);
            }
        }
        for (i = -distance + 1; i < (distance); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY + 1 < gridplateau.getRowCount() && positionY + 1 > -1 && positionX + i > -1) {
                generatorboutondist(positionX + i, positionY + 1);
            }
        }

        for (i = -distance + 2; i < (distance - 1); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY - 2 < gridplateau.getRowCount() && positionY - 2 > -1 && positionX + i > -1) {
                generatorboutondist(positionX + i, positionY - 2);
            }
        }
        for (i = -distance + 2; i < (distance - 1); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY + 2 < gridplateau.getRowCount() && positionY + 2 > -1 && positionX + i > -1) {
                generatorboutondist(positionX + i, positionY + 2);
            }
        }

        for (i = -distance + 3; i < (distance - 2); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY - 3 < gridplateau.getRowCount() && positionY - 3 > -1 && positionX + i > -1) {
                generatorboutondist(positionX + i, positionY - 3);
            }
        }
        for (i = -distance + 3; i < (distance - 2); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY + 3 < gridplateau.getRowCount() && positionY + 3 > -1 && positionX + i > -1) {
                generatorboutondist(positionX + i, positionY + 3);
            }
        }
    }

    /**
     * Boutonmouvement.
     *
     * @param positionX     the position x
     * @param positionY     the position y
     * @param pts_mouvement the pts mouvement
     */
    public void boutonmouvement(int positionX, int positionY, int pts_mouvement) {
        int i;
        for (i = -pts_mouvement; i < (pts_mouvement + 1); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY < gridplateau.getRowCount() && positionY > -1 && positionX + i > -1 && positionX + i != positionX) {
                generatorboutonmvt(positionX + i, positionY);
            }
        }

        for (i = -pts_mouvement + 1; i < (pts_mouvement); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY - 1 < gridplateau.getRowCount() && positionY - 1 > -1 && positionX + i > -1) {
                generatorboutonmvt(positionX + i, positionY - 1);
            }
        }
        for (i = -pts_mouvement + 1; i < (pts_mouvement); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY + 1 < gridplateau.getRowCount() && positionY + 1 > -1 && positionX + i > -1) {
                generatorboutonmvt(positionX + i, positionY + 1);
            }
        }

        for (i = -pts_mouvement + 2; i < (pts_mouvement - 1); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY - 2 < gridplateau.getRowCount() && positionY - 2 > -1 && positionX + i > -1) {
                generatorboutonmvt(positionX + i, positionY - 2);
            }
        }
        for (i = -pts_mouvement + 2; i < (pts_mouvement - 1); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY + 2 < gridplateau.getRowCount() && positionY + 2 > -1 && positionX + i > -1) {
                generatorboutonmvt(positionX + i, positionY + 2);
            }
        }

        for (i = -pts_mouvement + 3; i < (pts_mouvement - 2); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY - 3 < gridplateau.getRowCount() && positionY - 3 > -1 && positionX + i > -1) {
                generatorboutonmvt(positionX + i, positionY - 3);
            }
        }
        for (i = -pts_mouvement + 3; i < (pts_mouvement - 2); i++) {
            if (positionX + i < gridplateau.getColumnCount() && positionY + 3 < gridplateau.getRowCount() && positionY + 3 > -1 && positionX + i > -1) {
                generatorboutonmvt(positionX + i, positionY + 3);
            }
        }
    }

    /**
     * Generatorboutonmvt.
     *
     * @param column the column
     * @param row    the row
     */
    public void generatorboutonmvt(int column, int row) {
        Button buttonmvt = new Button();
        buttonmvt.prefHeight(gridplateau.getColumnConstraints().get(column).getMaxWidth());
        buttonmvt.prefHeight(gridplateau.getRowConstraints().get(row).getMaxHeight());
        buttonmvt.setStyle("-fx-background-color: rgba(140,203,77 , 0.5)");
        buttonmvt.setOnAction(deplacementHandler);
        gridplateau.add(buttonmvt, column, row);
        buttonmvt.setText(column + "/" + row);
    }

    /**
     * Generatorboutondist.
     *
     * @param column the column
     * @param row    the row
     */
    public void generatorboutondist(int column, int row) {
        Button buttondistance = new Button();
        buttondistance.prefHeight(gridplateau.getColumnConstraints().get(column).getMaxWidth());
        buttondistance.prefHeight(gridplateau.getRowConstraints().get(row).getMaxHeight());
        buttondistance.setStyle("-fx-background-color: rgba(232,7,7 , 0.5)");
        buttondistance.setOnAction(actionHandler);
        gridplateau.add(buttondistance, column, row);
        buttondistance.setText(column + "/" + row);
    }

    /**
     * Deplacement.
     *
     * @param actionEvent the action event
     */
    public void deplacement(ActionEvent actionEvent) {
        boutonmouvement(personnage.getPositionX(), personnage.getPositionY(), personnage.getPts_mouvement());
    }

    /**
     * Action.
     *
     * @param actionEvent the action event
     */
    public void action(ActionEvent actionEvent) {
        boutondistance(personnage.getPositionX(), personnage.getPositionY(), personnage.getDistance());
    }

    /**
     * Reintiliaser.
     */
    public void reintiliaser(){
        Personnage joueur = personnage;
        joueur = personnage.reintiliaserPersonnage(joueur);
        setPartie(joueur);
    }

    /**
     * Page resultat.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void pageResultat(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sourceResultat));
        loader.load();
        FenetreResultat fenetreResultat = loader.getController();
        System.out.println(personnage.getPseudo() + " / " + vague + "/" + nbtour);
        Joueur j = new Joueur(personnage.getPseudo(), vague,nbtour,temps);
        fenetreResultat.setScore(j);
        fenetreResultat.setTheme(this.theme);
        fenetreResultat.setmusicMP3(mon_mediaPlayer,mon_volume);
        Parent root = loader.getRoot();
        Scene sc = new Scene(root, widthScene, heightScene);
        stage.setScene(sc);
    }

    /**
     * Fintour.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void fintour(ActionEvent actionEvent) throws IOException {
        int distancenemiejoueurX;
        int distancenemiejoueurY;
        int distanceattaqueenemie;
        if (personnage.getPts_vie() < 1) {
            pageResultat(actionEvent);
        } else {
            if (ennemi.getPts_vie() < 1) {
                showAlert("Vague gagné au bout de " + nbtour + " tour(s) -> Vous montez de vague et de LVL");
                textinfo.appendText("Vague gagné au bout de " + nbtour + " tour(s) -> Vous montez de vague et de LVL \n");
                this.ennemi = new Ennemi(getRandom(1, 3), getRandom(1, 3), getRandom(40,(40 * vague)-40), getRandom(0, 11), getRandom(0, 5), getRandom(1, 2),0);
                personnage.setLvl(personnage.getLvl() + 1);
                vague = vague + 1;
                personnage.setNb_potion(personnage.getNb_potion() + getRandom(0, 1));
            } else {
                distancenemiejoueurX = personnage.getPositionX() - ennemi.getPositionX();
                distancenemiejoueurY = personnage.getPositionY() - ennemi.getPositionY();
                distanceattaqueenemie = ennemi.getDistance();
                if (Math.abs(distancenemiejoueurX) < (distanceattaqueenemie + 1) && Math.abs(distancenemiejoueurY) < (distanceattaqueenemie + 1)) {
                    botenemieattaque(actionEvent);
                } else {
                    botenemiedeplacement();
                    if (Math.abs(distancenemiejoueurX) < (distanceattaqueenemie + 1) && Math.abs(distancenemiejoueurY) < (distanceattaqueenemie + 1)) {
                        botenemieattaque(actionEvent);
                    }
                }
                if (personnage.getPts_vie() < 1) {
                    pageResultat(actionEvent);
                }
                nbtour = nbtour + 1;
            }
            if(nbtour == 10 || nbtour == 20){
                buttoncompetence.setDisable(false);
            }
            reintiliaser();
        }
    }

    /**
     * Botenemieattaque.
     *
     * @param actionEvent the action event
     */
    public void botenemieattaque(ActionEvent actionEvent) {
        Personnage joueur = personnage;
        Ennemi bot = ennemi;
        joueur = joueur.personnageSubisDegat(joueur,bot);
        personnage = joueur;
        System.out.println("Vous avez subis : -" + personnage.getDegat() + " degat(s) et il vous reste " + personnage.getPts_vie() + " PV");
        textinfo.appendText("Vous avez subis : -" + personnage.getDegat() + " degat(s) et il vous reste " + personnage.getPts_vie() + " PV \n");
        setPartie(personnage);
    }

    /**
     * Botenemiedeplacement.
     */
    public void botenemiedeplacement() {
        int EpositionX;
        int EpositionY;
        int Eptsmvt;
        int mvt;
        int ptsmvtrestant;
        EpositionX = ennemi.getPositionX();
        EpositionY = ennemi.getPositionY();
        Eptsmvt = ennemi.getPts_mouvement();

        mvt = getRandom(-Eptsmvt, Eptsmvt);
        EpositionX = EpositionX + mvt;
        while ((ennemi.getPositionY() == personnage.getPositionY() && EpositionX == personnage.getPositionX()) || EpositionX > gridplateau.getColumnCount() - 1 || EpositionX < 0) {
            mvt = getRandom(-Eptsmvt, Eptsmvt);
            EpositionX = EpositionX + mvt;
        }
        ennemi.setPositionX(EpositionX);
        ptsmvtrestant = Eptsmvt - Math.abs(mvt);
        ennemi.setPts_mouvement(ptsmvtrestant);

        mvt = getRandom(-ptsmvtrestant, ptsmvtrestant);
        EpositionY = EpositionY + mvt;
        while ((EpositionY == personnage.getPositionY() && ennemi.getPositionX() == personnage.getPositionX()) || EpositionY > gridplateau.getRowCount() - 1 || EpositionY < 0) {
            mvt = getRandom(-ptsmvtrestant, ptsmvtrestant);
            EpositionY = EpositionY + mvt;
        }
        ennemi.setPositionY(EpositionY);

        this.ennemi.setPts_mouvement(Eptsmvt);
        setPartie(personnage);
    }

    /**
     * Soin.
     *
     * @param actionEvent the action event
     */
    public void soin(ActionEvent actionEvent) {
        int nbpotion;
        int nbvie;
        nbpotion = personnage.getNb_potion();
        nbvie = personnage.getPts_vie();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Soin");
        alert.setHeaderText("Vous avez a votre dispostion : " + nbpotion + " potion(s) et " + nbvie + " vie");
        alert.setContentText("Voulez vous utilisez votre potion :");
        ButtonType buttonTypeOne = new ButtonType("+10Pdv");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            if(nbpotion > 0 && nbvie+10 < personnage.getVietotal()){
                personnage.setNb_potion(nbpotion-1);
                personnage.setPts_vie(nbvie+10);
                labelvie.setText(personnage.getPts_vie()+10 + " / " + personnage.getVietotal() + " PV");
                buttonsoin.setText("Soin ("+(nbpotion-1)+")");
            }
            else {
                showAlert("Impossible");
            }
        }
    }

    /**
     * Competence.
     *
     * @param actionEvent the action event
     */
    public void competence(ActionEvent actionEvent) {
        String competence ;
        competence = personnage.getCompetence();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Competence");
        alert.setHeaderText("Vous avez a votre dispostion la competence : " + competence+ " (-50 Pdv  à votre enemie)");
        alert.setContentText("Voulez vous l'utilisez");
        ButtonType buttonTypeOne = new ButtonType("-50 Pdv");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            vboxback.getChildren().add(2,imageViewfleche);
            TranslateTransition tt = new TranslateTransition(Duration.millis(2000), imageViewfleche);
            tt.setFromX(-1000);
            tt.setToX(1000);
            tt.setFromY(-1000);
            tt.setToY(1000);
            tt.setCycleCount(1);
            tt.setOnFinished(flechefinieHandler);
            tt.play();
        }
    }

    private Timer createTimer () {
        ActionListener action = actionEvent -> temps = temps + 1;
        return new Timer (1000, action);
    }
}
