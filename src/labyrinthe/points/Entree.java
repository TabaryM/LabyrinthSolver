package labyrinthe.points;

public class Entree extends Cellule {
    /** Instancie une case d'entrée de labyrinthe
     * @param x int coordonnée en abscisse de la case
     * @param y int coordonnée en ordonnée de la case
     */
    public Entree(int x, int y) {
        super(x, y, false);
    }

    public Entree(Cellule cellule){
        this(cellule.getX(), cellule.getY());
    }

    @Override
    public String getDessin() {
        return "◉";
    }
}
