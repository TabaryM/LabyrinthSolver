package modele.algos;

import modele.Carte;
import modele.points.Cellule;

import java.util.ArrayList;

public class AStar extends Algo {
    public AStar(Carte carte) {
        super(carte);
    }

    /**
     * Retourne le chemin calculé par l'algorithme A*
     * @return chemin : ArrayList<Cellule> une chemin entre entrée et sortie
     */
    public ArrayList<Cellule> getChemin(){
        calculDistances();
        ArrayList<Cellule> chemin = new ArrayList<>();

        chemin.add(getCarte().getSortie());
        Cellule courrant = getCarte().getSortie();
        while (courrant != null){
            chemin.add(courrant.getPere());
            courrant = courrant.getPere();
        }

        return chemin;
    }

    /**
     * Calcule la distance pour chaque Cellule vers la sortie selon l'algorithme A*
     */
    public void calculDistances(){
        getCarte().resetValeursAlgos();
        // On calcule les heuristiques pour toutes les cellules
        for(Cellule cellule : getCarte().getCouloirs()){
            cellule.setDistanceEuclidienne(getCarte().calculeHeuristique(cellule));
        }
        ArrayList<Cellule> ouverts = new ArrayList<>();
        ArrayList<Cellule> fermees = new ArrayList<>();

        ouverts.add(getCarte().getEntree());

        int scoreTmp;
        Cellule courrant;
        while (!ouverts.isEmpty()){
            courrant = Cellule.plusInteressante(ouverts);
            if(courrant.equals(getCarte().getSortie())){
                // Chemin trouvé, on affiche le chemin
                return;
            }

            ouverts.remove(courrant);
            fermees.add(courrant);
            for(Cellule voisin : getCarte().voisins(courrant)){
                if(!voisin.isMur()){
                    scoreTmp = courrant.getCoutAcces() + courrant.getDistance();
                    if(scoreTmp < voisin.getDistance()){
                        voisin.setPere(courrant);
                        voisin.setDistance(scoreTmp);
                        if(!ouverts.contains(voisin)){
                            ouverts.add(voisin);
                        }
                    }
                }
            }
        }
    }

}
