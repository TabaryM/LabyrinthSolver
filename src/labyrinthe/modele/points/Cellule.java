package labyrinthe.modele.points;

public class Cellule {

    private int x;
    private int y;
    private boolean estMur;

    /**
     * Instancie un nouveau point
     * @param x int : coordonnées en abscisse
     * @param y int : coordonnées en ordonnée
     */
    public Cellule(int x, int y, boolean estMur) {
        this.x = x;
        this.y = y;
        this.estMur = estMur;
    }

    /**
     * Retourne la coordonnées en abscisse du point
     * @return x int
     */
    public int getX() {
        return x;
    }

    /**
     * Retourne la coordonnées en ordonnée du point
     * @return y int
     */
    public int getY() {
        return y;
    }

    /**
     * Retourne vrai si la cellule est un mur
     * @return boolean estMur
     */
    public boolean isMur(){
        return estMur;
    }

    /**
     * Fixe l'état de la cellule
     * @param estMur boolean : vrai si la cellule est un mur, faux sinon
     */
    public void setEstMur(boolean estMur) {
        this.estMur = estMur;
    }

    /**
     * Retourne un caractère représentant la case
     * @return String caractère de la case
     */
    public String getDessin(){
        if(estMur){
            return "██";
        } else {
            return "  ";
        }
    }

    @Override
    public String toString() {
        return "[" + x +
                "," + y +
                ']';
    }
}
