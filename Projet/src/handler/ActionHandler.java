package handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.Ennemi;
import model.Personnage;

/**
 * The type Action handler.
 */
public class ActionHandler extends GameHandler{

    private EventHandler<ActionEvent> actionHandler;
    private Personnage personnage;
    private Ennemi ennemi;
    private TextArea textinfo;

    /**
     * Instantiates a new Action handler.
     *
     * @param personnage the personnage
     * @param ennemi     the ennemi
     * @param textinfo   the textinfo
     */
    public ActionHandler(Personnage personnage, Ennemi ennemi, TextArea textinfo) {
        this.personnage = personnage;
        this.ennemi = ennemi;
        this.textinfo=textinfo;
    }

    /**
     * Handle.
     */
    public void handle(){
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
    }
}
