package modele;

import modele.algos.AStar;
import modele.algos.Dijkstra;
import modele.points.Cellule;

import java.util.ArrayList;

public class ModeleLabyrinthe extends Modele {
    private Carte carte;
    private ArrayList<Cellule> chemin;

    public ModeleLabyrinthe(int nbLignes, int nbColonnes){
        super();
        carte = new Carte(nbLignes, nbColonnes);
        chemin = new ArrayList<>();
    }

    public void genereLabyrinthe(String methode){
        switch (methode){
            case "Prim":
                carte.generateLabyPrim();
                break;
            default:
                break;
        }
    }

    public void calculChemin(String methode){
        switch (methode){
            case "Dijkstra":
                Dijkstra dijkstra = new Dijkstra(carte);
                chemin = dijkstra.getCheminDijkstra();
                break;
            case "AStar":
                AStar aStar = new AStar(carte);
                chemin = aStar.getCheminAStar();
                break;
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        Cellule courrant;
        stringBuilder.append("███".repeat(carte.getNbColonnes() +2));
        stringBuilder.append("\n");

        for(int i = 0; i < carte.getNbLignes(); i++) {
            stringBuilder.append("███");
            for (int j = 0; j < carte.getNbColonnes(); j++) {
                courrant = carte.getCellule(j, i);
                if(chemin.contains(courrant)){
                    stringBuilder.append("░");
                    stringBuilder.append(courrant.getDessin());
                    stringBuilder.append("░");
                } else {
                    if(courrant.isMur()){
                        stringBuilder.append(courrant.getDessin());
                    } else {
                        stringBuilder.append(" ");
                        stringBuilder.append(courrant.getDessin());
                        stringBuilder.append(" ");
                    }
                }
            }
            stringBuilder.append("███");
            stringBuilder.append("\n");
        }
        stringBuilder.append("███".repeat(carte.getNbColonnes() +2));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
