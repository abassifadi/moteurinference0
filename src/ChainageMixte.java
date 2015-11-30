import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Abassi
 */
public class ChainageMixte {
    
	
	private Vector<entite> gauche ;
	private BFGenerator bf = new BFGenerator() ;
	private UserInteraction interaction = new UserInteraction() ;
	
	
	public ChainageMixte() {
		 
		 bf.readFromFile("C:\\Users\\Abassi\\Desktop\\IB.txt");
	}
	
      
    
    public boolean contenir(Vector<entite> v , entite fait) {
    	boolean result=false ;
    	for(int i=0 ; i< v.size();i++) {
    		if(v.elementAt(i).getNom().equalsIgnoreCase(fait.getNom()) ){
    			result = true ;
    		}
    	}
    	return result ;
    }
    
    
    public boolean contenirFait(Vector<Fait> v , entite fait) {
    	boolean result=false ;
    	for(int i=0 ; i< v.size();i++) {
    		if(v.elementAt(i).getNom().equalsIgnoreCase(fait.getNom()) ){
    			result = true ;
    		}
    	}
    	return result ;
    }
    
   public  Boolean observable(entite fait)
   {
        Vector<entite> conclusions = new Vector<entite>();
        for(int i=0;i<bf.getBregle().size();i++)
            conclusions.addAll(bf.getBregle().get(i).getConclusion());
        System.out.println("--------Observation------");
        System.out.println("L Conclusion : "+conclusions.size());
        System.out.println("-------------------");
        
        if(this.contenir(conclusions, fait) ||  this.contenirFait(bf.getBfait(), fait))
        {
            System.out.println(fait.getNom()+" non observable");
            return false;
        }
        else {
            System.out.println(fait.getNom()+"  observable");
        return true; }
   }
       
 
   
   //Methode Principale du chainage Mixte
   public void chainageMixte()
   {
            entite obs=null;
       ChainageAvant chainageAvant = new ChainageAvant();
        chainageAvant.chainageAvantAvecConflit("Prem");
        
        if(!chainageAvant.getButAtteind()&& chainageAvant.getBR().size()>0 && chainageAvant.getBUT()!=null)
        {
        	
        	//On Sature La BF Par chianage Avant
            int i=0;
            while(!chainageAvant.getButAtteind()&& chainageAvant.getBR().size()>0 && i<chainageAvant.getBR().size())
                
            {
                System.out.println("Nombre De Regle Declenchable "+chainageAvant.getBR().size() );
                int observable=0;
                for(int j=0;j<chainageAvant.getBR().get(i).getPremisse().size();j++)
                {
                   if(observable(chainageAvant.getBR().get(i).getPremisse().get(j)))
                   {
                       observable++;
                        obs = chainageAvant.getBR().get(i).getPremisse().get(j);
                        System.out.println("Nombre Observable : "+observable); 
                   }
                }
                if(observable>=1)
                {
                      System.out.println("la regle a declancher est "+chainageAvant.getBR().get(i).getNum() );
                       int reponse ; 
                      if (obs.getValeur()==false) {
                          reponse = interaction.poserFalseQuestion(obs);    
                              
                     }
                      else 
                       reponse = interaction.poserTrueQuestion(obs) ; 
                     
                      
                   if(reponse==1)
                   {
                       chainageAvant.getBF().add(new Fait(obs.getNom(),obs.getValeur(),-1));
                       System.out.println("Fait Ajoute "+ obs.getNom());
                       chainageAvant.setBaseSature(false);
                       chainageAvant.chainageAvantAvecConflit("Prem");
                       
                   }
                   
                }else
                 i++;
                observable=0;
                
            }
            
            
        }
            
            
       
   }
    
}
