package model;

public abstract class Entite {

    /**
     * The Pts action.
     */
    protected int pts_action;
    /**
     * The Pts mouvement.
     */
    protected int pts_mouvement;
    /**
     * The Pts vie.
     */
    protected int pts_vie;
    /**
     * The Position x.
     */
    protected int positionX;
    /**
     * The Position y.
     */
    protected int positionY;
    /**
     * The Distance.
     */
    protected int distance;

    protected int degat;

    /**
     * Gets pts action.
     *
     * @return the pts action
     */
    public int getPts_action() {
        return pts_action;
    }

    /**
     * Sets pts action.
     *
     * @param pts_action the pts action
     */
    public void setPts_action(int pts_action) {
        this.pts_action = pts_action;
    }

    /**
     * Gets pts mouvement.
     *
     * @return the pts mouvement
     */
    public int getPts_mouvement() {
        return pts_mouvement;
    }

    /**
     * Sets pts mouvement.
     *
     * @param pts_mouvement the pts mouvement
     */
    public void setPts_mouvement(int pts_mouvement) {
        this.pts_mouvement = pts_mouvement;
    }

    /**
     * Gets pts vie.
     *
     * @return the pts vie
     */
    public int getPts_vie() {
        return pts_vie;
    }

    /**
     * Sets pts vie.
     *
     * @param pts_vie the pts vie
     */
    public void setPts_vie(int pts_vie) {
        this.pts_vie = pts_vie;
    }

    /**
     * Gets position x.
     *
     * @return the position x
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Sets position x.
     *
     * @param positionX the position x
     */
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    /**
     * Gets position y.
     *
     * @return the position y
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Sets position y.
     *
     * @param positionY the position y
     */
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Sets distance.
     *
     * @param distance the distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDegat(){return  degat;}

    public void setDegat(int degat){this.degat = degat;}
}
