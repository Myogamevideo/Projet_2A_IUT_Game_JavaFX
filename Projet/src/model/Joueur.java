package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The type Joueur.
 */
public class Joueur {

    private StringProperty pseudo = new SimpleStringProperty();
    private IntegerProperty vague = new SimpleIntegerProperty();
    private IntegerProperty nbtour = new SimpleIntegerProperty();
    private IntegerProperty tempsJ = new SimpleIntegerProperty();

    /**
     * Instantiates a new Joueur.
     *
     * @param pseudo the pseudo
     * @param vague  the vague
     * @param nbtour the nbtour
     */
    public Joueur(String pseudo, int vague, int nbtour,int tempsJ) {
        setPseudo(pseudo);
        setVague(vague);
        setNbtour(nbtour);
        setTempsJ(tempsJ);
    }

    /**
     * Pseudo property string property.
     *
     * @return the string property
     */
    public StringProperty pseudoProperty() {
        return pseudo;
    }

    /**
     * Gets pseudo.
     *
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo.get();
    }

    /**
     * Sets pseudo.
     *
     * @param pseudo the pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo.set(pseudo);
    }


    /**
     * Vague property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty vagueProperty() {
        return vague;
    }

    /**
     * Gets vague.
     *
     * @return the vague
     */
    public int getVague() {
        return vague.get();
    }

    /**
     * Sets vague.
     *
     * @param vagueProperty the vague property
     */
    public void setVague(int vagueProperty) {
        this.vague.set(vagueProperty);
    }


    /**
     * Nbtour property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty nbtourProperty() {
        return nbtour;
    }

    /**
     * Gets nbtour.
     *
     * @return the nbtour
     */
    public int getNbtour() {
        return nbtour.get();
    }

    /**
     * Sets nbtour.
     *
     * @param nbtourProperty the nbtour property
     */
    public void setNbtour(int nbtourProperty) {
        this.nbtour.set(nbtourProperty);
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "pseudo ='" + getPseudo() +
                "' vague ='" + getVague() +
                "' nbtour ='" + getNbtour() +
                "' tempsJ =" + getTempsJ() +
                '}';
    }

    public int getTempsJ() {
        return tempsJ.get();
    }

    public IntegerProperty tempsJProperty() {
        return tempsJ;
    }

    public void setTempsJ(int tempsJProperty) {
        this.tempsJ.set(tempsJProperty);
    }
}
