/*Online Store - Item.java
 * Kevin Lin
 * This is the object class for items. Contains constructor methods, instance/class variables, Accessor/Mutator methods.
 * June 19, 2017
 */

package storefront;

import javax.swing.*;

public class Item extends JButton
{
	// Class variable
	private static int numItems;
	
	// Instance variables
	private String name; //Name of item
	private String desc; //Description of item
	private double price; //Price of item
	private String image; //Image path of item
	private String category; //Category tag of item
	private boolean InCart; //A flag to determine whether an item is in the shopping cart or not


	// Constructor methods
	public Item(String n, double p, String d, String i, String c)
	{
		name = n;
		price = p;
		desc = d;
		image = i;
		category = c;
		numItems ++;
		InCart = false;
	} // end constructor
	
	public Item(String n, double p, String i, String c)
	{
		this(n, p, "", i, c);	// go to the other constructor...
	} // end constructor
	
	public Item()
	{
		this("", 0, "", "", "");
	} // end constructor
	
	// Accessor methods
	public String getName()
	{ 
		return name; 
	} 
	
	public String getDesc()
	{
		return desc;
	}  
	
	public double getPrice()
	{
		return price;
	} 
	
	public String getImage()
	{
		return image;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public static int getNumItems()
	{
		return numItems;
	} 
	
	public boolean getInCart()
	{
		return InCart;
	}
	
	// Mutator methods
	public void setName(String n)
	{
		name= n;
	} 
	
	public void setDesc(String d)
	{
		desc = d;
	} 
	
	public void setPrice(int p)
	{
		price = p;
	} 
		
	public void setImage(String i)
	{
		image = i;
	}

	public void setCategory(String c)
	{
		category = c;
	}
	
	public void setInCart(boolean b)
	{
		InCart = b;
	}
	public String toString()
	{
		return ("Item: Name: " + name +
			    ", Description: " + desc + ", Price: $" + price + ", Image path: " + image + ", Category: " + category);
	} // end toString method	

} // end class