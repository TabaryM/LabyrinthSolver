package modele.algos;

import modele.Carte;
import modele.points.Cellule;

import java.util.ArrayList;
import java.util.Vector;

public class Dijkstra extends Algo {

    private ArrayList<Cellule> visites;
    private ArrayList<Cellule> liste;

    public Dijkstra(Carte carte) {
        super(carte);
    }

    /**
     * Calcule la distance pour chaque CelluleType vers la sortie selon l'algorithme de Dijkstra
     */
    public void calculDistances(){
        getCarte().resetValeursAlgos();

        visites = new ArrayList<>();
        liste = new ArrayList<>();
        liste.add(getCarte().getEntree());

        Cellule courrant;
        while(!liste.isEmpty()){
            courrant = liste.get(0);
            Vector<Cellule> voisins = getCarte().voisins(courrant);
            for(Cellule voisin : voisins){
                if(!voisin.isMur()){
                    if(courrant.getPere() != voisin){
                        courrant.majDistanceVers(voisin);
                    }
                    if(!visites.contains(voisin) && !liste.contains(voisin)){
                        liste.add(voisin);
                    }
                }
            }

            liste.remove(courrant);
            if(!visites.contains(courrant)) {
                visites.add(courrant);
            }
        }
    }

    /**
     * Retourne un chemin depuis l'entrée vers la sortie selon l'algorithme de Dijkstra
     */
    @Override
    public void getChemin(ArrayList<Cellule> chemin) {
        Cellule courrant = getCarte().getSortie();
        while(!courrant.equals(getCarte().getEntree())){
            chemin.add(courrant);
            courrant = courrant.getPere();
        }
        chemin.add(getCarte().getEntree());
    }

    @Override
    public ArrayList<Cellule> getChemin() {
        ArrayList<Cellule> chemin = new ArrayList<>();
        Cellule courrant = getCarte().getSortie();
        while(!courrant.equals(getCarte().getEntree())){
            chemin.add(courrant);
            courrant = courrant.getPere();
        }
        chemin.add(getCarte().getEntree());
        return chemin;
    }

    @Override
    public void initCalcul() {
        visites = new ArrayList<>();
        liste = new ArrayList<>();
        liste.add(getCarte().getEntree());
    }

    @Override
    public boolean calculChemin() {
        Cellule courrant;
        if(!liste.isEmpty()){
            courrant = liste.get(0);
            Vector<Cellule> voisins = getCarte().voisins(courrant);
            for(Cellule voisin : voisins){
                if(!voisin.isMur()){
                    if(courrant.getPere() != voisin){
                        courrant.majDistanceVers(voisin);
                    }
                    if(!visites.contains(voisin) && !liste.contains(voisin)){
                        liste.add(voisin);
                    }
                }
            }

            liste.remove(courrant);
            if(!visites.contains(courrant)) {
                visites.add(courrant);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ArrayList<Cellule> getOuverts() {
        return liste;
    }

    @Override
    public ArrayList<Cellule> getFermes() {
        return visites;
    }

}
