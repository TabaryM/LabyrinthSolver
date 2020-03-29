package modele.algos;

import modele.Carte;
import modele.points.Cellule;

import java.util.ArrayList;

public class AStar {
    private Carte carte;

    public AStar(Carte carte) {
        this.carte = carte;
    }

    /**
     * Retourne le chemin calculé par l'algorithme A*
     * @return chemin : ArrayList<Cellule> une chemin entre entrée et sortie
     */
    public ArrayList<Cellule> getCheminAStar(){
        calculDistanceAStar();
        ArrayList<Cellule> chemin = new ArrayList<>();

        chemin.add(carte.getSortie());
        Cellule courrant = carte.getSortie();
        while (courrant != null){
            chemin.add(courrant.getPere());
            courrant = courrant.getPere();
        }

        return chemin;
    }

    /**
     * Calcule la distance pour chaque Cellule vers la sortie selon l'algorithme A*
     */
    public void calculDistanceAStar(){
        carte.resetValeursAlgos();
        // On calcule les heuristiques pour toutes les cellules
        for(Cellule cellule : carte.getCouloirs()){
            cellule.setDistanceEuclidienne(carte.calculeHeuristique(cellule));
        }
        ArrayList<Cellule> ouverts = new ArrayList<>();
        ArrayList<Cellule> fermees = new ArrayList<>();

        ouverts.add(carte.getEntree());

        int scoreTmp;
        Cellule courrant;
        while (!ouverts.isEmpty()){
            courrant = Cellule.plusInteressante(ouverts);
            if(courrant.equals(carte.getSortie())){
                // Chemin trouvé, on affiche le chemin
                return;
            }

            ouverts.remove(courrant);
            fermees.add(courrant);
            for(Cellule voisin : carte.voisins(courrant)){
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
