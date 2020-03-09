package labyrinthe.modele;

public class Murnt extends Point {
    public Murnt(int x, int y) {
        super(x, y);
    }

    @Override
    public String getDessin() {
        return "  ";
    }
}
