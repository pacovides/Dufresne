package org.dufresne.desktop;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1680144728615370750L;

	public MainWindow() {

		int width = 600;
		int height = 400;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int X = (screenSize.width / 2) - (width / 2); // Center horizontally.
		int Y = (screenSize.height / 2) - (height / 2); // Center vertically.

		this.setBounds(X, Y, width, height);
		this.setPreferredSize(new Dimension(width, height));

		// center the jframe on screen
		this.setLocationRelativeTo(null);

		Image appIcon = ImageStore.getImage("piggy.png");
		this.setIconImage(appIcon);

		this.setMinimumSize(new Dimension(300, 300));
		this.setTitle("Dufresne");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}

}
