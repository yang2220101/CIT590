package battleship;
/**
 * This is the class for submarine, which is a subclass of Ship.
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public class Submarine extends Ship {
	/**
	 * ship type of the submarine.
	 */
	private static final String shipType = "submarine";

	/**
	 * 
	 */
	private static final int shipLength = 1;

	/**
	 * set the length variable to the correct value.
	 */
	public Submarine() {
		super(shipLength); 
	}

	@Override
	public String getShipType() {
		return shipType;
	}

}
