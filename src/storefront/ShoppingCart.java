/*Online Store - ShoppingCart.java
 * Kevin Lin
 * This class contains the constructor to build the shopping cart GUI when clicking the shopping cart button. 
 * June 19, 2017
 */

package storefront;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ShoppingCart extends JFrame {
	 
    private JScrollPane scroll = new JScrollPane();
    private JPanel mainPanel = new JPanel();
    private int totalNum = 0;
    private double totalPrice = 0.00;
    
    public ShoppingCart(ArrayList<Item> itemList) {
    	super("Hello Shoppers! This Is Shopping Cart Item Show...");
        Point p = new Point(100, 100);
        setLocation(p.x, p.y);
        
        mainPanel.setLayout(new FlowLayout()); //displays items using FlowLayout

    	for (int i = 0; i < itemList.size(); i++) {
    		totalNum = totalNum + 1; //finds total of items in cart
    		totalPrice = totalPrice + itemList.get(i).getPrice(); //calculates total price
    		new ItemDisplay(itemList.get(i));
    	}	       
        
    	JPanel statsPanel = new JPanel();
    	JLabel lblNum = new JLabel("Total Number of items in Cart: ");
    	JTextArea textNum = new JTextArea();
    	textNum.setText(Integer.toString(totalNum));
    	JLabel lblPrice = new JLabel("Total Price: ");
    	JTextArea textPrice = new JTextArea();  
    	textPrice.setText(Double.toString(totalPrice));
    	textPrice.setEditable(false);
    	textNum.setEditable(false);
    	statsPanel.add(lblNum);
    	statsPanel.add(textNum);
    	statsPanel.add(lblPrice);
    	statsPanel.add(textPrice);
    	
    	mainPanel.add(statsPanel); 	
    	
    	mainPanel.setPreferredSize(new Dimension(600, 3600));
        scroll.setPreferredSize(new Dimension(600,600));
        scroll.setViewportView(mainPanel);
        scroll.getVerticalScrollBar().setUnitIncrement(16);//allows faster scrolling
        getContentPane().add(scroll);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
    } 
    
    //inner class to display items
	class ItemDisplay extends JPanel {
		
		public ItemDisplay(Item item) {
	       
			BufferedImage buttonImage = null;
			String imagePath = item.getImage();
 			try {
				buttonImage = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
 		
 		Icon itemIcon = new ImageIcon(buttonImage);
 		JButton buttonItem = new JButton(itemIcon);
 		buttonItem.setBorder(BorderFactory.createEmptyBorder());
 		mainPanel.add(buttonItem);

	    JPanel messagePane = new JPanel();
	    
		JLabel lblDesc = new JLabel("Name: ");
	    JTextArea textDesc = new JTextArea();
	    textDesc.setText(item.getName());
	    textDesc.setLineWrap(true);
	    textDesc.setWrapStyleWord(true);
    	textDesc.setEditable(false);
	    
		JLabel lblPrice = new JLabel("Price: ");
		JTextArea textPrice = new JTextArea();
		textPrice.setText(Double.toString(item.getPrice()));
    	textPrice.setEditable(false);
		
    	//adding components
		messagePane.add(lblDesc);
		messagePane.add(textDesc);
		messagePane.add(lblPrice);
	    messagePane.add(textPrice);
	    
	    mainPanel.add(messagePane);
	 
	    // Create a button
	    JPanel buttonPane = new JPanel();
	    
	    BufferedImage buttonImage2 = null;
	    String imagePath2 = "Images\\remove_from_cart.png";
       	
 		try {
			buttonImage2 = ImageIO.read(new File(imagePath2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	   
 		Icon deleteIcon = new ImageIcon(buttonImage2);
	    JButton buttonDelete = new JButton(deleteIcon); 			    
 		buttonDelete.setRolloverIcon(deleteIcon);
 		buttonDelete.setPressedIcon(deleteIcon);
		
	    buttonPane.add(buttonDelete);
	        
	    // set action listener on the button
	    buttonDelete.addActionListener(new RemoveFromCart(item));
	        
	    mainPanel.add(buttonPane);
	    
	    getContentPane().add(mainPanel);

	    setVisible(true);
	    }
	 

	    //an action listener to be used when an action is performed
	    //remove item from cart: set flag to false
	    class RemoveFromCart implements ActionListener {
	        private Item item;

	        public RemoveFromCart(Item item) {
	            this.item = item;
	        }

	    	public void actionPerformed(ActionEvent e) {
	    		item.setInCart(false); //sets InCart variable to false again
				JOptionPane.showMessageDialog(null, "You have successfully removed the item from your shopping cart. " ,
			            "Remove Item!", JOptionPane.INFORMATION_MESSAGE);
	    		//System.out.println("Remove item from cart..");
	 		}
	    }//end of inner class RemoveFromCart
	}//end of inner class ItemDisplay
}//end of class