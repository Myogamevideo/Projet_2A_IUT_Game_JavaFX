package model;

/**
 * The type Archer.
 */
public class Archer extends Personnage {
    /**
     * Instantiates a new Archer.
     *
     * @param Ppseudo    the ppseudo
     * @param PpositionX the pposition x
     * @param PpositionY the pposition y
     */
    public Archer(String Ppseudo, int PpositionX, int PpositionY) {
        this.pseudo = Ppseudo;
        this.positionX = PpositionX;
        this.positionY = PpositionY;
        this.pts_action = 3;
        this.pts_mouvement = 2;
        this.pts_vie = 40;
        this.competence = "BOUUUM";
        this.nb_potion = 1;
        this.distance = 3;
        this.lvl = 1;
        this.vietotale = 40;
        this.degat = 0;
    }
}
