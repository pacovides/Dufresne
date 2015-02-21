package org.dufresne.desktop;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Convenient class to retrieve images
 * 
 * @author Francisco
 *
 */
public class ImageStore {

	private static final String IMAGE_FOLDER = "img/";

	/**
	 * given the image name returns the image from the known image location.
	 * 
	 * @return
	 */
	public static Image getImage(String imageName) {
		String fullName = ImageStore.class.getResource("/").getPath();
		fullName += IMAGE_FOLDER;
		fullName += imageName;
		ImageIcon imageIcon = new ImageIcon(fullName);
		return imageIcon.getImage();
	}

}
