public class entite {
    
	

	//
    private String nom ;
    private boolean valeur ;
    
    
    public entite(String k , boolean bl) {
        this.nom = k ;
        this.valeur = bl ;
    }
    
    public String getNom(){ return this.nom ; }
    public void setNom(String nom) {this.nom = nom ;}
    public boolean getValeur() {return this.valeur ;}
    public void setValeur(boolean valeur) { this.valeur= valeur ; }
    //Value Of not this
    public entite not(){
       
      String  name = this.nom  ;
      boolean val = !(this.valeur) ;
      entite k = new entite(name , val) ;
      return k; 
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + (valeur ? 1231 : 1237);
		return result;
	}

	public boolean egal(entite e){
		if((this.valeur == e.valeur)&&(this.nom.equalsIgnoreCase(e.nom))) {
			return true ;
		}
		else {
			return false ;
		}
		         
	}
    
    
 
    
}