package labyrinthe.points;

public class Cellule {

    private int x;
    private int y;
    private boolean estMur;

    private int distance;
    private Cellule pere;

    private int coutAcces;
    private double distanceEuclidienne;

    /**
     * Instancie un nouveau point
     * @param x int : coordonnées en abscisse
     * @param y int : coordonnées en ordonnée
     */
    public Cellule(int x, int y, int coutAcces) {
        this.x = x;
        this.y = y;
        this.estMur = coutAcces == 0;
        distance = Integer.MAX_VALUE - 10;
        distanceEuclidienne = Integer.MAX_VALUE - 10;
        pere = null;
        this.coutAcces = coutAcces;
    }

    /**
     * Instancie un nouveau point
     * @param x int : coordonnées en abscisse
     * @param y int : coordonnées en ordonnée
     */
    public Cellule(int x, int y) {
        this(x, y, 1);
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
     * Retourne la distance depuis le point de départ
     * @return int distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Met à jour la distance depuis le point de départ
     * @param distance int la nouvelle distance
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     * Retourne la cellule voisine à la cellule actuelle la plus proche du point de départ
     * @return Cellule pere
     */
    public Cellule getPere() {
        return pere;
    }

    /**
     * Met à jour le pere de la cellule actuelle
     * @param pere Cellule la plus proche du point de départ
     */
    public void setPere(Cellule pere) {
        this.pere = pere;
    }

    public boolean estFilsDe(Cellule cellule){
        return cellule.equals(pere);
    }

    /**
     * Retourne le cout d'acces à la cellule actuelle
     * @return int coutAcces
     */
    public int getCoutAcces(){
        return coutAcces;
    }

    public void setDistanceEuclidienne(double distanceEuclidienne){
        this.distanceEuclidienne = distanceEuclidienne;
    }

    public double getDistanceEuclidienne(){
        return distanceEuclidienne;
    }

    public int getCoutAStar(){
        return (int)(distance+distanceEuclidienne);
    }

    public int getCoutAStarAmplifie(double puissance){
        return distance + (int)(Math.pow(distanceEuclidienne, puissance));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cellule cellule = (Cellule) o;

        if (getX() != cellule.getX()) return false;
        return getY() == cellule.getY();
    }

    public void majDistanceVers(Cellule pere){
        if(pere.getDistance() + this.getCoutAcces() <= this.getDistance()){
            this.setDistance(pere.getDistance() + this.getCoutAcces());
            this.setPere(pere);
        }
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }

    /**
     * Retourne un caractère représentant la case
     * @return String caractère de la case
     */
    public String getDessin(){
        if(estMur){
            return "███";
        } else {
            switch (coutAcces){
                case 1:
                    return "p";
                case 2:
                    return "s";
                case 3:
                    return "e";
            }
            return "   ";
        }
    }

    @Override
    public String toString() {
        return "[" + x +
                "," + y +
                ']';
    }
}
