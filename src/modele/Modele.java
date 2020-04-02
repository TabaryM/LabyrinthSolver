package modele;

import javafx.application.Platform;
import modele.algos.AStar;
import modele.algos.Algo;
import modele.algos.Dijkstra;
import modele.points.Cellule;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Modele extends Observable {
    private Carte carte;
    private ArrayList<Cellule> chemin;
    private Algo algo;
    private Random random;
    private long seed;

    // Pour l'affichage
    private int cpt;
    private ArrayList<Cellule> cheminTmp;
    private int vitesse;

    public Modele(int nbLignes, int nbColonnes){
        super();
        Random tmp = new Random();
        seed = tmp.nextLong();
        random = new Random();
        random.setSeed(seed);
        carte = new Carte(nbLignes, nbColonnes, random);
        chemin = new ArrayList<>();
        vitesse = 1;
        algo = new AStar(carte);
    }

    public void genereLabyrinthe(String methode){
        switch (methode){
            case "Prim":
            default:
                carte.generateLabyPrim();
                break;
        }
    }

    public void calculChemin(String methode){
        chemin = new ArrayList<>();
        switch (methode){
            case "AStar":
            default:
                algo = new AStar(carte);
                break;
            case "Dijkstra":
                algo = new Dijkstra(carte);
                break;
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable affichageExploration = new Runnable() {
                    @Override
                    public void run() {
                        modifier();
                    }
                };
                Runnable dessinerChemin = new Runnable() {
                    @Override
                    public void run() {
                        chemin.add(cheminTmp.get(cheminTmp.size() - cpt));
                        modifier();
                    }
                };

                algo.initCalcul();
                while (!algo.calculChemin()) {
                    try {
                        Thread.sleep(1000/vitesse);
                    } catch (InterruptedException ex) {
                    }
                    Platform.runLater(affichageExploration);
                }

                cpt = 1;
                algo.calculDistances();
                cheminTmp = algo.getChemin();
                while (cpt < cheminTmp.size() - 2){
                    try {
                        Thread.sleep(1000/vitesse);
                    } catch (InterruptedException ex) {
                    }
                    Platform.runLater(dessinerChemin);
                    cpt++;
                }
            }
        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
    }

    public ArrayList<Cellule> getChemin(){
        return chemin;
    }

    public void removeChemin(){
        chemin.clear();
        algo.resetOF();
    }

    public int getCoutChemin(){
        int res = 0;
        for(Cellule cellule : chemin){
            res += cellule.getCoutAcces();
        }
        return res;
    }

    public Algo getAlgo(){
        return algo;
    }

    public Carte getCarte(){
        return carte;
    }

    public void setVitesse(int vitesse){
        this.vitesse = vitesse;
    }

    public void setSeed(long seed){
        this.seed = seed;
        random.setSeed(seed);
    }

    public long getSeed(){
        return seed;
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

    /**
     * Demande l'actualisation des observeurs
     */
    public void modifier(){
        setChanged();
        notifyObservers();
    }
}
