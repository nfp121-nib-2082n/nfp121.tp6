package question2;

import question1.*;

public class DebitMaximal implements Visiteur<Integer>{

  public Integer visite(Contributeur c){
    return c.solde();
  }
  
  public Integer visite(GroupeDeContributeurs g){
        int debit = Integer.MAX_VALUE;
        for (Cotisant c : g) {
            int d = c.accepter(this);
            if (d < debit) debit = d;
        }
        return debit;
  }
}