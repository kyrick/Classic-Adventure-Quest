package adventureGame;

/**
 * class for the bot (this man is an evil agent sent to foil the player character)
 * @author Stephen Young
 *
 */
public class Bot extends Character{
	private Level current;
	private int visited;

	/**
	 * @param currentLevel
	 * @param room
	 * @param numItems
	 * @param score
	 * @param current 
	 */
	public Bot(int currentLevel, Room room, int numItems, int score, Level current) {
		super(currentLevel, room, numItems,score);
		this.visited = 1;
		this.current = current;
	}

	
	/**
	 * moves the bot in a direction based on rooms visited
	 */
	public void moveDirection(){
		if (room.getEast() != null && room.getEast().isWall() == false && moveDirection(room, room.getEast())){
			mEast();
		}else if (room.getSouth() != null && room.getSouth().isWall() == false && moveDirection(room, room.getSouth())){
			mSouth();
		}else if (room.getWest() != null && room.getWest().isWall() == false && moveDirection(room, room.getWest())){
			mWest();
		}else if (room.getNorth() != null && room.getNorth().isWall() == false && moveDirection(room, room.getNorth())){
			mNorth();
		}
		visited++;
		boolean isFound;
		isFound = current.findBotItem(this.room);
		if (isFound == true){
			setNumItems(numItems + 1);
			System.out.println("Item found!");
		}
		
	}
	
	/**
	 * check if this room is a good place to go
	 * @param next
	 * @return if direction is viable
	 */
	private boolean moveDirection(Room parent, Room next){
		//two base cases, either room is a wall, or it's off the map (or not null)
		if (next == null){
			return false;
		}
		next.newVisit();
		if (next.getItem() != null)
			if (next.getItem().isBotFound()==false){
				return true;
			}
		if (next.getEast() != null)
			if (next.getEast().getVisited()<visited && next.getEast() != parent && next.getEast().isWall() == false){
				if (moveDirection(next, next.getEast())){
					return true;
				}
			}
		if (next.getSouth() != null)
			if (next.getSouth().getVisited()<visited && next.getSouth() != parent && next.getSouth().isWall() == false){
				if (moveDirection(next, next.getSouth())){
					return true;
				}
			}
		if (next.getWest() != null)
			if (next.getWest().getVisited()<visited && next.getWest() != parent && next.getWest().isWall() == false){
				if (moveDirection(next, next.getWest())){
					return true;
				}
			}
		if (next.getNorth() != null)		
			if (next.getNorth().getVisited()<visited && next.getNorth() != parent && next.getNorth().isWall() == false){
				if (moveDirection(next, next.getNorth())){
					return true;
				}
			}
		return false;
	}
	
	/**
	 * Moves the bot character north one square
	 */
	public void mNorth(){
		this.room = room.getNorth();
		this.room.newVisit();
		this.score--;
	}
	
	/**
	 * Move the bot character south one grid space
	 */
	public void mSouth(){
		this.room = room.getSouth();
		this.room.newVisit();
		this.score--;
	}
	
	/**
	 * Move the bot character east one grid space
	 */
	public void mEast(){
		this.room = room.getEast();
		this.room.newVisit();
		this.score--;
	}
	
	/**
	 * Move the bot character west one grid space
	 */
	public void mWest() {
		this.room = room.getWest();
		this.room.newVisit();
		this.score--;
	}


	/**
	 * get the current level object the bot is on
	 * @return level object
	 */
	public Level getCurrent() {
		return current;
	}


	/**
	 * set the current level object for the bot
	 * @param current level object
	 */
	public void setCurrent(Level current) {
		this.current = current;
	}
	
	
}
