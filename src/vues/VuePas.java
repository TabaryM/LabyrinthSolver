package vues;

import javafx.scene.control.Label;
import outils.ImageFactory;


public class VuePas extends Label {
    public VuePas(){
        super();
        this.setGraphic(ImageFactory.getInstance().getImageViewPas());
    }
}
