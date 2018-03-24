package adventureGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class handles an individual level of the game
 * @author Stephen Young
 *
 */
public class Level {
	private int numItems;
	private int sizeX;
	private int sizeY;
	private ArrayList<Item> items;
	private int startX;
	private int startY;
	private Room zero;
	private Room cornerStone;
	
	/**
	 * get the cornerStone room of the level for map generation
	 * @return cornerStone room object
	 */
	public Room getCornerStone() {
		return cornerStone;
	}

	/**
	 * Returns the starting room
	 * @return room zero
	 */
	public Room getZero() {
		return zero;
	}

	/**
	 * @param file file to load level
	 */
	public Level(String file) {
		super();
		this.loadLevel(file);
	}

	/**
	 * @return map array
	 */
	//public LinkedList<Row> getMap() {
	//	return map;
	//}
	
	/**
	 * number of items on level
	 * @return numItems 
	 */
	public int getNumItems() {
		return numItems;
	}
	
	/**
	 * width of level in grid spaces
	 * @return sizeX 
	 */
	public int getSizeX() {
		return sizeX;
	}
	
	/**
	 * height of level in grid spaces
	 * @return sizeY 
	 */
	public int getSizeY() {
		return sizeY;
	}
	
	/**
	 * starting x grid location
	 * @return startX
	 */
	public int getStartX() {
		return startX;
	}
	
	/**
	 * starting y grid location
	 * @return startY
	 */
	public int getStartY() {
		return startY;
	}
	
	/**
	 * load the level into memory
	 * @param file
	 * @return boolean success or failure of loading operation
	 */
	private boolean loadLevel(String file) {
		boolean result = false;
		boolean mapArray[][];
		
		FileReader inFile;
		BufferedReader bufLoad = null;
		try {
			inFile = new FileReader("level1.txt");
			bufLoad = new BufferedReader(inFile);
		} catch (FileNotFoundException e) {
			System.out.println("There was a problem loading the level! code 1");
			System.exit(0);
		}
		
		try {
			this.sizeX = Integer.parseInt(bufLoad.readLine());
			this.sizeY = Integer.parseInt(bufLoad.readLine());
			mapArray = new boolean[this.sizeX][this.sizeY];
			for (int j = 0; j < this.sizeY;j++){
				for (int i = 0; i < this.sizeX;i++){
					int tile = bufLoad.read();
					switch (tile){
					case 48:
						mapArray[i][j] = false;
						break;
					case 49:
						mapArray[i][j] = true;
						break;
					default: 
						System.out.println("There was a problem loading the level! code 2");
						System.exit(0);
					}
				}
				bufLoad.read();
				bufLoad.read();
			}
			
			ArrayList<Row> map = new ArrayList<Row>();
			
			//load rooms into master data structure
			for (int j = 0; j < this.sizeY; j++){
				map.add(new Row());
				for (int i = 0; i < this.sizeX; i++){
					if (mapArray[i][j] == true){
						map.get(j).add(new Room(false));
					}
					else map.get(j).add(new Room(true));
				}
			}

			//==========================================================
			//link room objects properly to be independent of master set
			//==========================================================
			/*
			//upper left corner
			map.get(0).get(0).setEast(map.get(0).get(1));
			map.get(0).get(0).setSouth(map.get(1).get(0));
			//upper right corner
			map.get(0).get(sizeX-1).setWest(map.get(0).get(sizeX-2));
			map.get(0).get(sizeX-1).setSouth(map.get(1).get(sizeX-1));
			//lower left corner
			map.get(sizeY-1).get(0).setNorth(map.get(sizeY-2).get(0));
			map.get(sizeY-1).get(0).setEast(map.get(sizeY-1).get(1));
			//lower right corner
			map.get(sizeY-1).get(sizeX-1).setNorth(map.get(sizeY-2).get(sizeX-1));
			map.get(sizeY-1).get(sizeX-1).setWest(map.get(sizeY-1).get(sizeX-2));
			//top row and bottom row
			for (int i = 1; i < sizeX-2;i++){
				//top
				map.get(0).get(i).setEast(map.get(0).get(i+1));
				map.get(0).get(i).setSouth(map.get(1).get(i));
				map.get(0).get(i).setWest(map.get(0).get(i-1));
				//bottom
				map.get(sizeY-1).get(i).setEast(map.get(sizeY-1).get(i+1));
				map.get(sizeY-1).get(i).setNorth(map.get(sizeY-2).get(i));
				map.get(sizeY-1).get(i).setWest(map.get(sizeY-1).get(i-1));
			}
			//east and west sides
			for (int i = 1; i < sizeY-2;i++){
				//west
				map.get(i).get(0).setNorth(map.get(i-1).get(0));
				map.get(i).get(0).setSouth(map.get(i+1).get(0));
				map.get(i).get(0).setWest(map.get(0).get(1));
				//east
				map.get(i).get(sizeX-1).setNorth(map.get(i-1).get(sizeX-1));
				map.get(i).get(sizeX-1).setSouth(map.get(i+1).get(sizeX-1));
				map.get(i).get(sizeX-1).setWest(map.get(i).get(sizeX-2));
			}
			//add center rooms
			for (int j = 2; j < sizeY-2;j++){
				for (int i = 2; i < sizeX-2;i++){
					map.get(j).get(i).setNorth(map.get(j-1).get(i));
					map.get(j).get(i).setSouth(map.get(j+1).get(i));
					map.get(j).get(i).setEast(map.get(j).get(i+1));
					map.get(j).get(i).setEast(map.get(j).get(i-1));
				}
			}
			*/
			for (int j = 0; j < sizeY;j++){
				for (int i = 0; i < sizeX;i++){
					if (j != 0){
						map.get(j).get(i).setNorth(map.get(j-1).get(i));
					}
					if (j != sizeY - 1){
						map.get(j).get(i).setSouth(map.get(j+1).get(i));
					}
					if (i != 0){
						map.get(j).get(i).setWest(map.get(j).get(i-1));
					}
					if (i != sizeX - 1){
						map.get(j).get(i).setEast(map.get(j).get(i+1));
					}
				}
			}
			
			
			this.startX = Integer.parseInt(bufLoad.readLine());
			this.startY = Integer.parseInt(bufLoad.readLine());
			
			cornerStone = map.get(0).get(0);
			zero = map.get(startY).get(startX);
			
			this.items = new ArrayList<Item>();
			
			//add all four items from map file
			int x,y;
			x = Integer.parseInt(bufLoad.readLine());
			y = Integer.parseInt(bufLoad.readLine());
			this.items.add(new Item(map.get(y).get(x),0));
			map.get(y).get(x).setItem(items.get(0));
			x = Integer.parseInt(bufLoad.readLine());
			y = Integer.parseInt(bufLoad.readLine());
			this.items.add(new Item(map.get(y).get(x),1));
			map.get(y).get(x).setItem(items.get(1));
			x = Integer.parseInt(bufLoad.readLine());
			y = Integer.parseInt(bufLoad.readLine());
			this.items.add(new Item(map.get(y).get(x),2));
			map.get(y).get(x).setItem(items.get(2));
			x = Integer.parseInt(bufLoad.readLine());
			y = Integer.parseInt(bufLoad.readLine());
			this.items.add(new Item(map.get(y).get(x),3));
			map.get(y).get(x).setItem(items.get(3));
			
			this.numItems = items.size();
		} catch (IOException e) {
			System.out.println("There was a problem loading the level! code 3");
			System.exit(0);
		}
		
		return result;
	}
	
	/**
	 * Determine if player has found this object
	 * @param room 
	 * @return true:item found false: no item or already found
	 */
	public boolean findItem(Room room){
		if (!room.getItem().isFound()){
			room.getItem().setFound(true);
			return true;
		}
		/*
		Iterator<Item> itr = this.items.iterator(); 
		while(itr.hasNext()) {
		    Item found = itr.next(); 
		    if (found.isFound() == false && found.getRoom() == room){
		    	found.setFound(true);
		    	return true;
		    }
		} 
		*/
		return false;
	}
	
	/**
	 * Determine if bot has found this item
	 * @param room 
	 * @return true:item found, false: no item or already found
	 */
	public boolean findBotItem(Room room){
		if (room.getItem() != null)
			if (!room.getItem().isBotFound()){
				room.getItem().setBotFound(true);
				return true;
			}
		/*Iterator<Item> itr = this.items.iterator(); 
		while(itr.hasNext()) {
		    Item found = itr.next(); 
		    if (found.getRoom() == room && found.isBotFound() == false){
		    	found.setBotFound(true);
		    	return true;
		    }
		} */
		return false;
	}
	
}
