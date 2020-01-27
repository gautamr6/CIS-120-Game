/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class Board extends JComponent {
    
	int[] amountInColumn = new int[6];
	int[][] boardValues = new int[6][6];
	Game parent;
	Scanner br = null;
	PrintWriter bw = null;
	File f = new File("files/save.txt");

    int turn = 1; // whose turn it is (1 for green, 2 for red)
    
    LinkedList<Integer> moves = new LinkedList<Integer>();

    public Board(Game g) {
    	super();
        setSize(660,660);
    	
        parent = g;
        
        try {
			br = new Scanner(f);
			
	        while (br.hasNext()) {
	        	String line = br.nextLine();
	        	dropInColumn(Integer.parseInt(line));
	        }

	        br.close();
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        repaint();
        
    	// creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
    }
    
    //Constructor for use in file input test
    public Board(File f) {
    	super();
        setSize(660,660);
        
        this.f = f;
        
        try {
			br = new Scanner(f);
			
	        while (br.hasNext()) {
	        	String line = br.nextLine();
	        	dropInColumn(Integer.parseInt(line));
	        }

	        br.close();
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        repaint();
        
    	// creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
    }
    
    //Constructor for use in tests
    /*public Board() {
    	super();
        setSize(660,660);
        
        try {
			br = new Scanner(f);
			
	        while (br.hasNext()) {
	        	String line = br.nextLine();
	        	dropInColumn(Integer.parseInt(line));
	        }

	        br.close();
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        repaint();
        
    	// creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key listener.
        setFocusable(true);
    }*/
    
    //For use in tests
    public void setBoardValues(int[][] values) {
    	boardValues = values;
    }
    
    //For use in tests
    public int[][] getBoardValues() {
    	return boardValues;
    }
    
    public void save() throws Exception {
    	if (f.delete())
    		f.createNewFile();
    	
    	bw = new PrintWriter(f);
    	
    	try {
    		for (Integer i: moves) {
    			bw.println(i.toString());
    		}
    		bw.flush();
    		bw.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void dropInColumn(int i) {
    	moves.add(new Integer(i));
    	
    	if (!(amountInColumn[i] == 6)) {
    		boardValues[i][amountInColumn[i]] = turn;
    		amountInColumn[i]++;
    	}
    	else {
    		parent.setStatus("This column is full");
    	}
    	
    	win();
    	
    	if (turn == 1) {
    		turn = 2;
    	}
    	else {
    		turn = 1;
    	}
    	repaint();
    }
    
    public boolean win() {
    	//Check for sequences
    	boolean seq = false;
    	int winner = 0;
    	
    	for (int i = 0; i < 6; i++) {
    		for (int j = 0; j < 6; j++) {

    			//Check for i-direction sequences
    			if (i < 3) {
    				if ((boardValues[i][j] == 1) && (boardValues[i+1][j] == 1) && (boardValues[i+2][j] == 1) && (boardValues[i+3][j] == 1)) {
    					seq = true;
    					winner = 1;
    				}
    				if ((boardValues[i][j] == 2) && (boardValues[i+1][j] == 2) && (boardValues[i+2][j] == 2) && (boardValues[i+3][j] == 2)) {
    					seq = true;
    					winner = 2;
    				}
    			}
    			
    			//Check for j-direction sequences
    			if (j < 3) {
    				if ((boardValues[i][j] == 1) && (boardValues[i][j+1] == 1) && (boardValues[i][j+2] == 1) && (boardValues[i][j+3] == 1)) {
    					seq = true;
    					winner = 1;
    				}
    				if ((boardValues[i][j] == 2) && (boardValues[i][j+1] == 2) && (boardValues[i][j+2] == 2) && (boardValues[i][j+3] == 2)) {
    					seq = true;
    					winner = 2;
    				}
    			}
    			
    			//Check for bottom-left to top-right sequences
    			if (i < 3 && j < 3) {
    				if ((boardValues[i][j] == 1) && (boardValues[i+1][j+1] == 1) && (boardValues[i+2][j+2] == 1) && (boardValues[i+3][j+3] == 1)) {
    					seq = true;
    					winner = 1;
    				}
    				if ((boardValues[i][j] == 2) && (boardValues[i+1][j+1] == 2) && (boardValues[i+2][j+2] == 2) && (boardValues[i+3][j+3] == 2)) {
    					seq = true;
    					winner = 2;
    				}
    			}
    			
    			//Check for bottom-right to top-left sequences
    			if (i > 2 && j < 3) {
    				if ((boardValues[i][j] == 1) && (boardValues[i-1][j+1] == 1) && (boardValues[i-2][j+2] == 1) && (boardValues[i-3][j+3] == 1)) {
    					seq = true;
    					winner = 1;
    				}
    				if ((boardValues[i][j] == 2) && (boardValues[i-1][j+1] == 2) && (boardValues[i-2][j+2] == 2) && (boardValues[i-3][j+3] == 2)) {
    					seq = true;
    					winner = 2;
    				}
    			}
    		}
    	}
    	
    	if (seq) {
    		try {
    			parent.setStatus(Game.intToColorName(winner) + " wins!");
    		} catch (Exception e) {
    			
    		}
    	}
    	return seq;
    }
    
    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        amountInColumn = new int[6];
        boardValues = new int[6][6];
        turn = 1;
        moves = new LinkedList<Integer>();
        
        repaint();

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 6; i++) {
        	for (int j = 0; j < 6; j++) {
        		int x = boardValues[i][j];
        		
        		g.setColor(Game.intToColor(x));
        		//System.out.println(x);
        		g.fillOval(110*i, 660-(110*(j+1)), 100, 100);
        	}
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(660, 660);
    }
}