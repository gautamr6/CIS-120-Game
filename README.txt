=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: gautam1
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2-D Arrays
  I used a 2-D int array to model the state of my connect four board.
  For each element, a value of 0 represents an empty space, 1 represents a green piece, and 2 a red piece.

  2. Collections
  I used a LinkedList<Integer> to store the sequence of moves made by the two players.
  This application:
  - iterates through the list when reading/writing to the text file
  - adds to the list when a new move is made
  - doesn't ever have to access the element at a specific index
  These three factors make a LinkedList (rather than an ArrayList or other List) the most efficient data structure.
  Each Integer value represents the column in which the player chose to drop their piece.

  3. File I/O
  I implemented a Save button allowing the user to save a game in progress to a text file and come back to it later.
  Using the LinkedList of player moves, the application writes the sequence of moves to the text file.
  When the application is reopened, it loads the game state from the text file by replaying the sequence of moves.

  4. JUnit Tests
  I tested the different types of winning sequences, as well as the file reading functionality.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
 
 Game:
 The main class, which implements Runnable.
 Constructs the GUI framework, including the buttons for resetting, saving, and dropping a piece.
 
Board:
Represents the connect four board. Has an instance variable storing the state of the game.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

I had some trouble figuring out the nuances of file reading and writing, but I was able to iron out my bugs by reading the Javadocs carefully.
Refining the layout of the GUI to line up properly was also challenging.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

There is a good separation of functionality between the Game and Board classes.
I encapsulate private state where necessary, using getter methods rather than public variables.
I wouldn't refactor anything.

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
