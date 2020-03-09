package labyrinthe.modele;

public class Mur extends Point {
    public Mur(int x, int y) {
        super(x, y);
    }

    @Override
    public String getDessin() {
        return "██";
    }
}
