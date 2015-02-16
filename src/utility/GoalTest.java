package utility;

import state.State;
import state.StateInfo;
/**
 * GoalTest class consist of two static methods
 * isGoal method determine if the game come to an end
 * cutOff method determine if the game reaches a depth where should be cut off by the level
 * 
 * @author Liang Gao
 *
 */
public class GoalTest {
	
	
	
	public static int isGoal(State state, Action act){
		int xstart = act.getXStart();
		//int xend = act.getXEnd();
		int ystart = act.getYStart();
		
		//int yend = act.getYEnd();
		State state2=(State)state.clone();
		//System.out.println("&&&&&&&&&&&&&&&&&_STATE2_1");
		//state2.printBoard();
		int[][] board = state2.board;
		//System.out.println("----isGoal---");
		//state.printBoard();
		//System.out.println("----isGoal---END");
		int row = board.length;
		int col = board[0].length;
		int playerNo = board[xstart][ystart];
		//System.out.println(playerNo + " row " + row + " col " + col + " start " + xstart + " ystart " + ystart);
		int enemyNo = 0;
		if (playerNo == 1) {
			enemyNo = 2;
		} else {
			enemyNo = 1;
		}
		int enemyCount = 0;

		state2.stateAfterAction(act, state2);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (state2.board[i][j] == enemyNo) {
					enemyCount++;
				}
			}
		}

		if (enemyCount == 0) {
			return 1; //////////// is goal state
		}
		return -1;	      // game continue
	}
	
	/**
	 * determine if the game reaches a depth where should be cut off by the level
	 * 
	 * @param state state needed to cut off
	 * @return value of utility calculated by a algorithm
	 */
	public static int cutOff(State state, int playerNo){
		int[][] board = state.getBoard();
		int row = board.length;
		int col = board[0].length;
		int whiteScore = 0;
		int blackScore = 0;
		int whiteCount = 0;
		int blackCount = 0;
		int totalCount = 0;
		if (row == 3) {
			totalCount = 4;
		} else if (row == 5) {
			totalCount = 12;
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 1) {
					whiteCount++;
				}
				if (board[i][j] == 2) {
					blackCount++;
				}
			}
		}

		whiteScore = (totalCount - blackCount) * 2;
		blackScore = (totalCount - whiteCount) * (-2);

		if (playerNo == 1) {
			return blackScore;
		} 
		if (playerNo == 2) {
			return whiteScore;
		}
		return 0;
	}
			
public static void stateAfterAction(Action act, State state) {
	int xstart = act.getXStart();
	int xend = act.getXEnd();
	int ystart = act.getYStart();
	int yend = act.getYEnd();
	int flag = act.getCaptureKind();

	if (flag == 0) {
		state.board[xend][yend] = state.board[xstart][ystart];
		state.board[xstart][ystart] = 0;
	} else {
		captureMove(xstart, xend, ystart, yend, flag, state);
	}
}


/**
 * make a capture move action
 * @param action coordites
 */
public static void captureMove(int xstart, int xend, int ystart, int yend, int flag, State state) {
	int playerNo = state.board[xstart][ystart];
	state.board[xend][yend] = playerNo;
	state.board[xstart][ystart] = 0;

	if (flag == 1) {
		if ((xend - xstart) == 0) {
			for (int j = 1; ((yend + (yend - ystart) * j) < state.COLUNM) && ((yend + (yend - ystart) * j) >= 0) ;j++) {
				if (state.board[xstart][yend + (yend - ystart) * j] == 0) {
					break;
				} else {
					state.board[xstart][yend + (yend - ystart) * j] = 0;
				}
			}
		} else if ((yend - ystart) == 0) {
			for (int i = 1; ((xend + (xend - xstart) * i) < state.ROW) && ((xend + (xend - xstart) * i) >= 0); i++) {
				if (state.board[xend + (xend - xstart) * i][ystart] == 0) {
					break;
				} else {
					state.board[xend + (xend - xstart) * i][ystart] = 0;
				}
			}
		} else {
			for (int i = 1; ((xend + (xend - xstart) * i) < state.ROW) && ((xend + (xend - xstart) * i) >= 0); i++) {
				for(int j = 1; ((yend + (yend - ystart) * j) < state.COLUNM) && ((yend + (yend - ystart) * j) >= 0); j++) {
					if (state.board[xend + (xend - xstart) * i][yend + (yend - ystart) * j] == 0) {
						break;
					} else {
						state.board[xend + (xend - xstart) * i][yend + (yend - ystart) * j] = 0;
					}
				}
			}	
		}
	} else {
		if ((xstart - xend) == 0) {
			for (int j = 1; ((ystart + (ystart - yend) * j) < state.COLUNM) && ((ystart + (ystart - yend) * j) >= 0); j++) {
				if (state.board[xstart][ystart + (ystart - yend) * j] == 0) {
					break;
				} else {
					state.board[xstart][ystart + (ystart - yend) * j] = 0;
				}
			}
		} else if ((ystart -yend) == 0) {
			for (int i = 1; ((xstart + (xstart - xend) * i) < state.ROW) && ((xstart + (xstart - xend) * i) >= 0) ;i++) {
				if (state.board[xstart + (xstart - xend) * i][ystart] == 0) {
					break;
				} else {
					state.board[xstart + (xstart - xend) * i][ystart] = 0;
				}
			}
		} else {
			for (int i = 1; ((xstart + (xstart - xend) * i) < state.ROW) && ((xstart + (xstart - xend) * i) >= 0); i++) {
				for(int j = 1; ((ystart + (ystart - yend) * j) < state.COLUNM) && ((ystart + (ystart - yend) * j) >= 0); j++) {
					if (state.board[xstart + (xstart - xend) * i][ystart + (ystart - yend) * j] == 0) {
						break;
					} else {
						state.board[xstart + (xstart - xend) * i][ystart + (ystart - yend) * j] = 0;
					}
				}
			}	
		}
	}
}


}
