package adventureGame;

/**
 * Abstract character class. Contains common methods for characters in the game
 * @author Stephen Young
 *
 */
public abstract class Character {

	protected Room room;
	protected int currentLevel;
	protected int numItems;
	protected int score;
	
	/**
	 * @param currentLevel
	 * @param room 
	 * @param numItems
	 * @param score
	 */
	public Character(int currentLevel, Room room, int numItems, int score) {
		super();
		this.currentLevel = currentLevel;
		this.score = score;
		this.numItems = numItems;
		this.room = room;
	}
	/**
	 * get current level of character
	 * @return current level the character is on
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	/**
	 * get number of objects the character has found
	 * @return number
	 */
	public int getNumItems() {
		return numItems;
	}

	/**
	 * get the current room the character is in
	 * @return room
	 */
	public Room getRoom() {
		return room;
	}
	
	/**
	 * get character score
	 * @return score
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Move the character character east one grid space
	 */
	public abstract void mEast();
	
	/**
	 * Moves the character character north one square
	 */
	public abstract void mNorth();
	
	/**
	 * Move the character character south one grid space
	 */
	public abstract void mSouth();
	
	/**
	 * Move the character character west one grid space
	 */
	public abstract void mWest();
	
	/**
	 * set the character's current level
	 * @param currentLevel
	 */
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
	
	/**
	 * set character number of items
	 * @param numItems
	 */
	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}
	
	/**
	 * set the current room for the character
	 * @param room
	 */
	public void setRoom(Room room) {
		this.room = room;
	}
	
	/**
	 * set character score
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
