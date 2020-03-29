package modele;

import modele.algos.AStar;
import modele.algos.Algo;
import modele.algos.Dijkstra;
import modele.points.Cellule;

import java.util.ArrayList;
import java.util.Random;

public class ModeleLabyrinthe extends Modele {
    private Carte carte;
    private ArrayList<Cellule> chemin;
    private Random random;

    public ModeleLabyrinthe(int nbLignes, int nbColonnes){
        super();
        random = new Random();
        carte = new Carte(nbLignes, nbColonnes, random);
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
        Algo algo;
        switch (methode){
            case "AStar":
                algo = new AStar(carte);
                break;
            case "Dijkstra":
            default:
                algo = new Dijkstra(carte);
                break;
        }
        chemin = algo.getChemin();
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
