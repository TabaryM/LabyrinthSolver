package labyrinthe.modele.graphe;

import java.util.HashMap;
import java.util.Iterator;

public abstract class Graphe implements Iterable<Voisins> {
    private HashMap<Noeud, Voisins> listeAdjacence;

    public Graphe(){
        listeAdjacence = new HashMap<>();
    }

    public void addNoeud(Noeud n, Voisins v){
        listeAdjacence.put(n, v);
    }

    public void removeNoeud(Noeud n){
        listeAdjacence.remove(n);
    }

    public void addSucceseur(Noeud n, Noeud successeur){
        listeAdjacence.get(n).ajouterSuccesseur(successeur);
    }

    public void removeSuccesseur(Noeud n, Noeud successeur){
        listeAdjacence.get(n).retirerSuccesseur(successeur);
    }

    public Iterator<Noeud> getSuccesseur(Noeud n){
        return listeAdjacence.get(n).iterator();
    }

    @Override
    public Iterator<Voisins> iterator() {
        return listeAdjacence.values().iterator();
    }
}
