package labyrinthe;

import labyrinthe.points.*;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class Laby {

    private Entree entree;
    private Sortie sortie;
    private int ligne;
    private int colonne;

    private ArrayList<ArrayList<Cellule>> carte;

    /**
     * Instancie un nouveau Labyrinthe
     * Génère l'interieur du laby de manière aléatoire
     * @param ligne int : hauteur max du laby
     * @param colonne int : largeur maximales du laby
     */
    public Laby(int ligne, int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
        resetCarte();
        generateLabyPrim();
        initEntreeSortie();
    }

    private void resetCarte(){
        carte = new ArrayList<>();
        for(int i = 0; i < ligne; i++){
            carte.add(new ArrayList<>());
        }
    }

    private void initEntreeSortie(){
        ArrayList<Cellule> tmp = getCouloirs();

        entree = new Entree(tmp.get(0));
        carte.get(entree.getX()).remove(entree.getY());
        carte.get(entree.getX()).add(entree.getY(), entree);

        sortie = new Sortie(tmp.get(tmp.size()-1));
        carte.get(sortie.getX()).remove(sortie.getY());
        carte.get(sortie.getX()).add(sortie.getY(), sortie);
        entree.setEstMur(false);
        sortie.setEstMur(false);
    }

    // Fonctions liées au labyrinthe
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
     * Retourne la liste des couloirs du labyrinthe
     * @return ArrayList<Cellule> : tout les couloirs du labyrinthe
     */
    public ArrayList<Cellule> getCouloirs() {
        ArrayList<Cellule> couloirs = new ArrayList<>();
        for(ArrayList<Cellule> ligne : carte){
            for(Cellule cellule : ligne){
                if(!cellule.isMur()){
                    couloirs.add(cellule);
                }
            }
        }
        return couloirs;
    }

    /**
     * Retourne les cellules voisines à la cellule passée en paramètres
     * @param cellule Cellule : cellule dont on veut les voisins
     * @return voisins Vector<Cellule> : liste des voisins
     */
    private Vector<Cellule> voisins(Cellule cellule){
            Vector<Cellule> voisins = new Vector<>();
            int posXVoisin;
            int posYVoisin;
            if(cellule.getX()>0){
                posXVoisin = cellule.getX()-1;
                posYVoisin = cellule.getY();
                voisins.add(carte.get(posXVoisin).get(posYVoisin));
            }
            if(cellule.getX()< colonne -1){
                posXVoisin = cellule.getX()+1;
                posYVoisin = cellule.getY();
                voisins.add(carte.get(posXVoisin).get(posYVoisin));
            }
            if(cellule.getY()>0){
                posXVoisin = cellule.getX();
                posYVoisin = cellule.getY()-1;
                voisins.add(carte.get(posXVoisin).get(posYVoisin));
            }
            if(cellule.getY()< ligne -1){
                posXVoisin = cellule.getX();
                posYVoisin = cellule.getY()+1;
                voisins.add(carte.get(posXVoisin).get(posYVoisin));
            }
            return voisins;
        }

    /**
     * Génère un labyrinthe selon l'algo aléatoire de Prim (avec modifications personnelles)
     * Modifications personnelles :
     *  On retire 10% des murs en contact avec des couloirs
     */
    private void generateLabyPrim(){
        resetCarte();
        // On remplis le labyrinthe de murs
        for(int i = 0; i < colonne; i++) {
            for (int j = 0; j < ligne; j++) {
                carte.get(i).add(new Cellule(i, j, true));
            }
        }

        // La liste des éléments que l'on va uniquement remplir
        ArrayList<Cellule> visites = new ArrayList<>();

        // On choisis un premier point de départ au hasard qui ne sera pas un mur du labyrinthe
        int randomX = (int) (Math.random() * (colonne));
        int randomY = (int) (Math.random() * (ligne));

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
                if(cptVoisinsVisites == 2 && (int) (Math.random()*100) >= 20){
                    carte.get(courrant.getX()).get(courrant.getY()).setEstMur(false);
                }
            }
        }

        // Si le labyrinthe est mal généré, on recommance
        if(visites.size() < (ligne * colonne)/2) {
            generateLabyPrim();
        }
    }

    /**
     * Retourne une représentation graphique à partir de caractère ASCII du laby
     * @return un rectangle aux dimensions du laby avec une case pleine pour un mur, et une case vide pour un couloir (murnt)
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("███".repeat(colonne +2));
        stringBuilder.append("\n");

        for(int i = 0; i < ligne; i++) {
            stringBuilder.append("███");
            for (int j = 0; j < colonne; j++) {
                stringBuilder.append(carte.get(j).get(i).getDessin());
            }
            stringBuilder.append("███");
            stringBuilder.append("\n");
        }
        stringBuilder.append("███".repeat(colonne +2));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    // Fonctions liées aux résoultions de labyrinthe

    /**
     * Retourne un chemin depuis l'entrée vers la sortie selon l'algorithme de Dijkstra
     * @return chemin ArrayList<Cellule> : chemin entre this.entree et this.sortie
     */
    private ArrayList<Cellule> cheminDijkstra(){
        calculDistanceDisjkstra();
        ArrayList<Cellule> chemin = new ArrayList<>();
        Cellule courrant = sortie;
        while(!courrant.equals(entree)){
            //System.out.println("Cellule : "+courrant+"\tPere : "+courrant.getPere());
            chemin.add(courrant);
            courrant = courrant.getPere();
        }
        chemin.remove(sortie);

        return chemin;
    }

    private ArrayList<Cellule> getCheminAStar(){
        aStar();
        ArrayList<Cellule> chemin = new ArrayList<>();

        chemin.add(sortie);
        Cellule courrant = sortie;
        while (courrant != null){
            chemin.add(courrant.getPere());
            courrant = courrant.getPere();
        }

        chemin.remove(entree);
        chemin.remove(sortie);

        return chemin;
    }

    private void calculDistanceDisjkstra(){
        resetDijkstra();

        ArrayList<Cellule> visites = new ArrayList<>();
        ArrayList<Cellule> liste = new ArrayList<>();
        Cellule courrant;

        liste.add(entree);

        while(!liste.isEmpty()){
            courrant = liste.get(0);
            Vector<Cellule> voisins = voisins(courrant);
            for(Cellule voisin : voisins){
                if(!voisin.isMur()){
                    if(courrant.getPere() != voisin){
                        courrant.majDistanceVers(voisin);
                    }
                    if(!visites.contains(voisin)){
                        liste.add(voisin);
                    }
                }
            }

            liste.remove(courrant);
            if(!visites.contains(courrant)) visites.add(courrant);
        }
    }

    private void resetDijkstra(){
        for(ArrayList<Cellule> colonne : carte){
            for(Cellule cellule : colonne){
                cellule.setDistance(Integer.MAX_VALUE -10);
                cellule.setPere(null);
            }
        }
        entree.setDistance(0);
    }

    public void aStar(){
        resetDijkstra();
        // On calcule les heuristiques pour toutes les cellules
        for(Cellule cellule : getCouloirs()){
            cellule.setDistanceEuclidienne(heuristique(cellule));
        }
        ArrayList<Cellule> ouverts = new ArrayList<>();
        ArrayList<Cellule> fermees = new ArrayList<>();

        ouverts.add(entree);

        int scoreTmp;
        Cellule courrant;
        while (!ouverts.isEmpty()){
            courrant = plusInteressante(ouverts);
            if(courrant.equals(sortie)){
                // Chemin trouvé, on affiche le chemin
                return;
            }

            ouverts.remove(courrant);
            fermees.add(courrant);
            for(Cellule voisin : voisins(courrant)){
                if(!voisin.isMur()){
                    scoreTmp = courrant.getCoutAcces() + courrant.getDistance();
                    if(scoreTmp < voisin.getDistance()){
                        voisin.setPere(courrant);
                        voisin.setDistance(scoreTmp);
                        if(!ouverts.contains(voisin)){
                            ouverts.add(voisin);
                        }
                    }
                }
            }
        }
    }

    private Cellule plusInteressante(ArrayList<Cellule> liste){
        int min = Integer.MAX_VALUE - 10;
        Cellule res = null;
        for(Cellule cellule : liste){
            if(cellule.getCoutAStar() < min){
                min = cellule.getCoutAStar();
                res = cellule;
            }
        }
        return res;
    }

    /**
     * Retourne la distance euclidienne
     * @param cellule
     * @return
     */
    private double heuristique(Cellule cellule){
        double res = 0 ;
        double coteX = Math.pow((Math.abs(cellule.getX() - sortie.getX())), 2);
        double coteY = Math.pow((Math.abs(cellule.getY() - sortie.getY())), 2);
        res = Math.sqrt(coteX + coteY);
        return res;
    }

    public String labyAvecChemin(String modeChemin){
        ArrayList<Cellule> chemin;
        StringBuilder stringBuilder = new StringBuilder();
        switch (modeChemin){
            case "Dijkstra":
                chemin = cheminDijkstra();
                stringBuilder.append("Chemin selon Dijkstra : \n");
                break;
            case "AStar":
                chemin = getCheminAStar();
                stringBuilder.append("Chemin selon A* : \n");
                break;
            default:
                chemin = cheminDijkstra();

        }
        Cellule courrant;

        stringBuilder.append("███".repeat(colonne +2));
        stringBuilder.append("\n");

        for(int i = 0; i < ligne; i++) {
            stringBuilder.append("███");
            for (int j = 0; j < colonne; j++) {
                courrant = carte.get(j).get(i);
                if(chemin.contains(courrant)){
                    stringBuilder.append("░░░");
                } else {
                    stringBuilder.append(carte.get(j).get(i).getDessin());
                }
            }
            stringBuilder.append("███");
            stringBuilder.append("\n");
        }
        stringBuilder.append("███".repeat(colonne +2));
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
