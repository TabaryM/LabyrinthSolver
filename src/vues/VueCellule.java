package vues;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import modele.Modele;
import modele.points.Cellule;
import outils.ImageFactory;

public class VueCellule extends Pane {

    public VueCellule(Modele modele, Cellule cellule){
        super();
        assert (modele != null):"Erreur : modele non défini";
        assert (cellule != null):"Erreur : cellule non définie";
        Label label = new Label();
        this.getChildren().add(label);
        ImageView imageView;
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
        label.setGraphic(imageView);
    }

    protected void setFils(Node fils){
        this.getChildren().add(fils);
    }

}
