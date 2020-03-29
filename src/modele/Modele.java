package modele;

import java.util.Observable;

public abstract class Modele extends Observable {

    public Modele(){
        super();
    }

    /**
     * Demande l'actualisation des observeurs
     */
    public void modifier(){
        setChanged();
        notifyObservers();
    }

}
