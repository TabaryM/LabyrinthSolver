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
     * Calcule la distance pour chaque CelluleType vers la sortie selon l'algorithme de Dijkstra
     */
    public void calculDistances(){
        getCarte().resetValeursAlgos();

        fermees = new ArrayList<>();
        ouverts = new ArrayList<>();
        ouverts.add(getCarte().getEntree());

        Cellule courrant;
        while(!ouverts.isEmpty()){
            courrant = ouverts.get(0);
            Vector<Cellule> voisins = getCarte().voisins(courrant);
            for(Cellule voisin : voisins){
                if(!voisin.isMur()){
                    if(courrant.getPere() != voisin){
                        courrant.majDistanceVers(voisin);
                    }
                    if(!fermees.contains(voisin) && !ouverts.contains(voisin)){
                        ouverts.add(voisin);
                    }
                }
            }

            ouverts.remove(courrant);
            if(!fermees.contains(courrant)) {
                fermees.add(courrant);
            }
        }
    }

    @Override
    public void initCalcul() {
        resetOF();
        ouverts.add(getCarte().getEntree());
    }

    @Override
    public boolean calculChemin() {
        Cellule courrant;
        if(!ouverts.isEmpty()){
            courrant = ouverts.get(0);
            Vector<Cellule> voisins = getCarte().voisins(courrant);
            for(Cellule voisin : voisins){
                if(!voisin.isMur()){
                    if(courrant.getPere() != voisin){
                        courrant.majDistanceVers(voisin);
                    }
                    if(!fermees.contains(voisin) && !ouverts.contains(voisin)){
                        ouverts.add(voisin);
                    }
                }
            }

            ouverts.remove(courrant);
            if(!fermees.contains(courrant)) {
                fermees.add(courrant);
            }
            return false;
        } else {
            return true;
        }
    }
}
