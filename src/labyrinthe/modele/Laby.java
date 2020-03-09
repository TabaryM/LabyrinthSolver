package labyrinthe.modele;

import java.util.ArrayList;

public class Laby {

    private Point entree;
    private Point sortie;
    private Point dimension;

    private ArrayList<ArrayList<Point>> carte;

    /**
     *
     * @param dimension
     */
    public Laby(Point dimension){
        carte = new ArrayList<>();
        this.dimension = dimension;
        for(int i = 0; i < dimension.getY(); i++){
            carte.add(new ArrayList<>());
        }
        generateLaby();
    }

    /**
     *
     * @return
     */
    public Point getEntree() {
        return entree;
    }

    /**
     *
     * @return
     */
    public Point getSortie() {
        return sortie;
    }

    /**
     *
     * @param entree
     */
    public void setEntree(Point entree) {
        this.entree = entree;
    }

    /**
     *
     * @param sortie
     */
    public void setSortie(Point sortie) {
        this.sortie = sortie;
    }

    /**
     *
     * @return
     */
    public Point getDimension() {
        return dimension;
    }

    /**
     *
     * @param dimension
     */
    public void setDimension(Point dimension) {
        this.dimension = dimension;
    }

    /**
     * Genere un labyrinthe moche
     */
    private void generateLaby(){
        for(int i = 0; i < dimension.getY(); i++) {
            for (int j = 0; j < dimension.getX(); j++) {
                if (Math.random() > 0.666) {
                    carte.get(j).add(new Mur(j, i));
                }else{
                    carte.get(j).add(new Murnt(j, i));
                }
            }
        }
    }

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
