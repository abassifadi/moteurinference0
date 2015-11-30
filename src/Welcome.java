import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Welcome implements ActionListener {

	private JFrame frame;

	JButton avant ;
	JButton mixte  ;
	JButton chainageplusPrem ;
	
	

	/**
	 * Create the application.
	 */
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMoteurInferenceOrdre = new JLabel("Moteur Inference Ordre 0");
		lblMoteurInferenceOrdre.setFont(new Font("Avant Garde BQ", Font.PLAIN, 18));
		lblMoteurInferenceOrdre.setBounds(109, 11, 233, 44);
		frame.getContentPane().add(lblMoteurInferenceOrdre);
		
		JLabel lblChoixMethode = new JLabel("Choix Methode");
		lblChoixMethode.setForeground(SystemColor.textHighlight);
		lblChoixMethode.setFont(new Font("AvantGarde", Font.PLAIN, 13));
		lblChoixMethode.setBounds(169, 66, 100, 14);
		frame.getContentPane().add(lblChoixMethode);
		
		avant = new JButton("Chainage Avant Premiere Regle Declenchable");
		avant.setBackground(new Color(70, 130, 180));
		avant.setForeground(SystemColor.window);
		avant.addActionListener(this);
		avant.setBounds(91, 103, 251, 34);
		frame.getContentPane().add(avant);
		
		mixte = new JButton("Chainage Mixte");
		mixte.setBackground(new Color(220, 20, 60));
		mixte.setForeground(new Color(255, 255, 255));
		mixte.addActionListener(this);
		mixte.setBounds(91, 199, 251, 34);
		frame.getContentPane().add(mixte);
		
		chainageplusPrem = new JButton("Chainage Avant Plus Premisse");
		chainageplusPrem.addActionListener(this);
		chainageplusPrem.setForeground(new Color(255, 255, 255));
		chainageplusPrem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chainageplusPrem.setBackground(new Color(34, 139, 34));
		chainageplusPrem.setBounds(91, 154, 251, 34);
		frame.getContentPane().add(chainageplusPrem);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==avant){
			Chainage ch = new Chainage("Prem") ;
			this.frame.setVisible(false);
		}
		if(arg0.getSource()==mixte) {
			System.out.println("Mixte Will Be Declenched");
		}
		if(arg0.getSource()== chainageplusPrem) {
			Chainage ch = new Chainage("Plus") ;
			this.frame.setVisible(false);
			
		}
	}
}
