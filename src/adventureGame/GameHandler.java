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
	 * write text describing the scene
	 * @param current level object
	 * @param man player object
	 * @param directions array of possible directions
	 */
	private void writeScene(Player man){
		String desc = "You are in a room. ";
		String com = "You may use one of the following commands: ";
		Room room = man.getRoom();

		if (room.getWest() != null)
			if (!room.getWest().isWall()) {
				desc += "There is a door to the west. ";
				com += "west, ";
			}
		if (room.getEast() != null )
			if (!room.getEast().isWall()){
				desc += "There is a door to the east. ";
				com += "east, ";
			}
		if (room.getNorth() != null)
			if (!room.getNorth().isWall()){
				desc += "There is a door to the north. ";
				com += "north, ";
			}
		if (room.getSouth() != null)
			if (!room.getSouth().isWall()){
				desc += "There is a door to the south. ";
				com += "south, ";
			}
		com += "search, look, map, quit";
		
		System.out.println("Your score: " + man.getScore());
		System.out.println(desc);
		System.out.println(com);
	}
	
	/**
	 * print the map of the level to the console
	 * @param current level object
	 * @param man player object
	 */
	private void printMap(Level current, Player man){
		String slice = "";
		Room room = current.getCornerStone();

		System.out.println("Map of area: (Items remain hidden)");
		while (room != null){
			Room oldRoom = room;
			while(room != null){
				if (room == man.getRoom()){
					slice += "P";
				} else if (room.isWall()){
					slice += "#";
				} else {
					slice += " ";
				}
				room = room.getEast();
			}
			room = oldRoom;
			room = room.getSouth();
			System.out.println(slice);
			slice = "";	
		}
	}
	
	/**
	 * process player command
	 * @param current current level
	 * @param man player object
	 * @param command command player issued
	 * @param directions
	 */
	private void doComand(Level current, Player man, String command){
		command = command.toUpperCase();
		if (command.equals("NORTH")){
			if (man.getRoom().getNorth() != null){//if (directions[0] == true){
				man.mNorth();
				System.out.println("You move north.");				
			}
			else System.out.println("You slam into a wall! Ouch!");
		}
		else if (command.equals("SOUTH")){
			if (man.getRoom().getSouth() != null){//if (directions[1] == true){
				man.mSouth();
				System.out.println("You move south.");
			}
			else System.out.println("You slam into a wall! Ouch!");
		}
		else if (command.equals("EAST")){
			if (man.getRoom().getEast() != null) {//if (directions[2] == true){
				man.mEast();
				System.out.println("You move east.");
			}
			else System.out.println("You slam into a wall! Ouch!");
		}
		else if (command.equals("WEST")){
			if (man.getRoom().getWest() != null) {//if (directions[3] == true){
				man.mWest();
				System.out.println("You move west.");
			}
			else System.out.println("You slam into a wall! Ouch!");
		}
		else if (command.equals("LOOK")){
			return;
		} else if (command.equals("SEARCH")){
			boolean isFound;
			isFound = current.findItem(man.getRoom());
			if (isFound == true){
				man.addItem(man.getRoom().getItem());
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
	 * check if the player has found all the items
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
	 * check if the bot has found all the items
	 * @param current current level
	 * @param bot bot object
	 * @return true or false
	 */
	private boolean allBotItems(Level current, Bot bot){
		if (current.getNumItems() == bot.getNumItems())
			return true;
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		GameHandler gameHandler = new GameHandler();
		Level levelOne = new Level("level1.txt");
		Player player = new Player(1,levelOne.getZero(),0,217);
		Bot bot = new Bot(1,levelOne.getZero(),0,217,levelOne);
		
		System.out.println("Welcome to Classic Adventure Quest 2: Final Conflict");
		System.out.println("You may type quit to leave the game at any time.");
		System.out.println("Everytime you move you lose 1 point.");
		System.out.println("If you get a negative score you suck!");
		System.out.println("Type map to view the map. This will cost you 5 points! The items will not show up on the map!");
		System.out.println("You must use the command search to find items.");
		System.out.println("The last item you find will teleport you to the next zone and eventually win the game!");
		System.out.println("There are 4 items per zone that must be located!");
		System.out.println("An evil bot is racing you to find the items! Beat him!");		
		
		boolean loser = true;
		boolean botLoser = true;
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		
		//start level one
		while (loser){
			String userInput = "";
			gameHandler.writeScene(player);
			try {
				userInput = userIn.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			gameHandler.doComand(levelOne,player,userInput);
			if (botLoser == true) bot.moveDirection();
			else 
				System.out.println();
			loser = !(gameHandler.allItems(levelOne, player));
			botLoser = !(gameHandler.allBotItems(levelOne, bot));
		}
		
		Level levelTwo = new Level("level2.txt");
		player.setRoom(levelTwo.getZero());
		bot.setRoom(levelTwo.getZero());
		bot.setCurrent(levelTwo);
		player.setNumItems(0);
		bot.setNumItems(0);
		
		player.puzzleChallenge();
		
		System.out.println("Welcome to level 2! You have 4 items to find! Good luck!");
		
		loser = true;
		botLoser = true;
		//start level two
		while (loser){
			String userInput = "";
			gameHandler.writeScene(player);
			try {
				userInput = userIn.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			gameHandler.doComand(levelTwo,player,userInput);
			if (botLoser == true) bot.moveDirection();
			loser = !(gameHandler.allItems(levelTwo, player));
			botLoser = !(gameHandler.allBotItems(levelOne, bot));
		}
		
		player.puzzleChallenge();
		
		System.out.println("You escaped the dungeon!");
		System.out.println("Your final score: " + player.getScore());
		System.out.println("The bots final score: " + bot.getScore());
		
	}
}
