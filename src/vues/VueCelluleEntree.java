package vues;

import modele.ModeleLabyrinthe;
import modele.points.Cellule;
import outils.ImageFactory;

public class VueCelluleEntree extends VueCellule{
    public VueCelluleEntree(ModeleLabyrinthe modele, Cellule cellule) {
        super(modele, cellule);
        super.getChildren().add(ImageFactory.getInstance().getImageViewEntree());
    }
}
