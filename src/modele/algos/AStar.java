package modele.algos;

import modele.Carte;
import modele.points.Cellule;

import java.util.ArrayList;

public class AStar extends Algo {
    public AStar(Carte carte) {
        super(carte);
    }

    /**
     * Calcule la distance pour chaque CelluleType vers la sortie selon l'algorithme A*
     */
    public void calculDistances(){
        initCalcul();
        int scoreTmp;
        Cellule courrant;
        while (!ouverts.isEmpty()){
            courrant = Cellule.plusInteressante(ouverts);
            if(courrant.equals(getCarte().getSortie())){
                // Chemin trouvé, on affiche le chemin
                return;
            }

            ouverts.remove(courrant);
            if(!fermees.contains(courrant)){
                fermees.add(courrant);
            }
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

    public void initCalcul(){
        getCarte().resetValeursAlgos();
        // On calcule les heuristiques pour toutes les cellules
        double heuristique;
        for(Cellule cellule : getCarte().getCouloirs()){
            // Le cout estimé entre la cellule actuelle et l'arrivée
            heuristique = getCarte().calculeHeuristique(cellule);
            cellule.setDistanceEuclidienne(heuristique);
        }
        resetOF();

        ouverts.add(getCarte().getEntree());
    }

    public boolean calculChemin(){
        int scoreTmp;
        Cellule courrant;
        if (!ouverts.isEmpty()){
            courrant = Cellule.plusInteressante(ouverts);
            if(courrant.equals(getCarte().getSortie())){
                // Chemin trouvé, on affiche le chemin
                return true;
            }

            ouverts.remove(courrant);
            if(!fermees.contains(courrant)){
                fermees.add(courrant);
            }
            for(Cellule voisin : getCarte().voisins(courrant)){
                if(!voisin.isMur()){
                    scoreTmp = courrant.getCoutAcces() + courrant.getDistance();
                    if(scoreTmp < voisin.getDistance()){
                        voisin.setPere(courrant);
                        voisin.setDistance(scoreTmp);
                        if(!ouverts.contains(voisin) && !fermees.contains(voisin)){
                            ouverts.add(voisin);
                        }
                    }
                }
            }
        }
        return false;
    }
}
