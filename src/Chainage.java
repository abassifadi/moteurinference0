

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Color;


public class Chainage implements ActionListener {

	private JFrame frame;
	private JTextPane regle ;
	private JLabel lblFait_1;
	private JTextPane fait;
	private JTextField textField;
	private JButton btnRegle ;
	private JButton btnFait ;
	String regleString ;
	String faitString ;
	private JButton btnCommencer ;
	JTextArea list ;
	
	
	
	
	/**
	 * @return the list
	 */
	public JTextArea getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(JTextArea list) {
		this.list = list;
	}

	BackTreatement  us = new BackTreatement() ;
	
	
	

	public JTextPane getRegle() {
		return regle;
	}

	public void setRegle(JTextPane regle) {
		this.regle = regle;
	}

	public JTextPane getFait() {
		return fait;
	}

	public void setFait(JTextPane fait) {
		this.fait = fait;
	}

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Chainage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JLabel lblRegle = new JLabel("Regle");
		lblRegle.setBounds(10, 11, 532, 14);
		frame.getContentPane().add(lblRegle);
		
		regle = new JTextPane();
		regle.setBackground(new Color(255, 255, 204));
		regle.setBounds(10, 31, 436 , 244);
		frame.getContentPane().add(regle);
		
		lblFait_1 = new JLabel("Fait");
		lblFait_1.setBounds(468, 11, 46, 14);
		frame.getContentPane().add(lblFait_1);
		
		fait = new JTextPane();
		fait.setBackground(new Color(255, 255, 204));
		fait.setBounds(468, 31, 394, 167);
		fait.disable();
		frame.getContentPane().add(fait);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 255, 204));
		textField.setBounds(468, 244, 394, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		list = new JTextArea();
		list.setBackground(new Color(255, 255, 204));
		list.setBounds(10, 345, 852, 174);
		frame.getContentPane().add(list);
		
		JLabel lblExecution = new JLabel("Execution");
		lblExecution.setBounds(10, 311, 68, 14);
		frame.getContentPane().add(lblExecution);
		
		JLabel lblBut = new JLabel("But");
		lblBut.setBounds(468, 219, 46, 14);
		frame.getContentPane().add(lblBut);
		
		btnRegle = new JButton("Vallider");
		btnRegle.setBackground(Color.RED);
		btnRegle.setForeground(Color.RED);
		btnRegle.setBounds(357, 286, 89, 39);
		btnRegle.addActionListener(this);
		frame.getContentPane().add(btnRegle);
		
		
		btnFait = new JButton("Valider");
		btnFait.setForeground(Color.ORANGE);
		btnFait.setBackground(Color.ORANGE);
		btnFait.setBounds(620, 286, 106, 39);
		btnFait.addActionListener(this);
		frame.getContentPane().add(btnFait);
		
		btnCommencer = new JButton("Commencer");
		btnCommencer.setBackground(Color.RED);
		btnCommencer.setForeground(Color.RED);
		btnCommencer.setBounds(744, 286, 118, 39);
		btnCommencer.disable();
		btnCommencer.addActionListener(this);
		frame.getContentPane().add(btnCommencer);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
      if(e.getSource()==btnRegle) {
    	  regleString = regle.getText().toString() ;
    	  fait.enable();	
    	  regle.disable();
    	  btnRegle.disable();
		}
		
		if(e.getSource()==btnFait) {
			System.out.println("HERE");
			faitString = fait.getText().toString() ;
			fait.disable(); 
			btnCommencer.enable();
			btnFait.disable();
			us.whiteInFile("REGLE \r\n"+regleString+ "\r\nFAIT \r\n"+ faitString);
		    
			
		  // ChainageAvant ch = new ChainageAvant() ;
		   //ch.chainageAvantAvecConflit("0");
		}
		
		if(e.getSource()==btnCommencer) {
			System.out.println("AKA");
			
		 ChainageAvant ch = new ChainageAvant(Chainage.this) ;
		  ch.chainageAvantAvecConflit("0");
		}
		
		
		
		
		
		
		
	}
}
