import modele.ModeleLabyrinthe;

public class Labyrinthe /*extends Application*/ {

    public static void main(String[] args) {
        ModeleLabyrinthe modeleLabyrinthe = new ModeleLabyrinthe(20, 20);
        modeleLabyrinthe.genereLabyrinthe("Prim");
        modeleLabyrinthe.calculChemin("AStar");
        System.out.println(modeleLabyrinthe);
    }
}
