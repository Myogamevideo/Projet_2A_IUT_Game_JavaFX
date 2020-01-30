package model;

import static view.FenetreJeu.getRandom;

/**
 * The type Enemie.
 */
public class Ennemi extends Entite{

    /**
     * Instantiates a new Enemie.
     *
     * @param Ppts_action    the ppts action
     * @param Ppts_mouvement the ppts mouvement
     * @param Ppts_vie       the ppts vie
     * @param PpositionX     the pposition x
     * @param PpositionY     the pposition y
     * @param Distance       the distance
     */
    public Ennemi(int Ppts_action, int Ppts_mouvement, int Ppts_vie, int PpositionX, int PpositionY, int Distance, int Degat) {
        pts_action = Ppts_action;
        pts_mouvement = Ppts_mouvement;
        pts_vie = Ppts_vie;
        positionX = PpositionX;
        positionY = PpositionY;
        distance = Distance;
        degat = Degat;
    }

    public Ennemi enemieSubisDegat(Ennemi bot, String distance , Personnage personnage){
        int fin;
        String columnS;
        int distanceX;
        String rowS;
        int distanceY;
        int ancienptsvieenemie;
        int ptsvieenemie;
        int degat;
        int degatmax;
        fin = distance.indexOf('/');
        columnS = distance.substring(0, fin);
        distanceX = Integer.parseInt(columnS.trim());
        rowS = distance.substring(fin + 1);
        distanceY = Integer.parseInt(rowS.trim());
        ancienptsvieenemie = bot.getPts_vie();
        if (distanceX == bot.getPositionX() && distanceY == bot.getPositionY()) {
            degatmax = 20 * personnage.getLvl();
            if (degatmax > 50) {
                degatmax = 50;
            }
            degat = getRandom(0, degatmax);
            ptsvieenemie = ancienptsvieenemie - degat;
            if (ptsvieenemie < 1) {
                ptsvieenemie = 0;
            }
            bot.setPts_vie(ptsvieenemie);
            bot.setDegat(degat);
        }
        return bot;
    }

    public Ennemi enemieSubisCompetence(Ennemi bot){
        int ptsvieenemie;
        int ancienptsvieenemie;
        ancienptsvieenemie = bot.getPts_vie();
        ptsvieenemie = ancienptsvieenemie - 50;
        if (ptsvieenemie < 1) {
            ptsvieenemie = 0;
        }
        bot.setPts_vie(ptsvieenemie);
        return bot;
    }
}
