package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

public class MusicManager {

	private List<File> playlist;
	private Clip musicClip;
	private boolean muted; 
	private float volume;
	private long lastFramePosition;
	private FloatControl gainControl;
	private JSlider volumeSlider;
	
	public MusicManager(JSlider volumeSlider) {
		playlist = new ArrayList<>();
		muted = false;
		volume = 1.0f;
		this.volumeSlider = volumeSlider;
	}
	
	public void loadMusic(File file) {
		try {
			if (musicClip != null) {
				musicClip.stop();
				musicClip.close();
			}
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
			musicClip = AudioSystem.getClip();
			musicClip.open(audioStream);
			gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
			setGain();
			
		}catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
		    JOptionPane.showMessageDialog(null, "Error loading or playing the file: " + e.getMessage());
		}
	}
	
	public void clearPlaylist() {
		playlist.clear();
	}
	
	public List<File> getPlaylist(){
		return playlist;
	}
	
	public void addTrack(File track) {
		playlist.add(track);
	}
	
	public void play() {
		if(musicClip != null && !musicClip.isRunning()) {
			long currentPos = musicClip.getLongFramePosition();
			if (currentPos >= musicClip.getFrameLength()){
				musicClip.setFramePosition(0);
			}
			if(muted) {
			  musicClip.setFramePosition((int)lastFramePosition);
			  volume = (float)volumeSlider.getValue() / 100f;
			  setGain();
			  muted = false;
			}
			musicClip.start();
		}
	}
	
	public void pause() {
		if (musicClip != null && musicClip.isRunning()) {
		  musicClip.stop();
	    }
	}
	
	public void unpause() {
		if(musicClip != null && !musicClip.isRunning()) {
		   musicClip.start();
		}
	}
	
	public void stop() {
		if(musicClip != null && musicClip.isRunning()) {
			musicClip.setFramePosition(0);
			musicClip.stop();
		}
	} 
	
	public void mute() {
		if (musicClip != null && musicClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			muted = !muted;
			if(muted) {
              volume = 0f;
              setGain();
              lastFramePosition = musicClip.getLongFramePosition();
              musicClip.stop();
			}else if(!muted) {
				volume = (float)volumeSlider.getValue() / 100f;
	            setGain();
	            musicClip.setFramePosition((int)lastFramePosition);
	            musicClip.start();
			}
        }
	}
	
	public void setMusicVolume(float volume) {
		this.volume = volume;
		if (musicClip != null && musicClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
			setGain();
		}
	}
	
	private void setGain() {
		float dB = (float) (20.0 * Math.log10(volume));
        gainControl.setValue(dB);
	}
	
	public void close() {
		if(musicClip != null) {
			if(musicClip.isRunning()) {
				musicClip.stop();
			}
			musicClip.close();
		}
	}

}
