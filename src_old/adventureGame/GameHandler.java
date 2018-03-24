package adventureGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class handles an instance of Classic Adventure Quest!
 * @author Stephen Young
 *
 */

public class GameHandler {

	/**
	 * @param current level object
	 * @param man player object
	 * @param directions array of possible directions
	 */
	private void writeScene(Level current, Player man, boolean directions[]){
		String desc = "You are in a room. ";
		String com = "You may use one of the following commands: ";
		boolean map[][];
		int x = man.getX();
		int y = man.getY();
		
		map = current.getMap();
		

		if (x != 0)
			if (map[x-1][y]) {
				desc += "There is a door to the west. ";
				com += "west, ";
				directions[3] = true;
			}
		
		if (x != current.getSizeX()-1 )
			if (map[x+1][y]){
				desc += "There is a door to the east. ";
				com += "east, ";
				directions[2] = true;
			}
		if (y != 0)
			if (map[x][y-1]){
				desc += "There is a door to the north. ";
				com += "north, ";
				directions[0] = true;
			}
		if (y != current.getSizeY()-1)
			if (map[x][y+1]){
				desc += "There is a door to the south. ";
				com += "south, ";
				directions[1] = true;
			}
		com += "search, look, map, quit";
		
		System.out.println("Your score: " + man.getScore());
		System.out.println(desc);
		System.out.println(com);
	}
	
	/**
	 * @param current level object
	 * @param man player object
	 */
	private void printMap(Level current, Player man){
		String slice = "";
		boolean map[][];
		
		map = current.getMap();
		
		System.out.println("Map of area: (Items remain hidden)");
		for (int i = 0; i < current.getSizeY(); i++){
			for (int j = 0; j < current.getSizeX(); j++){
				if (man.getX() == j && man.getY() == i) slice += "P";
				else if (map[j][i]==false)slice += "#";
				else slice += " ";
			}
			System.out.println(slice);
			slice = "";
		}
	}
	
	/**
	 * @param current current level
	 * @param man player object
	 * @param command command player issued
	 * @param directions
	 */
	private void doComand(Level current, Player man, String command, boolean directions[]){
		//TODO check that this method works
		command = command.toUpperCase();
		if (command.equals("NORTH")){
			if (directions[0] == true){
				man.mNorth();
				System.out.println("You move north.");				
			}
			else System.out.println("You slam into a wall! Ouch!");
		}
		else if (command.equals("SOUTH")){
			if (directions[1] == true){
				man.mSouth();
				System.out.println("You move south.");
			}
			else System.out.println("You slam into a wall! Ouch!");
		}
		else if (command.equals("EAST")){
			if (directions[2] == true){
				man.mEast();
				System.out.println("You move east.");
			}
			else System.out.println("You slam into a wall! Ouch!");
		}
		else if (command.equals("WEST")){
			if (directions[3] == true){
				man.mWest();
				System.out.println("You move west.");
			}
			else System.out.println("You slam into a wall! Ouch!");
		}
		else if (command.equals("LOOK")){
			return;
		} else if (command.equals("SEARCH")){
			boolean isFound;
			isFound = current.findItem(man.getX(), man.getY());
			if (isFound == true){
				man.setNumItems(man.getNumItems() + 1);
				System.out.println("You found an item! You are closer to leaving this vile maze!");
			}
			else System.out.println("You find nothing of interest.");
		}
		else if (command.equals("QUIT")){
			System.out.println("Thanks for playing!... quiter");
			System.exit(0);
		}
		else if (command.equals("MAP")){
			man.setScore(man.getScore()-5);
			this.printMap(current,man);
		}
		else{
			System.out.println("I'm sorry. I don't understand " + command.toLowerCase() + ". Are you sure you're up to the challenge... or intelligent...");
		}
	}
	
	/**
	 * @param current current level
	 * @param man player object
	 * @return true or false
	 */
	private boolean allItems(Level current, Player man){
		if (current.getNumItems() == man.getNumItems())
			return true;
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		boolean directions[] = {false,false,false,false};
		GameHandler gameHandler = new GameHandler();
		Level levelOne = new Level("level1.txt");
		Player player = new Player(1,levelOne.getStartX(),levelOne.getStartY(),0,217);
		
		System.out.println("Welcome to Classic Adventure Quest");
		System.out.println("You may type quit to leave the game at any time.");
		System.out.println("Everytime you move you lose 1 point.");
		System.out.println("If you get a negative score you suck!");
		System.out.println("Type map to view the map. This will cost you 5 points! The items will not show up on the map!");
		System.out.println("You must use the command search to find items.");
		System.out.println("The last item you find will teleport you to the next zone and eventually win the game!");
		System.out.println("There are 4 items per zone that must be located!");
		
		boolean loser = true;
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		//start level one
		while (loser){
			String userInput = "";
			gameHandler.writeScene(levelOne, player,directions);
			try {
				userInput = userIn.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			gameHandler.doComand(levelOne,player,userInput,directions);
			loser = !(gameHandler.allItems(levelOne, player));
			directions = new boolean[]{false,false,false,false};
		}
		
		Level levelTwo = new Level("level2.txt");
		player.setX(levelTwo.getStartX());
		player.setY(levelTwo.getStartY());
		player.setNumItems(0);
		
		System.out.println("Welcome to level 2! You have 4 items to find! Good luck!");
		
		loser = true;
		//start level two
		while (loser){
			String userInput = "";
			gameHandler.writeScene(levelTwo, player,directions);
			try {
				userInput = userIn.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			gameHandler.doComand(levelTwo,player,userInput,directions);
			loser = !(gameHandler.allItems(levelTwo, player));
			directions = new boolean[]{false,false,false,false};
		}
		
		System.out.println("You escaped the dungeon!");
		System.out.println("Your final score: " + player.getScore());
		
	}
}
