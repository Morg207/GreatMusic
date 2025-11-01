package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class PlaylistPanel extends JPanel {

	 private static final long serialVersionUID = 1L;
	
	    private JList<String> playlist;
	    private DefaultListModel<String> listModel;
	    private JLabel trackCountLabel;

	    public PlaylistPanel() {
	        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	        setBorder(BorderFactory.createRaisedBevelBorder());
	        createTracksLabel();
	        createPlaylist();
            createTrackCountLabel();
	    }
	    
	    private void createTracksLabel() {
	    	JLabel tracksLabel = new JLabel("Tracks");
	        tracksLabel.setFont(new Font("Arial", Font.PLAIN, 18));
	        tracksLabel.setBorder(new EmptyBorder(5, 5, 5, 0));
	        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
	        topPanel.add(tracksLabel);
	        add(topPanel);
	    }
	    
	    private void createTrackCountLabel() {
	    	JPanel trackCountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
	        trackCountLabel = new JLabel("Tracks: 0");
	        trackCountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
	        trackCountLabel.setBorder(new EmptyBorder(5, 5, 5, 0));
	        trackCountPanel.add(trackCountLabel);
	        add(trackCountPanel);
	    }
	    
	    private void createPlaylist() {
	    	listModel = new DefaultListModel<>();
	        playlist = new JList<>(listModel);
	        playlist.setBackground(Color.WHITE);
	        playlist.setForeground(Color.BLACK);
	        playlist.setFont(new Font("Arial", Font.PLAIN, 20));
	        playlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        playlist.setVisibleRowCount(12);
	        JScrollPane scrollPane = new JScrollPane(playlist);
	        scrollPane.setPreferredSize(new Dimension(260, 350));
	        add(scrollPane);
	    }

	    public JList<String> getPlaylist() {
	        return playlist;
	    }
	    
	    public DefaultListModel<String> getListModel(){
	    	return listModel;
	    }
	    
	    public void updateTrackCount(int count) {
	    	trackCountLabel.setText("Tracks: " + count);
	    }
}
