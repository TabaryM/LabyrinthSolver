package labyrinthe.modele;

public class Murnt extends Point {
    /**
     *
     * @param x
     * @param y
     */
    public Murnt(int x, int y) {
        super(x, y);
    }

    /**
     *
     * @return
     */
    @Override
    public String getDessin() {
        return "  ";
    }
}
