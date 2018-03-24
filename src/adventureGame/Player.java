package adventureGame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class for the player character
 * @author Stephen Young
 *
 */
public class Player extends Character{
	private PuzzleQueue theItems;
	
	/**
	 * @param currentLevel
	 * @param room
	 * @param numItems current number of items
	 * @param score starting score
	 */
	public Player(int currentLevel, Room room, int numItems, int score) {
		super(currentLevel,room,numItems,score);
		theItems = new PuzzleQueue();
	}
	
	/**
	 * Moves the player character north one square
	 */
	public void mNorth(){
		this.room = room.getNorth();
		this.score--;
	}
	
	/**
	 * Move the player character south one grid space
	 */
	public void mSouth(){
		this.room = room.getSouth();
		this.score--;
	}
	
	/**
	 * Move the player character east one grid space
	 */
	public void mEast(){
		this.room = room.getEast();
		this.score--;
	}
	
	/**
	 * Move the player character west one grid space
	 */
	public void mWest() {
		this.room = room.getWest();
		this.score--;
	}
	
	/**
	 * add item to queue of items
	 * @param item
	 */
	public void addItem(Item item){
		theItems.add(item);
	}
	
	/**
	 * clear list of items
	 */
	public void clearItems(){
		theItems.clearQueue();
	}
	
	/**
	 * start the puzzle challenge!
	 */
	public void puzzleChallenge(){
		ArrayList<Integer> towerOne = new ArrayList<Integer>();
		ArrayList<Integer> towerTwo = new ArrayList<Integer>();
		ArrayList<Integer> towerThree = new ArrayList<Integer>();
		
		System.out.println();
		System.out.println("As you magically transcend this level you are blocked by an ethereal puzzle!");
		System.out.println("You feel drawn to place your collection of magical items onto three magic poles!");
		System.out.println("Given that you are verifiably OCD, you feel compelled to place the items one at a time");
		System.out.println("in the order they were picked up. (ie. first picked up is first to be placed)");
		System.out.println("Magical letters float up above the three magical poles. They spell a magical phrase.");
		System.out.println("Instructions: this is the Towers of Henoi. All items must be arranged in order of");
		System.out.println("least on top to greatest on bottom on the THIRD pole. You can choose the initial");
		System.out.println("of the items from your inventory as long as that placement does not violate the");
		System.out.println("basic rule of the game. Basic Rule: A greater item cannot be placed upon a lesser.");
		System.out.println("This rule serves no purpose, but you are OCD and feel compelled to be impractical.");
		System.out.println();
		
		while (theItems.getSize() != 0){
			int value = theItems.peek().value;
			System.out.println("You must place an item of value " + value);
			System.out.println("Which pole do you place the item on? (1,2,3)");
			Scanner scanner = new Scanner(System.in);
			int input = scanner.nextInt();
			if (input > 0 && input < 4){
				switch (input){
				case 1:
					if (towerOne.size() == 0){
						towerOne.add(value);
						theItems.remove();
					} else if (towerOne.get(0) > value){
						towerOne.add(0, value);
						theItems.remove();
					} else {
						System.out.println("Woah! Woah! Woah! Can't do that! It would violate your OCD nature!");
					}
					break;
				case 2:
					if (towerTwo.size() == 0){
						towerTwo.add(value);
						theItems.remove();
					} else if (towerTwo.get(0) > value){
						towerTwo.add(0, value);
						theItems.remove();
					} else {
						System.out.println("Woah! Woah! Woah! Can't do that! It would violate your OCD nature!");
					}
					break;
				case 3:
					if (towerThree.size() == 0){
						towerThree.add(value);
						theItems.remove();
					} else if (towerThree.get(0) > value){
						towerThree.add(0, value);
						theItems.remove();
					} else {
						System.out.println("Woah! Woah! Woah! Can't do that! It would violate your OCD nature!");
					}
					break;
				default:
				}
			}else{
				System.out.println("Eh? What was that? Don't think that's an item.");
			}
			System.out.println("And the poles are!");
			if (towerOne.size() > 0){
				String print = "";
				print = "Tower one: ";
				for (int i = 0; i < towerOne.size(); i++){
					print += towerOne.get(i) + " ";
				}
				System.out.println(print);
			}
			else
				System.out.println("Tower one: empty");
			if (towerTwo.size() > 0){
				String print = "";
				print = "Tower two: ";
				for (int i = 0; i < towerTwo.size(); i++){
					print += towerTwo.get(i) + " ";
				}
				System.out.println(print);
			}
			else
				System.out.println("Tower two: empty");
			if (towerThree.size() > 0){
				String print = "";
				print = "Tower one: ";
				for (int i = 0; i < towerThree.size(); i++){
					print += towerThree.get(i) + " ";
				}
				System.out.println(print);
			}
			else
				System.out.println("Tower three: empty");
		}
		
		System.out.println();
		System.out.println("Now you must carefully place the items. All in the third pole.");
		
		while (towerThree.size() < 4){
			System.out.println("And the poles are!");
			if (towerOne.size() > 0){
				String print = "";
				print = "Tower one: ";
				for (int i = 0; i < towerOne.size(); i++){
					print += towerOne.get(i) + " ";
				}
				System.out.println(print);
			}
			else
				System.out.println("Tower one: empty");
			if (towerTwo.size() > 0){
				String print = "";
				print = "Tower two: ";
				for (int i = 0; i < towerTwo.size(); i++){
					print += towerTwo.get(i) + " ";
				}
				System.out.println(print);
			}
			else
				System.out.println("Tower two: empty");
			if (towerThree.size() > 0){
				String print = "";
				print = "Tower one: ";
				for (int i = 0; i < towerThree.size(); i++){
					print += towerThree.get(i) + " ";
				}
				System.out.println(print);
			}
			else
				System.out.println("Tower three: empty");
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Select a tower to get an item from: ");
			int from = scanner.nextInt();
			if (from > 0 && from < 4){
				System.out.println("Select a tower to place the item on: ");
				int to = scanner.nextInt();
				if (to > 0 && to < 4){
					ArrayList<Integer> first = null,second = null;
					switch (from){
					case 1:
						first = towerOne;
						break;
					case 2:
						first = towerTwo;
						break;
					case 3:
						first = towerThree;
						break;
					default:
					}
					
					switch (to) {
					case 1:
						second = towerOne;
						break;
					case 2:
						second = towerTwo;
						break;
					case 3:
						second = towerThree;
						break;
					default:
					}
					
					if (from == to){
						System.out.println("You pick up an item and put it right back down.... congrats!... nothing happens...");
					}else{
						if (first.size() > 0){
							int transfer = first.get(0);
							if (second.size() == 0){
								second.add(0,transfer);
								first.remove(0);
							}
							else if (transfer < second.get(0)){
								second.add(0,transfer);
								first.remove(0);	
							}
							else
								System.out.println("Woah woah woah!!! Can't do that! Sorry man! You are OCD! Select another tower");
						}else
							System.out.println("Um... no items on this pole... select a different one");
					}
				}else
					System.out.println("Wait wait wait! Pick a real tower! Stop making things up!");
			}else
				System.out.println("Wait wait wait! Pick a real tower! Stop making things up!");
		}
		System.out.println("W00T! You did it! You have transcended this plane and will shortly arrive at another");
	}
}
