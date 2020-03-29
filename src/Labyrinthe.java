import modele.ModeleLabyrinthe;

public class Labyrinthe /*extends Application*/ {

    public static void main(String[] args) {
        ModeleLabyrinthe modeleLabyrinthe = new ModeleLabyrinthe(20, 20);

        System.out.println("Génération du labyrinthe (algorithme de Prim modifié) : ");
        modeleLabyrinthe.genereLabyrinthe("Prim");
        System.out.println(modeleLabyrinthe);

        System.out.println("Chemin trouvé par l'algorithme A* : ");
        modeleLabyrinthe.calculChemin("AStar");
        System.out.println(modeleLabyrinthe);

        System.out.println("Chemin trouvé par l'algorithme de Dijkstra : ");
        modeleLabyrinthe.calculChemin("Dijkstra");
        System.out.println(modeleLabyrinthe);
    }
}
