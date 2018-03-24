package adventureGame;

/**
 * @author Stephen Young
 *
 */
public class Item {
	private boolean found;
	private boolean botFound;
	private int x;
	private int y;

	/**
	 * @param found
	 * @param x
	 * @param y
	 */
	public Item(boolean found, int x, int y) {
		super();
		this.found = found;
		this.botFound = false;
		this.x = x;
		this.y = y;
	}

	/**
	 * @return if found by bot
	 */
	public boolean isBotFound() {
		return botFound;
	}

	/**
	 * @param botFound
	 */
	public void setBotFound(boolean botFound) {
		this.botFound = botFound;
	}

	/**
	 * @return item x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return item y position
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return if item has been discovered by player
	 */
	public boolean isFound() {
		return found;
	}
	
	/**
	 * @param found set if object has been found by the player
	 */
	public void setFound(boolean found) {
		this.found = found;
	}
	
	/**
	 * @param x item x position
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * @param y item y position
	 */
	public void setY(int y) {
		this.y = y;
	}
}
