/*Online Store - EachItem.java
 * Kevin Lin
 * This class contains the constructor to build the item display GUI when clicking an item button. 
 * June 19, 2017
 */

package storefront;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
	 
public class EachItem extends JFrame {
        
		JButton buttonAdd = new JButton();
		
		//Takes item, and displays information of item
	    public EachItem(Item item) {
	        	    	
	    	super("Hello Shoppers! This Is Individual Item Show...");
	        	    	
	        // set the position of the window
	        Point p = new Point(200, 200);
	        setLocation(p.x, p.y);

	        JButton buttonItem = new JButton();
	         
	        BufferedImage buttonImage = null;
			String imagePath = item.getImage();
 			try {
				buttonImage = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
			
				e.printStackTrace();
			}
	   
 			buttonItem.setIcon(new ImageIcon(buttonImage));
 			buttonItem.setBorder(BorderFactory.createEmptyBorder());
 			buttonItem.setSize(50,50);  //Button size
 			getContentPane().add(buttonItem, BorderLayout.WEST);    //Add buttons 
	               
	        
	        // Create a message
	        JPanel messagePane = new JPanel();
	        messagePane.setLayout(new GridLayout(4,2));
	        
	        JLabel lblName = new JLabel("Name: ");
	        JTextArea textName = new JTextArea();
	        textName.setText(item.getName()); //Takes Name
	    	textName.setEditable(false);
	        
	        JLabel lblDesc = new JLabel("Description: ");
	        JTextArea textDesc = new JTextArea();
	        textDesc.setText(item.getDesc());//Takes Description
	        textDesc.setLineWrap(true);
	        textDesc.setWrapStyleWord(true);
	    	textDesc.setEditable(false);
	        
	        JLabel lblPrice = new JLabel("Price: ");
	        JTextArea textPrice = new JTextArea();
	        textPrice.setText(Double.toString(item.getPrice()));//Takes Price
	    	textPrice.setEditable(false);
	        
	        JLabel lblCategory = new JLabel("Category: ");
	        JTextArea textCategory = new JTextArea();
	        textCategory.setText(item.getCategory()); //Takes Category	        
	    	textCategory.setEditable(false);
	        
	    	//adds components
	        messagePane.add(lblName);
	        messagePane.add(textName);
	        messagePane.add(lblDesc);
	        messagePane.add(textDesc);
	        messagePane.add(lblPrice);
	        messagePane.add(textPrice);
	        messagePane.add(lblCategory);
	        messagePane.add(textCategory);
	        
	        getContentPane().add(messagePane, BorderLayout.CENTER);
	 
	        // Create a button
	        JPanel buttonPane = new JPanel();
	        buttonPane.setLayout(new BorderLayout());
	      	        
	        BufferedImage buttonImage2 = null;
			String imagePath2 = "Images\\add-to-cart-4.gif";
        	
 			try {
				buttonImage2 = ImageIO.read(new File(imagePath2));
			} catch (IOException e) {
				e.printStackTrace();
			}
 			
 			Icon addIcon = new ImageIcon(buttonImage2);
 			buttonAdd.setIcon(addIcon);
 			buttonAdd.setRolloverIcon(addIcon);
 			buttonAdd.setPressedIcon(addIcon);

	        buttonPane.add(buttonAdd, BorderLayout.PAGE_END);
	        
	        // set action listener on the button
	        buttonAdd.addActionListener(new AddToCart(item));
	        
	        getContentPane().add(buttonPane, BorderLayout.EAST);
	        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        pack();
	        setVisible(true);
	    }
	 
	    //An inner class, containing an actionPerformed method to add an item to cart
		class AddToCart implements ActionListener {
			private Item addedItem;
			
			public AddToCart(Item item){ //takes item and sets it to addedItem
				this.addedItem = item;
			}
	 		public void actionPerformed(ActionEvent e) {
				addedItem.setInCart(true);  //sets InCart boolean to true, has been added to cart
				JOptionPane.showMessageDialog(null, "You have successfully added item to your shopping cart. " ,
			            "Item to Cart!", JOptionPane.INFORMATION_MESSAGE);				
			}
		}//end of inner class AddToCart

}//end of class
