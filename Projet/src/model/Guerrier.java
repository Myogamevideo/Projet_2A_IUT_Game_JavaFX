package model;

/**
 * The type Guerrier.
 */
public class Guerrier extends Personnage {

    /**
     * Instantiates a new Guerrier.
     *
     * @param Ppseudo    the ppseudo
     * @param PpositionX the pposition x
     * @param PpositionY the pposition y
     */
    public Guerrier(String Ppseudo, int PpositionX, int PpositionY) {
        this.pseudo = Ppseudo;
        this.positionX = PpositionX;
        this.positionY = PpositionY;
        this.pts_action = 3;
        this.pts_mouvement = 3;
        this.pts_vie = 50;
        this.competence = "YAAAAA";
        this.nb_potion = 0;
        this.distance = 1;
        this.lvl = 1;
        this.vietotale = 50;
        this.degat = 0;
    }
}
