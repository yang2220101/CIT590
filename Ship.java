package battleship;
/**
 * This is the abstract ship class for battleship game.
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public abstract class Ship {

	/**
	 * The row that contains the bow (front part of the ship).
	 */
	private int bowRow;

	/**
	 * The column that contains the bow (front part of the ship).
	 */
	private int bowColumn;

	/**
	 * The length of the ship.
	 */
	private int length;

	/**
	 * A boolean that represents whether the ship is going to be placed horizontally or vertically.
	 */
	private boolean horizontal;

	/**
	 * An array of 4 booleans that indicate whether that part of the ship has been hit or not.
	 */
	private boolean[] hit;

	/**
	 * A boolean that represents whether this location was hit.
	 */
	protected boolean wasHit;

	/**
	 * This constructor sets the length property of the particular ship and initializes the hit array.
	 * @param length
	 */
	public Ship(int length) {
		this.length = length;
		this.hit = new boolean[4];
	}

	/**
	 * Returns the ship length.
	 * @return length
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * Returns the row corresponding to the position of the bow.
	 * @return bowRow
	 */
	public int getBowRow() {
		return this.bowRow;
	}

	/**
	 * Returns the bow column location.
	 * @return bowColumn
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}

	/**
	 * Returns the hit array.
	 * @return hit
	 */
	public boolean[] getHit() {
		return this.hit;
	}

	/**
	 * Returns whether the ship is horizontal or not.
	 * @return horizontal
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}

	/**
	 * Sets the value of bowRow.
	 * @param row
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}

	/**
	 * Sets the value of bowColumn.
	 * @param column
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}

	/**
	 * Sets the value of the instance variable horizontal.
	 * @param horizontal
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * Returns the type of ship as a String.
	 * @return type of ship
	 */
	public abstract String getShipType();

	/**
	 * Return whether the ship is hit at given location.
	 * @param row
	 * @param column
	 * @return true if hit
	 */
	public boolean isHit(int row, int column) {
		int shipPartCount = 0;

		if(this instanceof EmptySea && this.getBowColumn()== column && this.wasHit) {
			return true;
		}	

		if(this.isHorizontal()) {
			shipPartCount = this.getBowColumn() - column;
			if(0 <= shipPartCount && shipPartCount <= 3) {
				return this.getHit()[shipPartCount];
			}		
		}
		else if(!this.isHorizontal()) {
			shipPartCount = this.getBowRow() - row;
			if(0 <= shipPartCount && shipPartCount <= 3) {
				return this.getHit()[shipPartCount];
			}	
		}
		return false;
	}


	/**
	 * Based on the given row, column, and orientation, returns true if it is okay
	 * to put a ship of this length with its bow in this location.
	 * @param row
	 * @param column
	 * @return true if OK to place ship
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		Ship[][] shipArray = ocean.getShipArray();
		//the ship must not stick out beyond the array and be put on the empty sea
		if (horizontal) {
			if((column - this.length) < -1) {
				return false;
			}
			for (int i = 0; i < this.length; i++) {
				if(!(shipArray[row][column - i] instanceof EmptySea)) {
					return false;
				}
			}
		}else {
			if ((row - this.length) < -1) {
				return false;
			}
			for (int i = 0; i < this.length; i++) {
				if(!(shipArray[row - i][column] instanceof EmptySea)) {
					return false;
				}
			}
		}

		//All surrounding cells must not be occupied
		if (horizontal) {
			for (int i = -1; i < this.length + 1; i++) {
				try{
					for (int j = -1; j < 2; j++) {
						try {
							if(!(shipArray[row + j][column - i] instanceof EmptySea)) {
								return false;
							}	
						}
						catch(ArrayIndexOutOfBoundsException e) {
							continue;
						}					
					}	
				}catch(ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}
		}
		else {
			for (int i = -1; i < this.length + 1; i++) {
				try{
					for (int j = -1; j < 2; j++) {
						try {
							if(!(shipArray[row - i][column + j] instanceof EmptySea)) {
								return false;
							}	
						}
						catch(ArrayIndexOutOfBoundsException e) {
							continue;
						}					
					}	
				}catch(ArrayIndexOutOfBoundsException e) {
					continue;
				}
			}			
		}
		return true; 
	}

	/**
	 * Puts the ship in the ocean.
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {

		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		int shipLength = this.getLength();
		//place horizontal ship
		if(this.isHorizontal()) {
			int loc = column - shipLength + 1;
			for (int i = loc; i <= column; i++) {
				ocean.getShipArray()[row][i] = this;
			}
		}else {
			//place vertical ship
			int loc = row - shipLength + 1;
			for (int i = loc; i <= row; i++) {
				ocean.getShipArray()[i][column] = this;
			}
		}
	}

	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn't
	 * been sunk, mark that part of the ship as hit.
	 * @param row
	 * @param column
	 * @return true if hit
	 */
	boolean shootAt(int row, int column) {

		if(this.isHorizontal()) {
			if(row != this.bowRow) {
				return false;			
			}

			//check if any part of ship is hit
			for(int i = 0; i < this.length; i++) {
				if(column == this.bowColumn - i) {
					this.hit[i] = true;
					return true;
				}
			}
		}

		else { 
			if(column != this.bowColumn) {
				return false;
			}

			//check if any part of ship is hit
			for(int i = 0; i < this.length; i++) {
				if(row == this.bowRow - i) {
					this.hit[i] = true;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Return true if every part of the ship has been hit, false otherwise.
	 * @return true if all hit
	 */
	boolean isSunk() {
		int count = 0;
		//count how many parts have been hit
		for (int i = 0; i < 4; i++) {
			if (hit[i]) {
				count++;
			}
		}
		if (count == this.getLength()) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public String toString() {
		if(this.isSunk()) {
			return "s";
		}else {
			return "x";
		}

	}

}
