package gameUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import action_listener.PlayBeginListener;
/**
 * Class constructing the UI for welcome UI
 * 
 * @author Liang Gao
 *
 */
public class WelcomeUI extends JFrame{

/**
 * construct the WelcomeUI
 */
public WelcomeUI(){
	//set the size of the welcome UI
	this.setSize(800, 1000);
	//set default close operation for welcome UI
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//set the title for the welcome UI
	this.setTitle("Fanorona");
	
	//create three panels for the welcome UI
	JPanel panel_up=new JPanel();
	JPanel panel_mid=new JPanel();
	JPanel panel_down=new JPanel();
	
	//create a button to determine playing game 
	JButton sub=new JButton("Play");
	
	//create three components for HCI
	JTextField name=new JTextField();
	JComboBox level=new JComboBox();
	JComboBox first=new JComboBox();
	JComboBox size = new JComboBox();
	
	//items used to show combo box
	List items = new ArrayList();
	items.add("level 1: Child Level");
	items.add("level 2: Adult Level");
	items.add("level 3: God Level");
	
	level.setModel(new DefaultComboBoxModel(items.toArray()));
	
	List items2 = new ArrayList();
	items2.add("Human First");
	items2.add("Computer First");
	first.setModel(new DefaultComboBoxModel(items2.toArray()));
	
	List items3 = new ArrayList();
	items3.add("3 X 3");
	items3.add("5 X 5");
	size.setModel(new DefaultComboBoxModel(items3.toArray()));
	
	//set the label of the welcome UI
	JLabel wel=new JLabel("Welcome to  Fanorona game");
	wel.setFont(new Font("Times",Font.PLAIN,60));
	
	//add components to panels
	panel_up.add(wel);
	panel_up.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));
	
	panel_mid.setLayout(new GridLayout(4,2));
	panel_mid.add(new JLabel("Enter your name for game"));
	panel_mid.add(name);
	panel_mid.add(new JLabel("Please choose a level"));
	panel_mid.add(level);
	panel_mid.add(new JLabel("Please choose who go first"));
	panel_mid.add(first);
	panel_mid.add(new JLabel("Please choose board size:"));
	panel_mid.add(size);
	
	panel_mid.setBorder(BorderFactory.createEmptyBorder(80,180,180,180));

	panel_down.add(sub);
	panel_down.setBorder(BorderFactory.createEmptyBorder(0,0,100,0));
	
	//layout panels in the main panel of the welcome UI
	this.add(panel_up,BorderLayout.NORTH);
	this.add(panel_mid,BorderLayout.CENTER);
	this.add(panel_down,BorderLayout.SOUTH);
	
	//add action listener to the "play" button
	sub.addActionListener(new PlayBeginListener(this,name,level,first,size));
	
	//set the welcome UI visible
	this.setVisible(true);
	
	
}
	/**
	 * the main method of the project
	 * @param args no args needed
	 */
	public static void main(String[] args){
		new WelcomeUI();
	}
	
}
