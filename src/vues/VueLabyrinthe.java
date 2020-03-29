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
                this.add(vueCellule, cel.getX()+1, cel.getY()+1);
            }
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        Modele monde = (Modele)o;
        refresh(monde);
    }
}
