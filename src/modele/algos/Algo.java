package modele.algos;

import modele.Carte;
import modele.points.Cellule;

import java.util.ArrayList;

public abstract class Algo {
    private Carte carte;

    public Algo(Carte carte){
        this.carte = carte;
    }

    public abstract void calculDistances();

    public abstract ArrayList<Cellule> getChemin();

    public Carte getCarte(){
        return carte;
    }
}
