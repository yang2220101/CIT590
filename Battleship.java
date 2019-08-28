package battleship;
/**
 * This is the class for battleship, which is a subclass of Ship.
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public class Battleship extends Ship {
	/**
	 * ship type of the battleship.
	 */
	private static final String shipType = "battleship";

	/**
	 * 
	 */
	private static final int shipLength = 4;

	/**
	 * set the length variable to the correct value.
	 */
	public Battleship() {
		super(shipLength); 
	}

	@Override
	public String getShipType() {
		return shipType;
	}

}
