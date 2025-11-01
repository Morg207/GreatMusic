package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton playButton;
	private JButton stopButton;
	private JButton pauseButton;
	private JButton unpauseButton;
	private JButton muteButton;
	
	public ControlsPanel() {
		playButton = createButton("Play");
		stopButton = createButton("Stop");
		pauseButton = createButton("Pause");
		unpauseButton = createButton("Unpause");
		muteButton = createButton("Mute");
		add(playButton);
        add(stopButton);
        add(pauseButton);
        add(unpauseButton);
        add(muteButton);
	}
	
	private JButton createButton(String text) {
		JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(0, 180, 170));
        button.setMargin(new Insets(8, 12, 8, 12));
        return button;
	}
	
	public JButton getPlayButton() { return playButton; }
	public JButton getStopButton() { return stopButton; }
	public JButton getPauseButton() { return pauseButton; }
	public JButton getUnpauseButton() { return unpauseButton; }
	public JButton getMuteButton() { return muteButton; }

}
