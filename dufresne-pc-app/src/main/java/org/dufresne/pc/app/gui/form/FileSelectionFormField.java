package org.dufresne.pc.app.gui.form;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The TextAreaFormField allows input of long text. It allows multiple lines and
 * automatically renders scrollbars when needed.
 * 
 * @author Francisco
 *
 */
public class FileSelectionFormField extends FormField {

	private static final long serialVersionUID = -1345123477875885210L;

	private JTextField filePath = new JTextField();

	private JButton browseBtn = new JButton("...");

	private JPanel containerPanel = new JPanel();

	public FileSelectionFormField(String fieldName) {
		super();
		containerPanel.setLayout(new BorderLayout());
		containerPanel.add(filePath, BorderLayout.CENTER);
		containerPanel.add(browseBtn, BorderLayout.LINE_END);
		initLayout(fieldName);
		// file path is expected to fit in one line
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));
	}

	@Override
	public void setAlignmentX(float alignmentX) {
		label.setAlignmentX(alignmentX);
		filePath.setAlignmentX(alignmentX);
	}

	@Override
	public void setValue(String textValue) {
		filePath.setText(textValue);
	}

	@Override
	protected JComponent getActiveFieldContainer() {
		return containerPanel;
	}

}
