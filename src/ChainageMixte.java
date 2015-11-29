import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author Abassi
 */
public class ChainageMixte {
    
	
	private Vector<entite> gauche ;
	
	
	
	
    
    public Boolean terminal(entite fait)
    {
        BFGenerator mapper = new BFGenerator();
        mapper.readFromFile("C:\\Users\\Abassi\\Desktop\\IA.txt");
        gauche = new Vector<entite>();
        for(int i=0;i<mapper.getBregle().size();i++)
            gauche.addAll(mapper.getBregle().get(i).getPremisse());
        if(gauche.contains(fait) || mapper.getBfait().contains(fait))
            return false;
        return true;
        
    }
    
    
    
    
    
   public  Boolean observable(entite fait)
   {
        BFGenerator mapper = new BFGenerator();
        mapper.readFromFile("C:\\Users\\Abassi\\Desktop\\IA.txt");
        Vector<entite> conclusions = new Vector<entite>();
        for(int i=0;i<mapper.getBregle().size();i++)
            conclusions.addAll(mapper.getBregle().get(i).getConclusion());
        if(conclusions.contains(fait) || mapper.getBfait().contains(fait))
        {
            System.out.println(fait.getNom()+" non observable");
            return false;
        }
            System.out.println(fait.getNom()+"  observable");
        return true;
   }
       
   public Boolean poserQuestion(entite observable)
   {
       if (observable.getValeur()==false)
       System.out.println("est ce que not "+observable.getNom()+" ?(repondez par oui,non ou je ne sais pas )" );
       else 
            System.out.println("est ce que "+observable.getNom()+" ?(repondez par oui,non ou je ne sais pas )" );
        Scanner sc = new Scanner(System.in);
        String reponse  = sc.nextLine() ;
        if (reponse.equalsIgnoreCase("oui"))
            return true;
       return false;
   }
   public void chainageMixte()
   {
            entite obs=null;
       ChainageAvant chainageAvant = new ChainageAvant();
        chainageAvant.chainageAvantAvecConflit("0");
        //chercher les regles ayant une seul premisse observable
        
        if(!chainageAvant.getButAtteind()&& chainageAvant.getBR().size()>0 && chainageAvant.getBUT()!=null)
        {
            int i=0;
            while(!chainageAvant.getButAtteind()&& chainageAvant.getBR().size()>0 && i<chainageAvant.getBR().size())
                
            {
                System.out.println("nbr de regles non declanchés "+chainageAvant.getBR().size() );
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
                     if (obs.getValeur()==false)
                              System.out.println("est ce que not "+obs.getNom()+" ?(repondez par oui,non ou je ne sais pas )" );
                      else 
                              System.out.println("est ce que "+obs.getNom()+" ?(repondez par oui,non ou je ne sais pas )" );
                        Scanner sc = new Scanner(System.in);
                        String reponse  = sc.nextLine() ;
                   
                   if(reponse.equalsIgnoreCase("oui"))
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
