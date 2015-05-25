package org.dufresne.pc.app.gui.form;

import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * A convenient generic panel to hold labels and field of string data.
 * 
 * @author Francisco
 *
 */
public class SimpleFormPanel extends JPanel {

	private static final long serialVersionUID = 398940527309454953L;

	private Map<String, String> nameValueMap = new HashMap<String, String>();

	public SimpleFormPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	public void addField(FormFieldType type, String name, String initialValue) {

		FormField formField;

		switch (type) {
		case TEXT:
			formField = new TextFormField(name);
			break;
		case LARGE_TEXT:
			formField = new TextAreaFormField(name);
			break;
		case FILE:
			formField = new FileSelectionFormField(name);
			break;
		default:
			throw new IllegalArgumentException("Unsupported type " + type);
		}

		// If there is a preset value we set it now.
		formField.setValue(initialValue);
		formField.setAlignmentX(Component.LEFT_ALIGNMENT);

		nameValueMap.put(name, initialValue);

		// Adds field below label
		this.add(formField);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	public String getFieldValue(String name) {
		return nameValueMap.get(name);
	}

}
