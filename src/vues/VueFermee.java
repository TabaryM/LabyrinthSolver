package vues;

import javafx.scene.control.Label;


public class VueFermee extends Label {
    public VueFermee(){
        super();
        this.setPrefSize(32, 32);
        this.setMinSize(32, 32);
        this.setMaxSize(32, 32);
        this.setStyle("-fx-background-color: rgba(255, 50, 50, 0.5)");
    }
}
