package adventureGame;

import java.util.LinkedList;

/**
 * class for the bot (this man is an evil agent sent to foil the player character)
 * @author Stephen Young
 *
 */
public class Bot {
	/**
	 * Current level the bot is on
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
	 * @param x
	 * @param y
	 * @param numItems
	 * @param score
	 */
	public Bot(int currentLevel, int x, int y, int numItems, int score) {
		super();
		this.currentLevel = currentLevel;
		this.x = x;
		this.y = y;
		this.numItems = numItems;
		this.score = score;
	}

	
	/**
	 * @param current the current level
	 */
	public void moveDirection(Level current){
		LinkedList<Row> map = current.getMap();
		int direction = -1;
		int times = -1;
		
		if (x != 0)
			if (!map.get(y).get(x-1).isWall() && map.get(y).get(x-1).getVisited() == 0) {
				mWest(current);
				times = 0;
			}

		if (y != 0)
			if (!map.get(y-1).get(x).isWall() && times == -1 && map.get(y-1).get(x).getVisited() == 0){
				mNorth(current);
				times = 0;
			}
		
		if (x != current.getSizeX()-1 && times == -1)
			if (!map.get(y).get(x+1).isWall() && times == -1 && map.get(y).get(x+1).getVisited() == 0){
				mEast(current);
				times = 0;
			}
		
		if (y != current.getSizeY()-1 && times == -1)
			if (!map.get(y+1).get(x).isWall() && times == -1 && map.get(y+1).get(x).getVisited() == 0){
				mSouth(current);
				times = 0;
			}
		
		if (times == -1){
			if (x != 0)
				if (!map.get(y).get(x-1).isWall()) {
					direction = 3;
					times = map.get(y).get(x-1).getVisited();
				}
			
			if (y != 0)
				if (!map.get(y-1).get(x).isWall()){
					if (times > map.get(y-1).get(x).getVisited() || times == -1){
						direction = 0;
						times = map.get(y-1).get(x).getVisited();
					}
				}
			
			if (x != current.getSizeX()-1)
				if (!map.get(y).get(x+1).isWall()){
					if (times > map.get(y).get(x+1).getVisited() || times == -1){
						direction = 1;
						times = map.get(y).get(x+1).getVisited();
					}
				}
			
			if (y != current.getSizeY()-1)
				if (!map.get(y+1).get(x).isWall()){
					if (times > map.get(y+1).get(x).getVisited() || times == -1){
						direction = 2;
						times = map.get(y+1).get(x).getVisited();						
					}
				}
		}

		switch(direction){
		case -1:
			break;
		case 0:
			mNorth(current);
			break;
		case 1:
			mEast(current);
			break;
		case 2:
			mSouth(current);
			break;
		case 3:
			mWest(current);
			break;
		default:
		}
	
		boolean isFound;
		isFound = current.findBotItem(x,y);
		if (isFound == true){
			setNumItems(numItems + 1);
			System.out.println("Item found!");
		}
		
	}
	
	/**
	 * Moves the player character north one square
	 * @param current 
	 */
	public void mNorth(Level current){
		LinkedList<Row> map = current.getMap();
		this.y--;
		this.score--;
		map.get(y).get(x).newVisit();
	}
	
	/**
	 * Move the player character south one grid space
	 * @param current 
	 */
	public void mSouth(Level current){
		LinkedList<Row> map = current.getMap();
		this.y++;
		this.score--;
		map.get(y).get(x).newVisit();
	}
	
	/**
	 * Move the player character east one grid space
	 * @param current 
	 */
	public void mEast(Level current){
		LinkedList<Row> map = current.getMap();
		this.x++;
		this.score--;
		map.get(y).get(x).newVisit();
	}
	
	/**
	 * Move the player character west one grid space
	 * @param current 
	 */
	public void mWest(Level current) {
		LinkedList<Row> map = current.getMap();
		this.x--;
		this.score--;
		map.get(y).get(x).newVisit();
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
