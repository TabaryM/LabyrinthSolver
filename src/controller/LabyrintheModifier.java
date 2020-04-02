package controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import modele.Modele;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class LabyrintheModifier implements Observer {

    public TextField champsSeed;
    public Label coutChemin;
    private Modele modele;
    private String algo;

    @FXML
    private Slider speed;

    public LabyrintheModifier(Modele modele){
        super();
        assert (modele != null):"Erreur : modele non défini";
        modele.addObserver(this);
        this.modele = modele;
    }

    @FXML
    void initialize(){
        setAlgoAStar();
        coutChemin.setText("Aucun chemin trouvé");
        champsSeed.setText(modele.getSeed()+"");
        genererAvecSeed();
    }

    @FXML
    private void setAlgoAStar() {
        algo = "AStar";
    }

    @FXML
    private void setAlgoDijkstra() {
        algo = "Dijkstra";
    }

    @FXML
    private void resoudreLaby() {
        modele.calculChemin(algo);
    }

    public void setVitesse() {
        modele.setVitesse((int)(speed.getValue()));
    }

    private long getSeedUtilisateur(){
        return Long.parseLong(champsSeed.getText());
    }

    public void genererAvecSeed() {
        modele.setSeed(getSeedUtilisateur());
        modele.genereLabyrinthe("Prim");
        modele.removeChemin();
        modele.modifier();
    }

    public void genererAlea() {
        Random random = new Random();
        champsSeed.setText(random.nextLong()+"");
        genererAvecSeed();
    }

    @Override
    public void update(Observable observable, Object o) {
        Modele modele = (Modele)observable;
        refresh();
    }

    private void refresh() {
        coutChemin.setText(modele.getCoutChemin()+"");
    }
}
