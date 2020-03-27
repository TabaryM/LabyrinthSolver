import labyrinthe.Laby;

public class Labyrinthe /*extends Application*/ {

    public static void main(String[] args) {
        Laby laby = new Laby(20, 20);
        System.out.println(laby);
        System.out.println(laby.labyAvecChemin("Dijkstra"));
        System.out.println(laby.labyAvecChemin("AStar"));
        /*
        String labyrinhteAvecChemin = laby.labyAvecChemin();
        String labyrinhte = laby.toString();
        System.out.println(labyrinhte);
        System.out.println(labyrinhteAvecChemin);
        */
    }
}
