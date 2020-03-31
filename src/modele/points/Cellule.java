package modele.points;

import java.util.ArrayList;
import java.util.Random;

public class Cellule {
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
    private Cellule pere;
    private double distanceEuclidienne;

    public Cellule(int x, int y, typeEnum type) {
        this.type = type;
        this.x = x;
        this.y = y;
        distance = Integer.MAX_VALUE - 10;
        distanceEuclidienne = Integer.MAX_VALUE - 10;
        pere = null;
    }

    public Cellule(int x, int y, int coutAcces){
        this.x = x;
        this.y = y;
        distance = Integer.MAX_VALUE - 10;
        distanceEuclidienne = Integer.MAX_VALUE - 10;
        pere = null;
        switch (coutAcces){
            case 1:
            default:
                this.type = typeEnum.pierre;
                break;
            case 2:
                this.type = typeEnum.sable;
                break;
            case 3:
                this.type = typeEnum.eau;
                break;
        }
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

    public boolean isMur() {
        return this.type.equals(typeEnum.mur);
    }

    public void setType(typeEnum type) {
        this.type = type;
    }

    public void setRandomType(Random random){
        switch (random.nextInt(3) + 1){
            case 1:
            default:
                this.type = typeEnum.pierre;
                break;
            case 2:
                this.type = typeEnum.sable;
                break;
            case 3:
                this.type = typeEnum.eau;
                break;
        }
    }

    public int getCoutAcces(){
        return type.getCoutAcces();
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Cellule getPere() {
        return pere;
    }

    public void setPere(Cellule pere) {
        this.pere = pere;
    }

    public void majDistanceVers(Cellule pere){
        if(pere.getDistance() + this.getCoutAcces() <= this.getDistance()){
            this.setDistance(pere.getDistance() + this.getCoutAcces());
            this.setPere(pere);
        }
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
    public static Cellule plusInteressante(ArrayList<Cellule> liste){
        int min = Integer.MAX_VALUE - 10;
        Cellule res = null;
        for(Cellule cellule : liste){
            if(cellule.getCoutAStarAmplifie(2) < min){
                min = cellule.getCoutAStarAmplifie(2);
                res = cellule;
            }
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cellule that = (Cellule) o;

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
