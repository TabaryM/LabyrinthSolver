package labyrinthe.modele.points;

public class Sortie extends Cellule {
    /** Instancie une case de sortie de labyrinthe
     * @param x int coordonnée en abscisse de la case
     * @param y int coordonnée en ordonnée de la case
     */
    public Sortie(int x, int y) {
        super(x, y, false);
    }

    @Override
    public String getDessin() {
        return "◎";
    }
}
