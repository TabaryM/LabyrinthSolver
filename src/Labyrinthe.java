import modele.Laby;

public class Labyrinthe /*extends Application*/ {

    public static void main(String[] args) {
        Laby laby;

        /*
        laby = new Laby(20, 20);
        laby.initEntreeSortie();
        System.out.println(laby);
        System.out.println(laby.labyAvecChemin("Dijkstra"));
        System.out.println(laby.labyAvecChemin("AStar"));
        */

        laby = new Laby(20, 20);
        laby.generateLabyPrim();
        // Affichage du labyrinthe
        System.out.println(laby);
        //System.out.println(laby.labyAvecChemin("Dijkstra"));
        // Affichage du labyrinthe avec le chemin calculé par A*
        System.out.println(laby.labyAvecChemin("AStar"));
    }
}
