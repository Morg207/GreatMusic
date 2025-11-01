package controller;

import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.MusicManager;
import view.ControlsPanel;
import view.PlaylistPanel;
import view.SongInfoPanel;
import view.VolumePanel;

public class MusicPlayerController {
	
	 private MusicManager musicManager;
	 private SongInfoPanel songInfoPanel;
	 private ControlsPanel controlsPanel;
	 private VolumePanel volumePanel;
	 private PlaylistPanel playlistPanel;
	 private JLabel songLabel;
	 private JList<String> playlist;

	 public MusicPlayerController(MusicManager manager, SongInfoPanel songInfoPanel, ControlsPanel controlsPanel, VolumePanel volumePanel,PlaylistPanel playlistPanel) {
	        this.musicManager = manager;
	        this.songInfoPanel = songInfoPanel;
	        this.controlsPanel = controlsPanel;
	        this.volumePanel = volumePanel;
	        this.playlistPanel = playlistPanel;
	        this.playlist = this.playlistPanel.getPlaylist();
	        this.songLabel = this.songInfoPanel.getSongLabel();
	        initButtonListeners();
	        initVolumeSliderListener();
	        initPlaylistListener();
	        initLoadTracksListener();
	 }
	 
	 private void initButtonListeners() {
		    controlsPanel.getPlayButton().addActionListener(e -> {
	            musicManager.play();
	        });
	        controlsPanel.getStopButton().addActionListener(e -> {
	        	musicManager.stop();
	        });
	        controlsPanel.getPauseButton().addActionListener(e ->{
	        	musicManager.pause();
	        });
	        controlsPanel.getUnpauseButton().addActionListener(e -> {
	            musicManager.unpause();
	        });
	        controlsPanel.getMuteButton().addActionListener(e -> {
	        	musicManager.mute();
	        });
	 }
	 
	 private void initVolumeSliderListener() {
		 JSlider volumeSlider = volumePanel.getVolumeSlider();
		 volumePanel.getVolumeSlider().addChangeListener(e -> {
	    		float currentVolume = (float)volumeSlider.getValue() / 100f;
	    		musicManager.setMusicVolume(currentVolume);
	     });
	 }
	 
	 private void initPlaylistListener() {
		 playlist.addListSelectionListener(e ->{
	        	if(!e.getValueIsAdjusting()) {
	        		int index = playlist.getSelectedIndex();
	        		if (index == -1) {
	        			return;
	        		}
	        		String selection = playlist.getSelectedValue();
	        		songLabel.setToolTipText(selection);
	        		songInfoPanel.updateSongLabel(selection);
	        		File selectedFile = musicManager.getPlaylist().get(index);
	        		musicManager.loadMusic(selectedFile);
	        	}
	        });
	 }
	 
	 private void initLoadTracksListener() {

	        songInfoPanel.getLoadTracksButton().addActionListener(e -> {
	            JFileChooser chooser = new JFileChooser();
	            chooser.setMultiSelectionEnabled(true);
	            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	            FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio Files", "wav");
	            chooser.setFileFilter(filter);
	            int result = chooser.showOpenDialog(null);
	            if (result == JFileChooser.APPROVE_OPTION) {
	                populatePlaylist(chooser);
	            }
	        });
	   }
	 
	 private void populatePlaylist(JFileChooser chooser) {
		 DefaultListModel<String> listModel = playlistPanel.getListModel();
		 File[] files = chooser.getSelectedFiles();
		 musicManager.clearPlaylist();
         listModel.clear();
         for(File file : files) {
         	musicManager.addTrack(file);
         	listModel.addElement(file.getName());
         }
         File firstTrack = files[0];
         musicManager.loadMusic(firstTrack);
         playlist.setSelectedIndex(0);
         playlist.requestFocusInWindow();
         songLabel.setToolTipText(firstTrack.getName());
         songInfoPanel.updateSongLabel(firstTrack.getName());
         playlistPanel.updateTrackCount(listModel.size());
	 }
  }
