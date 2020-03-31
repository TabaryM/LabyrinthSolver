package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import modele.Modele;

public class LabyrintheModifier {

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
        modele.calculChemin(algo, (int)speed.getValue());
    }


}
