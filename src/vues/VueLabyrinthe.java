package vues;

import javafx.scene.layout.GridPane;
import modele.Modele;
import modele.points.Cellule;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VueLabyrinthe extends GridPane implements Observer {

    public VueLabyrinthe(Modele modele){
        super();
        assert (modele != null):"Erreur : modele non défini";
        modele.addObserver(this);
        this.setPrefSize(modele.getCarte().getNbLignes()*32, modele.getCarte().getNbColonnes()*32);
        refresh(modele);
    }

    private void refresh(Modele modele){
        assert (modele != null):"Erreur : modele non défini";
        getChildren().clear();
        afficheLaby(modele);
        afficheChemin(modele);
        afficherExploration(modele);
    }

    private void afficheLaby(Modele modele){
        for(ArrayList<Cellule> col : modele.getCarte()){
            for(Cellule cel : col){
                VueCellule vueCellule;
                if(cel.getClass().getSimpleName().equals("Entree")){
                    vueCellule = new VueCelluleEntree(modele, cel);
                } else if(cel.getClass().getSimpleName().equals("Sortie")){
                    vueCellule = new VueCelluleSortie(modele, cel);
                } else {
                    vueCellule = new VueCellule(modele, cel);
                }
                add(vueCellule, cel.getX(), cel.getY());
            }
        }
    }

    private void afficheChemin(Modele modele){
        ArrayList<Cellule> chemin = modele.getChemin();
        for(Cellule cellule : chemin){
            add(new VuePas(), cellule.getX(), cellule.getY());
        }
    }

    private void afficherExploration(Modele modele){
        if(modele.getAlgo() != null){
            ArrayList<Cellule> ouvert = modele.getAlgo().getOuverts();
            ArrayList<Cellule> ferme = modele.getAlgo().getFermes();
            for(Cellule cellule : ouvert){
                add(new VueOuvert(), cellule.getX(), cellule.getY());
            }
            for(Cellule cellule : ferme){
                add(new VueFermee(), cellule.getX(), cellule.getY());
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        Modele modele = (Modele)observable;
        refresh(modele);
    }
}
