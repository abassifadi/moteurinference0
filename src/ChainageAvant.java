import java.awt.Component;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JLabel;


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
    private int methodRegle ;  //Choix De Regle 
    private Chainage ch ;
    private boolean withInterface= false ;	
    
    
	
	/**
	 * @return the ch
	 */
	public Chainage getCh() {
		return ch;
	}




	/**
	 * @param ch the ch to set
	 */
	public void setCh(Chainage ch) {
		this.ch = ch;
	}




	public void NotifyUser(String s) {
		this.ch.getList().append(s);
		System.out.println("Notified....");
	}
	 
	
	
	
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

	
	
	
	
	/**
	 * @return the butAtteind
	 */
	public Boolean getButAtteind() {
		return butAtteind;
	}

	/**
	 * @param butAtteind the butAtteind to set
	 */
	public void setButAtteind(Boolean butAtteind) {
		this.butAtteind = butAtteind;
	}

	/**
	 * @return the baseSature
	 */
	public Boolean getBaseSature() {
		return baseSature;
	}

	/**
	 * @param baseSature the baseSature to set
	 */
	public void setBaseSature(Boolean baseSature) {
		this.baseSature = baseSature;
	}

	//Constructing the  Base of Chaining 
	public ChainageAvant(Chainage ch)  {
		BFGenerator gen = new BFGenerator() ;
		gen.readFromFile("C:\\Users\\Abassi\\Desktop\\IB.txt");
		this.setBF(gen.getBfait());
		this.ch = ch ;
		this.withInterface = true ;
		BR = gen.getBregle() ;
		BUT = gen.getBut() ;
	    this.NotifyUser("Started....");
	    System.out.println("Size of BR Before :" + BR.size());
	  		
	}
	public ChainageAvant()  {
		BFGenerator gen = new BFGenerator() ;
		gen.readFromFile("C:\\Users\\Abassi\\Desktop\\IB.txt");
		this.setBF(gen.getBfait());
		BR = gen.getBregle() ;
		BUT = gen.getBut() ;
	
	    
	    //UserInteraction i = new UserInteraction() ;
	   // i.ChoixRegleChainageAvant();
				
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
           
            if(methode.equals("Prem"))
        {
            System.out.println("Premiere Regle Choisie");
            return declanchable.get(0);
            
        }else if(methode.equals("Plus"))
                {    
        	
        	 System.out.println("Plus de Premisse Regle Choisie");
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
	         if(this.withInterface){
	         this.NotifyUser("Base des Fait Saturee\r\n"); }

	    }
	       
	    return declanchable;
	}
	
	
	public boolean notExistDansBfaits(entite conclusion)
	{   boolean exist = false ;
	    entite e = new entite(conclusion.getNom(),!conclusion.getValeur());
	    for(int i=0 ; i< BF.size();i++) {
	    	if(BF.elementAt(i).getNom().equalsIgnoreCase(conclusion.getNom())){
	    		exist = true ;
	    	}
	    }
	       
	    return exist;
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
	           if(this.withInterface){
	            this.NotifyUser("Fait Ajoute a la Base Des Fait :  "+regle.getConclusion().elementAt(i).getNom()+"\r\n"); }
	        }
	        
	        
	        else 
	        {
	            System.out.println("Element Existant");
	        //System.exit(-1);
	        }
	    }
	    BR.remove(regle);
	    
	    
	    }
	    
	}
	
	
	public void chainageAvantAvecConflit(String methode)
    {
		if(this.withInterface){
		this.NotifyUser("\r\nChaiange Avenc Conflit\r\n"); }
		System.out.println("Started Chaiange Avenc Conflit");
        Vector<Regle> reglesDeclanchables = new Vector<Regle>();
       
        Regle RegleChoisi;
        if (BUT==null)
        {   if(this.withInterface){  
        	this.NotifyUser("\r\nLe But Est Null"); }
        if(this.withInterface){
        this.NotifyUser("\r\nSaturation de la Base Des Fait\r\n"); }
        System.out.println("Taille de BR : "+BR.size());   
            while(BR.size()>0&& !baseSature)
            {
                
                reglesDeclanchables=filtrage(BR);
                RegleChoisi=choixRegle(reglesDeclanchables,methode);
               // System.out.println("la regle a declancher est: "+RegleChoisi.toString());
                if(this.withInterface){
                this.NotifyUser("la regle a declancher est: "+RegleChoisi.getNum()+"\r\n"); }
                declancherRegle(RegleChoisi);
            }
        }else 
            
        {
            while(BR.size()>0 && !butAtteind && !baseSature)
               {
                if(checkBut())
                {      System.out.println("Here");
                    System.out.println("le but "+BUT.toString()+" appartient a la base des faits");
                    if(this.withInterface){
                    this.NotifyUser("Le But Appartient a La Base des Fait\r\n");}
                    butAtteind=true;
                    
                }
                reglesDeclanchables=filtrage(BR);
                 if(!baseSature && !checkBut())
                {
                    RegleChoisi=choixRegle(reglesDeclanchables,methode);
             
                        //System.out.println("la regle a declancher est: "+RegleChoisi.getNum());
                        if(this.withInterface){
                        this.NotifyUser("La Regle Choisie Est : "+RegleChoisi.getNum()+" \r\n"); }
                        declancherRegle(RegleChoisi);
                       
                       
                       if(checkBut())
                       {
                           System.out.println("le but est atteind");
                          if(this.withInterface){
                           this.NotifyUser("But Atteint"); }
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
