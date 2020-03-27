package labyrinthe.points;

public class Sortie extends Cellule {
    /** Instancie une case de sortie de labyrinthe
     * @param x int coordonnée en abscisse de la case
     * @param y int coordonnée en ordonnée de la case
     */
    public Sortie(int x, int y) {
        super(x, y, 1);
    }

    public Sortie(Cellule cellule){
        this(cellule.getX(), cellule.getY());
    }

    @Override
    public String toString() {
        return "Sortie : [" + getX() +
                "," + getY() +
                ']' +
                " Distance Euclidienne : " +
                getDistanceEuclidienne() +
                " gScore : " +
                getDistance()+getCoutAcces() ;
    }

    @Override
    public String getDessin() {
        return "S";
    }
}
