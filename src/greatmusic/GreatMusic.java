package greatmusic;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.MusicPlayerController;
import model.MusicManager;
import view.ControlsPanel;
import view.PlaylistPanel;
import view.SongInfoPanel;
import view.VolumePanel;
import view.WelcomePanel;

public class GreatMusic extends JFrame {

	private static final long serialVersionUID = 1L;

	private WelcomePanel welcomePanel;
    private SongInfoPanel songInfoPanel;  
    private ControlsPanel controlsPanel;
    private VolumePanel volumePanel;
    private PlaylistPanel playlistPanel;
    private MusicManager musicManager;
	
	public GreatMusic() {

	     super("Great Music");
	     setDefaultCloseOperation(EXIT_ON_CLOSE);
	     setLayout(new BorderLayout());
	     createGui();
	     configureGui();
	     this.addWindowListener(new WindowAdapter() {
	    	    @Override
	    	    public void windowClosing(WindowEvent e) {
	    	        musicManager.close();
	    	        System.exit(0);
	    	    }
	     });
	     pack();
	     setLocationRelativeTo(null);
	     setVisible(true);
	    
	}
	
	private void createGui() {
		 welcomePanel = new WelcomePanel();
	     songInfoPanel = new SongInfoPanel();
	     controlsPanel = new ControlsPanel();
	     volumePanel = new VolumePanel();
	     playlistPanel = new PlaylistPanel();
	     musicManager = new MusicManager(volumePanel.getVolumeSlider());
	     new MusicPlayerController(musicManager,songInfoPanel,
                 controlsPanel, volumePanel, playlistPanel);
	}
	
	private void configureGui() {
		  JPanel songArea = new JPanel();
		     songArea.setLayout(new BoxLayout(songArea, BoxLayout.Y_AXIS));
		     songArea.add(songInfoPanel);
		     songArea.add(controlsPanel);
		     songArea.add(volumePanel);
		     JPanel centerPanel = new JPanel(new FlowLayout());
		     centerPanel.add(songArea);
		     centerPanel.add(playlistPanel);
		     centerPanel.setBorder(new EmptyBorder(20,20,20,20));
		     add(welcomePanel, BorderLayout.NORTH);
		     add(centerPanel, BorderLayout.CENTER);
	}
}
