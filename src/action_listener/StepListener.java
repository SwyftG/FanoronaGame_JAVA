package action_listener;

import game.Game;
import gameUI.GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import game.Game;

import javax.swing.JOptionPane;

import utility.Action;
import utility.Result;
/**
 * Listener controlling step when player fills a grid
 * 
 * @author LiangGao
 *
 */
public class StepListener implements MouseListener{
	private Game game;     //object of game 
	//private int row;		//for each button , the row of it
	//private int colunm;		//for each button, the colunm of it 
	private GameUI gui;		//object of game UI
	private int xstart;
	private int ystart;
	private int xclick;
	private int yclick;
	private Action act;
	private int num;

	//private boolean flag = false;
	
	/**
	 * construct the listener get parameters needed 
	 * 
	 * @param g game object need to be controlled 
	 * @param r the row of the button
	 * @param c the column of the button
	 * @param gui game UI object need to be controlled
	 */
	public StepListener(Game g, int xs, int ys, GameUI gui){
		game=g;
		xstart = xs;
		ystart = ys;
		this.gui=gui;
		//this.flag = flag;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		this.xclick = xstart;
		this.yclick = ystart;
		if(Game.times == 1) { // times == 1
			// xian shi ke yi chi de dian
			ArrayList<ArrayList<Integer>> movelist = game.game_state.getOneNodeMove(game.game_state.board, xstart, ystart);
			for(int i = 0; i < game.game_state.board.length; i++) {
				for (int j = 0; j < game.game_state.board.length; j++) {
					gui.grid[i][j].setEnabled(false);
				}
			}	
			int length = movelist.size();
			for(int i = 0; i < length; i++) {
				int xb = movelist.get(i).get(0);
				int yb = movelist.get(i).get(1);
				gui.grid[xb][yb].setEnabled(true);
			} // xian shi ke yi chi de dian
			GameUI.xclick = xclick;
			GameUI.yclick = yclick;
			Game.times++;
		} else if (Game.times == 2) { // times == 2

			int xe = xstart;
			int ye = ystart;
			int xs = GameUI.xclick;
			int ys = GameUI.yclick;		
			for(int i = 0; i < game.game_state.board.length; i++) {
				for (int j = 0; j < game.game_state.board.length; j++) {
					gui.grid[i][j].setEnabled(false);
				}
			}
			ArrayList<ArrayList<Integer>> captureList = game.game_state.getCaptureMoveList(game.game_state.board,xs,xe,ys,ye);
			if(captureList.size() > 1) {  // dao di san bu
				int lengthm = captureList.size();
				for(int i = 0; i < lengthm; i++) {
					int xc = captureList.get(i).get(0);
					int yc = captureList.get(i).get(1);
					gui.grid[xc][yc].setEnabled(true);
				}	
				Game.times++;
				GameUI.xclick2 = xe;
				GameUI.yclick2 = ye;
			} else { // bu chi huo zhe zhi neng chi yi ge 
				num = game.game_state.board[xs][ys];
				if (game.game_state.isCapture(xs, xe, ys, ye)) { // ke yi chi ,er qie zhi chi yi ge 
					int captureType = 0;
				if(game.game_state.isCaptureF(xs, xe, ys, ye)) {
						captureType = 1;
					} else if (game.game_state.isCaptureB(xs, xe, ys, ye)) {
						captureType = -1;
					}
					act = new Action(xs, xe, ys, ye, captureType);
					Result re = game.play(act);
					if(re.result == 9) {
						for(int i = 0; i < game.game_state.board.length; i++) {
							for (int j = 0; j < game.game_state.board.length; j++) {
								if(game.game_state.board[i][j] == 1) {
									gui.grid[i][j].setText("1");;
									} else if(game.game_state.board[i][j] == 2) {
									gui.grid[i][j].setText("2");
								} else if(game.game_state.board[i][j] == 0){
									gui.grid[i][j].setText("0");;
								}
							}
						}

						for(int i = 0; i < game.game_state.board.length; i++) {
							for (int j = 0; j < game.game_state.board.length; j++) {
								gui.grid[i][j].setEnabled(false);
							}
						}

						if(num == 2) {
							ArrayList<ArrayList<Integer>> player2MoveList = game.game_state.getPlayer_2MoveList(game.game_state.board);
							int lengthcc = player2MoveList.size();
							for(int i = 0; i < lengthcc; i++) {
								gui.grid[player2MoveList.get(i).get(0)][player2MoveList.get(i).get(1)].setEnabled(true);
							}	
						} else if(num == 1) {
							ArrayList<ArrayList<Integer>> player1MoveList = game.game_state.getPlayer_1MoveList(game.game_state.board);
							int lengthcc = player1MoveList.size();
							for(int i = 0; i < lengthcc; i++) {
								gui.grid[player1MoveList.get(i).get(0)][player1MoveList.get(i).get(1)].setEnabled(true);
							}
						}


							Game.times = 1;
						} else {
							if(re.result==1)
								JOptionPane.showMessageDialog(gui, "Congritulations, you win!");
							else if(re.result==-1){
								JOptionPane.showMessageDialog(gui, "So close, you lose!");
							}
						}
					} else {	// bu chi 
						act = new Action(xs, xe, ys, ye, 0);
						Result re = game.play(act);

						if(re.result == 9) { // you xi ji xu
							for(int i = 0; i < game.game_state.board.length; i++) {
								for (int j = 0; j < game.game_state.board.length; j++) {
									if(game.game_state.board[i][j] == 1) {
										gui.grid[i][j].setText("1");;
										} else if(game.game_state.board[i][j] == 2) {
										gui.grid[i][j].setText("2");
									} else if(game.game_state.board[i][j] == 0){
										gui.grid[i][j].setText("0");;
									}
								}
							}


		
							for(int i = 0; i < game.game_state.board.length; i++) {
								for (int j = 0; j < game.game_state.board.length; j++) {
									gui.grid[i][j].setEnabled(false);
								}
							}

							if(num == 2) {
								ArrayList<ArrayList<Integer>> player2MoveList = game.game_state.getPlayer_2MoveList(game.game_state.board);
								int lengthcc = player2MoveList.size();
							//	System.out.println("2 " + player2MoveList);
								for(int i = 0; i < lengthcc; i++) {
									gui.grid[player2MoveList.get(i).get(0)][player2MoveList.get(i).get(1)].setEnabled(true);
								}	
							} else if(num == 1) {
								ArrayList<ArrayList<Integer>> player1MoveList = game.game_state.getPlayer_1MoveList(game.game_state.board);
								int lengthcc = player1MoveList.size();
							//	System.out.println("1 " + player1MoveList);
								for(int i = 0; i < lengthcc; i++) {
									gui.grid[player1MoveList.get(i).get(0)][player1MoveList.get(i).get(1)].setEnabled(true);
								}
							}


							Game.times = 1;

						} else {	// sheng fu yi fen
							if(re.result==1)
								JOptionPane.showMessageDialog(gui, "Congritulations, you win!");
							else if(re.result==-1){
								JOptionPane.showMessageDialog(gui, "So close, you lose!");
							}
						}

					}

				}



		} else if (Game.times == 3) { // times == 3
			
			int xs = GameUI.xclick;
			int xe = GameUI.xclick2;
			int ys = GameUI.yclick;
			int ye = GameUI.yclick2;

			int xcapture = xstart;
			int ycapture = ystart;

			num = game.game_state.board[xs][ys];

			int distence = (xcapture - xe) * (xcapture - xe) + (ycapture - ye) * (ycapture - ye);
			if ((distence == 2) || (distence == 1)) {
				act = new Action(GameUI.xclick, GameUI.xclick2, GameUI.yclick, GameUI.yclick2,1);
			} else {
				act = new Action(GameUI.xclick, GameUI.xclick2, GameUI.yclick, GameUI.yclick2,-1);
			}

			Result re = game.play(act);

			if(re.result == 9) { // you xi ji xu
				for(int i = 0; i < game.game_state.board.length; i++) {
					for (int j = 0; j < game.game_state.board.length; j++) {
						if(game.game_state.board[i][j] == 1) {
							gui.grid[i][j].setText("1");;
							} else if(game.game_state.board[i][j] == 2) {
							gui.grid[i][j].setText("2");
						} else if(game.game_state.board[i][j] == 0){
							gui.grid[i][j].setText("0");;
						}
					}
				}

				for(int i = 0; i < game.game_state.board.length; i++) {
					for (int j = 0; j < game.game_state.board.length; j++) {
						gui.grid[i][j].setEnabled(false);
					}
				}
		
				if(num == 2) {
					ArrayList<ArrayList<Integer>> player2MoveList = game.game_state.getPlayer_2MoveList(game.game_state.board);
					int lengthcc = player2MoveList.size();
				//	System.out.println("2 " + player2MoveList);
					for(int i = 0; i < lengthcc; i++) {
						gui.grid[player2MoveList.get(i).get(0)][player2MoveList.get(i).get(1)].setEnabled(true);
					}	
				} else if(num == 1) {
					ArrayList<ArrayList<Integer>> player1MoveList = game.game_state.getPlayer_1MoveList(game.game_state.board);
					int lengthcc = player1MoveList.size();
				//	System.out.println("1 " + player1MoveList);
					for(int i = 0; i < lengthcc; i++) {
						gui.grid[player1MoveList.get(i).get(0)][player1MoveList.get(i).get(1)].setEnabled(true);
					}
				}


				Game.times = 1;

			} else {	// sheng fu yi fen
				if(re.result==1)
					JOptionPane.showMessageDialog(gui, "Congritulations, you win!");
				else if(re.result==-1){
					JOptionPane.showMessageDialog(gui, "So close, you lose!");
				}
			}
		} 
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
