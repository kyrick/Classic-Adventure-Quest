package adventureGame;

/**
 * Class defines the structure of a room object
 * It holds information on the associated rooms, if it's a wall, and the item in the room
 * @author Stephen Young
 *
 */
public class Room {

	private Item item;
	
	
	/**
	 * get item associated with the room. Default is null if no item is present
	 * @return item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * set the item in the room
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	private int visited;
	private boolean wall;
	private Room north;
	private Room south;
	private Room east;
	private Room west;

	/**
	 * @param wall
	 */
	public Room(boolean wall) {
		super();
		this.item = null;
		this.visited = 0;
		this.wall = wall;
		this.north = null;
		this.south = null;
		this.east = null;
		this.west = null;
	}

	/**
	 * @param wall
	 * @param north 
	 * @param south 
	 * @param east 
	 * @param west 
	 */
	public Room(boolean wall,Room north, Room south, Room east, Room west) {
		super();
		this.visited = 0;
		this.wall = wall;
		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
	}
	
	/**
	 * returns the number of times visited
	 * @return visited
	 */
	public int getVisited() {
		return visited;
	}
	
	/**
	 * returns true if the room is a wall, false if it is open
	 * @return wall
	 */
	public boolean isWall() {
		return wall;
	}

	/**
	 * Increments visited
	 */
	public void newVisit() {
		this.visited++;
	}

	/**
	 * get the room north of this room
	 * @return north room
	 */
	public Room getNorth() {
		return north;
	}

	/**
	 * set the room north of this room
	 * @param north room
	 */
	public void setNorth(Room north) {
		this.north = north;
	}

	/**
	 * get the room south of this room
	 * @return south room
	 */
	public Room getSouth() {
		return south;
	}

	/**
	 * set the room south of this room
	 * @param south room
	 */
	public void setSouth(Room south) {
		this.south = south;
	}

	/**
	 * get the room east of this room
	 * @return east room
	 */
	public Room getEast() {
		return east;
	}

	/**
	 * set the room east of this room
	 * @param east room
	 */
	public void setEast(Room east) {
		this.east = east;
	}

	/**
	 * get the room west of this room
	 * @return west room
	 */
	public Room getWest() {
		return west;
	}

	/**
	 * set the room west of this room
	 * @param west room
	 */
	public void setWest(Room west) {
		this.west = west;
	}
	
}