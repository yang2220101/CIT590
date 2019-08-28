package battleship;
/**
 * This is the class for destroyer, which is a subclass of Ship.
 * @author Shiyan Yang and Shuqi Liu
 *
 */
public class Destroyer extends Ship {
	/**
	 * ship type of the destroyer.
	 */
	private static final String shipType = "destroyer";

	/**
	 * 
	 */
	private static final int shipLength = 2;

	/**
	 * set the length variable to the correct value.
	 */
	public Destroyer() {
		super(shipLength); 
	}

	@Override
	public String getShipType() {
		return shipType;
	}

}
