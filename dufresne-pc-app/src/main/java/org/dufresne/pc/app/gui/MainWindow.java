package org.dufresne.pc.app.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dufresne.pc.app.actions.NewLedgerAction;
import org.dufresne.pc.app.actions.OpenLedgerAction;
import org.dufresne.pc.app.actions.SaveLedgerAction;
import org.dufresne.pc.app.gui.model.WorkspaceView;
import org.dufresne.pc.app.util.ImageStore;

import com.pacovides.money.service.LedgerService;

public class MainWindow extends JFrame implements ItemListener {

	private static final String LOOK_AND_FEEL_NAME = "Nimbus";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1680144728615370750L;

	private static final Logger logger = LogManager.getLogger(MainWindow.class);

	private LedgerService ledgerService;

	// Workspaces
	private MainWorkspace mainWorkspace;

	private TransactionsWorkspace transactionsWorkspace;

	private AccountsPanel accountsPanel;

	// Toolbars
	private MainToolbar mainToolbar;

	private StatusBar statusBar;

	// Actions
	private NewLedgerAction newLedgerAction;
	private OpenLedgerAction openLedgerAction;
	private SaveLedgerAction saveLedgerAction;


	public MainWindow(LedgerService ledgerService) {
		logger.info("Initializing main window");
		this.ledgerService = ledgerService;
		customizeAppearance();
		initActions();
		this.setJMenuBar(createMenuBar());
		this.setContentPane(createMainPanel());
		this.pack();
	}

	/**
	 * Initializes the actions so they can be used by the different visual
	 * components
	 */
	private void initActions() {
		openLedgerAction = new OpenLedgerAction(ledgerService);
		saveLedgerAction = new SaveLedgerAction(ledgerService);
		newLedgerAction = new NewLedgerAction(ledgerService);

	}

	private JMenuBar createMenuBar() {

		// Main menu bar
		JMenuBar menuBar = new JMenuBar();

		// File menu (F)
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem newLedger = new JMenuItem(newLedgerAction);
		newLedger.setMnemonic(KeyEvent.VK_N);
		fileMenu.add(newLedger);

		JMenuItem openLedger = new JMenuItem(openLedgerAction);
		fileMenu.add(openLedger);

		fileMenu.addSeparator();

		JMenuItem saveLedger = new JMenuItem(saveLedgerAction);
		fileMenu.add(saveLedger);

		fileMenu.addSeparator();

		JMenuItem closeLedger = new JMenuItem("Close Ledger");
		fileMenu.add(closeLedger);

		// View Menu (V)
		JMenu viewMenu = new JMenu("View");
		viewMenu.setMnemonic(KeyEvent.VK_V);

		JMenu chooseViewSubMenu = new JMenu("Show view");
		for (WorkspaceView workspaceView : WorkspaceView.values()) {
			JMenuItem workspaceViewItem = new JMenuItem(workspaceView.getViewname());
			chooseViewSubMenu.add(workspaceViewItem);
		}
		viewMenu.add(chooseViewSubMenu);

		menuBar.add(fileMenu);
		menuBar.add(viewMenu);

		return menuBar;
	}

	private JPanel createMainPanel() {

		// Our main panel is a simple BorderLayout
		JPanel mainPanel = new JPanel(new BorderLayout());

		// The toolbar holder has a the view selector and a dynamic toolbar
		JPanel toolbarHolder = new JPanel(new BorderLayout());
		mainToolbar = new MainToolbar();

		// Creates the accounts left panel
		accountsPanel = new AccountsPanel();
		openLedgerAction.addObserver(accountsPanel);

		// Init workspace visual components
		transactionsWorkspace = new TransactionsWorkspace(ledgerService);
		accountsPanel.addObserver(transactionsWorkspace);

		// main workspace will contain the other workspaces
		mainWorkspace = new MainWorkspace();
		mainWorkspace.addView(WorkspaceView.TRANSACTION_VIEW, transactionsWorkspace);
		mainWorkspace.addView(WorkspaceView.ACCOUNT_VIEW, new JPanel());

		ViewSelector viewSelector = new ViewSelector();
		viewSelector.addObserver(mainWorkspace);
		viewSelector.addObserver(mainToolbar);

		toolbarHolder.add(mainToolbar, BorderLayout.CENTER);
		toolbarHolder.add(viewSelector, BorderLayout.LINE_END);

		// Create a split pane with the two scroll panes in it.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, accountsPanel, mainWorkspace);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		statusBar = new StatusBar();
		accountsPanel.addObserver(statusBar);

		mainPanel.add(toolbarHolder, BorderLayout.PAGE_START);
		mainPanel.add(splitPane, BorderLayout.CENTER);
		mainPanel.add(statusBar, BorderLayout.PAGE_END);

		return mainPanel;
	}

	private void customizeAppearance() {

		// Set the look and feel
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (LOOK_AND_FEEL_NAME.equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
			logger.warn(LOOK_AND_FEEL_NAME + " look and feel could nor be set. Using default.");
		}

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
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		mainWorkspace.viewSelected((WorkspaceView) e.getItem());
	}

}
