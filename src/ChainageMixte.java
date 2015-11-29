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
		 
		 bf.readFromFile("C:\\Users\\Abassi\\Desktop\\IA.txt");
	}
	
    
    public Boolean terminal(entite fait)
    {
        gauche = new Vector<entite>();
        for(int i=0;i<bf.getBregle().size();i++)
            gauche.addAll(bf.getBregle().get(i).getPremisse());
        if(gauche.contains(fait) || bf.getBfait().contains(fait))
            return false;
        return true;
        
    }
    
    
    
    
    
   public  Boolean observable(entite fait)
   {
        Vector<entite> conclusions = new Vector<entite>();
        for(int i=0;i<bf.getBregle().size();i++)
            conclusions.addAll(bf.getBregle().get(i).getConclusion());
        if(conclusions.contains(fait) || bf.getBfait().contains(fait))
        {
            System.out.println(fait.getNom()+" non observable");
            return false;
        }
            System.out.println(fait.getNom()+"  observable");
        return true;
   }
       
 
   
   //Methode Principale du chainage Mixte
   public void chainageMixte()
   {
            entite obs=null;
       ChainageAvant chainageAvant = new ChainageAvant();
        chainageAvant.chainageAvantAvecConflit("0");
        
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
                       
                   }
                }
                if(observable==1)
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
                       chainageAvant.setBaseSature(false);
                       chainageAvant.chainageAvantAvecConflit("0");
                       
                   }
                   
                }else
                 i++;
                observable=0;
                
            }
            
            
        }
            
            
       
   }
    
}
