package vues;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import modele.ModeleLabyrinthe;
import modele.points.Cellule;
import outils.ImageFactory;

public class VueCellule extends Pane {

    private ImageView imageView;

    public VueCellule(ModeleLabyrinthe modele, Cellule cellule){
        super();
        assert (modele != null):"Erreur : modele non défini";
        assert (cellule != null):"Erreur : cellule non définie";
        switch (cellule.type){
            case mur:
                imageView = ImageFactory.getInstance().getImageViewMur();
                break;
            case pierre:
            default:
                imageView = ImageFactory.getInstance().getImageViewPierre();
                break;
            case sable:
                imageView = ImageFactory.getInstance().getImageViewSable();
                break;
            case eau:
                imageView = ImageFactory.getInstance().getImageViewEau();
                break;
        }
        this.getChildren().add(imageView);
    }

}
