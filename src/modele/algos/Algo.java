package modele.algos;

import modele.Carte;
import modele.points.Cellule;

import java.util.ArrayList;

public abstract class Algo {
    private Carte carte;
    protected ArrayList<Cellule> ouverts;
    protected ArrayList<Cellule> fermees;

    public Algo(Carte carte){
        this.carte = carte;
    }

    public abstract void calculDistances();

    public abstract void getChemin(ArrayList<Cellule> chemin);

    public abstract ArrayList<Cellule> getChemin();

    public abstract void initCalcul();

    public abstract boolean calculChemin();

    public abstract ArrayList<Cellule> getOuverts();

    public abstract ArrayList<Cellule> getFermes();

    public Carte getCarte(){
        return carte;
    }
}
