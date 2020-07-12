package question1;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.*;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>{
  private List<Cotisant> liste;
  
  public GroupeDeContributeurs(String nomDuGroupe){
    super(nomDuGroupe);
     this.liste = new ArrayList<Cotisant>();
  }
  
  public void ajouter(Cotisant cotisant){
     if (cotisant == null) throw new IllegalArgumentException();
        liste.add(cotisant);
        cotisant.setParent(this);
  }
  
  
  public int nombreDeCotisants(){
       return liste.stream().map(c -> c instanceof GroupeDeContributeurs ? ((GroupeDeContributeurs)c).nombreDeCotisants() : 1).collect(Collectors.summingInt(t -> t.intValue()));
  }
  
  public String toString(){
    StringBuffer buffer = new StringBuffer();
        buffer.append("<GroupeDeContributeurs>\n");
        for (Cotisant cotisant : liste) {
            buffer.append(cotisant.toString() + "\n");
        }
        buffer.append("</GroupeDeContributeurs>");
        return buffer.toString();
  }
  
  public List<Cotisant> getChildren(){
    return new ArrayList<>(liste);
  }
  
  public void debit(int somme) throws SoldeDebiteurException{
      for (Cotisant cotisant : liste) {
            cotisant.debit(somme);
        }
  }
  
  public void credit(int somme){
    for (Cotisant cotisant : liste) {
            cotisant.credit(somme);
        }
  }
  
  public int solde(){
   return liste.stream().map(c -> c.solde()).collect(Collectors.summingInt(t -> t.intValue()));}
  
  // méthodes fournies
  
 public Iterator<Cotisant> iterator(){
    return new GroupeIterator(liste.iterator());
  }
  
  private class GroupeIterator implements Iterator<Cotisant>{
    private Stack<Iterator<Cotisant>> stk;
    
    public GroupeIterator(Iterator<Cotisant> iterator){
      this.stk = new Stack<Iterator<Cotisant>>();
      this.stk.push(iterator);
    }
    
    public boolean hasNext(){
      if(stk.empty()){
        return false;
      }else{
         Iterator<Cotisant> iterator = stk.peek();
         if( !iterator.hasNext()){
           stk.pop();
           return hasNext();
         }else{
           return true;
         }
      }
    }
    
    public Cotisant next(){
      if(hasNext()){
        Iterator<Cotisant> iterator = stk.peek();
        Cotisant cpt = iterator.next();
        if(cpt instanceof GroupeDeContributeurs){
          GroupeDeContributeurs gr = (GroupeDeContributeurs)cpt;
          stk.push(gr.liste.iterator());
        }
        return cpt;
      }else{
        throw new NoSuchElementException();
      }
    }
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
  

  public <T> T accepter(Visiteur<T> visiteur){
    return visiteur.visite(this);
  }
  

}