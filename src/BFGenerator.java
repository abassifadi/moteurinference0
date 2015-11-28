import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;


public class BFGenerator {
	
	private Vector<Regle> Bregle = new Vector<Regle>()  ;
	private Vector<Fait> Bfait = new Vector<Fait>() ; 
	private entite butGen ;
	
	
	
	public Vector<Regle> getBregle() {
		return Bregle;
	}



	public void setBregle(Vector<Regle> bregle) {
		Bregle = bregle;
	}








	public Vector<Fait> getBfait() {
		return Bfait;
	}








	public void setBfait(Vector<Fait> bfait) {
		Bfait = bfait;
	}








	public entite getBut() {
		return butGen;
	}








	public void setBut(entite but) {
		this.butGen = but;
	}








	public  void readFromFile(String s) {
		
		try {
			InputStream ips=new FileInputStream(s);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			//is Reading Fait 
			boolean isfait = false ;
			boolean isregle = false ;
			boolean isBut = false ;
			boolean isButGen = false ;
			boolean isfalse = false ;
			boolean isPremisse = false ;
			boolean isButPremisse = false ;
			Vector<entite> premisse = new Vector<entite>() ;
			Vector<entite> but = new Vector<entite>() ;
			while ((ligne=br.readLine())!=null){
				
				if(ligne.equalsIgnoreCase("REGLE")) {
					isButGen = false ;
					isregle = true ;
					isfait = false ; 
					isPremisse = true ;
					System.out.println("Ready To read Regle");
					
				}
				
				else if(ligne.equalsIgnoreCase("FAIT")) {
					isfait= true ;
					isButGen = false ;
					isregle = false ;
					//System.out.println("Reading Fait");
				}
				
				else if(ligne.equalsIgnoreCase("BUT")) {
					isfait= false ;
					isButGen = true ;
					isregle = false ;
					//System.out.println("Reading But") ;
				}
				else {
					
					if(isregle) {
						
						//System.out.println("Reading Regle");
						isPremisse = true ;
						isBut = false ;
					Vector<entite> premisses = new Vector<entite>(); 
					Vector<entite> buts = new Vector<entite>();
					String[] str = ligne.split(" ");
					String numString = str[0] ;
					int numRegle = Integer.parseInt(numString);
					for(int i = 1; i< str.length ; i++) {
				     if(str[i].equalsIgnoreCase("Not")) {
				    	 isfalse= true ;
				    	 //System.out.println("Reading False Premisses");
				     }
				     
				     //test if Start Reading But
				     else if(str[i].equalsIgnoreCase("ALORS")) {
				    	 isPremisse = false ;
				    	 isBut = true ;
				     }
				     
					//Adding False Premisse	
				     else if(isPremisse && isfalse){
					   entite a = new entite(str[i],false) ; 
					   premisses.add(a);
					   isfalse = false ;
					   //System.out.println("Added False Premisse");
					}
				     
				     //Added True Premisse
				     else if(isPremisse && !isfalse) {
						isfalse = false ;
						entite a = new entite(str[i], true);
						premisses.add(a);
						//System.out.println("Added True Regle");
					}
				     
				     else if(isBut && isfalse){
				    	 entite a = new entite(str[i], false);
				    	 isfalse = false ;
				    	 buts.add(a);
				    	 //System.out.println("Added But False");
				     }
				     else if(isBut && !isfalse) {
				    	 isfalse = false ;
				    	 entite b = new entite(str[i], true);
				    	 buts.add(b) ;
				    	 //System.out.println("Added But true");	 
				     }
				   
				     
				     
					
					}
					
					Regle r = new Regle(numRegle,premisses , buts); 
					Bregle.add(r) ;
					
					}
					
					if(isfait){
						Fait fait ;
						String[] str = ligne.split(" ");
						boolean isfalseFait = false ;
						for(int i=0 ; i< str.length ; i++) {
							
							if(str[i].equalsIgnoreCase("Not")) {
							  isfalseFait = true ;	
							}
							else if (isfait && isfalseFait) {
								 fait = new Fait(str[i],false ,-1);
								 isfalseFait = false ;
								 Bfait.add(fait);
								 //System.out.println("Added false Fait");
							}
							else if (isfait && !isfalseFait){
								fait = new Fait(str[i], true, -1) ;
								Bfait.add(fait);
								 //System.out.println("Added true Fait");
								
							}
							
							
							
							
						}
					}
					
					if(isButGen){
						entite a ;
						boolean isButFalse = false ;
						String[] str = ligne.split(" ");
	                     for(int i=0 ; i< str.length ; i++) {
							
							if(str[i].equalsIgnoreCase("Not")) {
								isButFalse = true ;	
							}
							else if (isButGen && isButFalse) {
								 butGen = new entite(str[i],false);
								 isButFalse = false ;
								 //System.out.println("Added false But");
							}
							else if (isButGen && !isButFalse){
								butGen = new entite(str[i], true) ;
								// System.out.println("Added true But");
								
							}
							
							
							
							
						}
						
					}
					
					
					
					
				}
				
				
				
				
	                
			}
			br.close(); 

			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		
		
		
	}
	

}
