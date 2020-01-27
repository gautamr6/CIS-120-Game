import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/** 
 *  You can use this file (and others) to test your
 *  implementation.
 */

public class GameTest {

    @Test
    public void testVerticalWin() {
    	Game g = new Game();
    	g.run();
    	Board b = g.getBoard();
        int[][] ar = {{1,1,1,1,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};
        b.setBoardValues(ar);
        assertTrue(b.win());
    }
    
    @Test
    public void testHorizontalWin() {
    	Game g = new Game();
    	g.run();
    	Board b = g.getBoard();
        int[][] ar = {{2,1,1,0,0,0},{2,0,0,0,0,0},{2,0,0,0,0,0},{2,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};
        b.setBoardValues(ar);
        assertTrue(b.win());
    }
    
    @Test
    public void testDiagonalWinDirection1() {
    	Game g = new Game();
    	g.run();
    	Board b = g.getBoard();
        int[][] ar = {{2,1,1,0,0,0},{0,2,0,0,0,0},{0,0,2,0,0,0},{0,0,0,2,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};
        b.setBoardValues(ar);
        assertTrue(b.win());
    }
    
    @Test
    public void testDiagonalWinDirection2() {
    	Game g = new Game();
    	g.run();
    	Board b = g.getBoard();
        int[][] ar = {{1,1,1,2,0,0},{0,0,2,0,0,0},{0,2,0,0,0,0},{2,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};
        b.setBoardValues(ar);
        assertTrue(b.win());
    }
    
    @Test
    public void testMultiColorSequence() {
    	Game g = new Game();
    	g.run();
    	Board b = g.getBoard();
        int[][] ar = {{1,1,2,1,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};
        b.setBoardValues(ar);
        assertFalse(b.win());
    }
    
    @Test
    public void testFileInput() {
    	Board b = new Board(new File("files/test.txt"));
    	int[][] ar = {{1,2,0,0,0,0},{2,1,0,0,0,0},{1,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0},{2,0,0,0,0,0}};
    	assertArrayEquals(b.getBoardValues(), ar);
    }
    
    @Test
    public void testWinLabel() {
    	Game g = new Game();
    	g.run();
    	Board b = g.getBoard();
        int[][] ar = {{1,1,1,2,0,0},{0,0,2,0,0,0},{0,2,0,0,0,0},{2,0,0,0,0,0},{0,0,0,0,0,0},{0,0,0,0,0,0}};
        b.setBoardValues(ar);
        b.win();
        System.out.println(g.getStatus());
        assertEquals(g.getStatus(), "Red wins!");
    }

}
