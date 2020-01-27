/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	
	private JLabel status;
	private JFrame frame;
	private JPanel gamePanel;
	private Board gameBoard;
	
	public Game() {}
	
	public void setStatus(String stat) {
		status.setText(stat);
	}
	
	public String getStatus() {
		return status.getText();
	}
	
	public static Color intToColor(int contents) {
		if (contents == 0) {
			return Color.WHITE;
		}
		if (contents == 1) {
			return Color.GREEN;
		}
		return Color.RED;
	}
	
	public static String intToColorName(int contents) {
		if (contents == 0) {
			return "White";
		}
		if (contents == 1) {
			return "Green";
		}
		return "Red";
	}
	
	public void run() {
		
		//6x6 game board
		//Represent blank as 0, green as 1, red as 2

		status = new JLabel();
		
		frame = new JFrame("Connect 4");
        frame.setLayout(new BorderLayout());
        frame.setLocation(300,300);
        frame.setSize(400,1200);
        
        gamePanel = new JPanel();
		
		gameBoard = new Board(this);
		
        // NOTE : recall that the 'final' keyword notes immutability even for local variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        
		
        JPanel controlPanel = new JPanel();
        controlPanel.setSize(50,25);
        
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setSize(50,50);
		buttonsPanel.setLayout(new GridLayout());
        
		
		gamePanel.add(gameBoard);

        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton newGame = new JButton("New Game");
        newGame.setSize(110,50);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameBoard.reset();
            }
        });
        
        final JButton save = new JButton("Save to File");
        save.setSize(110,50);
        save.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					gameBoard.save();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
        final JButton structButton = new JButton("Instructions");
        final JFrame structFrame = new JFrame();
        structFrame.setSize(1000,500);
        structFrame.setLocation(100,100);
        final JTextArea structText = new JTextArea("This game is Connect 4.\n"
        		+ "There are two players, red and green, who take turns.\n"
        		+ "On each turn, a player drops a piece of their color into a column of the 6x6 game board.\n"
        		+ "The piece falls down through the column until it hits another piece, coming to rest on the space above it.\n"
        		+ "A player wins when they have 4 pieces in a row, either vertically, horizontally, or diagonally.\n\n"
        		+ "To drop a piece, click the \"Drop Here\" button above the desired column.\n"
        		+ "When a player wins, or if you try to drop a piece into a full column, a message will appear.\n"
        		+ "Press the save button to save the current game state to a text file.\n"
        		+ "You can then restore the saved game by closing and opening the application.");
        structText.setLineWrap(true);
        structText.setEditable(false);
        structFrame.add(structText);
        structButton.setSize(110,50);
        structButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		structFrame.setVisible(true);
        	}
        });
        
        controlPanel.add(newGame);
        controlPanel.add(status);
        controlPanel.add(save);
        controlPanel.add(structButton);
        
        //buttonsPanel
        JButton b0 = new JButton("Drop Here");
        b0.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gameBoard.dropInColumn(0);
        	}
        });
        buttonsPanel.add(b0);
        
        JButton b1 = new JButton("Drop Here");
        b1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gameBoard.dropInColumn(1);
        	}
        });
        buttonsPanel.add(b1);
        
        JButton b2 = new JButton("Drop Here");
        b2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gameBoard.dropInColumn(2);
        	}
        });
        buttonsPanel.add(b2);
        
        JButton b3 = new JButton("Drop Here");
        b3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gameBoard.dropInColumn(3);
        	}
        });
        buttonsPanel.add(b3);
        
        JButton b4 = new JButton("Drop Here");
        b4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gameBoard.dropInColumn(4);
        	}
        });
        buttonsPanel.add(b4);
        
        JButton b5 = new JButton("Drop Here");
        b5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		gameBoard.dropInColumn(5);
        	}
        });
        buttonsPanel.add(b5);
        
        //Add panels to frame
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(buttonsPanel, BorderLayout.CENTER);
        frame.add(gamePanel, BorderLayout.SOUTH);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        //gameBoard.reset();
    }
	
	public Board getBoard() {
		return gameBoard;
	}

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}