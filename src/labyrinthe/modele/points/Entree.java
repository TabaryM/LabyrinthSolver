package labyrinthe.modele.points;

public class Entree extends Point {
    /** Instancie une case d'entrée de labyrinthe
     * @param x int coordonnée en abscisse de la case
     * @param y int coordonnée en ordonnée de la case
     */
    public Entree(int x, int y) {
        super(x, y);
    }

    @Override
    public String getDessin() {
        return "◉";
    }
}
