package labyrinthe.modele.points;

public abstract class Point {

    private int x;
    private int y;

    /**
     * Instancie un nouveau point
     * @param x int : coordonnées en abscisse
     * @param y int : coordonnées en ordonnée
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne la coordonnées en abscisse du point
     * @return x int
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la coordonnées en ordonnée du point
     * @return y int
     */
    public int getY() {
        return y;
    }

    /**
     * retourne
     * @return
     */
    public abstract String getDessin();

}
