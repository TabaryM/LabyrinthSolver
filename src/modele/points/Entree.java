package modele.points;

public class Entree extends Cellule {
    /** Instancie une case d'entrée de labyrinthe
     * @param x int coordonnée en abscisse de la case
     * @param y int coordonnée en ordonnée de la case
     */
    public Entree(int x, int y) {
        super(x, y, 1);
    }

    public Entree(Cellule cellule){
        this(cellule.getX(), cellule.getY());
    }

    @Override
    public String toString() {
        return "Entree : [" + getX() +
                "," + getY() +
                ']';
    }

    @Override
    public String getDessin() {
        return "E";
    }
}
