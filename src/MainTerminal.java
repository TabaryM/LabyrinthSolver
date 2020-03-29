import modele.Modele;

public class MainTerminal /*extends Application*/ {

    public static void main(String[] args) {
        Modele modele = new Modele(20, 20);

        System.out.println("Génération du labyrinthe (algorithme de Prim modifié) : ");
        modele.genereLabyrinthe("Prim");
        System.out.println(modele);

        System.out.println("Chemin trouvé par l'algorithme A* : ");
        modele.calculChemin("AStar");
        System.out.println(modele);

        System.out.println("Chemin trouvé par l'algorithme de Dijkstra : ");
        modele.calculChemin("Dijkstra");
        System.out.println(modele);
    }
}
