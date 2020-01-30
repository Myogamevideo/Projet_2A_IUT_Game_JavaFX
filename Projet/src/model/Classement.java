package model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The type Classement.
 */
public class Classement {

    private ObservableList<Joueur> joueursObs = FXCollections.observableArrayList();

    private ListProperty<Joueur> joueurs = new SimpleListProperty<>(joueursObs);

    /**
     * Joueurs property list property.
     *
     * @return the list property
     */
    public ListProperty<Joueur> joueursProperty() {
        return joueurs;
    }

    /**
     * Gets joueurs.
     *
     * @return the joueurs
     */
    public ObservableList<Joueur> getJoueurs() {
        return joueurs.get();
    }

    /**
     * Sets joueurs.
     *
     * @param joueurs the joueurs
     */
    public void setJoueurs(ObservableList<Joueur> joueurs) {
        this.joueurs.set(joueurs);
    }

    /**
     * Add joueur.
     *
     * @param j the j
     */
    public void addJoueur(Joueur j) {
        joueursObs.add(j);
    }

}
