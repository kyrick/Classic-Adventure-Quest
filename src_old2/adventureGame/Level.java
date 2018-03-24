package adventureGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class handles an individual level of the game
 * @author Stephen Young
 *
 */
public class Level {
	private int numItems;
	private int sizeX;
	private int sizeY;
	private LinkedList<Row> map;
	private ArrayList<Item> items;
	private int startX;
	private int startY;

	/**
	 * @param file file to load level
	 */
	public Level(String file) {
		super();
		this.map = new LinkedList<Row>();
		this.loadLevel(file);
	}

	/**
	 * @return map array
	 */
	public LinkedList<Row> getMap() {
		return map;
	}
	
	/**
	 * @return numItems number of items on level
	 */
	public int getNumItems() {
		return numItems;
	}
	
	/**
	 * @return sizeX width of level in grid spaces
	 */
	public int getSizeX() {
		return sizeX;
	}
	
	/**
	 * @return sizeY height of level in grid spaces
	 */
	public int getSizeY() {
		return sizeY;
	}
	
	/**
	 * @return starting x grid location
	 */
	public int getStartX() {
		return startX;
	}
	
	/**
	 * @return starting y grid location
	 */
	public int getStartY() {
		return startY;
	}
	
	/**
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
			System.out.println("There was a problem loading the level! 1");
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
						System.out.println("There was a problem loading the level! 2");
						System.exit(0);
					}
				}
				bufLoad.read();
				bufLoad.read();
			}
			
			for (int j = 0; j < this.sizeY; j++){
				this.map.add(new Row());
				for (int i = 0; i < this.sizeX; i++){
					if (mapArray[i][j] == true){
						this.map.get(j).add(new Room(false));
					}
					else this.map.get(j).add(new Room(true));
				}
			}
			
			this.startX = Integer.parseInt(bufLoad.readLine());
			this.startY = Integer.parseInt(bufLoad.readLine());

			this.items = new ArrayList<Item>();
			
			this.items.add(new Item(false,Integer.parseInt(bufLoad.readLine()),Integer.parseInt(bufLoad.readLine())));
			this.items.add(new Item(false,Integer.parseInt(bufLoad.readLine()),Integer.parseInt(bufLoad.readLine())));
			this.items.add(new Item(false,Integer.parseInt(bufLoad.readLine()),Integer.parseInt(bufLoad.readLine())));
			this.items.add(new Item(false,Integer.parseInt(bufLoad.readLine()),Integer.parseInt(bufLoad.readLine())));
			
			this.numItems = items.size();
		} catch (IOException e) {
			System.out.println("There was a problem loading the level! 3");
			System.exit(0);
		}
		
		return result;
	}
	
	/**
	 * @param x player x
	 * @param y player y
	 * @return true:item found false: no item or already found
	 */
	public boolean findItem(int x, int y){
		
		Iterator<Item> itr = this.items.iterator(); 
		while(itr.hasNext()) {
		    Item found = itr.next(); 
		    if (x == found.getX() && y == found.getY() && found.isFound() == false){
		    	found.setFound(true);
		    	return true;
		    }
		} 
		return false;
	}
	
	/**
	 * @param x bot x
	 * @param y bot y
	 * @return true:item found, false: no item or already found
	 */
	public boolean findBotItem(int x, int y){
		
		Iterator<Item> itr = this.items.iterator(); 
		while(itr.hasNext()) {
		    Item found = itr.next(); 
		    if (x == found.getX() && y == found.getY() && found.isBotFound() == false){
		    	found.setBotFound(true);
		    	return true;
		    }
		} 
		return false;
	}
	
}
