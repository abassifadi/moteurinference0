
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;

public class BackTreatement {
	
	
	
	
	public void whiteInFile(String s) {
		      String filePath = "C:\\Users\\Abassi\\Desktop\\IB.txt" ;
	          String str = "Heyyyyyyyyyyyyyyy" ;
	          try {
				FileWriter fw = new FileWriter (filePath);
				 fw.write (s);
				 fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
