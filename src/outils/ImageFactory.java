package outils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class ImageFactory {
    private static ImageFactory instance;
    private Image mur;
    private Image pierre;
    private Image sable;
    private Image eau;
    private Image entree;
    private Image sortie;
    private Image pas;

    private ImageFactory(){
        mur = new Image("images/mur.png", 32, 32, true, true);
        pierre = new Image("images/pierre.png", 32, 32, true, true);
        sable = new Image("images/sable.png", 32, 32, true, true);
        eau = new Image("images/eau.png", 32, 32, true, true);
        entree = new Image("images/entree.png", 32, 32, true, true);
        sortie = new Image("images/sortie.png", 32, 32, true, true);
        pas = new Image("images/pas.png", 32, 32, true, true);
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

    public ImageView getImageViewPas(){
        return new ImageView(pas);
    }
//////////////////////////////////////////////////////////////////
    public Image getMur() {
        return mur;
    }

    public Image getPierre() {
        return pierre;
    }

    public Image getSable() {
        return sable;
    }

    public Image getEau() {
        return eau;
    }

    public Image getEntree() {
        return entree;
    }

    public Image getSortie() {
        return sortie;
    }

    public Image getPas() {
        return pas;
    }
}
