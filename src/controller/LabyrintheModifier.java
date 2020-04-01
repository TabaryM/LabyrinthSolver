package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import modele.Modele;

import java.util.Random;

public class LabyrintheModifier {

    public TextField champsSeed;
    private Modele modele;
    private String algo;

    @FXML
    private Slider speed;

    public LabyrintheModifier(Modele modele){
        super();
        assert (modele != null):"Erreur : modele non défini";
        this.modele = modele;
    }

    @FXML
    void initialize(){
        setAlgoAStar();
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
        modele.modifier();
    }

    public void genererAlea() {
        Random random = new Random();
        champsSeed.setText(random.nextLong()+"");
        genererAvecSeed();
    }
}
