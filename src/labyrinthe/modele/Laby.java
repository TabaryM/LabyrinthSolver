package labyrinthe.modele;

import labyrinthe.modele.points.*;

import java.util.ArrayList;

public class Laby {

    private Entree entree;
    private Sortie sortie;
    private int hauteur;
    private int largeur;

    private ArrayList<ArrayList<Point>> carte;

    /**
     * Instancie un nouveau Labyrinthe
     * Génère l'interieur du laby de manière aléatoire
     * @param hauteur int : hauteur max du laby
     * @param largeur int : largeur maximales du laby
     */
    public Laby(int hauteur, int largeur){
        carte = new ArrayList<>();
        this.hauteur = hauteur;
        this.largeur = largeur;
        for(int i = 0; i < hauteur; i++){
            carte.add(new ArrayList<>());
        }
        generateLaby();
    }

    /**
     * Retourne les coordonnées du point d'entrée du laby
     * @return entree Point : le point d'entrée du laby
     */
    public Point getEntree() {
        return entree;
    }

    /**
     * Retourne les coordonnées du point de sortie du laby
     * @return sortie Point : le point de sortie du laby
     */
    public Point getSortie() {
        return sortie;
    }

    /**
     * Fixe les coordonées de l'entrée du laby
     * @param entree Point : les coordonnées du point d'entrée du laby
     */
    public void setEntree(Entree entree) {
        this.entree = entree;
    }

    /**
     * Fixe les coordonnées de la sortie du laby
     * @param sortie Point : les coordonnées du point de sortie du laby
     */
    public void setSortie(Sortie sortie) {
        this.sortie = sortie;
    }

    /**
     * Retourne la hauteur du labyrinthe
     * @return int : la hauteur du laby
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * Retourne la largeur du labyrinthe
     * @return int : la largeur du laby
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * Genere un labyrinthe moche
     */
    private void generateLaby(){
        for(int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (Math.random() > 0.666) {
                    carte.get(j).add(new Mur(j, i));
                }else{
                    carte.get(j).add(new Murnt(j, i));
                }
            }
        }
        carte.get(0).set(0, new Entree(0, 0));
        carte.get(largeur - 1).set(hauteur - 1, new Sortie(largeur - 1, hauteur - 1));
    }

    /**
     * Retourne une représentation graphique à partir de caractère ASCII du laby
     * @return un rectangle aux dimensions du laby avec une case pleine pour un mur, et une case vide pour un couloir (murnt)
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("██".repeat(dimension.getX()+2));
        stringBuilder.append("\n");
        for(ArrayList<Point> ligne : carte){
            stringBuilder.append("██");
            for(Point col : ligne){
                stringBuilder.append(col.getDessin());
            }
            stringBuilder.append("██");
            stringBuilder.append("\n");
        }
        stringBuilder.append("██".repeat(dimension.getX()+2));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
