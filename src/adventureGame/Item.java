package adventureGame;

/**
 * Class defines the structure of an item
 * it holds the value of the item, whether it's been found, and the room it resides in
 * @author Stephen Young
 *
 */
public class Item {
	private boolean found;
	private boolean botFound;
	private Room room;
	/**
	 * the value of this item... used for the puzzle
	 */
	public final int value;

	/**
	 * returns the room that the object resides in
	 * @return room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * the value field will be final
	 * @param room
	 * @param value 
	 */
	public Item(Room room,int value) {
		super();
		this.value = value;
		this.found = false;
		this.botFound = false;
		this.room = room;
	}

	/**
	 * return true if the item has been found by the bot, false if it has not
	 * @return if found by bot
	 */
	public boolean isBotFound() {
		return botFound;
	}

	/**
	 * set whether the item was found by the bot
	 * @param botFound
	 */
	public void setBotFound(boolean botFound) {
		this.botFound = botFound;
	}

	/**
	 * if item has been discovered by player
	 * @return found
	 */
	public boolean isFound() {
		return found;
	}
	
	/**
	 * set if object has been found by the player
	 * @param found 
	 */
	public void setFound(boolean found) {
		this.found = found;
	}
}
