
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class InterFace extends JFrame {
      
	private JPanel pan = new JPanel();
	  private JButton bouton = new JButton("Mon bouton");

	
	public InterFace() {
		  this.setTitle("Ma première fenêtre Java");
		    this.setSize(800, 800);
		    this.setLocationRelativeTo(null); 
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		    this.setVisible(true); 
		    
		    //Creating The Content File
		    JPanel b1 = new JPanel();
		    //On définit le layout en lui indiquant qu'il travaillera en ligne
		    
		    b1.setPreferredSize(new Dimension(500, 200));
		    JLabel jl = new JLabel() ;
            jl.setText("Regle");
            b1.add(jl) ;
            JTextArea regle = new JTextArea();
            regle.setPreferredSize(new Dimension(600,600));
		    b1.add(regle);
		  b1.setLayout(new BoxLayout(b1, BoxLayout.PAGE_AXIS)); 
		   
		    JPanel b4 = new JPanel();
		    //On positionne maintenant ces trois lignes en colonne
		    b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
		    b4.setPreferredSize(new Dimension(380,480)) ;
		    b4.add(b1);
		  
				
		    this.getContentPane().add(b4);
	}
	
}
