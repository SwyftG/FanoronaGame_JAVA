package gameUI;

import game.Game;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import action_listener.StepListener;
/**
 * Class that construct the UI of the game
 * 
 * @author Liang Gao
 *
 */
public class GameUI extends JFrame{
private String[] level;		//record the level of the game, 0,1,2 referring to different level of game

private JPanel up;	//the up panel
private JPanel mid;	//the middle panel 
private JPanel down;	//the down panel
public JButton[][] grid;	//buttons represents grids in the game
public JTextField turn;    //text field showing player name
public JTextField step;		//text field showing count of step
private Game game;		//game object
public int count;		//counting the step
public int sizer;
public int sizec;
public int[] startPosition = new int[2];
public static int xclick;
public static int yclick;
public static int xclick2;
public static int yclick2;
/**
 * construct the game UI
 * 
 * @param name player name 
 * @param level	the level player choosess
 * @param first	who go first
 */
public GameUI(String name, int level, int first, int size){
	//initialize the count to 0
	count=0;
	sizer=0;
	sizec=0;
	//set first to 1 when player choose player go first
	if(first==0)
		first=1;
	//set first to 2 when player choose AI agent go first
	else
		first=2;
	if (size == 0) {
		sizer=3;
		sizec=3;
	} else {
		sizer=5;
		sizec=5;
	}
	//initialize the UI of game 
	initUI(name,level, first, sizer, sizec);	
	//layout the panel of UI
	add(mid,BorderLayout.CENTER);
	add(up,BorderLayout.NORTH);
	add(down,BorderLayout.SOUTH);
	//set UI visible 
	this.setVisible(true);
}

/**
 * function to initialize the UI of game
 * 
 * @param name player name
 * @param l the game in which depth should be cut off by the level given by user
 * @param first who go first
 */
private void initUI(String name, int l,int first, int sizer, int sizec){
	//create new buttons for grids
	grid=new JButton[sizer][sizec];
	//initialize the buttons 
	
	for (int i = 0; i < (sizer / 2); i++) {
		for (int j = 0; j < sizec ; j++) {
			grid[i][j]=new JButton("2");
			grid[i][j].setFont(new Font("Times",Font.PLAIN,50));

		}
	}
	for (int i = (sizer / 2) + 1; i < sizer; i++) {
		for (int j = 0; j < sizec; j++) {
			grid[i][j]=new JButton("1");
			grid[i][j].setFont(new Font("Times",Font.PLAIN,50));

		}
	}
	boolean black = true;
	for (int i = sizec / 2; i < sizec; i++) {
		if (i == sizec / 2) {
			grid[sizer / 2][i]=new JButton("0");
			grid[sizer / 2][i].setFont(new Font("Times",Font.PLAIN,50));
			continue;
		} 
		if (black) {
			grid[sizer / 2][i]=new JButton("2");
			grid[sizer / 2][i].setFont(new Font("Times",Font.PLAIN,50));
			
			black = false;
		} else {
			grid[sizer / 2][i]=new JButton("1");
			grid[sizer / 2][i].setFont(new Font("Times",Font.PLAIN,50));
			
			black = true;
		}
	}
	black = false;
	for (int i = sizec / 2 - 1; i >= 0; i--) {
		if (black) {
			grid[sizer / 2][i]=new JButton("2");
			grid[sizer / 2][i].setFont(new Font("Times",Font.PLAIN,50));
			
			black = false;
		} else {
			grid[sizer / 2][i]=new JButton("1");
			grid[sizer / 2][i].setFont(new Font("Times",Font.PLAIN,50));
			
			black = true;
		}
	}
	
	
	for(int i = 0; i < sizer; i++) {
		for(int j = 0; j < sizec; j++) {
			grid[i][j].setEnabled(false);
		}
	}
	if (first == 1) {
		for(int i = (sizer) / 2; i <= (sizer / 2) + 1; i++) {
			if (i == (sizer / 2)){
				grid[i][(sizer / 2) - 1].setEnabled(true);
				continue;
			}
			for(int j = (sizec / 2) - 1; j <= (sizer / 2) + 1; j++) {
				grid[i][j].setEnabled(true);
			}
		}
	} else {
		for(int i = 0; i < sizer; i++) {
			for(int j = 0; j < sizer; j++) {
				grid[i][j].setEnabled(false);
			}
		}
	}
	
	//if player choose AI agent go first 
	//set all buttons unable 
	//determine the deepest depth according to level player choose
	int diff;

		if(l==0)
			diff=2;
		else if(l==1)
			diff=4;
		else
			diff=25;

	
	//create a game object
	//using the level and whom going first 
	game=new Game(diff,first,sizer,sizec);
	game.game_state.printBoard();
		
	if(first == 2){
		for(int i = 0; i < sizer; i++) {///////////////////////zi ji xie de
			for(int j = 0; j < sizer; j++) {
				if(game.game_state.board[i][j] == 1) {
					grid[i][j].setText("1");;
				} else if(game.game_state.board[i][j] == 2) {
					grid[i][j].setText("2");
				} else if(game.game_state.board[i][j] == 0){
					grid[i][j].setText("0");;
					
				}
			}
		}
		ArrayList<ArrayList<Integer>> player2MoveList = game.game_state.getPlayer_2MoveList(game.game_state.board);
		int length = player2MoveList.size();
		for(int i = 0; i < length; i++) {
			grid[player2MoveList.get(i).get(0)][player2MoveList.get(i).get(1)].setEnabled(true);
		}
	}
	//if player choose AI agent go first
	//get the first action AI agent choose and set the referred button to "X" 
	//set the size of the UI frame
	this.setSize(800, 1000);
	//set the close operation of the frame
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//set the title of the UI
	this.setTitle("Fanorona Game");
	
	//construct the show information of level
	level=new String[3];
	level[0]="Child Level";
	level[1]="Adult Level";
	level[2]="God Level";
	
	//create three panels to layout the main panel
	up=new JPanel();
	mid=new JPanel();
	down=new JPanel();
	
	//create new text fields for information show
	turn=new JTextField();
	step=new JTextField();
	
	//set the text fields uneditable
	turn.setEditable(false);
	step.setEditable(false);
	//set the show information of the player name
	turn.setText(name);
	//set the show information of the step count
	step.setText(String.valueOf(count));
	//set the text of the labels
	JLabel wel1=new JLabel("Human: "+name);
	JLabel wel2=new JLabel("Computer: "+level[l]);
	
	//add labels to the panel
	up.add(wel1,BorderLayout.WEST);
	up.add(wel2,BorderLayout.EAST);
	
	//layout the panels
	up.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	if(sizer ==5){
	mid.setLayout(new GridLayout(5,5));
	}else{
		mid.setLayout(new GridLayout(3,3));
	}
	mid.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
	
	//add action listener to the buttons
	for(int i=0;i<sizer;i++)
		for(int j=0;j<sizec;j++)
		{	
			final int myI = i;
			final int myJ = j;
			grid[i][j].addMouseListener(new StepListener(game,i,j,this){
				public int geti(){
					return myI;
				}
				
				public int getj(){
					return myJ;
				}
				
			});
			mid.add(grid[i][j]);
		}
	
	//add components to the main panel
	down.setLayout(new GridLayout(2,2));
	down.add(new JLabel("Your Name"));
	down.add(turn);
	down.add(new JLabel("Step"));
	down.add(step);
	down.setBorder(BorderFactory.createEmptyBorder(0,300,0,300));
	
	
}

public static void main(String args[]){
	GameUI g = new GameUI("aa",2,0,1);//GameUI( name,  level,  first,  size);
}

}
