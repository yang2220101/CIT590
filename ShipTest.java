package battleship;
/**
 * Test class for Ship.
 * @author Shiyan Yang and Shuqi Liu
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ship battleShip;
	Ship cruiser;
	Ship destroyer;
	Ship submarine;
	Ship emptySea;
	Ocean ocean;

	@BeforeEach
	void setUp() throws Exception {

		this.battleShip = new Battleship();
		this.cruiser = new Cruiser();
		this.destroyer = new Destroyer();
		this.submarine = new Submarine();
		this.emptySea = new EmptySea();
		this.ocean = new Ocean();
	}

	@Test
	void testShip() {
		//test constructors of 4 types of ship and emptySea
		assertEquals(4, battleShip.getLength());
		assertEquals(3, cruiser.getLength());
		assertEquals(2, destroyer.getLength());
		assertEquals(1, submarine.getLength());
		assertEquals(1, emptySea.getLength());	
	}

	@Test
	void testGetLength() {
		assertEquals(4, battleShip.getLength());
		assertEquals(3, cruiser.getLength());
		assertEquals(2, destroyer.getLength());
		assertEquals(1, submarine.getLength());
		assertEquals(1, emptySea.getLength());	
	}

	@Test
	void testGetBowRow() {
		//first set bow row, then test location of bow
		battleShip.setBowRow(9);
		assertEquals(9, battleShip.getBowRow());
		cruiser.setBowRow(6);
		assertEquals(6, cruiser.getBowRow());
		destroyer.setBowRow(3);
		assertEquals(3, destroyer.getBowRow());	
		submarine.setBowRow(0);
		assertEquals(0, submarine.getBowRow());	

	}

	@Test
	void testGetBowColumn() {
		//first set bow column, then test location of column
		battleShip.setBowColumn(8);
		assertEquals(8, battleShip.getBowColumn());
		cruiser.setBowColumn(5);
		assertEquals(5, cruiser.getBowColumn());
		destroyer.setBowColumn(0);
		assertEquals(0, destroyer.getBowColumn());	
		submarine.setBowColumn(7);
		assertEquals(7, submarine.getBowColumn());	
	}

	@Test
	void testGetHit() {
		//the elements of hit array should all be false before the game starts
		assertEquals(false, battleShip.getHit()[0]);
		assertEquals(false, cruiser.getHit()[1]);
		assertEquals(false, destroyer.getHit()[2]);
		assertEquals(false, submarine.getHit()[3]);
	}

	@Test
	void testIsHorizontal() {
		//first set horizontal status then test it
		battleShip.setHorizontal(true);
		assertEquals(true, battleShip.isHorizontal());
		cruiser.setHorizontal(false);
		assertEquals(false, cruiser.isHorizontal());
		destroyer.setHorizontal(true);
		assertEquals(true, destroyer.isHorizontal());
		submarine.setHorizontal(false);
		assertEquals(false, submarine.isHorizontal());
	}

	@Test
	void testSetBowRow() {
		//first set bow row, then test location of bow
		battleShip.setBowRow(9);
		assertEquals(9, battleShip.getBowRow());
		cruiser.setBowRow(6);
		assertEquals(6, cruiser.getBowRow());
		destroyer.setBowRow(3);
		assertEquals(3, destroyer.getBowRow());	
		submarine.setBowRow(0);
		assertEquals(0, submarine.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		//first set bow column, then test location of column
		battleShip.setBowColumn(8);
		assertEquals(8, battleShip.getBowColumn());
		cruiser.setBowColumn(5);
		assertEquals(5, cruiser.getBowColumn());
		destroyer.setBowColumn(0);
		assertEquals(0, destroyer.getBowColumn());	
		submarine.setBowColumn(7);
		assertEquals(7, submarine.getBowColumn());	
	}

	@Test
	void testSetHorizontal() {
		//first set horizontal status then test it
		battleShip.setHorizontal(true);
		assertEquals(true, battleShip.isHorizontal());
		cruiser.setHorizontal(false);
		assertEquals(false, cruiser.isHorizontal());
		destroyer.setHorizontal(true);
		assertEquals(true, destroyer.isHorizontal());
		submarine.setHorizontal(false);
		assertEquals(false, submarine.isHorizontal());
	}

	@Test
	void testGetShipType() {
		//test GetShipType() from 5 subclasses
		assertEquals("battleship", battleShip.getShipType());
		assertEquals("cruiser", cruiser.getShipType());
		assertEquals("destroyer", destroyer.getShipType());
		assertEquals("submarine", submarine.getShipType());
		assertEquals("empty", emptySea.getShipType());
	}

	@Test
	void testIsHit() {

		//hit the tail of a battleship
		battleShip.placeShipAt(3, 0, false, ocean);
		ocean.shootAt(0, 0);
		assertTrue(battleShip.isHit(0, 0));

		//hit a cruiser in the middle
		cruiser.placeShipAt(2, 2, false, ocean);
		ocean.shootAt(1, 2);
		assertTrue(cruiser.isHit(1, 2));

		//hit a destroyer
		destroyer.placeShipAt(5, 5, true, ocean);
		ocean.shootAt(5, 5);
		assertTrue(destroyer.isHit(5, 5));

		//hit a submarine
		submarine.placeShipAt(9, 9, true, ocean);
		ocean.shootAt(9, 9);
		assertTrue(submarine.isHit(9, 9));	

		//miss fire
		ocean.shootAt(4, 0);
		assertFalse(battleShip.isHit(4, 0));
		ocean.shootAt(8, 8);
		assertFalse(battleShip.isHit(8, 8));
	}

	@Test
	void testOkToPlaceShipAt() {
		//place 2 ships first
		battleShip.placeShipAt(0, 6, true, ocean);
		cruiser.placeShipAt(2, 4, false, ocean);

		//out of boundary
		assertFalse(battleShip.okToPlaceShipAt(5, 2, true, ocean));
		assertFalse(cruiser.okToPlaceShipAt(0, 9, false, ocean));

		//adjacent or cross to each other
		assertFalse(battleShip.okToPlaceShipAt(3, 4, false, ocean));
		assertFalse(cruiser.okToPlaceShipAt(0, 9, false, ocean));	
		assertFalse(submarine.okToPlaceShipAt(1, 7, true, ocean));

		//placement success
		assertTrue(cruiser.okToPlaceShipAt(9, 0, false, ocean));	
		assertTrue(battleShip.okToPlaceShipAt(8, 6, false, ocean));
		assertTrue(submarine.okToPlaceShipAt(9, 9, true, ocean));
	}

	@Test
	void testPlaceShipAt() {

		//place ships at certain locations
		battleShip.placeShipAt(9, 9, true, ocean);
		submarine.placeShipAt(0, 0, true, ocean);
		emptySea.placeShipAt(5, 5, true, ocean);
		//test if these locations are occupied
		assertTrue(ocean.isOccupied(9, 9));
		assertTrue(ocean.isOccupied(9, 8));
		assertTrue(ocean.isOccupied(9, 7));
		assertTrue(ocean.isOccupied(0, 0));
		assertFalse(ocean.isOccupied(5, 5));

	}

	@Test
	void testShootAt() {
		//hit the tail of a battleship
		battleShip.placeShipAt(3, 0, false, ocean);
		ocean.shootAt(0, 0);
		assertTrue(battleShip.shootAt(0, 0));

		//hit a cruiser in the middle
		cruiser.placeShipAt(2, 2, false, ocean);
		ocean.shootAt(1, 2);
		assertTrue(cruiser.shootAt(1, 2));

		//hit a destroyer
		destroyer.placeShipAt(5, 5, true, ocean);
		ocean.shootAt(5, 5);
		assertTrue(destroyer.shootAt(5, 5));

		//hit a submarine
		submarine.placeShipAt(9, 9, true, ocean);
		ocean.shootAt(9, 9);
		assertTrue(submarine.shootAt(9, 9));	

		//miss fire
		ocean.shootAt(4, 0);
		assertFalse(battleShip.shootAt(4, 0));
		ocean.shootAt(8, 8);
		assertFalse(battleShip.shootAt(8, 8));
	}

	@Test
	void testIsSunk() {
		//sink a battleship
		battleShip.placeShipAt(3, 0, false, ocean);
		ocean.shootAt(0, 0);
		ocean.shootAt(1, 0);
		ocean.shootAt(2, 0);
		ocean.shootAt(3, 0);
		assertTrue(battleShip.isSunk());

		//fail to sink a cruiser
		cruiser.placeShipAt(2, 3, false, ocean);
		ocean.shootAt(0, 3);
		ocean.shootAt(1, 3);
		assertFalse(cruiser.isSunk());

		//fail to sink a destroyer
		destroyer.placeShipAt(4, 5, true, ocean);
		ocean.shootAt(4, 5);
		assertFalse(destroyer.isSunk());

		//sink a submarine
		submarine.placeShipAt(9, 9, true, ocean);
		ocean.shootAt(9, 9);
		assertTrue(submarine.isSunk());	
	}

	@Test
	void testToString() {
		//before sunk
		assertEquals("x", battleShip.toString());
		assertEquals("x", cruiser.toString());
		assertEquals("x", destroyer.toString());
		assertEquals("x", submarine.toString());
		assertEquals("-", emptySea.toString());

		//after sunk 
		//sink a battleship
		battleShip.placeShipAt(3, 0, false, ocean);
		ocean.shootAt(0, 0);
		ocean.shootAt(1, 0);
		ocean.shootAt(2, 0);
		ocean.shootAt(3, 0);
		assertTrue(battleShip.isSunk());

		//sink a cruiser
		cruiser.placeShipAt(2, 3, false, ocean);
		ocean.shootAt(0, 3);
		ocean.shootAt(1, 3);
		ocean.shootAt(2, 3);
		assertTrue(cruiser.isSunk());

		//sink a destroyer
		destroyer.placeShipAt(4, 5, true, ocean);
		ocean.shootAt(4, 5);
		ocean.shootAt(4, 4);
		assertTrue(destroyer.isSunk());

		//sink a submarine
		submarine.placeShipAt(9, 9, true, ocean);
		ocean.shootAt(9, 9);
		assertTrue(submarine.isSunk());

		//try to sink empty sea
		emptySea.placeShipAt(0, 9, true, ocean);
		ocean.shootAt(0, 9);

		assertEquals("s", submarine.toString());
		assertEquals("s", destroyer.toString());
		assertEquals("s", cruiser.toString());
		assertEquals("s", battleShip.toString());
		assertEquals("-", emptySea.toString());
	}

}
