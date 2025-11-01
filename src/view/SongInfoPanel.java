package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SongInfoPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel songLabel;
	private JButton loadTracksButton;

	public SongInfoPanel() {
	     setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	     setBorder(new EmptyBorder(15, 0, 10, 0));
	     createSongLabel();
	     createTracksButton();
	     add(songLabel);
	     add(loadTracksButton); 
	 }
	
	private void createTracksButton() {
		 loadTracksButton = new JButton("Load Tracks");
	     loadTracksButton.setFont(new Font("Arial", Font.PLAIN, 16));
	     loadTracksButton.setBackground(new Color(219, 108, 48));
	     loadTracksButton.setForeground(Color.white);
	     loadTracksButton.setMargin(new Insets(8, 16, 8, 16));
	}
	
	private void createSongLabel() {
		songLabel = new JLabel("Song title..."){
			private static final long serialVersionUID = 1L;

				@Override
	    	    public Dimension getPreferredSize() {
	    	        Dimension size = super.getPreferredSize();
	    	        size.width = Math.min(size.width, 213);
	    	        return size;
	    	    }
	    	};
	     songLabel.setFont(new Font("Arial", Font.PLAIN, 20));
	}

	public JLabel getSongLabel() {
	     return songLabel;
	    }

	public JButton getLoadTracksButton() {
	     return loadTracksButton;
	    }

	public void updateSongLabel(String title) {
	     songLabel.setText(title);
	    }
}
