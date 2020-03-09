package labyrinthe.modele.points;

public class Mur extends Point {
    /**
     *
     * @param x
     * @param y
     */
    public Mur(int x, int y) {
        super(x, y);
    }

    /**
     *
     * @return
     */
    @Override
    public String getDessin() {
        return "██";
    }
}
