package question1;


public class Contributeur extends Cotisant{
  private int solde;
  
  public Contributeur(String nom, int somme){
    super(nom);
    if (somme <0) throw new RuntimeException("nombre Negatif !!");
    this.solde =somme;
  }
  
  public int solde(){
    return this.solde;
  }
  
  public int nombreDeCotisants(){
    return 1;
  }
	public void debit(int somme) throws SoldeDebiteurException{
	   if (somme < 0) throw new RuntimeException("nombre négatif !!!");
        int soldeTemp = solde - somme;
        if (soldeTemp < 0) throw new SoldeDebiteurException();
        solde = soldeTemp;
	}
	
	/**
	 * throws RuntimeException new RuntimeException("nombre nÃ©gatif !!!");
	 */
  public  void credit(int somme){
	if (somme < 0) throw new RuntimeException("nombre négatif !!!");
        solde += somme;
	}
	
	/**
	 * throws RuntimeException new RuntimeException("nombre nÃ©gatif !!!");
	 */
  public void affecterSolde(int somme){
     if(somme <0) throw new RuntimeException("nombre nÃ©gatif !!!");
     solde = somme;
  }
  
  public <T> T accepter(Visiteur<T> visiteur){
    return visiteur.visite(this);
  }
  
  public String toString(){
    return "<Contributeur : " + nom + "," + solde + ">";
  }

}
