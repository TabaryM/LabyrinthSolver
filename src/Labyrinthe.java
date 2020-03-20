import labyrinthe.Laby;

public class Labyrinthe /*extends Application*/ {

    public static void main(String[] args) {
        Laby laby = new Laby(20, 20);
        System.out.println(laby);
        System.out.println(laby.labyAvecChemin());
    }
}
