package modele.points;

import java.util.ArrayList;

public class CelluleType {
    public enum typeEnum{
        mur,
        pierre,
        sable,
        eau;

        public int getCoutAcces(){
            int res;
            switch (this){
                case mur:
                    res = Integer.MAX_VALUE-5;
                    break;
                case pierre:
                default:
                    res = 1;
                    break;
                case sable:
                    res = 2;
                    break;
                case eau:
                    res = 3;
                    break;
            }
            return res;
        }
        public String getCouleurTerminal(){
            String res;
            switch (this){
                case mur:
                case pierre:
                default:
                    res = "\u001B[37m";
                    break;
                case sable:
                    res = "\u001B[33m";
                    break;
                case eau:
                    res = "\u001B[34m";
                    break;
            }
            return res;
        }
    }
    public typeEnum type;
    private int x;
    private int y;
    private int distance;
    private CelluleType pere;
    private double distanceEuclidienne;

    public CelluleType(int x, int y, typeEnum type) {
        this.type = type;
        this.x = x;
        this.y = y;
        distance = Integer.MAX_VALUE - 10;
        distanceEuclidienne = Integer.MAX_VALUE - 10;
        pere = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public typeEnum getType() {
        return type;
    }

    public void setType(typeEnum type) {
        this.type = type;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public CelluleType getPere() {
        return pere;
    }

    public void setPere(CelluleType pere) {
        this.pere = pere;
    }

    public double getDistanceEuclidienne() {
        return distanceEuclidienne;
    }

    public void setDistanceEuclidienne(double distanceEuclidienne) {
        this.distanceEuclidienne = distanceEuclidienne;
    }

    public int getCoutAStar(){
        return (int)(distance+distanceEuclidienne);
    }

    public int getCoutAStarAmplifie(double puissance){
        return distance + (int)(Math.pow(distanceEuclidienne, puissance));
    }

    /**
     * Retourne la Cellule la plus prometteuse pour aller vers la sortie
     * @param liste Liste des cellules à évaluer
     * @return res Cellule : une des meilleures cellules
     */
    public static CelluleType plusInteressante(ArrayList<CelluleType> liste){
        int min = Integer.MAX_VALUE - 10;
        CelluleType res = null;
        for(CelluleType cellule : liste){
            if(cellule.getCoutAStar() < min){
                min = cellule.getCoutAStarAmplifie(1);
                res = cellule;
            }
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CelluleType that = (CelluleType) o;

        if (getX() != that.getX()) return false;
        return getY() == that.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }

    public String getDessin(){
        StringBuilder stringBuilder = new StringBuilder(type.getCouleurTerminal());
        switch (type){
            case mur:
                stringBuilder.append("███");
                break;
            case pierre:
                stringBuilder.append("p");
                break;
            case sable:
                stringBuilder.append("s");
                break;
            case eau:
                stringBuilder.append("e");
                break;
        }
        stringBuilder.append("\u001B[0m");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CelluleType{");
        sb.append("type=").append(type);
        sb.append(", x=").append(x);
        sb.append(", y=").append(y);
        sb.append('}');
        return sb.toString();
    }
}
