package battleship;
/**
 * Test class for Ocean.
 * @author Shiyan Yang and Shuqi Liu
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ship battleShip;
	Ship cruiser1;
	Ship cruiser2;
	Ship destroyer1;
	Ship destroyer2;
	Ship destroyer3;
	Ship submarine1;
	Ship submarine2;
	Ship submarine3;
	Ship submarine4;
	Ship emptySea;
	Ocean ocean;

	//to test placeAllShipsRandomly() 
	Ocean ocean2;
	Ocean ocean3;

	@BeforeEach
	void setUp() throws Exception {

		this.battleShip = new Battleship();
		this.cruiser1 = new Cruiser();
		this.cruiser2 = new Cruiser();
		this.destroyer1 = new Destroyer();
		this.destroyer2 = new Destroyer();
		this.destroyer3 = new Destroyer();
		this.submarine1 = new Submarine();
		this.submarine2 = new Submarine();
		this.submarine3 = new Submarine();
		this.submarine4 = new Submarine();
		this.emptySea = new EmptySea();
		this.ocean = new Ocean();

		//place ships for general tests
		battleShip.placeShipAt(3, 0, false, ocean);

		cruiser1.placeShipAt(2, 2, false, ocean);
		cruiser2.placeShipAt(2, 4, false, ocean);

		destroyer1.placeShipAt(5, 1, true, ocean);
		destroyer2.placeShipAt(5, 4, true, ocean);
		destroyer3.placeShipAt(5, 9, true, ocean);

		submarine1.placeShipAt(9, 9, true, ocean);
		submarine2.placeShipAt(9, 7, true, ocean);
		submarine3.placeShipAt(9, 5, true, ocean);
		submarine4.placeShipAt(9, 3, true, ocean);
	}

	@Test
	void testOcean() {
		//test if all game variables are initialized
		assertEquals(0, ocean.getShotsFired());
		assertEquals(0, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());

		//test if cells that not occupied are emptySea
		for (int i = 6; i < 9; i++) {
			for(int j = 0; j < 10; j++) {
				assertTrue(ocean.getShipArray()[i][j] instanceof EmptySea);
			}
		}
	}

	@Test
	void testPlaceAllShipsRandomly() {
		//randomly place ships on new ocean2
		this.ocean2 = new Ocean();
		ocean2.placeAllShipsRandomly();

		//use the bow coordinations and isHorizontal of all ships in ocean2 to test in a new ocean3	
		this.ocean3 = new Ocean();
		assertTrue(battleShip.okToPlaceShipAt(ocean2.coor[0][0], ocean2.coor[1][0], ocean2.hor[0], ocean3));
		assertTrue(cruiser1.okToPlaceShipAt(ocean2.coor[0][1], ocean2.coor[1][1], ocean2.hor[1], ocean3));
		assertTrue(cruiser2.okToPlaceShipAt(ocean2.coor[0][2], ocean2.coor[1][2], ocean2.hor[2], ocean3));
		assertTrue(destroyer1.okToPlaceShipAt(ocean2.coor[0][3], ocean2.coor[1][3], ocean2.hor[3], ocean3));
		assertTrue(destroyer2.okToPlaceShipAt(ocean2.coor[0][4], ocean2.coor[1][4], ocean2.hor[4], ocean3));
		assertTrue(destroyer3.okToPlaceShipAt(ocean2.coor[0][5], ocean2.coor[1][5], ocean2.hor[5], ocean3));
		assertTrue(submarine1.okToPlaceShipAt(ocean2.coor[0][6], ocean2.coor[1][6], ocean2.hor[6], ocean3));
		assertTrue(submarine2.okToPlaceShipAt(ocean2.coor[0][7], ocean2.coor[1][7], ocean2.hor[7], ocean3));
		assertTrue(submarine3.okToPlaceShipAt(ocean2.coor[0][8], ocean2.coor[1][8], ocean2.hor[8], ocean3));
		assertTrue(submarine4.okToPlaceShipAt(ocean2.coor[0][9], ocean2.coor[1][9], ocean2.hor[9], ocean3));

		//sink all ships to check the ship numbers
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				ocean2.shootAt(i, j); 
			}
		}
		assertTrue(ocean2.isGameOver());

	}

	@Test
	void testIsOccupied() {

		//test if these locations are occupied
		assertTrue(ocean.isOccupied(9, 9));
		assertFalse(ocean.isOccupied(9, 8));
		assertTrue(ocean.isOccupied(9, 7));
		assertTrue(ocean.isOccupied(0, 0));
		assertFalse(ocean.isOccupied(5, 5));
	}

	@Test
	void testShootAt() {
		//hit the tail of a battleship
		ocean.shootAt(0, 0);	

		//hit a cruiser in the middle 3 times but fail to sink it 
		ocean.shootAt(1, 2);
		ocean.shootAt(1, 2);
		ocean.shootAt(0, 2);

		//hit a destroyer twice and sink it  
		ocean.shootAt(5, 3);
		ocean.shootAt(5, 4);

		//hit a submarine
		ocean.shootAt(9, 9);

		//miss fire
		ocean.shootAt(4, 0);
		assertFalse(battleShip.shootAt(4, 0));
		ocean.shootAt(8, 8);
		assertFalse(battleShip.shootAt(8, 8));

		//test counts
		assertEquals(9, ocean.getShotsFired());
		assertEquals(7, ocean.getHitCount());
		assertEquals(2, ocean.getShipsSunk());
	}

	@Test
	void testGetShotsFired() {
		//tested with "ShootAt"
	}

	@Test
	void testGetHitCount() {
		//tested with "ShootAt"
	}

	@Test
	void testGetShipsSunk() {
		//tested with "ShootAt"
	}

	@Test
	void testIsGameOver() {
		//should return false before game starts
		assertFalse(ocean.isGameOver());

		//not all ships are sunk
		ocean.shootAt(0, 0);
		ocean.shootAt(1, 0);
		ocean.shootAt(2, 0);
		ocean.shootAt(3, 0);
		ocean.shootAt(9, 9);
		assertFalse(ocean.isGameOver());

		//all ships are sunk
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				ocean.shootAt(i, j); 
			}
		}
		assertTrue(ocean.isGameOver());


	}

	@Test
	void testGetShipArray() {
		//test array size
		assertTrue(ocean.getShipArray().length == 10);
		for(int i = 0; i < 10; i++) {
			assertTrue(ocean.getShipArray()[i].length == 10);
		}
		//test ship placements
		assertTrue(ocean.getShipArray()[3][0] instanceof Battleship);
		assertTrue(ocean.getShipArray()[2][2] instanceof Cruiser);
		assertTrue(ocean.getShipArray()[5][9] instanceof Destroyer);
		assertTrue(ocean.getShipArray()[9][9] instanceof Submarine);
		assertTrue(ocean.getShipArray()[6][9] instanceof EmptySea);
	}

	@Test
	void testPrint() {
		//no test needed for this method
	}

}
