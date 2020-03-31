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

    /**
     * Retourne le chemin calculé par l'algorithme A*
     */
    @Override
    public void getChemin(ArrayList<Cellule> chemin) {
        chemin.add(getCarte().getSortie());
        Cellule courrant = getCarte().getSortie();
        while (courrant != null){
            chemin.add(courrant);
            courrant = courrant.getPere();
        }
    }

    @Override
    public ArrayList<Cellule> getChemin() {
        ArrayList<Cellule> chemin = new ArrayList<>();
        chemin.add(getCarte().getSortie());
        Cellule courrant = getCarte().getSortie();
        while (courrant != null){
            chemin.add(courrant);
            courrant = courrant.getPere();
        }
        return chemin;
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
        ouverts = new ArrayList<>();
        fermees = new ArrayList<>();

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

    /**
     * Retourne la liste des cellules potentiellement utilisées pour aller vers la sortie
     * @return this.ouvert
     */
    @Override
    public ArrayList<Cellule> getOuverts() {
        return ouverts;
    }

    /**
     * Retourne la liste des cellules qui ne seront pas utilisées pour aller à la sortie
     * @return this.fermees
     */
    @Override
    public ArrayList<Cellule> getFermes() {
        return fermees;
    }
}
