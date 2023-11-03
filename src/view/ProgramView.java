package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.ChargeGraph2D;

public class ProgramView
{
	// ProgramView has a frame to hold the UI
	private JFrame mainFrame;
	// ProgramView has a panel to hold the model display
	private JPanel mainPanel;
	// ProgramView shares a file selector
	private JFileChooser fileSelector;

	public ProgramView()
	{
		//Initialize fields
		mainFrame = new JFrame("CISC_191 Charge Project");

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainFrame.add(mainPanel);

		createToolbar();
		mainPanel.add(toolbarPanel, BorderLayout.NORTH);
		
		fileSelector = new JFileChooser();

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	// ProgramView has a panel to hold toolbar elements
	private JPanel toolbarPanel;
	// ProgramView has buttons to control file io
	private JButton fileSaveButton;
	private JButton fileOpenButton;

	private void createToolbar()
	{
		toolbarPanel = new JPanel();
		toolbarPanel.setPreferredSize(null);

		// Setup panel's layout
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		layout.setHgap(0);
		layout.setVgap(0);
		toolbarPanel.setLayout(layout);

		//Add toolbar buttons
		fileSaveButton = new JButton("Save File");
		toolbarPanel.add(fileSaveButton);

		fileOpenButton = new JButton("Open File");
		toolbarPanel.add(fileOpenButton);

		// Blend in with the window's own toolbar
		toolbarPanel.setBackground(Color.WHITE);
	}

	// ProgramView has a GraphView
	ChargeGraphView graphView;

	public void initializeChargeBoard(ChargeGraph2D model)
	{
		graphView = new ChargeGraphView(model);
		mainPanel.add(graphView, BorderLayout.CENTER);
		mainFrame.pack();
	}

	public void repaintGraph()
	{
		graphView.repaint();
	}

	public JFileChooser getFileSelector()
	{
		return fileSelector;
	}
	
	public void addGraphMouseListener(MouseListener listener)
	{
		graphView.addMouseListener(listener);
	}

	public void addFileSaveListener(ActionListener listener)
	{
		fileSaveButton.addActionListener(listener);
	}

	public void addFileOpenListener(ActionListener listener)
	{
		fileOpenButton.addActionListener(listener);
	}

}
