package handler;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Ennemi;
import model.Personnage;

/**
 * The type Game handler.
 */
public class GameHandler {

    private Label labelpseudo;
    private Label labelvague;
    private Label labelnbtour;
    private Label labelptmouv;
    private Label labelptact;
    private Label labelvie;
    private GridPane gridplateau;
    private TextArea textinfo;
    private Button buttonsoin;
    private Button buttoncompetence;
    private  VBox vbox;

    private Personnage personnage;
    private Ennemi ennemi = new Ennemi(getRandom(1, 3), getRandom(1, 3), getRandom(40, 60), getRandom(0, 11), getRandom(0, 5), getRandom(1, 2),0);
    private int vague = 0;
    private int nbtour = 1;

    private DeplacementHandler deplacementHandler;
    private EnnemiHandler ennemiHandler;
    private ActionHandler actionHandler;
    private AnimationCompetenceHandler animationCompetenceHandler;

    /**
     * Instantiates a new Game handler.
     */
    public GameHandler(){
        deplacementHandler = new DeplacementHandler(personnage);
        ennemiHandler = new EnnemiHandler(ennemi);
        actionHandler = new ActionHandler(personnage, ennemi, textinfo);
        animationCompetenceHandler = new AnimationCompetenceHandler(personnage, ennemi, textinfo, buttoncompetence, vbox);
        deplacementHandler.handle();
        ennemiHandler.handle();
        actionHandler.handle();
        animationCompetenceHandler.handle();
    }

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
     * Set partie.
     *
     * @param personnage the personnage
     */
    public void setPartie(Personnage personnage){
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
     * Init composants.
     *
     * @param labelpseudo      the labelpseudo
     * @param labelvague       the labelvague
     * @param labelnbtour      the labelnbtour
     * @param labelptmouv      the labelptmouv
     * @param labelptact       the labelptact
     * @param labelvie         the labelvie
     * @param gridplateau      the gridplateau
     * @param textinfo         the textinfo
     * @param buttonsoin       the buttonsoin
     * @param buttoncompetence the buttoncompetence
     * @param vbox             the vbox
     */
    public void initComposants(Label labelpseudo, Label labelvague, Label labelnbtour, Label labelptmouv, Label labelptact, Label labelvie, GridPane gridplateau, TextArea textinfo, Button buttonsoin, Button buttoncompetence, VBox vbox) {
        this.labelpseudo = labelpseudo;
        this.labelvague = labelvague;
        this.labelnbtour = labelnbtour;
        this.labelptmouv = labelptmouv;
        this.labelptact = labelptact;
        this.labelvie = labelvie;
        this.gridplateau = gridplateau;
        this.textinfo = textinfo;
        this.buttonsoin = buttonsoin;
        this.buttoncompetence = buttoncompetence;
        this.vbox=vbox;
    }

    /**
     * Imagejoueur.
     *
     * @param personnage the personnage
     */
    private void imagejoueur(Personnage personnage) {
        generatorimage("/image/" + personnage.getClass().getSimpleName().toLowerCase() + ".png", personnage.getPositionX(), personnage.getPositionY());
    }

    /**
     * Imageenemie.
     *
     * @param ennemi the enemie
     */
    private void imageenemie(Ennemi ennemi) {
        generatorimage("/image/" + ennemi.getClass().getSimpleName().toLowerCase() + ".png", ennemi.getPositionX(), ennemi.getPositionY());
    }

    /**
     * Vague.
     *
     * @param vague      the vague
     * @param personnage the personnage
     * @param enemie     the enemie
     */
    private void vague(int vague, Personnage personnage, Ennemi enemie) {
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
    private void tonneau(Personnage personnage, Ennemi enemie) {
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
    private void generatorimage(String url, int column, int row) {
        Image image = new Image(url, gridplateau.getColumnConstraints().get(column).getPrefWidth(), gridplateau.getRowConstraints().get(row).getPrefHeight(), true, false);
        ImageView imageView = new ImageView(image);
        Button button = new Button();
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: rgba(0, 0, 0, 0.0); -fx-border-color: rgba(0, 0, 0, 0.0)");
        button.setId(url);
        button.setOnAction(ennemiHandler.getEnnemiHandler());
        gridplateau.add(button, column, row);
    }

}
