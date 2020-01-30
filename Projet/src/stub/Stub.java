package stub;

import model.Classement;
import model.Joueur;

/**
 * The type Stub.
 */
public class Stub {

    /**
     * Create liste joueur classement.
     *
     * @return the classement
     */
    public static Classement createListeJoueur() {
        Classement classement = new Classement();
        classement.addJoueur(new Joueur("J1", 5, 2,0));
        classement.addJoueur(new Joueur("J2", 15, 4,0));
        classement.addJoueur(new Joueur("J3", 25, 1,0));
        classement.addJoueur(new Joueur("J4", 55, 3,0));
        return classement;
    }
}
