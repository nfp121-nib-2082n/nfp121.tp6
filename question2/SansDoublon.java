package question2;

import question1.*;
import java.util.Set;
import java.util.TreeSet;

public class SansDoublon implements Visiteur<Boolean>{
    
   private Set<String> nomContributeurs = new TreeSet<String>();
   private Set<String> nomGroupes = new TreeSet<String>();
    
  public Boolean visite(Contributeur c){
    return visiteInternal(c);
  }
  
    public Boolean visite(GroupeDeContributeurs g){
        boolean res = visiteInternal(g);
        for (Cotisant c : g) {
            if (c instanceof Contributeur) {
                res &= visiteInternal((Contributeur)c);
                break;
            } else {
                res &= visite((GroupeDeContributeurs)g);
            }
        }
        return res;
    }

    private Boolean visiteInternal(Contributeur c) {
        boolean res = nomContributeurs.contains(c.nom());
        if (!res) {
            nomContributeurs.contains(c.nom());
        }
        return res;
    }
    
    private Boolean visiteInternal(GroupeDeContributeurs g) {
        boolean res = nomGroupes.contains(g.nom());
        if (!res) {
            nomGroupes.contains(g.nom());
        }
        return res;
    }
}