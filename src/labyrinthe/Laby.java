package labyrinthe;

import labyrinthe.points.*;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Vector;

public class Laby {

    private Entree entree;
    private Sortie sortie;
    private int hauteur;
    private int largeur;

    private ArrayList<ArrayList<Cellule>> carte;

    /**
     * Instancie un nouveau Labyrinthe
     * Génère l'interieur du laby de manière aléatoire
     * @param hauteur int : hauteur max du laby
     * @param largeur int : largeur maximales du laby
     */
    public Laby(int hauteur, int largeur){
        resetCarte();
        this.hauteur = hauteur;
        this.largeur = largeur;
        generateLabyPrim();

        // Ajoute une entrée en haut à gauche
        ArrayList<Cellule> tmp = new ArrayList<>();
        for(int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                if(!carte.get(i).get(j).isMur()){
                    tmp.add(carte.get(i).get(j));
                }
            }
        }

        entree = new Entree(tmp.get(0));
        carte.get(entree.getX()).remove(entree.getY());
        carte.get(entree.getX()).add(entree.getY(), entree);

        sortie = new Sortie(tmp.get(tmp.size()-1));
        carte.get(sortie.getX()).remove(sortie.getY());
        carte.get(sortie.getX()).add(sortie.getY(), sortie);
    }

    public void resetCarte(){
        carte = new ArrayList<>();
        for(int i = 0; i < largeur; i++){
            carte.add(new ArrayList<>());
        }
    }

    /**
     * Retourne les coordonnées du point d'entrée du laby
     * @return entree Point : le point d'entrée du laby
     */
    public Cellule getEntree() {
        return entree;
    }

    /**
     * Retourne les coordonnées du point de sortie du laby
     * @return sortie Point : le point de sortie du laby
     */
    public Cellule getSortie() {
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
     * Génère un labyrinthe selon l'algo aléatoire de Prim (avec modifications personnelles)
     * Modifications personnelles :
     *  On retire 10% des murs en contact avec des couloirs
     */
    private void generateLabyPrim(){
        resetCarte();
        // On remplis le labyrinthe de murs
        for(int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                carte.get(i).add(new Cellule(i, j, true));
            }
        }

        // La liste des éléments que l'on va uniquement remplir
        ArrayList<Cellule> visites = new ArrayList<>();

        // On choisis un premier point de départ au hasard qui ne sera pas un mur du labyrinthe
        int randomX = (int) (Math.random() * (largeur));
        int randomY = (int) (Math.random() * (hauteur));

        Cellule courrant = carte.get(randomX).get(randomY);
        courrant.setEstMur(false);
        int cptVoisinsVisites;

        // La liste des murs que l'on va remplir et vider dynamiquement
        // On ajoute tous les murs voisins du point de départ à la liste des murs à visiter
        ArrayList<Cellule> murs = new ArrayList<>();
        murs.add(courrant);
        murs.addAll(voisins(courrant));

        // Tant que la liste des murs à visiter n'est pas vide
        // On choisis un mur aléatoire parmis les murs à visiter
        // On retire la cellule actuelle de la liste des murs à visiter
        // On ajoute la cellule actuelle à la liste des visitées
        // Si il y a seulement une cellule que le mur sépare qui est visitée
        //   On marque la cellule actuelle comme un non mur
        //   On ajoute les voisins de cette cellule à la liste des murs à visiter
        while(!murs.isEmpty()){
            courrant = murs.get((int) (Math.random()*murs.size()));

            //courrant = murs.get(0);
            murs.remove(courrant);
            // On ajoute l'element courrant à la liste des éléments visités
            visites.add(courrant);

            cptVoisinsVisites = 0;
            // compte le nombre de voisins visités
            Vector<Cellule> voisins = voisins(courrant);
            for(Cellule p : voisins){
                if(visites.contains(p)){
                    cptVoisinsVisites++;
                }
            }
            // Si il y a seulement un voisin déjà visité
            // On retire le mur de la cellule actuelle
            if(cptVoisinsVisites == 1){
                carte.get(courrant.getX()).get(courrant.getY()).setEstMur(false);
                // On ajoute tout les murs voisins à l'élément courrant à la liste des murs à visiter
                for(Cellule voisin : voisins(courrant)){
                    if(!murs.contains(voisin) && !visites.contains(voisin) && voisin.isMur()){
                        murs.add(voisin);
                    }
                }
            } else {
                /*
                // On transforme 10% des murs à coté des couloirs en couloir
                if((int) (Math.random()*10) == 1){
                    carte.get(courrant.getX()).get(courrant.getY()).setEstMur(false);
                }
                */

                // Si la cellule est entre deux couloirs on la transforme en couloir
                cptVoisinsVisites = 0;
                for (Cellule p : voisins){
                    if(carte.get(p.getX()).get(p.getY()).isMur()){
                        cptVoisinsVisites++;
                    }
                }
                if(cptVoisinsVisites == 2 && (int) (Math.random()*100) >= 33){
                    carte.get(courrant.getX()).get(courrant.getY()).setEstMur(false);
                }

            }
        }

        // Si le labyrinthe est mal généré, on recommance
        if(visites.size() < (hauteur*largeur)/2) {
            generateLabyPrim();
        }
    }

    private ArrayList<Cellule> cheminDijkstra(){
        initDijsktra();
        // Initialisation de la route
        ArrayList<Cellule> route = new ArrayList<>();

        // Liste des cellules à voir
        ArrayList<Cellule> liste = new ArrayList<>();
        for(ArrayList<Cellule> colonne : carte){
            for(Cellule cellule : colonne){
                if(!cellule.isMur()){
                    liste.add(cellule);
                }
            }
        }

        Cellule courrant;
        while(!liste.isEmpty()){
            courrant = cellulePlusSimpleDAcces(liste);
            liste.remove(courrant);

            for(Cellule voisin : voisins(courrant)){
                majDistance(courrant, voisin);
            }
        }
        return getCheminVersSortie();
    }

    public ArrayList<Cellule> getCheminVersSortie(){
        ArrayList<Cellule> inverse = new ArrayList<>();
        ArrayList<Cellule> chemin = new ArrayList<>();

        inverse.add(sortie);
        while(!inverse.get(inverse.size() -1).equals(entree)){
            inverse.add(inverse.get(inverse.size() -1).getPere());
        }

        for (int i = inverse.size() -1; i >= 0; i--){
            chemin.add(inverse.get(i));
        }

        chemin.remove(0);
        chemin.remove(chemin.size() -1);

        return chemin;
    }

    private void initDijsktra(){
        for(ArrayList<Cellule> colonne : carte){
            for(Cellule cellule : colonne){
                cellule.setDistance(Integer.MAX_VALUE -10);
            }
        }
        entree.setDistance(0);
    }

    /**
     * Retoure la cellule avec le cout d'accès le plus faible de la liste
     * @param liste liste de Cellules
     * @return la cellule avec le cout d'accès le plus faible
     */
    private Cellule cellulePlusSimpleDAcces(ArrayList<Cellule> liste){
        int coutMax = Integer.MAX_VALUE;
        Cellule res = null;
        for(Cellule cellule : liste){
            if(cellule.getDistance() < coutMax){
                coutMax = cellule.getCoutAcces();
                res = cellule;
            }
        }
        return res;
    }

    private void majDistance(Cellule c1, Cellule c2){
        if(c1.getDistance() + c2.getCoutAcces() < c2.getDistance()){
            c2.setDistance(c1.getDistance() + c2.getCoutAcces());
            c2.setPere(c1);
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

    public String labyAvecChemin(){
        ArrayList<Cellule> chemin = cheminDijkstra();
        Cellule courrant;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("██".repeat(largeur+2));
        stringBuilder.append("\n");

        for(int i = 0; i < hauteur; i++) {
            stringBuilder.append("██");
            for (int j = 0; j < largeur; j++) {
                courrant = carte.get(j).get(i);
                if(chemin.contains(courrant)){
                    stringBuilder.append("░░");
                } else {
                    stringBuilder.append(carte.get(j).get(i).getDessin());
                }
            }
            stringBuilder.append("██");
            stringBuilder.append("\n");
        }
        stringBuilder.append("██".repeat(largeur+2));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private Vector<Cellule> voisins(Cellule p){
        Vector<Cellule> voisins = new Vector<>();
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
