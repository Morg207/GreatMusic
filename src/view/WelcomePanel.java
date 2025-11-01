package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WelcomePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public WelcomePanel() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel welcomeLabel = new JLabel("Welcome to great music");
		welcomeLabel.setBorder(new EmptyBorder(7,7,7,7));
		welcomeLabel.setOpaque(true);
		welcomeLabel.setBackground(new Color(0, 180, 170));
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 23));
		this.setBorder(new EmptyBorder(10,0,0,0));
		this.add(welcomeLabel);
	}

}
