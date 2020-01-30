package handler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.Personnage;

/**
 * The type Deplacement handler.
 */
public class DeplacementHandler extends GameHandler{

    private EventHandler<ActionEvent> deplacementHandler;
    private Personnage personnage;

    /**
     * Instantiates a new Deplacement handler.
     *
     * @param personnage the personnage
     */
    public DeplacementHandler(Personnage personnage) {
        this.personnage=personnage;
    }

    /**
     * Handle.
     */
    public void handle(){
        deplacementHandler = event -> {
            Personnage joueur = personnage;;
            String position = ((((Button) event.getSource()).getText()).trim());
            joueur = joueur.deplacementPersonnage(joueur,position);
            if (joueur.getPts_mouvement() > -1) {
                setPartie(joueur);
            }
            personnage = joueur;
        };
    }
}
