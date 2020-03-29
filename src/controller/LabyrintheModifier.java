package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import modele.Modele;

import java.util.Observable;
import java.util.Observer;

public class LabyrintheModifier {

    private Modele modele;
    private String algo;

    @FXML
    private ToggleGroup selectAlgo;
    @FXML
    private RadioButton aStar;
    @FXML
    private RadioButton dijkstra;
    @FXML
    private Button resoudre;

    public LabyrintheModifier(Modele modele){
        super();
        assert (modele != null):"Erreur : modele non défini";
        this.modele = modele;
    }

    @FXML
    void initialize(){}

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
}
