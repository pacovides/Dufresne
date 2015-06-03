package org.dufresne.pc.app.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dufresne.pc.app.gui.ErrorHandler;
import org.dufresne.pc.app.gui.form.FormFieldType;
import org.dufresne.pc.app.gui.form.SimpleFormPanel;
import org.dufresne.pc.app.gui.model.LedgerFileObserver;
import org.dufresne.pc.app.gui.wizard.WizardObserver;
import org.dufresne.pc.app.gui.wizard.WizardStep;
import org.dufresne.pc.app.gui.wizard.WizardWindow;

import com.pacovides.money.service.LedgerService;

public class NewLedgerAction extends AbstractAction implements WizardObserver {

	private static final long serialVersionUID = 7775731649289865015L;

	private static final String FIELD_KEY_FILE_LOCATION = "File Location";

	private static final String FIELD_KEY_DESCRIPTION = "Description";

	private static final String FIELD_KEY_LEDGER_NAME = "Ledger Name";

	private static final Logger logger = LogManager.getLogger(NewLedgerAction.class);

	private LedgerService ledgerService;

	private SimpleFormPanel basicLedgerInfoPanel;

	private List<LedgerFileObserver> ledgerFileObservers = new ArrayList<LedgerFileObserver>();

	public NewLedgerAction(LedgerService ledgerService) {
		// Initializes with name and image TODO
		super("New Ledger...");
		// We need a controller to delegate to
		this.ledgerService = ledgerService;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// We create a new wizard upon each action.
		// NewLedgerWizard wizard = new NewLedgerWizard(ledgerService);
		WizardWindow wizard = buildLedgerWizard();
		wizard.setVisible(true);
	}

	public WizardWindow buildLedgerWizard() {
		WizardWindow wizard = new WizardWindow("New Ledger");

		// We want to observe wizard actions
		wizard.addObserver(this);

		// We create the first step to grab basic info
		basicLedgerInfoPanel = new SimpleFormPanel();
		basicLedgerInfoPanel.addField(FormFieldType.TEXT, FIELD_KEY_LEDGER_NAME, "");
		basicLedgerInfoPanel.addField(FormFieldType.LARGE_TEXT, FIELD_KEY_DESCRIPTION, "");
		basicLedgerInfoPanel.addField(FormFieldType.DIR, FIELD_KEY_FILE_LOCATION, System.getProperty("user.home"));
		WizardStep step1 = new WizardStep(basicLedgerInfoPanel, "LedgerBasicInfo");
		wizard.addStep(step1);

		return wizard;
	}

	public void addObserver(LedgerFileObserver observer) {
		ledgerFileObservers.add(observer);
	}

	@Override
	public void wizardNext() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizardBack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizardCancel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizardFinished() {
		// When finished, we take the information from the info panel and create
		// a new ledger and save it.
		String ledgerName = basicLedgerInfoPanel.getFieldValue(FIELD_KEY_LEDGER_NAME);
		String ledgerDesc = basicLedgerInfoPanel.getFieldValue(FIELD_KEY_DESCRIPTION);
		String ledgerDestinationFolder = basicLedgerInfoPanel.getFieldValue(FIELD_KEY_FILE_LOCATION);

		// Validate fields
		validateFields(ledgerName, ledgerDestinationFolder);

		ledgerService.createNewLedger(ledgerName, ledgerDesc);

		ledgerService.saveLedger(getLedgerDestinationFile(new File(ledgerDestinationFolder), ledgerName));

		for (LedgerFileObserver observer : ledgerFileObservers) {
			observer.changedActiveLedger(ledgerService.getLedger());
		}
		logger.info("ledger created {}", ledgerService.getLedger().getName());
	}

	public boolean validateFields(String ledgerName, String ledgerDestinationFolder) {
		if (StringUtils.isEmpty(ledgerName)) {
			ErrorHandler.displayUserError("Ledger name cannot be empty");
			return false;
		}

		if (StringUtils.isEmpty(ledgerDestinationFolder)) {
			ErrorHandler.displayUserError("Ledger destination folder cannot be empty");
			return false;
		}

		File ledgerDir = new File(ledgerDestinationFolder);
		if (!ledgerDir.exists() || !ledgerDir.isDirectory()) {
			ErrorHandler.displayUserError("Ledger destination folder " + ledgerDestinationFolder
					+ " must be a valid directory");
			return false;
		}

		String ledgerDestinationFile = getLedgerDestinationFile(ledgerDir, ledgerName);
		File file = new File(ledgerDestinationFile);
		if (file.exists()) {
			boolean overwrite = ErrorHandler.displayUserWarn("The file " + ledgerDestinationFile
					+ " already exists, do you which to overwrite?");
			if (!overwrite) {
				return false;
			}
		}

		return true;
	}

	private String getLedgerDestinationFile(File ledgerDir, String ledgerName) {
		String filename = ledgerName.toLowerCase().replace(" ", "_");
		filename += ".xml";
		File file = new File(ledgerDir, filename);

		try {
			return file.getCanonicalPath();
		} catch (IOException e) {
			ErrorHandler.displayUserError("The ledger has an invalid name " + filename);
			logger.error(e);
			return null;
		}
	}

}
