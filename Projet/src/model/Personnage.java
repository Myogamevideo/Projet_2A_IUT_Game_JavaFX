package model;

import static view.FenetreJeu.getRandom;

/**
 * The type Personnage.
 */
public abstract class Personnage extends Entite{

    /**
     * The Pseudo.
     */
    public String pseudo;
    /**
     * The Compétence.
     */
    protected String competence;
    /**
     * The Nb potion.
     */
    protected int nb_potion;
    /**
     * The Lvl.
     */
    protected int lvl;

    protected int vietotale;

    /**
     * Gets pseudo.
     *
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Sets pseudo.
     *
     * @param pseudo the pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Get compétence string.
     *
     * @return the string
     */
    public String getCompetence() {
        return competence;
    }

    /**
     * Set compétence.
     *
     * @param competence the compétence
     */
    public void setCompetence(String competence) {
        this.competence = competence;
    }

    /**
     * Gets nb potion.
     *
     * @return the nb potion
     */
    public int getNb_potion() {
        return nb_potion;
    }

    /**
     * Sets nb potion.
     *
     * @param nb_potion the nb potion
     */
    public void setNb_potion(int nb_potion) {
        this.nb_potion = nb_potion;
    }

    /**
     * Gets lvl.
     *
     * @return the lvl
     */
    public int getLvl() {
        return lvl;
    }

    /**
     * Sets lvl.
     *
     * @param lvl the lvl
     */
    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getVietotal(){ return vietotale; }

    private void setVietotale(int vietotale){ this.vietotale = vietotale; }

    public Personnage deplacementPersonnage(Personnage joueur , String position){
        int fin;
        String columnS;
        int positionX;
        String rowS;
        int positionY;
        int ancienpositionX;
        int ancienpositionY;
        int difpositionX;
        int difpositionY;
        int ancienptsmouv;
        int ptsmvt;
        fin = position.indexOf('/');
        columnS = position.substring(0, fin);
        positionX = Integer.parseInt(columnS.trim());
        rowS = position.substring(fin + 1);
        positionY = Integer.parseInt(rowS.trim());
        ancienpositionX = joueur.getPositionX();
        ancienpositionY = joueur.getPositionY();
        ancienptsmouv = joueur.getPts_mouvement();
        difpositionX = Math.abs(ancienpositionX - positionX);
        difpositionY = Math.abs(ancienpositionY - positionY);
        ptsmvt = Math.abs(ancienptsmouv - difpositionX);
        ptsmvt = Math.abs(ptsmvt - difpositionY);
        joueur.setPositionX(positionX);
        joueur.setPositionY(positionY);
        joueur.setPts_mouvement(ptsmvt);
        return joueur;
    }

    public Personnage actionPersonnage(Personnage joueur){
        int ancienaction;
        int action;
        ancienaction = joueur.getPts_action();
        if (ancienaction > -1) {
            action = ancienaction - 1;
            joueur.setPts_action(action);
        }
        if (joueur.getPts_action() == 0) {
            joueur.setDistance(0);
        }
        return joueur;
    }
    public Personnage reintiliaserPersonnage(Personnage joueur) {
        Guerrier guerrier = new Guerrier("", 0, 0);
        Archer archer = new Archer("", 0, 0);
        Class<? extends Personnage> pclass = joueur.getClass();
        if (pclass == guerrier.getClass()) {
            joueur.setPts_mouvement(guerrier.getPts_mouvement());
            joueur.setDistance(guerrier.getDistance());
            joueur.setPts_action(guerrier.getPts_action());
        }
        if (pclass == archer.getClass()) {
            joueur.setPts_mouvement(archer.getPts_mouvement());
            joueur.setDistance(archer.getDistance());
            joueur.setPts_action(archer.getPts_action());
        }
        return joueur;
    }

    public Personnage personnageSubisDegat(Personnage joueur, Ennemi bot){
        int ancienaction;
        int ancienptsviejoueur;
        int degat;
        int degattotal = 0;
        int ptsviejoueur;
        ancienaction = bot.getPts_action();
        ancienptsviejoueur = joueur.getPts_vie();
        while (ancienaction > 0) {
            ancienaction = ancienaction - 1;
            degat = getRandom(0, 30);
            degattotal = degattotal + degat;
            ptsviejoueur = ancienptsviejoueur - degat;
            if (ptsviejoueur < 1) {
                ptsviejoueur = 0;
            }
            joueur.setPts_vie(ptsviejoueur);
            joueur.setDegat(degattotal);
        }
        return joueur;
    }


}
