import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class containing GUI: board + buttons
 */
public class GUI extends JPanel implements ActionListener, ChangeListener {
	private Container mainContainer;
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Board board;
	private JButton start;
	private JButton clear;
	private JButton run;
	private JSlider pred;
	private JFrame frame;
	private JButton modeSwitch;

	private int iterNum = 0;
	private final int maxDelay = 500;
	private final int initDelay = 100;
	private boolean running = false;
	private int MODE = 0; //

	public GUI(JFrame jf) {
		frame = jf;
		timer = new Timer(initDelay, this);
		timer.stop();
	}


	/*public void startingWindow(Container container)
	{
		mainContainer = container;
		container.setLayout(new BorderLayout());
		container.setSize(new Dimension(300, 300));

		modeSwitch = new JCheckBox("Life");
		modeSwitch.setToolTipText("Choose between Rain/Life ( Life 1- checked, Rain - 0 unchecked)");
		modeSwitch.addChangeListener(this);

		run = new JButton("Run");
		run.setActionCommand("Run");
		run.setToolTipText("Runs simulation");
		run.addActionListener(this);

		JPanel startpanel = new JPanel();
		startpanel.add(modeSwitch);
		startpanel.add(run);
		container.add(startpanel);

	}*/
	/**
	 * @param container to which GUI and board is added
	 */
	public void initialize(Container container, int MODE) {
		container.setLayout(new BorderLayout());
		container.setSize(new Dimension(1024, 768));

		JPanel buttonPanel = new JPanel();

		start = new JButton("Start");
		start.setActionCommand("Start");
		start.setToolTipText("Starts clock");
		start.addActionListener(this);

		clear = new JButton("Clear");
		clear.setActionCommand("clear");
		clear.setToolTipText("Clears the board");
		clear.addActionListener(this);

		pred = new JSlider();
		pred.setMinimum(0);
		pred.setMaximum(maxDelay);
		pred.setToolTipText("Time speed");
		pred.addChangeListener(this);
		pred.setValue(maxDelay - timer.getDelay());

		modeSwitch = new JButton("Switch");
		modeSwitch.setActionCommand("switch");
		modeSwitch.setToolTipText("Choose between Rain/Life ( Life - unchecked, Rain - checked)");
		modeSwitch.addActionListener(this);

		buttonPanel.add(start);
		buttonPanel.add(clear);
		buttonPanel.add(pred);
		buttonPanel.add(modeSwitch);

		board = new Board(1024, 768 - buttonPanel.getHeight(),MODE);
		container.add(board, BorderLayout.CENTER);
		container.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * handles clicking on each button
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(timer)) {
			iterNum++;
			frame.setTitle("Game of Life (" + Integer.toString(iterNum) + " iteration)");
			board.iteration();
		} else {
			String command = e.getActionCommand();
			if (command.equals("Start")) {
				if (!running) {
					timer.start();
					start.setText("Pause");
				} else {
					timer.stop();
					start.setText("Start");
				}
				running = !running;
				clear.setEnabled(true);

			}
			else if (command.equals("clear")) {
				iterNum = 0;
				timer.stop();
				start.setEnabled(true);
				board.clear();
				frame.setTitle("Cellular Automata Toolbox");
			}
			else if (command.equals("switch")) {
				iterNum = 0;
				timer.stop();
				start.setEnabled(true);
				board.clear();
				frame.setTitle("Cellular Automata Toolbox");
				MODE = (MODE+1)%2;
				board.switchMode(MODE);
				System.out.println("switched");
			}

		}
	}

	/**
	 * slider to control simulation speed
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	public void stateChanged(ChangeEvent e) {
		timer.setDelay(maxDelay - pred.getValue());
	}
}
