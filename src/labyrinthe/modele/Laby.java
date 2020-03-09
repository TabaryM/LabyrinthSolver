package labyrinthe.modele;

import java.util.ArrayList;

public class Laby {

    private Point entree;
    private Point sortie;
    private Point dimension;

    private ArrayList<Point> murs;

    /**
     *
     * @param dimension
     */
    public Laby(Point dimension){
        murs = new ArrayList<>();
        this.dimension = dimension;
    }

    /**
     *
     * @return
     */
    public Point getEntree() {
        return entree;
    }

    /**
     *
     * @param entree
     */
    public void setEntree(Point entree) {
        this.entree = entree;
    }

    /**
     *
     * @return
     */
    public Point getSortie() {
        return sortie;
    }

    /**
     *
     * @param sortie
     */
    public void setSortie(Point sortie) {
        this.sortie = sortie;
    }

    /**
     *
     * @return
     */
    public Point getDimension() {
        return dimension;
    }

    /**
     *
     * @param dimension
     */
    public void setDimension(Point dimension) {
        this.dimension = dimension;
    }

    /**
     * Genere un labyrinthe moche
     */
    public void generateLaby(){

    }
}
