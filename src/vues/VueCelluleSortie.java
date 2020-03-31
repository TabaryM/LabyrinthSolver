package vues;

import javafx.scene.control.Label;
import modele.Modele;
import modele.points.Cellule;
import outils.ImageFactory;

public class VueCelluleSortie extends VueCellule{
    public VueCelluleSortie(Modele modele, Cellule cellule) {
        super(modele, cellule);
        Label label = new Label();
        label.setGraphic(ImageFactory.getInstance().getImageViewSortie());
        super.setFils(label);
    }
}
