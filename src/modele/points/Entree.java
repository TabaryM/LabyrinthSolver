package modele.points;

public class Entree extends Cellule {
    /** Instancie une case d'entrée de labyrinthe
     * @param x int coordonnée en abscisse de la case
     * @param y int coordonnée en ordonnée de la case
     */
    public Entree(int x, int y, typeEnum typeEnum) {
        super(x, y, typeEnum);
    }

    public Entree(Cellule cellule){
        this(cellule.getX(), cellule.getY(), cellule.type);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{Entree, ");
        stringBuilder.append(super.toString());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public String getDessin() {
        return "E";
    }
}
