import java.util.Scanner;
import java.util.Vector;


public class ChainageAvant {

	private Vector<Fait> BF  ;
	private Vector<Regle>  BR ;
	private entite BUT ;
	private entite RegleDeclenche ;
	private int regleAdeclencher ;
	Vector<Integer> indexDeclenchable = new Vector<>();
	private Vector<Regle> Declenched = new Vector<Regle>()  ;
	private String method ;
	private Boolean butAtteind = false;
    private Boolean baseSature = false;
	
	
	
	
	public Vector<Fait> getBF() {
		return BF;
	}

	public void setBF(Vector<Fait> bF) {
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
		System.out.println("Longueur BF : "+BF.size());
		BR = gen.getBregle() ;
		System.out.println("Longueur BR : "+BR.size());
		BUT = gen.getBut() ;
		System.out.println("But is : "+BUT.getNom());
		
			
	}
	
	//check if but exist dans BF 
	public boolean checkBut() {
		boolean trouve = false ;
		for(int i=0 ; i < BF.size(); i++) {
	
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
	public Regle choixRegle(Vector<Regle> declanchable ,String methode)
    {
       
        if(!baseSature)
        {
           
            if(methode.equals("0"))
        {
            
            return declanchable.get(0);
            
        }else if(methode.equals("1"))
                {
                    int numRegleDeclanchable=0;
                    int nbrPremisses=0;
                    for(int i=0;i<declanchable.size();i++)
                    {
                        if(declanchable.get(i).getPremisseList().size()>nbrPremisses)
                        {
                            numRegleDeclanchable=i;
                            nbrPremisses=declanchable.get(i).getPremisseList().size();
                        }
                    }
                    
                    return declanchable.get(numRegleDeclanchable);
                }
        
         
        }
        return null;
    }
	
	
	public Vector<Regle> filtrage(Vector<Regle> Bregles)
	{
	    Vector<Regle> declanchable = new Vector<Regle>();
	    for(int i=0; i<Bregles.size();i++)
	    {
	        if(regleDeclenchable(Bregles.elementAt(i)))
	            declanchable.add(Bregles.elementAt(i));
	    }
	    if (declanchable.size()==0)
	    {
	         System.out.println("la base des faits est saturée ");
	         baseSature=true;
	   // System.exit(-1);mdif pour faire un chainage mixte
	    }
	       
	    return declanchable;
	}
	
	
	public boolean notExistDansBfaits(entite conclusion)
	{
	    entite e = new entite(conclusion.getNom(),!conclusion.getValeur());
	    if(BF.contains(e))
	        return true;
	    return false;
	}
	
	
	
	public void declancherRegle(Regle regle)
	{
	    if(regle!=null)
	    {
	        for(int i=0;i<regle.getConclusion().size();i++)
	    {
	        if(!notExistDansBfaits(regle.getButList().get(i)))
	        {
	            BF.add(new Fait(regle.getConclusion().elementAt(i).getNom(), regle.getConclusion().elementAt(i).getValeur(),regle.getNum()));
	            System.out.println("fait ajouté a la base des faits: "+new Fait(regle.getConclusion().elementAt(i).getNom(),
	                    regle.getConclusion().elementAt(i).getValeur(),regle.getNum()).toString());
	        }
	        
	        
	        else 
	        {
	            System.out.println("erreur:X et !X ne peuvent pas etre dans la meme base de faits");
	        System.exit(-1);
	        }
	    }
	    BR.remove(regle);
	    
	    
	    }
	    
	}
	
	
	public void chainageAvantAvecConflit(String methode)
    {
        
        Vector<Regle> reglesDeclanchables = new Vector<Regle>();
       
        Regle RegleChoisi;
        if (BUT==null)
        {  
            while(BR.size()>0&& !baseSature)
            {
                
                reglesDeclanchables=filtrage(BR);
                RegleChoisi=choixRegle(reglesDeclanchables,methode);
                System.out.println("la regle a declancher est: "+RegleChoisi.toString());
                declancherRegle(RegleChoisi);
            }
        }else 
            
        {
            while(BR.size()>0 && !butAtteind && !baseSature)
               {
                if(checkBut())
                {      System.out.println("Here");
                    System.out.println("le but "+BUT.toString()+" appartient a la base des faits");
                    butAtteind=true;
                    
                }
                reglesDeclanchables=filtrage(BR);
                if(!baseSature)
                {
                    RegleChoisi=choixRegle(reglesDeclanchables,methode);
             
                        System.out.println("la regle a declancher est: "+RegleChoisi.toString());
                       declancherRegle(RegleChoisi);
                       
                       
                       if(checkBut())
                       {
                           System.out.println("le but est atteind");
                           butAtteind=true;
                       }
                }
                

                
              
            }
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
	    			  // BF.add(BR.get(i).getButList().get(j)) ;
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
