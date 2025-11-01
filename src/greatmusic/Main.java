package greatmusic;

import java.awt.EventQueue;

import javax.swing.UIManager;

import com.formdev.flatlaf.*;

public class Main {

	public static void main(String[] args) {
		 try {
			 UIManager.put("Button.arc",15);
			 FlatDarkLaf.setup();
	        } catch (Exception ex) {
	            ex.printStackTrace();  
	        }
		EventQueue.invokeLater(() -> {
			new GreatMusic();
		}); 
	}

}
