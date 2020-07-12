package question2;

import question1.*;
import java.util.stream.*;

public class CompositeValide implements Visiteur<Boolean>{
  // Le solde de chaque contributeur doit être supérieur ou égal à 0 
  // et il n’existe pas de groupe n’ayant pas de contributeurs.
  
  public Boolean visite(Contributeur c){
     return c.solde() >= 0;
  }
  
  public Boolean visite(GroupeDeContributeurs g){
    boolean res = g.getChildren().size() > 0;
    if(res){
     for ( Cotisant c : g) {
                res &= c.accepter(this);
                if (!res) break; // Si au moins un des cotisant n'est pas valide on arrête la boucle
            } 
    }
    return res ;
  }
}