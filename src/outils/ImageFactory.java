package outils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class ImageFactory {
    private static ImageFactory instance;
    private static Image mur;
    private static Image pierre;
    private static Image sable;
    private static Image eau;
    private static Image entree;
    private static Image sortie;

    private ImageFactory(){
        mur = new Image("images/mur.png", 32, 32, true, true);
        pierre = new Image("images/pierre.png", 32, 32, true, true);
        sable = new Image("images/sable.png", 32, 32, true, true);
        eau = new Image("images/eau.png", 32, 32, true, true);
        entree = new Image("images/entree.png", 32, 32, true, true);
        sortie = new Image("images/sortie.png", 32, 32, true, true);
    }

    /**
     * Créer l'instance du singleton puis la retourne.
     * @return instance
     */
    public static ImageFactory getInstance(){
        if (instance == null){
            instance = new ImageFactory();
        }
        return instance;
    }

    public ImageView getImageViewMur(){
        return new ImageView(mur);
    }

    public ImageView getImageViewPierre(){
        return new ImageView(pierre);
    }

    public ImageView getImageViewSable(){
        return new ImageView(sable);
    }

    public ImageView getImageViewEau(){
        return new ImageView(eau);
    }

    public ImageView getImageViewEntree(){
        return new ImageView(entree);
    }

    public ImageView getImageViewSortie(){
        return new ImageView(sortie);
    }

}
