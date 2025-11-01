package view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class VolumePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JSlider volumeSlider;
	
	public VolumePanel() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        volumeSlider.setPreferredSize(new Dimension(170, volumeSlider.getPreferredSize().height));
        add(volumeSlider);
	}
	
	public JSlider getVolumeSlider() {
		return volumeSlider;
	}

}
