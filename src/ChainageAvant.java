import java.util.Scanner;
import java.util.Vector;


public class ChainageAvant {

	private Vector<entite> BF  ;
	private Vector<Regle>  BR ;
	private entite BUT ;
	private entite RegleDeclenche ;
	private int regleAdeclencher ;
	Vector<Integer> indexDeclenchable = new Vector<>();
	private Vector<Regle> Declenched = new Vector<Regle>()  ;
	private String method ;
	
	
	
	
	public Vector<entite> getBF() {
		return BF;
	}

	public void setBF(Vector<entite> bF) {
		BF = bF;
	}

	public Vector<Regle> getBR() {
		return BR;
	}

	public void setBR(Vector<Regle> bR) {
		BR = bR;
	}

	public entite getBUT() {
		return BUT;
	}

	public void setBUT(entite bUT) {
		BUT = bUT;
	}

	public entite getRegleDeclenche() {
		return RegleDeclenche;
	}

	public void setRegleDeclenche(entite regleDeclenche) {
		RegleDeclenche = regleDeclenche;
	}

	//Constructing the  Base of Chaining 
	public ChainageAvant()  {
		BFGenerator gen = new BFGenerator() ;
		gen.readFromFile("C:\\Users\\Abassi\\Desktop\\IA.txt");
		this.setBF(gen.getBfait());
		
		BR = gen.getBregle() ;
		BUT = gen.getBut() ;
			
	}
	
	//check if but exist dans BF 
	public boolean checkBut() {
		boolean trouve = false ;
		for(int i=0 ; i < BF.size(); i++) {
			//System.out.println("Test BF n "+i);
			//System.out.println("le But " +BUT.getNom()+ " "+BUT.getValeur() );
			//System.out.println("Val Tester "+BF.get(i).getNom()+ " "+ BF.get(i).getValeur() );
			//System.out.println("Test :" +BUT.egal(BF.get(i)));
			if(BUT.egal(BF.get(i))){
				trouve = true ;
			}
		}
		return trouve ;
	}
	
	
	//Choix Regle Declenchable 
	public Vector<Regle> choixRegleDeclenchable() {
		   Vector<Regle> declenchable = new Vector<Regle>() ;
		//choisir les Regle Declenchable 
		   System.out.println(BR.size());
		for(int i=0 ; i < BR.size(); i++){
			if(this.regleDeclenchable(BR.get(i))){
				//System.out.println("Regle Declenchable added "+i);
				declenchable.add(BR.get(i)) ;
				indexDeclenchable.add(i);
				
			}     		
		}
		
		return declenchable ;
		
	}
	
	//Cas de premiere regle declenchable
	public Regle choixRegle(Vector<Regle> R , String method){
		
		  if(method.equalsIgnoreCase(method)){
			  Regle  k = R.get(0);
			  return k ;
		  }
		if(method.equalsIgnoreCase(method)) {
		Regle k  ; 
		k = R.get(0);
		int indice = indexDeclenchable.get(0) ;
		regleAdeclencher = indice ;		
		indexDeclenchable.removeAllElements();
		return k ;
		}
		
		else return null ;
	}
	
	
	public void ChainageAvantAvecConflit() {
		boolean butAtteint= false ;  
		       System.out.println("Donner Le Type de resolution de conflit");
		       Scanner sc = new Scanner(System.in);
		       method = sc.nextLine() ;
		       
		
		   while(!butAtteint && !BF.isEmpty()) {
			   Vector<Regle>  rg =  this.choixRegleDeclenchable() ;
				if(rg.isEmpty()) {
					System.out.println();
				}
			  for(int k = 0 ; k < indexDeclenchable.size();k++){
		        	System.out.println("Regle Declenchable "+k );
		        }        
		        System.out.println("----------------------------");
			        Regle r = this.choixRegle(rg, method);   
			     for(int i = 0 ; i< r.getButList().size();i++ ){
			    	 BF.add(r.getButList().get(i)) ;
			     }
			   //  System.out.println("Regle Declenche"+ regleAdeclencher);
			     BR.remove(regleAdeclencher);
			     if(this.checkBut()) {
			    	 butAtteint = true ;
			    	 System.out.println("But Atteint");
			     }   
			   indexDeclenchable.removeAllElements();
			   
			     
		   }
		
		
	}
	
	
	//check Fait in Base Fait
	public boolean regleDeclenchable(Regle r) {
		boolean existeAll = false ;
		int nbrExist = 0 ;
		for(int i = 0 ; i< r.getPremisseList().size();i++){
			for(int j = 0 ; j < BF.size(); j++) {
				if(r.getPremisseList().get(i).egal(BF.get(j))) {
					nbrExist++ ;
					
				}
			}
		}
		
		if(nbrExist== r.getPremisseList().size()){
			existeAll = true ;
		}
		
		return existeAll ;
		
	}
	
	
	//public void But 
	public void chainageAvantSansConflit(){
		boolean butAtteint = false ;
		 int offset = 0 ;
		 int lastdeleted= -1 ;
		 int index ;
		while(!butAtteint && !BR.isEmpty()) {
	       for(int i=0 ; i< BR.size();i++) {
	    	   if(regleDeclenchable(BR.get(i))) {
	    		   Declenched.add(BR.get(i)) ;
	    		   //ajout des Resultat a la Base de Fait
	    		   for(int j=0 ; j < BR.get(i).getButList().size();j++){
	    			   if(i < lastdeleted) {
	    				   index = i ;
	    			   } else {
	    				   index = offset +i ;
	    			   }
	    			   System.out.println("Regle Declenche : "+ index);
	    			   BF.add(BR.get(i).getButList().get(j)) ;
	    		   }
	    		BR.remove(i) ; 
	    		offset++ ; 
	    		lastdeleted = i ;
	    		
	    		if(this.checkBut()){
	    			butAtteint = true ;
	    			System.out.println("But Trouvé");
	    		}
	    		
	    	   }   
	       } 	
		}
		
		if(!this.checkBut()){
			System.out.println("But Non Trouvé");
		}
		
		
	}
	
	
	
	
	

}
