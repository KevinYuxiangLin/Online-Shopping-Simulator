/*Online Store - ItemInterface.java
 * Kevin Lin
 * This class contains the main method and main components of the entire GUI.  
 * June 19, 2017
 */

package storefront;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ItemInterface {

    private JFrame frame = new JFrame("Kevin's Online Shopping");
    private JScrollPane scroll = new JScrollPane();
    private JPanel mainPanel = new JPanel();
    private JPanel bySearchPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private String category = "All";
    private ItemData id;
	JTextField textSearch = new JTextField();

    public ItemInterface ()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        Container contentPane = frame.getContentPane();        
        contentPane.setLayout(new BorderLayout());
        
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        mainPanel.setLayout(new FlowLayout());
        bySearchPanel.setLayout(new FlowLayout());
        
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        
        JPanel upPanel = new JPanel();
        JPanel downPanel = new JPanel();
        upPanel.setLayout(new BorderLayout());
        downPanel.setLayout(new GridLayout());
        
        JLabel logo = new JLabel();
        BufferedImage lblImage = null;
		String lblImagePath = "Images\\online_shop.png";
		try {
			lblImage = ImageIO.read(new File(lblImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		Icon onlineIcon = new ImageIcon(lblImage);
		logo.setIcon(onlineIcon);
        
        topPanel.add(logo);
        topPanel.add(Box.createRigidArea(new Dimension(50,0)));
        
		JButton btnCart = new JButton();
		BufferedImage cartImage = null;
		try {
			cartImage = ImageIO.read(new File("Images\\empty-cart-icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnCart = new JButton(new ImageIcon(cartImage));
    	btnCart.setBorder(BorderFactory.createEmptyBorder());
    	btnCart.addActionListener(new ClickOnCart());

		textSearch.setColumns(10);
		upPanel.add(textSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setSize(10, 10);
		upPanel.add(btnSearch, BorderLayout.EAST);
		btnSearch.addActionListener(new Search());

		JLabel lblSearch = new JLabel("Search by:  ");
		downPanel.add(lblSearch);
		
    	ButtonGroup buttonGrp = new ButtonGroup();
    	String[] searchItem = {"Clothing", "Auto", "Grocery", "Electronics", "Movie", "All"};

    	JCheckBox[] boxes = new JCheckBox[searchItem.length];  

    	for(int i = 0; i < boxes.length; i++) {
    		boxes[i] = new JCheckBox(searchItem[i]);
    		downPanel.add(boxes[i]);
        	buttonGrp.add(boxes[i]);
        	boxes[i].addActionListener(new cbSelected(boxes[i]));
        	
    	}
    	
    	searchPanel.add(upPanel, BorderLayout.NORTH);
    	searchPanel.add(downPanel, BorderLayout.SOUTH);
    	
    	topPanel.add(searchPanel);
    	topPanel.add(Box.createRigidArea(new Dimension(50,0)));
    	
		topPanel.add(btnCart);
		
        id = new ItemData();
        id.initialize();
        items = id.sortItemsByCategory("All"); //defaults to "All" to display all items
        
        InitializeMainPanel(category);
        
        scroll.getVerticalScrollBar().setUnitIncrement(16);//allows faster scrolling
        contentPane.add(scroll);
    	contentPane.add(topPanel,BorderLayout.NORTH);
    
    	frame.setSize(1000, 600);
    	frame.setVisible(true);
    }
    
    public static void main(String args[]) { //main method
			new ItemInterface();
    }
    //display items by category in main panel
    public void InitializeMainPanel(String category) {
    	mainPanel.removeAll();
        
    	ArrayList<Item> categoryItems  = new ArrayList<Item>();
    	categoryItems = id.sortItemsByCategory(category);
		
        BufferedImage[] images = new BufferedImage[categoryItems.size()];
		String imagePath;
        for  (int i = 0; i < categoryItems.size(); i++) {
        	imagePath = categoryItems.get(i).getImage();
 			try {
 				images[i] = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        JButton[] categoryButtons = new JButton[categoryItems.size()];
        for(int i = 0; i < categoryItems.size(); i++) {
        	categoryButtons[i] = new JButton();
        	categoryButtons[i].setIcon(new ImageIcon(images[i]));
        	categoryButtons[i].setBorder(BorderFactory.createEmptyBorder());
        	categoryButtons[i].addActionListener(new ClickEachItem(categoryItems.get(i)));
        	mainPanel.add(categoryButtons[i]);
        }

        //refresh the main panel
        mainPanel.revalidate();
        mainPanel.repaint(); 
        mainPanel.setPreferredSize(new Dimension(1000, 3600));
        scroll.setPreferredSize(new Dimension(1000,600));
        scroll.setViewportView(mainPanel);

    }

    //display items searched by string
    public void DisplayItemsBySearch(ArrayList<Item> searchItems) {
    	bySearchPanel.removeAll();
		
        BufferedImage[] images = new BufferedImage[searchItems.size()];
		String imagePath;
        for  (int i = 0; i < searchItems.size(); i++) {
        	imagePath = searchItems.get(i).getImage();
 			try {
 				images[i] = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

        JButton[] searchButtons = new JButton[searchItems.size()];
        for(int i = 0; i < searchItems.size(); i++) {
        	searchButtons[i] = new JButton();
        	searchButtons[i].setIcon(new ImageIcon(images[i]));
        	searchButtons[i].setBorder(BorderFactory.createEmptyBorder());
        	searchButtons[i].addActionListener(new ClickEachItem(searchItems.get(i)));
        	bySearchPanel.add(searchButtons[i]);
        }

        //refresh the panel
        bySearchPanel.revalidate();
        bySearchPanel.repaint(); 
       		
        bySearchPanel.setPreferredSize(new Dimension(1000, 3600));
        scroll.setPreferredSize(new Dimension(1000,600));
        scroll.setViewportView(bySearchPanel);
    }
    
    
    //click the shopping cart icon action
	class ClickOnCart implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				itemList.clear();
				for (int i = 0; i < items.size(); i++) {
					if (items.get(i).getInCart() == true){ //displays items that are marked as true, meaning they've been added to cart
						itemList.add(items.get(i));
					
					}
				}
				if (itemList.isEmpty()){
					JOptionPane.showMessageDialog(null, "You have no items in your shopping cart. " ,
				            "Add Item to Cart!", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					new ShoppingCart(itemList);		
				}
			}
	}//end of inner class ClickOnCart

	//click each item to produce pop up window for that item
	class ClickEachItem implements ActionListener {
		private Item clickItem;
		public ClickEachItem (Item item){
				this.clickItem = item;
		}
		public void actionPerformed(ActionEvent e) {
				EachItem dialog = new EachItem(clickItem);
				// set the size of the window
				dialog.setSize(600, 240);	
		}
	}//end of inner class ClickEachItem

	//search by category 
    class cbSelected implements ActionListener {
    	private JCheckBox cbox;
    	
    	public cbSelected(JCheckBox box){
    		this.cbox = box;
    	}
    	public void actionPerformed(ActionEvent e) {
            if(cbox.isSelected())
                category = cbox.getText();
            InitializeMainPanel(category);
    	}    
    }//end of inner class cbSelected
    
    //search by string processing
	class Search implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ArrayList<Item> searchitems = new ArrayList<Item>();
			String searchStr = textSearch.getText();
			for (int i = 0; i < items.size(); i++) { //goes through item list
				if (items.get(i).getName().toLowerCase().contains(searchStr.toLowerCase()) == true){ //sets everything to lowercase to compare
					searchitems.add(items.get(i));
				
				}
			}
			if (searchitems.isEmpty()){ //displays this message if string oesn't exist
				JOptionPane.showMessageDialog(null, "No items found. " ,
			            "Search item!", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				DisplayItemsBySearch(searchitems); //displays items
			}
		}
	}//end of inner class Search
}//end of class
