package vues;

import modele.ModeleLabyrinthe;
import modele.points.Cellule;
import outils.ImageFactory;

public class VueCelluleSortie extends VueCellule{
    public VueCelluleSortie(ModeleLabyrinthe modele, Cellule cellule) {
        super(modele, cellule);
        super.getChildren().add(ImageFactory.getInstance().getImageViewSortie());
    }
}
