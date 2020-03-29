package modele.algos;

import modele.Carte;
import modele.points.Cellule;

import java.util.ArrayList;
import java.util.Vector;

public class Dijkstra extends Algo {
    public Dijkstra(Carte carte) {
        super(carte);
    }

    /**
     * Retourne un chemin depuis l'entrée vers la sortie selon l'algorithme de Dijkstra
     * @return chemin ArrayList<Cellule> : chemin entre this.entree et this.sortie
     */
    public ArrayList<Cellule> getChemin(){
        calculDistances();
        ArrayList<Cellule> chemin = new ArrayList<>();
        Cellule courrant = getCarte().getSortie();
        while(!courrant.equals(getCarte().getEntree())){
            //System.out.println("Cellule : "+courrant+"\tPere : "+courrant.getPere());
            chemin.add(courrant);
            courrant = courrant.getPere();
        }
        chemin.add(getCarte().getEntree());

        return chemin;
    }

    /**
     * Calcule la distance pour chaque Cellule vers la sortie selon l'algorithme de Dijkstra
     */
    public void calculDistances(){
        getCarte().resetValeursAlgos();

        ArrayList<Cellule> visites = new ArrayList<>();
        ArrayList<Cellule> liste = new ArrayList<>();
        Cellule courrant;

        liste.add(getCarte().getEntree());

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

}
