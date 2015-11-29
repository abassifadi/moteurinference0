
import java.util.Scanner;


public class UserInteraction {

	public int poserTrueQuestion(entite t) {
		System.out.println("est ce que "+t.getNom()+" ?(repondez par oui,non ou je ne sais pas )" );
        Scanner sc = new Scanner(System.in);
        String reponse  = sc.nextLine() ;
   		
        if(reponse.equalsIgnoreCase("oui")) {
        	return 1 ;
        }
        else if (reponse.equalsIgnoreCase("non")  ) {
        	
        	return 2 ;
        }
        
	     	
        else {
		return 0; }
		
	}
	
	
	public int poserFalseQuestion(entite t) {
		System.out.println("est ce que not  "+t.getNom()+" ?(repondez par oui,non ou je ne sais pas )" );
        Scanner sc = new Scanner(System.in);
        String reponse  = sc.nextLine() ;
   		
        if(reponse.equalsIgnoreCase("oui")) {
        	return 1 ;
        }
        else if (reponse.equalsIgnoreCase("non")  ) {
        	
        	return 2 ;
        }
        
	     	
        else {
		return 0; }
		
	}
	
	
	public void ChoixRegleChainageAvant() {
		
		System.out.println("Voulez Vous Choisir la Methode de Choix : \n 0 - Premiere Regle \n 1 - Ayant Le Plus De Premisse  ");
		Scanner sc= new Scanner(System.in);
		String val = sc.nextLine() ;
		System.out.println("Value Readed Is "+val);
		
	}
	
	
	
}
