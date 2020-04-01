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

    public ArrayList<Cellule> getChemin(){
        ArrayList<Cellule> chemin = new ArrayList<>();
        chemin.add(getCarte().getSortie());
        Cellule courrant = getCarte().getSortie();
        while (courrant != null){
            chemin.add(courrant);
            courrant = courrant.getPere();
        }
        return chemin;
    }

    public abstract void initCalcul();

    public abstract boolean calculChemin();

    /**
     * Retourne la liste des cellules potentiellement utilisées pour aller vers la sortie
     * @return this.ouvert
     */
    public ArrayList<Cellule> getOuverts() {
        return ouverts;
    }

    /**
     * Retourne la liste des cellules qui ne seront pas utilisées pour aller à la sortie
     * @return this.fermees
     */
    public ArrayList<Cellule> getFermes() {
        return fermees;
    }

    public Carte getCarte(){
        return carte;
    }
}
