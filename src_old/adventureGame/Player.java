package adventureGame;

/**
 * The class for the player character
 * @author Stephen Young
 *
 */
public class Player {
	/**
	 * Current level the player is on
	 */
	private int currentLevel;
	private int x;
	private int y;
	/**
	 * Number of items found
	 */
	private int numItems;

	private int score;
	
	/**
	 * @param currentLevel
	 * @param x starting x
	 * @param y starting y
	 * @param numItems current number of items
	 * @param score starting score
	 */
	public Player(int currentLevel, int x, int y, int numItems, int score) {
		super();
		this.currentLevel = currentLevel;
		this.x = x;
		this.y = y;
		this.score = score;
		this.numItems = numItems;
	}
	
	/**
	 * Moves the player character north one square
	 */
	public void mNorth(){
		this.y--;
		this.score--;
	}
	
	/**
	 * Move the player character south one grid space
	 */
	public void mSouth(){
		this.y++;
		this.score--;
	}
	
	/**
	 * Move the player character east one grid space
	 */
	public void mEast(){
		this.x++;
		this.score--;
	}
	
	/**
	 * Move the player character west one grid space
	 */
	public void mWest() {
		this.x--;
		this.score--;
	}
	
	/**
	 * @return current level the player is on
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	/**
	 * @return number of objects the player has found
	 */
	public int getNumItems() {
		return numItems;
	}
	
	/**
	 * @return player x location
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return player y location
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @param currentLevel
	 */
	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
	
	/**
	 * @param numItems
	 */
	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}
	
	/**
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * @return score
	 */
	public int getScore() {
		return this.score;
	}
	

	/**
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
}
