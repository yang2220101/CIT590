package battleship;
/**
 * The BattleshipGame class is the main class.
 * @author Shiyan Yang and Shuqi Liu
 *
 */
import java.util.*;
public class BattleshipGame {
	public static void main(String[] args) {
		//to decide if keeping running the game
		boolean playing = true;
		Scanner scan = new Scanner(System.in);

		while(playing) {
			System.out.println("************Welcome to Battleship!************");
			System.out.println("*********Try to sink all enemy ships!*********");
			System.out.println("");

			//set up the game
			Ocean ocean = new Ocean();
			ocean.placeAllShipsRandomly();
			ocean.print();

			//game start
			while(!ocean.isGameOver()) {

				System.out.println("Please enter the number of row and column you wish to fire at (comma separated)!");

				String coor = scan.nextLine();

				//the length requirement can prevent the user from entering number greater than 9
				if (coor.length()!= 3) {
					System.out.println("Please enter coordination exactly as : row, column");
					System.out.println("Row and column number must between 0 and 9.");
					continue;
				}

				//get the numbers from the string the user inputs
				String rowStr = coor.substring(0, 1);
				String columnStr = coor.substring(2, 3);

				//make sure user inputs are integers
				try {Integer.parseInt(rowStr);
				} 
				catch (NumberFormatException e) {
					System.out.println("Plase enter integers between 0 and 9");
					continue;
				}
				int row = Integer.parseInt(rowStr);

				try {Integer.parseInt(columnStr);
				} 
				catch (NumberFormatException e) {
					System.out.println("Plase enter integers between 0 and 9");
					continue;
				}
				int column = Integer.parseInt(columnStr);

				//accept shots from user
				boolean fire = ocean.shootAt(row, column);

				//if a ship is hit
				if(fire) {
					System.out.println("hit");
					Ship[][] shipArray = ocean.getShipArray();
					Ship ship = shipArray[row][column];

					//if the ship is sunk
					if(ship.isSunk()) {
						System.out.println("You just sank a ship - " + ship.getShipType() + ".");
					}
				}else {
					//if the fire is miss
					System.out.println("miss");
				}

				//print the updated ocean
				System.out.println("");
				ocean.print();		

			}

			//display the results and final scores
			System.out.println("Congratulation! All enemy ships have been sunk!");
			System.out.printf("Total shots fired: %d\n", ocean.getShotsFired());
			System.out.printf("Total hits: %d\n", ocean.getHitCount());

			//ask the user if he/she wants to play again
			System.out.println("Do you want to play again? ");
			System.out.println("Press y to start again, press other key to exit.");
			String again = scan.next();
			if(!("y".equals(again))) {
				//quit game
				playing = false;
				System.out.println("Game Over");				
			}
		}
		scan.close();
	}

}
