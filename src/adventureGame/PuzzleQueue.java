package adventureGame;

/**
 * Queue class for the purposes of the puzzle in this adventure game
 * @author Stephen Young
 *
 */
public class PuzzleQueue {
	private Node first;
	private int size;
	
	/**
	 * returns the size of the queue
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * constructor sets first to null as default (queue empty)
	 */
	public PuzzleQueue(){
		first = null;
		size = 0;
	}
	
	/**
	 * add item to back of the queue
	 * @param item
	 */
	public void add(Item item){
		if (first == null){
			first = new Node();
			first.item = item;
			first.next = first;
			first.last = first;
			size++;
		}else{
			Node node = new Node();
			node.item = item;
			Node last = first.last;
			
			node.next = first;
			node.last = last;
			
			first.last = node;
			last.next = node;
			
			size++;
		}
	}
	
	/**
	 * remove first item from queue
	 * @return item
	 */
	public Item remove(){
		//check if queue is empty
		if (first == null)
			return null;
		if (first.last == first){
			Item onlyOne = first.item;
			
			first = null;
			
			size = 0;
			
			return onlyOne;
		}
		//grab first item
		Item firstItem = first.item;
		Node next = first.next;
		Node last = first.last;
		
		first = next;
		first.last = last;
		last.next = first;
		size--;
		return firstItem;
	}
	
	/**
	 * peek at the first item in the queue
	 * @return item
	 */
	public Item peek(){
		if (first == null)
			return null;
		return first.item;
	}
	
	/**
	 * clear all items from queue
	 */
	public void clearQueue() {
		first = null;
		size = 0;
	}
	
	/**
	 * a simple node class for use in this queue
	 * @author Stephen Young
	 *
	 */
	class Node {
		Node last;
		Node next;
		Item item;
	}
}
