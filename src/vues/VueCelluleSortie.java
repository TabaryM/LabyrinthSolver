package vues;

import modele.Modele;
import modele.points.Cellule;
import outils.ImageFactory;

public class VueCelluleSortie extends VueCellule{
    public VueCelluleSortie(Modele modele, Cellule cellule) {
        super(modele, cellule);
        this.setGraphic(ImageFactory.getInstance().getImageViewSortie());
    }
}
