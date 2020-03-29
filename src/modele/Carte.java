package modele;

import modele.points.Cellule;
import modele.points.Entree;
import modele.points.Sortie;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

public class Carte implements Iterable<ArrayList<Cellule>> {
    private ArrayList<ArrayList<Cellule>> carte;
    private Entree entree;
    private Sortie sortie;
    private int nbLignes;
    private int nbColonnes;
    private Random random;

    public Carte(int nbLignes, int nbColonnes, Random random){
        this.nbColonnes = nbColonnes;
        this.nbLignes = nbLignes;
        this.random = random;

        // Initialisation du labyrinthe
        resetCarte();
        initEntreeSortie();
    }

    /**
     * Réinitialise la carte (supprime toutes les cellules)
     */
    private void resetCarte(){
        int coutCellule;
        carte = new ArrayList<>();
        for(int i = 0; i < nbColonnes; i++){
            carte.add(new ArrayList<>());
            for(int j = 0; j < nbLignes; j++){
                coutCellule = random.nextInt(3) + 1;
                carte.get(i).add(new Cellule(i, j, coutCellule));
            }
        }
    }

    /**
     * Initialise une entrée et une sortie
     */
    public void initEntreeSortie(){
        ArrayList<Cellule> tmp = getCouloirs();

        entree = new Entree(tmp.get(0));
        carte.get(entree.getX()).remove(entree.getY());
        carte.get(entree.getX()).add(entree.getY(), entree);

        sortie = new Sortie(tmp.get(tmp.size()-1));
        carte.get(sortie.getX()).remove(sortie.getY());
        carte.get(sortie.getX()).add(sortie.getY(), sortie);
        entree.setRandomType(random);
        sortie.setRandomType(random);
    }

    /**
     * Retourne la liste des couloirs du labyrinthe
     * @return ArrayList<CelluleType> : tout les couloirs du labyrinthe
     */
    public ArrayList<Cellule> getCouloirs() {
        ArrayList<Cellule> couloirs = new ArrayList<>();
        Cellule cellule;
        for(int i = 0; i < nbColonnes; i++){
            for(int j = 0; j < nbLignes; j++){
                cellule = carte.get(i).get(j);
                if(!cellule.isMur()){
                    couloirs.add(cellule);
                }
            }
        }
        return couloirs;
    }

    /**
     * Retourne les cellules voisines à la cellule passée en paramètres
     * @param cellule CelluleType : cellule dont on veut les voisins
     * @return voisins Vector<CelluleType> : liste des voisins
     */
    public Vector<Cellule> voisins(Cellule cellule){
        Vector<Cellule> voisins = new Vector<>();
        int posXVoisin;
        int posYVoisin;
        if(cellule.getX()>0){
            posXVoisin = cellule.getX()-1;
            posYVoisin = cellule.getY();
            voisins.add(carte.get(posXVoisin).get(posYVoisin));
        }
        if(cellule.getX()< nbColonnes -1){
            posXVoisin = cellule.getX()+1;
            posYVoisin = cellule.getY();
            voisins.add(carte.get(posXVoisin).get(posYVoisin));
        }
        if(cellule.getY()>0){
            posXVoisin = cellule.getX();
            posYVoisin = cellule.getY()-1;
            voisins.add(carte.get(posXVoisin).get(posYVoisin));
        }
        if(cellule.getY()< nbLignes -1){
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
    public void generateLabyPrim(){
        resetCarte();
        // On remplis le labyrinthe de murs
        for(int i = 0; i < nbColonnes; i++) {
            for (int j = 0; j < nbLignes; j++) {
                carte.get(i).get(j).setType(Cellule.typeEnum.mur);
            }
        }

        // La liste des éléments que l'on va uniquement remplir
        ArrayList<Cellule> visites = new ArrayList<>();

        // On choisis un premier point de départ au hasard qui ne sera pas un mur du labyrinthe
        int randomX = (int) (Math.random() * (nbColonnes));
        int randomY = (int) (Math.random() * (nbLignes));

        Cellule courrant = carte.get(randomX).get(randomY);
        courrant.setRandomType(random);
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
                carte.get(courrant.getX()).get(courrant.getY()).setRandomType(random);
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
                    carte.get(courrant.getX()).get(courrant.getY()).setRandomType(random);
                }
            }
        }

        // Si le labyrinthe est mal généré, on recommance
        if(visites.size() < (nbLignes * nbColonnes)/2) {
            generateLabyPrim();
        }
        initEntreeSortie();
    }

    /**
     * Retourne la distance euclidienne d'une cellule vers la sortie
     * @param cellule CelluleType qui nous interesse
     * @return distance euclidienne entre le cellule passée en paramètre et la sortie
     */
    public double calculeHeuristique(Cellule cellule){
        double res;
        double coteX = Math.pow((Math.abs(cellule.getX() - sortie.getX())), 2);
        double coteY = Math.pow((Math.abs(cellule.getY() - sortie.getY())), 2);
        res = Math.sqrt(coteX + coteY);
        return res;
    }

    public void resetValeursAlgos(){
        for(ArrayList<Cellule> colonne : carte){
            for(Cellule cellule : colonne){
                cellule.setDistance(Integer.MAX_VALUE -10);
                cellule.setPere(null);
            }
        }
        getEntree().setDistance(0);
    }

    public Cellule getCellule(int col, int lig){
        return carte.get(col).get(lig);
    }

    public Entree getEntree() {
        return entree;
    }

    public void setEntree(Entree entree) {
        this.entree = entree;
    }

    public Sortie getSortie() {
        return sortie;
    }

    public void setSortie(Sortie sortie) {
        this.sortie = sortie;
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public void setNbLignes(int nbLignes) {
        this.nbLignes = nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }

    public void setNbColonnes(int nbColonnes) {
        this.nbColonnes = nbColonnes;
    }

    @Override
    public Iterator<ArrayList<Cellule>> iterator() {
        return carte.iterator();
    }
}
