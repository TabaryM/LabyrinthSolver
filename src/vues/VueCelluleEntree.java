package vues;

import javafx.scene.control.Label;
import modele.Modele;
import modele.points.Cellule;
import outils.ImageFactory;

public class VueCelluleEntree extends VueCellule{
    public VueCelluleEntree(Modele modele, Cellule cellule) {
        super(modele, cellule);
        Label label = new Label();
        label.setGraphic(ImageFactory.getInstance().getImageViewEntree());
        super.setFils(label);
    }
}
