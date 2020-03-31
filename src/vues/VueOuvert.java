package vues;

import javafx.scene.control.Label;


public class VueOuvert extends Label {
    public VueOuvert(){
        super();
        this.setPrefSize(32, 32);
        this.setMinSize(32, 32);
        this.setMaxSize(32, 32);
        this.setStyle("-fx-background-color: rgba(50, 255, 50, 0.5)");
    }
}
