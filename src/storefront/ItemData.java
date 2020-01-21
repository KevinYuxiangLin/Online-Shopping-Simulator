/*Online Store - ItemData.java
 * Kevin Lin
 * This class reads the text file and separates the information into groups. 
 * June 19, 2017
 */

package storefront;

import java.io.IOException;
import java.util.ArrayList;

public class ItemData {
	
	public Item[] items;
	private int numberOfItems;
	
	private String category;
	
	private ArrayList clothing = new ArrayList();
	private ArrayList auto = new ArrayList();
	private ArrayList grocery = new ArrayList();
	private ArrayList electronics = new ArrayList();
	private ArrayList movie = new ArrayList();
	private ArrayList all = new ArrayList();
	
	/**creates an Item based on String input*/
	private Item makeItem(String s){
		
		//parse string into 5 variables
		int marker1 = s.indexOf('/');
		int marker2 = s.indexOf('/', marker1 + 1);
		int marker3 = s.indexOf('/', marker2 + 1);
		int marker4 = s.indexOf('/', marker3 + 1);
		int marker5 = s.indexOf('/', marker4 + 1);
		
		String name = s.substring(0,marker1);
		double price = Double.parseDouble(s.substring(marker1 + 1, marker2));
		String desc = s.substring(marker2 + 1, marker3);
		String category = s.substring(marker3 + 1, marker4);
		String image = s.substring(marker4 + 1, marker5);
		
		//create an Item with these variables
		Item storeItem = new Item(name, price, desc, image, category);
		
		return storeItem;
		
	}
	
	/**This method will do the following:
	 * 1. read the first line of the text file, and set it = to numberOfItems.
	 * 2. instantiate the array to the size numberOfItems
	 * 3. read the content from the text file and populate the 
	 *    global array of Item objects.*/
	public void initialize(){

		//open the file for reading
		IO.openInputFile("ItemList");
		
		try
		{
			
			//read the first value and store it in numberOfItems
			String lineOfFile = IO.readLine();
			numberOfItems = Integer.parseInt(lineOfFile);
						
			//initialize the array of items to the correct size
			items = new Item[numberOfItems];
			
			//for each line, we read in the text and create the next item
			for(int i = 0; i < items.length; i++){
				
				lineOfFile = IO.readLine();
				items[i] = makeItem(lineOfFile);
				
				all.add(items[i]);
				switch (items[i].getCategory()){
					case "Clothing": clothing.add(items[i]);
					break;
					case "Auto": auto.add(items[i]);
					break;
					case "Grocery": grocery.add(items[i]);
					break;
					case "Electronics": electronics.add(items[i]);
					break;
					case "Movie": movie.add(items[i]);
					break;
				}
			}
			
			
			//close file
			IO.closeInputFile();
		}
		catch(IOException e)
		{
			System.out.println("Error reading the file");
		}
				
	}//end initialize()

	/**This method displays each of the items on the command prompt*/
	public void displayItems(){
		
		for (int i = 0; i < items.length; i++){
			
			System.out.println(items[i].toString());
		}
		
	}//end displayItems()
	
	/**This method displays only the name in each of the items.*/
	public void displayItemName(){
		
		for (int i = 0; i < items.length; i++){
			
			System.out.println(items[i].getName());
		}
		
	}//end displayItemName()
	
	//new method, returning each list by category
	public ArrayList sortItemsByCategory(String category){
		ArrayList ret = new ArrayList();
		
		switch (category){
		case "Clothing": ret = clothing;
		break;
		case "Auto": ret = auto;
		break;
		case "Grocery": ret = grocery;
		break;
		case "Electronics": ret = electronics;
		break;
		case "Movie": ret = movie;
		break;
		case "All": ret = all;
		break;
		}
		return ret;
	
	}
}
