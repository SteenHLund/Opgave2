import java.io.*;
import java.text.ParseException;
import java.util.*;

/*
 * This class models a tunnel map 
 */
public class TunnelMap {
	public Scanner sc;
	public int rows, numGhosts, numZombies; // number of map rows
	public int cols; // number of map columns
	public char[][] mapData; // raw map data, i.e., mapData[i][j] is the char at
								// row i and column j of the map file. Here i
								// goes from 0 to rows-1, and j from 0 to
								// columns-1.
	public Position[] ghosts; // an array of the ghost positions. The size
								// should be equal to the number of ghosts.
	public Position[] zombies; // an array of the zombie positions. The size
								// should be equal to the number of zombies.

	public TunnelMap() {
		// the following code initializes the object with a simple test map

		this.rows = 4;
		this.cols = 4;
//		this.mapData = new char[][] { { '#', ' ', '#', '#' }, { ' ', ' ', ' ', 'Z' }, { '#', 'G', ' ', '#' },
//				{ '#', '#', ' ', '#' }, };
//		this.ghosts = new Position[] {};
//		this.zombies = new Position[] {};

	}

	/*
	 * Exercise 2.1
	 */
	public void loadMap(String fileName) {
		// System.out.println("Loading map...");

		// length >= 1
		// width >= 1

		// load file using scanner

		// read file data (format):
		// - length
		// - Witdh
		// - Antal Ghosts
		// - Antal Zombies

		File file = new File(fileName);

		// Load 4 first lines here:
		
		boolean gotError = false;
		try {

			sc = new Scanner(file);

			this.rows = Integer.parseInt(sc.nextLine());
			this.cols = Integer.parseInt(sc.nextLine());
			this.numGhosts = Integer.parseInt(sc.nextLine());
			this.numZombies = Integer.parseInt(sc.nextLine());

			if (rows < 1 || cols < 1 || numGhosts < 0 || numZombies < 0)
				rows = 1 / 0;

			this.mapData = new char[this.rows][this.cols];
			
			int numOfGhosts = 0, numOfZombies = 0;
			this.ghosts = new Position[numGhosts];
			this.zombies = new Position[numZombies];
			
			for (int j = 0; j < this.rows; j++) {
				String line = sc.nextLine();

				for (int i = 0; i < this.cols; i++) {
					if (this.cols == line.length()) {
						if (line.charAt(i) == ' ') {
							this.mapData[j][i] = ' ';
						} else if (line.charAt(i) == '#') {
							this.mapData[j][i] = '#';
						} else if (line.charAt(i) == 'G') {
							numOfGhosts++;
							if (numOfGhosts > numGhosts)
								gotError = true;
								numGhosts /= 0;
							this.ghosts[numOfGhosts] = new Position(j, i);
							this.mapData[j][i] = 'G';
						} else if (line.charAt(i) == 'Z') {
							numOfZombies++;
							if (numOfZombies > numZombies)
								gotError = true;
								numZombies /= 0;
							this.zombies[numOfZombies] = new Position(j, i);
							this.mapData[j][i] = 'Z';
						} else {
							gotError = true;
							numOfZombies /= 0;
						}
					} else {
						gotError = true;
						numOfZombies /= 0;
					}
				}
				if (numGhosts != numOfGhosts && gotError == false) {
					gotError = true;
					System.out.println("Map loading error");
				}

				else if (numZombies != numOfZombies && gotError == false) {
					gotError = true;
					System.out.println("Map loading error");
				}
			}
			
			sc.close();
		} catch (Exception e) {
			gotError = true;
			System.out.println("Map loading error");
		}
	}

	/*
	 * Exercise 2.2
	 */
	public int[][] distanceMap(Position start) {
		return null;
	}

	/*
	 * Exercise 2.3
	 */
	public Position[] findRoute(Position start, Position end) {
		return null;
	}

	/*
	 * Exercise 2.4
	 */
	public Position[] findSpookyRoute(Position start, Position end) {
		return null;
	}

	/*
	 * Exercise 2.5
	 */
	public boolean isSafeRoute(Position[] route) {
		return true;
	}

	/*
	 * The following methods concerns the movement of zombies. You can call them
	 * in your code, but you should not modify them!
	 */

	/*
	 * Returns the position that a zombie located on {currentPosition} will move
	 * to if its target is located at {targetPosition}. YOU SHOULD NOT MODIFY
	 * THIS METHOD
	 */
	public Position getNextZombiePosition(Position currentPosition, Position targetPosition) {
		Position nextPosition = new Position(currentPosition.row, currentPosition.col);
		int dr = targetPosition.row - currentPosition.row;
		int dc = targetPosition.col - currentPosition.col;

		if (Math.abs(dr) > Math.abs(dc)) {
			// move vertically
			dr = dr / Math.abs(dr);
			dc = 0;
		} else if (dc != 0) {
			// move horizontally
			dr = 0;
			dc = dc / Math.abs(dc);
		}
		// move to next position unless it's a wall
		if (mapData[currentPosition.row + dr][currentPosition.col + dc] != '#') {
			nextPosition.row += dr;
			nextPosition.col += dc;
		}

		return nextPosition;
	}

	/*
	 * Moves all zombies one step towards the position {target}. Assumes
	 * {target} is a position within the map boundaries. YOU SHOULD NOT MODIFY
	 * THIS METHOD
	 */
	public void moveZombies(Position targetPosition) {
		for (int i = 0; i < zombies.length; i++) {
			zombies[i] = getNextZombiePosition(zombies[i], targetPosition);
		}
	}
}
