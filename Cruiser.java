package battleship;
/**
 * This is the class for cruiser, which is a subclass of Ship.
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public class Cruiser extends Ship {
	/**
	 * ship type of the cruiser.
	 */
	private static final String shipType = "cruiser";

	/**
	 * 
	 */
	private static final int shipLength = 3;

	/**
	 * set the length variable to the correct value.
	 */
	public Cruiser() {
		super(shipLength); 
	}

	@Override
	public String getShipType() {
		return shipType;
	}

}
