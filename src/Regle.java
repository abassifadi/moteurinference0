import java.util.Vector;

public class Regle {
    
	
	//Number Of Rule
    private int num ;
    // List of Facts
    private Vector<entite> premisse ;
    //List Of Goals
    private Vector<entite> conclusion ;
    
    
    
    
    
    
    public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Regle(){
       premisse = new Vector<entite>() ;
       conclusion = new Vector<entite>() ;
    }
    
    public Regle(int num ,Vector<entite> premisse , Vector<entite> conclusion){
        this.num = num ; 
    	this.premisse = premisse ;
        this.conclusion = conclusion ;
     }
    
    /**
	 * @return the premisse
	 */
	public Vector<entite> getPremisse() {
		return premisse;
	}

	/**
	 * @param premisse the premisse to set
	 */
	public void setPremisse(Vector<entite> premisse) {
		this.premisse = premisse;
	}

	/**
	 * @return the conclusion
	 */
	public Vector<entite> getConclusion() {
		return conclusion;
	}

	/**
	 * @param conclusion the conclusion to set
	 */
	public void setConclusion(Vector<entite> conclusion) {
		this.conclusion = conclusion;
	}

	//Ajouter une Regle 
    public void ajouterPremisse(entite premisse){
        this.premisse.add(premisse);
    }
    
    //ajouter conclusion
    public void AjouterConclusion(entite but){
        this.conclusion.add(but);
    }
    
    //obtenir liste des premisse
    public Vector<entite> getPremisseList(){
        return this.premisse ;
    }
    
    //obtenir liste de But 
    public Vector<entite> getButList(){
        return this.conclusion; 
    }
    
    //set premisse Liste
    public void setListPremisse(Vector<entite> v) {
            for(int i=0 ; i< v.size() ; i++) {
                   this.ajouterPremisse(v.get(i));
            }
    }
    
    public void setListeConclusion(Vector<entite> c ){
        
        for(int i=0 ; i< c.size();i++) {
        this.AjouterConclusion(c.get(i));
        }
    }
    
    
    @Override
    public String toString() {
        return "Regle{" + "num=" + num + ", premisses=" + premisse + ", conclusions=" + conclusion + '}';
    }
    
    
    
   
}

