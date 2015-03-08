package org.dufresne.pc.app.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import org.dufresne.pc.app.actions.OpenLedgerAction;
import org.dufresne.pc.app.actions.SaveLedgerAction;
import org.dufresne.pc.app.util.ImageStore;

import com.pacovides.money.service.LedgerService;

public class MainWindow extends JFrame implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1680144728615370750L;

	private LedgerService ledgerService;

	// Workspaces
	private MainWorkspace mainWorkspace;

	private TransactionsWorkspace transactionsWorkspace;

	private AccountsPanel accountsPanel;

	// Toolbars
	private MainToolbar mainToolbar;

	// Actions
	private OpenLedgerAction openLedgerAction;
	private SaveLedgerAction saveLedgerAction;

	public MainWindow(LedgerService ledgerService) {
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

	}

	private JMenuBar createMenuBar() {

		// Main menu bar
		JMenuBar menuBar = new JMenuBar();

		// File menu (F)
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem newLedger = new JMenuItem("New Ledger...");
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
		transactionsWorkspace = new TransactionsWorkspace();

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

		JPanel statusBar = createStatusBar();

		mainPanel.add(toolbarHolder, BorderLayout.PAGE_START);
		mainPanel.add(splitPane, BorderLayout.CENTER);
		mainPanel.add(statusBar, BorderLayout.PAGE_END);

		return mainPanel;
	}

	private JPanel createStatusBar() {
		JPanel statusBar = new JPanel(new BorderLayout());
		statusBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		JLabel statusLabel = new JLabel("status...", SwingConstants.LEFT);
		statusBar.add(statusLabel, BorderLayout.LINE_START);
		return statusBar;
	}

	private void customizeAppearance() {
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