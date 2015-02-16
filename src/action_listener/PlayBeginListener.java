package action_listener;

import gameUI.GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 * Listener control the play button in the welcome UI
 * @author Liang Gao
 *
 */
public class PlayBeginListener implements ActionListener{

	private JComboBox level;   //record the level player choose
	private JComboBox first;	//record who go first 
	private JTextField name;	//record name of the player
	private JFrame jf;			//parent UI
	private JComboBox size;
	
	/**
	 * construct the Listener
	 * get field needed
	 * 
	 * @param jf parent frame
	 * @param name TextField record the name of player
	 * @param level	ComboBox record which level the player chooses
	 * @param first ComboBox record who go first
	 */
	public PlayBeginListener(JFrame jf,JTextField name,JComboBox level,JComboBox first, JComboBox size){
		this.level=level;
		this.first=first;
		this.name=name;
		this.size = size;
		this.jf=jf;
	}
	@Override
	/**
	 * override the actionPerformed method to display the Game UI
	 */
	public void actionPerformed(ActionEvent arg0) {
		//judge if the player name is void or just blank 
		if(name.getText().trim().equals(""))
			 JOptionPane.showMessageDialog(jf, "Please enter a name(not all space)!");
		 else
		 {   //construct a Game UI  to play game.
			 new GameUI(name.getText(),level.getSelectedIndex(),first.getSelectedIndex(),size.getSelectedIndex());
			 //close the welcome UI
			 jf.dispose();
		 }
	}

}
