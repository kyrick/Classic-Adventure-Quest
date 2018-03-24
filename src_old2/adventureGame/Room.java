package adventureGame;

/**
 * @author Stephen Young
 *
 */
public class Room {

	private int visited;
	
	/**
	 * @return visited
	 */
	public int getVisited() {
		return visited;
	}

	/**
	 * Increments visited
	 */
	public void newVisit() {
		this.visited++;
	}

	private boolean wall;
	
	/**
	 * @param wall
	 */
	public Room(boolean wall) {
		super();
		this.visited = 0;
		this.wall = wall;
	}

	/**
	 * @return wall
	 */
	public boolean isWall() {
		return wall;
	}
	
}