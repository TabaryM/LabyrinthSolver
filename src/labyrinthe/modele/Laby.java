package labyrinthe.modele;

import labyrinthe.modele.points.*;

import java.util.ArrayList;
import java.util.Vector;

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
        for(int i = 0; i < largeur; i++){
            carte.add(new ArrayList<>());
        }
        generateLabyPrim();
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
    private void generateLabyRandom(){
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

    // TODO comprendre https://en.wikipedia.org/wiki/Maze_generation_algorithm#Randomized_Prim's_algorithm
    private void generateLabyPrim(){
        // On remplis le labyrinthe de murs
        for(int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                carte.get(i).add(new Mur(i, j));
            }
        }

        // La liste des murs que l'on va remplir et vider dynamiquement
        ArrayList<Mur> murs = new ArrayList<>();
        // La liste des éléments que l'on va uniquement remplir
        ArrayList<Point> visites = new ArrayList<>();

        // On choisis un premier point de départ au hasard qui ne sera pas un mur du labyrinthe
        int randomX = (int) (Math.random() * (largeur));
        int randomY = (int) (Math.random() * (hauteur));

        // On passe le point de départ en non mur
        carte.get(randomX).remove(randomY);
        carte.get(randomX).add(randomY, new Murnt(randomX, randomY));

        // On ajoute tous les murs voisins du point de départ à la liste des murs à visiter
        for(Point voisin : voisins(carte.get(randomX).get(randomY))){
            if(voisin.getClass().getSimpleName().equals("Mur")){
                murs.add((Mur) voisin);
            }
        }

        Point courrant;
        int random;
        int cptVoisinsVisites;

        // Tant que la liste des murs à visiter n'est pas vide
        // On choisis un mur aléatoire parmis les murs à visiter
        // On retire la cellule actuelle de la liste des murs à visiter
        // On ajoute la cellule actuelle à la liste des visitées
        // Si il y a seulement une cellule que le mur sépare qui est visitée
        //   On marque la cellule actuelle comme un non mur
        //   On ajoute les voisins de cette cellule à la liste des murs à visiter
        while(!murs.isEmpty()){
            random = (int) (Math.random()*murs.size());
            courrant = murs.get(random);
            visites.add(courrant);
            murs.remove(random);

            Vector<Point> v = voisins(courrant);
            cptVoisinsVisites = 0;
            for(Point p : v){
                if(visites.contains(p)){
                    cptVoisinsVisites++;
                }
            }

            // Si il y a seulement un voisin déjà visité
            if(cptVoisinsVisites == 1){
                carte.get(courrant.getX()).remove(courrant);
                carte.get(courrant.getX()).add(courrant.getY(), new Murnt(courrant.getX(), courrant.getY()));
            }

            for(Point voisin : voisins(courrant)){
                if(voisin.getClass().getSimpleName().equals("Mur")){
                    murs.add((Mur) voisin);
                }
            }
        }

    }

    /**
     * Retourne une représentation graphique à partir de caractère ASCII du laby
     * @return un rectangle aux dimensions du laby avec une case pleine pour un mur, et une case vide pour un couloir (murnt)
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("██".repeat(largeur+2));
        stringBuilder.append("\n");

        for(int i = 0; i < hauteur; i++) {
            stringBuilder.append("██");
            for (int j = 0; j < largeur; j++) {
                stringBuilder.append(carte.get(j).get(i).getDessin());
            }
            stringBuilder.append("██");
            stringBuilder.append("\n");
        }
        stringBuilder.append("██".repeat(largeur+2));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private Vector<Point> voisins(Point p){
        Vector<Point> voisins = new Vector<>();
        int posXVoisin;
        int posYVoisin;
        if(p.getX()>0){
            posXVoisin = p.getX()-1;
            posYVoisin = p.getY();
            voisins.add(carte.get(posXVoisin).get(posYVoisin));
        }
        if(p.getX()<largeur-1){
            posXVoisin = p.getX()+1;
            posYVoisin = p.getY();
            voisins.add(carte.get(posXVoisin).get(posYVoisin));
        }
        if(p.getY()>0){
            posXVoisin = p.getX();
            posYVoisin = p.getY()-1;
            voisins.add(carte.get(posXVoisin).get(posYVoisin));
        }
        if(p.getY()<hauteur-1){
            posXVoisin = p.getX();
            posYVoisin = p.getY()+1;
            voisins.add(carte.get(posXVoisin).get(posYVoisin));
        }
        return voisins;
    }

}
