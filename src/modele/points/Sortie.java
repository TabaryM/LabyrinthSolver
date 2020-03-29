package modele.points;

public class Sortie extends Cellule {
    /** Instancie une case de sortie de labyrinthe
     * @param x int coordonnée en abscisse de la case
     * @param y int coordonnée en ordonnée de la case
     */
    public Sortie(int x, int y, Cellule.typeEnum typeEnum) {
        super(x, y, typeEnum);
    }

    public Sortie(Cellule cellule){
        this(cellule.getX(), cellule.getY(), cellule.type);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{Sortie, ");
        stringBuilder.append(super.toString());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public String getDessin() {
        return "S";
    }
}
