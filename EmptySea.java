package battleship;
/**
 * This is the class for EmptySea, which is a subclass of Ship.
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public class EmptySea extends Ship {
	/**
	 * 
	 */
	private static final int shipLength = 1;

	/**
	 * ship type of the empty sea.
	 */
	private static final String shipType = "empty";

	/**
	 * This constructor sets the length variable to 1.
	 */
	public EmptySea() {
		super(shipLength);
	}

	@Override
	boolean shootAt(int row, int column) {
		return false;
	}

	@Override
	boolean isSunk() {
		return false;
	}

	@Override
	public String toString() {
		return "-";
	}

	@Override
	public String getShipType() {
		return shipType;
	}

}
