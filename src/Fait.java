
public class Fait extends entite {
	
	private int RegleDeclencheur ;
	
	
     //Constructs 
	public Fait(String k, boolean bl ,int RegleDeclencheur) {
		super(k, bl);
		this.RegleDeclencheur = RegleDeclencheur ;
	}


	public int getRegleDeclencheur() {
		return RegleDeclencheur;
	}


	public void setRegleDeclencheur(int regleDeclencheur) {
		RegleDeclencheur = regleDeclencheur;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fait [RegleDeclencheur=" + RegleDeclencheur + ", getNom()="
				+ getNom() + ", getValeur()=" + getValeur() + "]";
	}
	
	
	
	
	

}
