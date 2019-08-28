package battleship;

import java.util.Random;
/**
 * This contains a 10x10 array of Ships, representing an ocean.
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public class Ocean {
	/**
	 * Used to quickly determine which ship is in any given location.
	 */
	private Ship[][]ships = new Ship[10][10];

	/**
	 * The total number of shots fired by the user.
	 */
	private int shotsFired;

	/**
	 * The number of times a shot hit a ship. 
	 */
	private int hitCount;

	/**
	 * The number of ships sunk (10 ships in all).
	 */
	private int shipsSunk;

	/**
	 * For OceanTest, help test placeAllShipsRandomly(). 
	 */
	int[][]coor =  new int[2][10];
	boolean[]hor = new boolean[10];

	/**
	 * Creates an empty ocean.
	 */
	public Ocean() {
		this.hitCount = 0;
		this.shipsSunk = 0;
		this.shotsFired = 0;
		this.createEmptyOcean();
	}

	/**
	 * Fills the ships array with EmptySea objects.
	 */
	private void createEmptyOcean() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Ship emptysea = new EmptySea();
				emptysea.placeShipAt(i, j, true, this);
			}
		}
	}

	/**
	 * Place all ten ships randomly on the (initially empty) ocean.
	 */
	void placeAllShipsRandomly() {
		Random random = new Random();
		int row;
		int column;
		int bool;
		boolean isHorizontal;

		//place battleship
		Ship battleship = new Battleship(); 
		row = random.nextInt(10);
		column = random.nextInt(10);
		bool = random.nextInt(2);
		if(bool == 0) {
			isHorizontal = true;
		}else {
			isHorizontal = false;
		}

		//if the location is not OK, try next random location.
		while(!battleship.okToPlaceShipAt(row, column, isHorizontal, this)) {
			row = random.nextInt(10);
			column = random.nextInt(10);
			bool = random.nextInt(2);
			if(bool == 0) {
				isHorizontal = true;
			}else {
				isHorizontal = false;
			}
		}
		battleship.placeShipAt(row, column, isHorizontal, this);
		this.coor[0][0] = row;
		this.coor[1][0] = column;
		this.hor[0] = isHorizontal;


		// place cruisers		
		for(int i = 0; i < 2; i++){
			Ship cruiser = new Cruiser(); 
			row = random.nextInt(10);
			column = random.nextInt(10);		
			bool = random.nextInt(2);
			if(bool == 0) {
				isHorizontal = true;
			}else {
				isHorizontal = false;
			}
			//if the location is not OK, try next random location.
			while(!cruiser.okToPlaceShipAt(row, column, isHorizontal, this)) {
				row = random.nextInt(10);
				column = random.nextInt(10);

				bool = random.nextInt(2);
				if(bool == 0) {
					isHorizontal = true;
				}else {
					isHorizontal = false;
				} 
			}
			cruiser.placeShipAt(row, column, isHorizontal, this);
			this.coor[0][1 + i] = row;
			this.coor[1][1 + i] = column;
			this.hor[1 + i] = isHorizontal;
		}
		//place destroyers	
		for(int j = 0; j < 3; j++){
			// Randomly generate position
			Ship destroyer = new Destroyer(); 
			row = random.nextInt(10);
			column = random.nextInt(10);		
			bool = random.nextInt(2);
			if(bool == 0) {
				isHorizontal = true;
			}else {
				isHorizontal = false;
			} 
			//If the location is not OK, try next random location.
			while(!destroyer.okToPlaceShipAt(row, column, isHorizontal, this)) {
				row = random.nextInt(10);
				column = random.nextInt(10);		
				bool = random.nextInt(2);
				if(bool == 0) {
					isHorizontal = true;
				}else {
					isHorizontal = false;
				} 
			}
			destroyer.placeShipAt(row, column, isHorizontal, this);	
			this.coor[0][3 + j] = row;
			this.coor[1][3 + j] = column;
			this.hor[3 + j] = isHorizontal;
		}
		//place submarines	
		for(int k = 0; k < 4; k++){
			// Randomly generate position
			Ship submarine = new Submarine(); 
			row = random.nextInt(10);
			column = random.nextInt(10);		
			bool = random.nextInt(2);
			if(bool == 0) {
				isHorizontal = true;
			}else {
				isHorizontal = false;
			}  
			//If the location is not OK, try next random location.
			while(!submarine.okToPlaceShipAt(row, column, isHorizontal, this)) {
				row = random.nextInt(10);
				column = random.nextInt(10);		
				bool = random.nextInt(2);
				if(bool == 0) {
					isHorizontal = true;
				}else {
					isHorizontal = false;
				}  
			}
			submarine.placeShipAt(row, column, isHorizontal, this);
			this.coor[0][6 + k] = row;
			this.coor[1][6 + k] = column;
			this.hor[6 + k] = isHorizontal;
		}
	}

	/**
	 * Returns true if the given location contains a ship, false if it does not.
	 * @param row
	 * @param column
	 * @return true if occupied
	 */
	boolean isOccupied(int row, int column) {
		if(this.ships[row][column] instanceof EmptySea){
			return false;
		}else {
			return true;
		}
	}

	/**
	 * Returns true if the given location contains a ¡±real¡± ship, still afloat, (not
	 * an EmptySea), false if it does not.
	 * @param row
	 * @param column
	 * @return true if hit a ship
	 */
	boolean shootAt(int row, int column) {
		this.shotsFired ++;
		Ship target = this.ships[row][column];

		//if hit empty sea
		if (target instanceof EmptySea) {
			target.wasHit = true;
			return false;
		}

		//if target already sunk
		if (target.isSunk()) {
			return false;
		}


		//the ship hit sunk or not
		boolean isHit = target.shootAt(row, column);
		if(isHit) {
			this.hitCount ++;
		}
		if(target.isSunk()) {
			this.shipsSunk ++ ;
		}
		return isHit;
	}

	/**
	 * Returns the number of shots fired.
	 * @return the number of shots fired
	 */
	int getShotsFired() {
		return this.shotsFired;
	}

	/**
	 * Returns the number of hits recorded.
	 * @return the number of hits recorded
	 */
	int getHitCount() {
		return this.hitCount;
	}

	/**
	 * Returns the number of ships sunk.
	 * @return the number of ships sunk
	 */
	int getShipsSunk() {
		return this.shipsSunk;
	}

	/**
	 * Returns true if all ships have been sunk, otherwise false.
	 * @return true if all ships have been sunk
	 */
	boolean isGameOver() {
		if(this.getShipsSunk() == 10) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Returns the 10x10 array of Ships.
	 * @return array of Ships
	 */
	Ship[][] getShipArray(){
		return this.ships;
	}

	/**
	 * Prints the Ocean.
	 */
	void print() {
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for(int i = 0; i < 10; i++) {
			System.out.print(i + " ");

			//print status for each row
			for(int j =0; j < 10; j++) {
				Ship ship = this.ships[i][j];

				if (ship.isSunk() || ship.isHit(i, j)){
					System.out.print(ship + " ");
				}
				else{
					System.out.print("." + " ");
				}	
			}
			System.out.println("");	
		}		
	}
}
