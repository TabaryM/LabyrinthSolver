package labyrinthe.modele.graphe;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Voisins implements Iterable<Noeud>{
    private ArrayList<Noeud> successeurs;

    public Voisins(){
        successeurs = new ArrayList<>();
    }

    public void ajouterSuccesseur(Noeud successeur){
        successeurs.add(successeur);
    }

    public void retirerSuccesseur(Noeud successeur){
        successeurs.remove(successeur);
    }

    @Override
    public Iterator<Noeud> iterator() {
        return successeurs.iterator();
    }
}
