package handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Ennemi;
import model.Personnage;

/**
 * The type Animation competence handler.
 */
public class AnimationCompetenceHandler {

    private EventHandler<ActionEvent> animationCompetence;
    private Personnage personnage;
    private Ennemi ennemi;
    private TextArea textinfo;
    private Image imagefleche = new Image("/image/fleche.png",500,500,true,false);
    private ImageView imageViewfleche = new ImageView(imagefleche);
    private Button buttoncompetence;
    private VBox vbox;

    /**
     * Instantiates a new Animation competence handler.
     *
     * @param personnage       the personnage
     * @param ennemi           the ennemi
     * @param textinfo         the textinfo
     * @param buttoncompetence the buttoncompetence
     * @param vbox             the vbox
     */
    public AnimationCompetenceHandler(Personnage personnage, Ennemi ennemi, TextArea textinfo, Button buttoncompetence, VBox vbox) {
        this.personnage=personnage;
        this.ennemi=ennemi;
        this.textinfo=textinfo;
        this.buttoncompetence=buttoncompetence;
        this.vbox=vbox;
    }

    /**
     * Handle.
     */
    public void handle(){
        animationCompetence = event -> {
            Ennemi bot = ennemi;
            bot = bot.enemieSubisCompetence(bot);
            ennemi = bot;
            personnage.setPts_action(0);
            personnage.setPts_mouvement(0);

            vbox.getChildren().remove(imageViewfleche);
            buttoncompetence.setDisable(true);
            System.out.println("Utilisation de votre capacité special : "+personnage.getCompetence());
            textinfo.appendText("Utilisation de votre capacité special : "+personnage.getCompetence() +"\n");
            System.out.println("Enemie a perdu : -" + 50 + " et il lui reste " + ennemi.getPts_vie() + " PV");
            textinfo.appendText("Enemie a perdu : -" + 50 + " et il lui reste " + ennemi.getPts_vie() + " PV \n");
        };
    }
}
