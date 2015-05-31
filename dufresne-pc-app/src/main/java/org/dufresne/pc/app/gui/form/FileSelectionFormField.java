package org.dufresne.pc.app.gui.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The TextAreaFormField allows input of long text. It allows multiple lines and
 * automatically renders scrollbars when needed.
 * 
 * @author Francisco
 *
 */
public class FileSelectionFormField extends FormField implements ActionListener {

	private static final long serialVersionUID = -1345123477875885210L;

	private JTextField filePath = new JTextField();

	private JButton browseBtn = new JButton("...");

	private JPanel containerPanel = new JPanel();

	private final JFileChooser fileChooser = new JFileChooser();

	public FileSelectionFormField(String fieldName, int fileSelectionMode) {
		super();
		containerPanel.setLayout(new BorderLayout());
		containerPanel.add(filePath, BorderLayout.CENTER);
		containerPanel.add(browseBtn, BorderLayout.LINE_END);
		browseBtn.addActionListener(this);
		fileChooser.setFileSelectionMode(fileSelectionMode);
		filePath.setText("WTF??");
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
		File fileValue = new File(textValue);
		if (fileValue.exists()) {
			if (fileValue.isDirectory()) {
				fileChooser.setCurrentDirectory(fileValue);
			} else {
				fileChooser.setCurrentDirectory(fileValue.getParentFile());
			}
		}

	}

	@Override
	protected JComponent getActiveFieldContainer() {
		return containerPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			filePath.setText(selectedFile.getPath());
		}

	}

	@Override
	public String getValue() {
		return filePath.getText();
	}

}
