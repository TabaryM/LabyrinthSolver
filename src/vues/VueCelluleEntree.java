package vues;

import modele.Modele;
import modele.points.Cellule;
import outils.ImageFactory;

public class VueCelluleEntree extends VueCellule{
    public VueCelluleEntree(Modele modele, Cellule cellule) {
        super(modele, cellule);
        this.setGraphic(ImageFactory.getInstance().getImageViewEntree());
    }
}
