package state;

import java.util.ArrayList;
import utility.Action;
import utility.Action;
import utility.Action;
public class State implements Cloneable{

public  int ROW;
public  int COLUNM;

public int[][] board;
public ArrayList<ArrayList<Integer>> player_1MoveList = new ArrayList<ArrayList<Integer>>();
public ArrayList<ArrayList<Integer>> player_2MoveList = new ArrayList<ArrayList<Integer>>();

public StateInfo player_1;
public StateInfo player_2;


/**
 * the State of this game.
 * 
 * @param row number of the game board
 * @param col nomber of the game board
 */
public State(int row, int col){
	this.ROW = row;
	this.COLUNM = col;
	board = new int[row][col];
	for (int i = 0; i < (ROW / 2); i++) {
		for (int j = 0; j < COLUNM ; j++) {
			board[i][j] = 2;
		}
	}
	for (int i = (ROW / 2) + 1; i < ROW; i++) {
		for (int j = 0; j < COLUNM; j++) {
			board[i][j] = 1;
		}
	}
	boolean black = true;
	for (int i = COLUNM / 2; i < COLUNM; i++) {
		if (i == COLUNM / 2) {
			board[ROW / 2][i] = 0;
			continue;
		} 
		if (black) {
			board[ROW / 2][i] = 2;
			black = false;
		} else {
			board[ROW / 2][i] = 1;
			black = true;
		}
	}
	black = false;
	for (int i = COLUNM / 2 - 1; i >= 0; i--) {
		if (black) {
			board[ROW / 2][i] = 2;
			black = false;
		} else {
			board[ROW / 2][i] = 1;
			black = true;
		}
	}

	player_1 = new StateInfo(ROW, COLUNM, 1);//1 is white
	player_2 = new StateInfo(ROW, COLUNM, 2);//2 is black

	player_1MoveList = getPlayer_1MoveList(board);
	player_2MoveList = getPlayer_2MoveList(board);

}

/**
 * print the game board
 */
public void printBoard()
	{
		for(int i = 0; i < ROW; i++)
		{
			for(int j = 0; j < COLUNM; j++)
			{
								
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}


/**
 * set the board
 * @param board the matrix you want to set 
 */
public void setBoard(int[][] board) {
	this.board = board;
}

/**
 * set the state information to player 1
 * @param player_1 the stateInfo you want to set
 */
public void setPlayer_1(StateInfo player_1) {
	this.player_1 = player_1;
}
/**
 * set the state information to player 2
 * @param player_2 the stateInfo you want to set
 */
public void setPlayer_2(StateInfo player_2) {
	this.player_2 = player_2;
}
/**
 * get the stateInfo of the player 1
 * 
 * @return the StateInfo of player 1
 */
public StateInfo getPlayer_1() {
	return player_1;
}

/**
 * get the stateInfo of the player 2
 * @return the stateInfo of player 2
 */
public StateInfo getPlayer_2() {
	return player_2;
}
/**
 * set the player 1's moveList
 * @param playerlist
 */
public void setPlayer_1MoveList(ArrayList<ArrayList<Integer>> playlist) {
	this.player_1MoveList = playlist;
}
/**
 * get the player1's movelist
 * @return the move list of player 1
 */
public ArrayList<ArrayList<Integer>> getPlayer_1MoveList() {
	return player_1MoveList;
}
/**
 * set the player 2's move list
 * @param the playerlist of player 2
 */
public void setPlayer_2MoveList(ArrayList<ArrayList<Integer>> playlist) {
	this.player_2MoveList = playlist;
}
/**
 * get the player 2's move list
 * @return player 2's move list
 */
public ArrayList<ArrayList<Integer>> getPlayer_2MoveList() {
	return player_2MoveList;
}
/**
 * get the board
 * @return the board of the state
 */
public int[][] getBoard() {
	return board;
}
/**
 * get player 1's move list from game board
 * @param game board
 * @return player 1's move list
 */
public ArrayList<ArrayList<Integer>> getPlayer_1MoveList(int[][] board) {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	int row = board.length;
	int col = board[0].length;
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			if (board[i][j] == 1) {
				if (isValidMove(board, i, j)) {
					if(isCaptureMove(board, i, j)) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(i);
						tmp.add(j);
						result.add(tmp);
					}
				}
			}
		}
	}
	if(result.size() != 0) {
		return result;
	} else {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 1) {
					if (isValidMove(board, i, j)) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(i);
						tmp.add(j);
						result.add(tmp);
					}
				}
			}
		}
	}	
	return result;
}
/**
 * get player 2's move list from game board
 * @param game board
 * @return player 2's move list
 */
public ArrayList<ArrayList<Integer>> getPlayer_2MoveList(int[][] board) {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	int row = board.length;
	int col = board[0].length;
	for (int i = 0; i < row; i++) {
		for (int j = 0; j < col; j++) {
			if (board[i][j] == 2) {
				if (isValidMove(board, i, j)) {
					if(isCaptureMove(board, i, j)) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(i);
						tmp.add(j);
						result.add(tmp);
					}
				}
			}
		}
	}
	if(result.size() != 0) {
		return result;
	} else {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 2) {
					if (isValidMove(board, i, j)) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(i);
						tmp.add(j);
						result.add(tmp);
					}
				}
			}
		}
	}	
	return result;
}
/**
 * get the capture move list form board
 * @param the information of game board
 * @return the move list 
 */
public ArrayList<ArrayList<Integer>> getCaptureMoveList(int[][] board, int xs, int xe, int ys, int ye){
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	if(isCaptureF(xs,xe,ys, ye)) {
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		tmp.add(xe + (xe - xs));
		tmp.add(ye + (ye - ys));
		result.add(tmp);
	}		
	if (isCaptureB(xs,xe,ys,ye)) {
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		tmp.add(xs - (xe - xs));
		tmp.add(ys - (ye - ys));
		result.add(tmp);
	}
	return result;
}
/**
 * get one node move list form board information
 * @param game board information
 * @return move list
 */
public ArrayList<ArrayList<Integer>> getOneNodeMove(int[][] board, int x, int y) {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	int row = board.length;
	int col = board[0].length;
	result = isCaptureMove2(board, x, y);
	if(result.size() != 0) {
		return result;
	}
	result = isValidMove2(board, x, y);

	return result;
}
/**
 * get one node move list form board information
 * @param game board information
 * @return move list
 */
public ArrayList<ArrayList<Integer>> getOneNodeMove2(int[][] board, int x, int y, int xlast, int ylast) {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	int row = board.length;
	int col = board[0].length;
	result = isCaptureMove2(board, x, y);
	if(result.size() != 0) {
		return result;
	}
	return result;
}

/**
 * get capture move list form board information
 * @param game board information
 * @return move list
 */
public ArrayList<ArrayList<Integer>> isCaptureMove2(int[][] board, int x, int y) {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	int row = board.length;
	int col = board[0].length;
	int playerNo = board[x][y];
	if(((x + y) % 2) == 0) {
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(i == 0 && j == 0) {
					continue;
				}	 
				if(((x + i) < 0) || ((x + i) >= row) || ((y + j) < 0) || ((y + j) >= col)) {
					continue;
				} else {
					if (board[x + i][y + j] == 0) {
						if(isCapture(x, x+i, y, y+j)){
							ArrayList<Integer> tmp = new ArrayList<Integer>();
							tmp.add(x + i);
							tmp.add(y+j);
							result.add(tmp);
						}
					}
				}
			}
		}		
	} else {
		for (int i = -1; i < 2; i++) {
			if (i == 0) {
				for (int j = -1; j < 2; j += 2) {
					if (((y + j) >= col) || ((y + j) < 0)) {
						continue;
					} else {
						if (board[x][y + j] == 0) {
							if(isCapture(x,x,y,y+j)){
								ArrayList<Integer> tmp = new ArrayList<Integer>();
								tmp.add(x);
								tmp.add(y + j);
								result.add(tmp);								
							}

						}
					}
				}
				continue;
			} else {
				if (((x + i) < 0) || ((x + i) >= row) ) {
					continue;
				} else {
					if (board[x + i][y] == 0) {
						if(isCapture(x,x + i,y,y)){
							ArrayList<Integer> tmp = new ArrayList<Integer>();
							tmp.add(x + i);
							tmp.add(y );
							result.add(tmp);								
						}
					}
				}
			}
		}
	}
	
	return result;
}
/**
 * get the valid move options  form board information
 * @param game board information
 * @return move list
 */
public ArrayList<ArrayList<Integer>> isValidMove2(int[][] board, int x, int y) {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	int row = board.length;
	int col = board[0].length;
	if(((x + y) % 2) == 0) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (((x + i) < 0) || ((x + i) >= row) || ((y + j) < 0) || ((y + j) >= col)) {
					continue;
				} else {
					if (board[x + i][y + j] == 0) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
							tmp.add(x + i);
							tmp.add(y+j);
							result.add(tmp);
					}
				}
			}
		}		
	} else {
		for (int i = -1; i < 2; i++) {
			if (i == 0) {
				for (int j = -1; j < 2; j += 2) {
					if (((y + j) >= col) || ((y + j) < 0) ) {
						continue;
					} else {
						if (board[x][y + j] == 0) {
							
								ArrayList<Integer> tmp = new ArrayList<Integer>();
								tmp.add(x);
								tmp.add(y + j);
								result.add(tmp);								
							

						}
					}
				}
				continue;
			} else {
				if (((x + i) < 0) || ((x + i) >= row) ) {
					continue;
				} else {
					if (board[x + i][y] == 0) {
						
							ArrayList<Integer> tmp = new ArrayList<Integer>();
							tmp.add(x + i);
							tmp.add(y );
							result.add(tmp);								
						
					}
				}
			}
		}
	}
	
	return result;
}

/**
 * judge the move is capture move or not
 * @param game board information
 * @return true or false
 */
public boolean isCaptureMove(int[][] board, int x, int y) {
	int row = board.length;
	int col = board[0].length;
	int playerNo = board[x][y];
	if (((x + y) % 2) == 0) {
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(i == 0 && j == 0) {
					continue;
				} 	
				if(((x + i) < 0) || ((x + i) >= row) || ((y + j) < 0) || ((y + j) >= col)) {
					continue;
				} else {
					if (board[x + i][y + j] == 0) {
						return isCapture(x, x+i, y, y+j);
					}
				}
			}
		}	
	} else {
		for (int i = -1; i < 2; i++) {
			if (i == 0) {
				for (int j = -1; j < 2; j += 2) {
					if (((y + j) >= col) || ((y + j) < 0)) {
						continue;
					} else {
						if (board[x][y + j] == 0) {
							 return isCapture(x,x,y,y+j);
						}
					}
				}
				continue;
			} else {
				if (((x + i) < 0) || ((x + i) >= row) ) {
					continue;
				} else {
					if (board[x + i][y] == 0) {
						return isCapture(x,x + i,y,y);					}
				}
			}
		}

	}
	return false;
}
/**
 * judge the move is valid move or not
 * @param game board information
 * @return true or false
 */
public boolean isValidMove(int[][] board, int x, int y) {
	int row = board.length;
	int col = board[0].length;
	int num = board[x][y];
	if (((x + y) % 2) == 0) {
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if (((x + i) < 0) || ((x + i) >= row) || ((y + j) < 0) || ((y + j) >= col)) {
						continue;
					} else {
						if (board[x + i][y + j] == 0) {
							return true;
						}
					}
				}
			}		
	} else {
		for (int i = -1; i < 2; i++) {
			if (i == 0) {
				for (int j = -1; j < 2; j += 2) {
					if (((y + j) >= col) || ((y + j) < 0)) {
						continue;
					} else {
						if (board[x][y + j] == 0) {
							return true;

						}
					}
				}
				continue;
			} else {
				if (((x + i) < 0) || ((x + i) >= row) ) {
					continue;
				} else {
					if (board[x + i][y] == 0) {
						return true;
					}
				}
			}
		}

	}

	return false;
}

public int getBoardValue(int x, int y) {
	return board[x][y];
}

public int getBoardRow() {
	return board.length;
}

public int getBoardCol() {
	return board[0].length;
}

/** 
 * use player movelist to set the board
 * @param player 1's movelist
 * @param player 2's movelist
 * @return game board
 */
public int[][] getBoardFromPlayerList(ArrayList<ArrayList<Integer>> player_1, ArrayList<ArrayList<Integer>> player_2) {///////////////get board from player list
	int[][] result = new int[ROW][COLUNM];
	for (int i = 0; i < ROW; i++) {
		for (int j = 0; j < COLUNM; j++) {
			result[i][j] = 0;
		}
	}
	int long1 = player_1.size();
	int long2 = player_2.size();
	for (int i = 0; i < long1; i++) {
		result[player_1.get(i).get(0)][player_1.get(i).get(1)] = 1;
	}
	for (int i = 0; i < long2; i++) {
		result[player_2.get(i).get(0)][player_2.get(i).get(1)] = 2;	
	}
	return result;
}

/**
 * get the action list from player No.
 * @param player's No.
 * @return action list
 */
public ArrayList<Action> getActionList(int playerNo) {
	ArrayList<Action> result = new ArrayList<Action>();
	ArrayList<ArrayList<Integer>> playerMoveList = new ArrayList<ArrayList<Integer>>();
	if (playerNo == 1) {
		playerMoveList =  getPlayer_1MoveList(board);
	} else {
		playerMoveList =  getPlayer_2MoveList(board);
	}
	
	int length = playerMoveList.size();
	for (int i = 0; i < length; i++) {
		int xstart = playerMoveList.get(i).get(0);
		int ystart = playerMoveList.get(i).get(1);
		ArrayList<ArrayList<Integer>> validMoveNode = getValidMoveNode(xstart, ystart, xstart, ystart); 
		int length2 = validMoveNode.size();
		ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
		for (int k = 0; k < length2; k++) {
			ArrayList<Integer> compare = new ArrayList<Integer>();
			compare.add(playerMoveList.get(i).get(0));
			compare.add(validMoveNode.get(k).get(0)); 	
			compare.add(playerMoveList.get(i).get(1));	
			compare.add(validMoveNode.get(k).get(1));
			tmp.add(compare);
		}
		for (int j = 0; j < length2; j++) {
			int xend = tmp.get(j).get(1);
			int yend = tmp.get(j).get(3);
			if (isCapture(xstart, xend, ystart, yend)) {
				if (isCaptureF(xstart, xend, ystart, yend)) {
					Action act = new Action(xstart, xend, ystart, yend, 1); //////////////capture forward 
					result.add(act);
				} else if (isCaptureB(xstart, xend, ystart, yend)) {
					Action act = new Action(xstart, xend, ystart, yend, -1); //////////////capture back 
					result.add(act);
				}
			} 
		}
		if(result.size()!= 0) {
			return result;
		}
		for (int j = 0; j < length2; j++) {
			int xend = tmp.get(j).get(1);
			int yend = tmp.get(j).get(3);
			Action act = new Action(xstart, xend, ystart, yend, 0);
			result.add(act); 
		}
	}	
	return result;
}
/**
 * get one node move action from palyer no and player's action
 * @param player's No.
 * @param player's action
 * @return player's action list
 */
public ArrayList<Action> getOneNodeActionList(int playerNo, Action act) {
	ArrayList<Action> result = new ArrayList<Action>();
	int xend = act.getXStart();
	int xstart = act.getXEnd();
	int yend = act.getYStart();
	int ystart = act.getYEnd();
	ArrayList<ArrayList<Integer>> validMoveNode = getValidMoveNode(xstart, ystart, xend, yend);
	int length2 = validMoveNode.size();
	ArrayList<ArrayList<Integer>> tmp = new ArrayList<ArrayList<Integer>>();
	for (int k = 0; k < length2; k++) {
		ArrayList<Integer> compare = new ArrayList<Integer>();
		compare.add(xstart);
		compare.add(validMoveNode.get(k).get(0)); 	
		compare.add(ystart);	
		compare.add(validMoveNode.get(k).get(1));
		tmp.add(compare);
	}
	for (int j = 0; j < length2; j++) {
		 xend = tmp.get(j).get(1);
		 yend = tmp.get(j).get(3);
		if (isCapture(xstart, xend, ystart, yend)) {
			if (isCaptureF(xstart, xend, ystart, yend)) {
				Action action = new Action(xstart, xend, ystart, yend, 1); //////////////capture forward 
				result.add(action);
			} else if (isCaptureB(xstart, xend, ystart, yend)) {
				Action action = new Action(xstart, xend, ystart, yend, -1); //////////////capture back 
				result.add(action);
			}
		} 									////----- x, y, x' y' dou zai, gai pan duan shi fou capture le
	}
	return result;
}
/**
 * get the capture move list from the game board
 * @param board information
 * @return  action list
 */
public ArrayList<ArrayList<Integer>> getNextCaptureMoveList(int[][] board, int x, int y, int xlast, int ylast) {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	int changdu = board.length;
		if(((x + y) % 2) == 0) { // 8 ge dian
			for(int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					if(((x + i) < 0) || ((x + i) >= changdu) || ((y + j) >= changdu) || ((y + j) < 0) || (((x + i) == xlast) && ((y + j) == ylast)) ) {
						continue;
					} else {
						if (isCapture(x, (x + i), y, (y + j))) {
							ArrayList<Integer> tmp = new ArrayList<Integer>();
							tmp.add(x + i);
							tmp.add(y + j);
							result.add(tmp);
						}
					}
				}
			}
		} else {	// 4 ge dian
			for(int i = -1; i < 2; i++) {
				if (i == 0) {
					for (int j = -1; j < 2; j++) {
						if (((x + i) < 0) || ((x + i) >= changdu) || ((y + j) >= changdu) || ((y + j) < 0) || (((x + i) == xlast) && ((y + j) == ylast)) || (((x + i) == x)) && ((y + j) == y)) {
							continue;
						} else {
							if (isCapture(x, (x + i), y, (y + j))) {
								ArrayList<Integer> tmp = new ArrayList<Integer>();
								tmp.add(x + i);
								tmp.add(y + j);
								result.add(tmp);
							}			
						}
					}
					continue;
				} else {
					if ( ((x + i) < 0) || ((x + i) >= changdu) || ((x + i) == xlast)) {
						continue;
					} else {
						if (isCapture(x, (x + i), y, y)) {
							ArrayList<Integer> tmp = new ArrayList<Integer>();
							tmp.add(x + i);
							tmp.add(y);
							result.add(tmp);
						}			
					}
				}
			}
		}
	return result;
}
/**
 * judge the move is a capture move or not
 * @param action coordinate
 * @return true or false
 */
public boolean isCapture(int x1, int x2, int y1, int y2) {
	int playerNo = board[x1][y1];
	
	int xb = 2 * x1 - x2;
	int yb = 2 * y1 - y2;
	int xf = 2 * x2 - x1;
	int yf = 2 * y2 - y1;
	boolean result = false;
	if ((xb < ROW) && (xb >= 0) && (yb < COLUNM) && (yb >= 0)) {
		if ((board[xb][yb] != playerNo) && (board[xb][yb] != 0)) {
			result = true;
		}
	}
	if ((xf < ROW) && (xf >= 0) && (yf < COLUNM) && (yf >= 0)) {
		if ((board[xf][yf] != playerNo) && (board[xf][yf] != 0)) {
			result = true;
		}
	}
	return result;
}
/**
 * judge the move is a forward capture move or not
 * @param action coordinate
 * @return true or false
 */
public boolean isCaptureF(int x1, int x2, int y1, int y2) {
	int playerNo = board[x1][y1];
	int xf = 2 * x2 - x1;
	int yf = 2 * y2 - y1;
	boolean result = false;
	if ((xf < ROW) && (xf >= 0) && (yf < COLUNM) && (yf >= 0)) {
		if ((board[xf][yf] != playerNo) && (board[xf][yf] != 0)) {
			result = true;
		}
	}
	return result;
}
/**
 * judge the move is a backword capture move or not
 * @param action coordinate
 * @return true or false
 */
public boolean isCaptureB(int x1, int x2, int y1, int y2) {
	int playerNo = board[x1][y1];
	
	int xb = 2 * x1 - x2;
	int yb = 2 * y1 - y2;
	boolean result = false;
	if ((xb < ROW) && (xb >= 0) && (yb < COLUNM) && (yb >= 0)) {
		if ((board[xb][yb] != playerNo) && (board[xb][yb] != 0)) {
			result = true;
		}
	}
	return result;
}
/**
 * make one action
 * @param action coordinate
 * @return return a action
 */
public Action madeAction(int xstart, int ystart, int xend, int yend) {
	Action result = new Action(900,900,900,900,900);
	result.setStartNode(xstart, ystart);
	result.setEndNode(xend, yend);
	if(isCapture(xstart,xend,ystart,yend)) {
		if (isCaptureF(xstart, xend, ystart, yend)) {
			result.setCaptureKind(1);
		} else if (isCaptureB(xstart, xend, ystart, yend)) {
			result.setCaptureKind(-1);
		} else {
			result.setCaptureKind(0);
		}
	}
	return result;
}
/**
 * get the valid move list from the state of game board 
 * @param action coordinate
 * @return action list
 */
public ArrayList<ArrayList<Integer>> getValidMoveNode(int x, int y, int xlast, int ylast) {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	if ((x + y) % 2 == 0) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (((x + i) < 0) || ((x + i) >= ROW) || ((y + j) >= COLUNM) || ((y + j) < 0) || ((x + i) == xlast) && ((y + j) == ylast)) {
					continue;
				} else {
					if (board[x + i][y + j] == 0) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(x + i);
						tmp.add(y + j);
						if ((i == 0) && (j == 0)) {
							continue;
						} else {
							result.add(tmp);
						}
					}
				}
			}
		}
	} else {
		for (int i = -1; i < 2; i++) {
			if (i == 0) {
				for (int j = -1; j < 2; j += 2) {
					if (((y + j) >= COLUNM) || ((y + j) < 0) || ((x + i) == xlast) && ((y + j) == ylast)) {
						continue;
					} else {
						if (board[x][y + j] == 0) {
							ArrayList<Integer> tmp = new ArrayList<Integer>();
							tmp.add(x);
							tmp.add(y + j);
							result.add(tmp);
						}
					}
				}
				continue;
			} else {
				if (((x + i) < 0) || ((x + i) >= ROW) || ((x + i) == xlast) && ((y) == ylast)) {
					continue;
				} else {
					if (board[x + i][y] == 0) {
						ArrayList<Integer> tmp = new ArrayList<Integer>();
						tmp.add(x + i);
						tmp.add(y);
						result.add(tmp);
					}
				}
			}
		}
	}
	return result;
}

/**
 * state after action
 * @param state
 * @param action
 */
public void stateAfterAction(Action act, State state) {
	int xstart = act.getXStart();
	int xend = act.getXEnd();
	int ystart = act.getYStart();
	int yend = act.getYEnd();
	int flag = act.getCaptureKind();

	if (flag == 0) {
		state.board[xend][yend] = state.board[xstart][ystart];
		state.board[xstart][ystart] = 0;
	} else {
		captureMove(xstart, xend, ystart, yend, flag);
	}
}

/**
 * make a capture move 
 * @param action coordinate
 */
public void captureMove(int xstart, int xend, int ystart, int yend, int flag) {
	int playerNo = board[xstart][ystart];
	board[xend][yend] = playerNo;
	board[xstart][ystart] = 0;

	if (flag == 1) {
		if ((xend - xstart) == 0) {
			for (int j = 1; ((yend + (yend - ystart) * j) < this.COLUNM) && ((yend + (yend - ystart) * j) >= 0) ;j++) {
				if (board[xstart][yend + (yend - ystart) * j] == 0) {
					break;
				} else {
					board[xstart][yend + (yend - ystart) * j] = 0;
				}
			}
		} else if ((yend - ystart) == 0) {
			for (int i = 1; ((xend + (xend - xstart) * i) < this.ROW) && ((xend + (xend - xstart) * i) >= 0); i++) {
				if (board[xend + (xend - xstart) * i][ystart] == 0) {
					break;
				} else {
					board[xend + (xend - xstart) * i][ystart] = 0;
				}
			}
		} else {
			for (int i = 1; ((xend + (xend - xstart) * i) < this.ROW) && ((xend + (xend - xstart) * i) >= 0) && ((yend + (yend - ystart) * i) < this.COLUNM) && ((yend + (yend - ystart) * i) >= 0); i++) {
				if (board[xend + (xend - xstart) * i][yend + (yend - ystart) * i] == 0) {
					break;
				} else {
					board[xend + (xend - xstart) * i][yend + (yend - ystart) * i] = 0;
				}
			}	
		}
	} else {
		if ((xstart - xend) == 0) {
			for (int j = 1; ((ystart + (ystart - yend) * j) < this.COLUNM) && ((ystart + (ystart - yend) * j) >= 0); j++) {
				if (board[xstart][ystart + (ystart - yend) * j] == 0) {
					break;
				} else {
					board[xstart][ystart + (ystart - yend) * j] = 0;
				}
			}
		} else if ((ystart -yend) == 0) {
			for (int i = 1; ((xstart + (xstart - xend) * i) < this.ROW) && ((xstart + (xstart - xend) * i) >= 0) ;i++) {
				if (board[xstart + (xstart - xend) * i][ystart] == 0) {
					break;
				} else {
					board[xstart + (xstart - xend) * i][ystart] = 0;
				}
			}
		} else {
			for (int i = 1; ((xstart + (xstart - xend) * i) < this.ROW) && ((xstart + (xstart - xend) * i) >= 0) &&  ((ystart + (ystart - yend) * i) < this.COLUNM) && ((ystart + (ystart - yend) * i) >= 0); i++) {
				if (board[xstart + (xstart - xend) * i][ystart + (ystart - yend) * i] == 0) {
					break;
				} else {
					board[xstart + (xstart - xend) * i][ystart + (ystart - yend) * i] = 0;
				}
			}	
		}
	}
}
/**
 * update the player token
 * @param player No.
 * @return action coordinate list
 */
public ArrayList<ArrayList<Integer>> updatePlayerToken(int num) {
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	for (int i = 0; i < ROW; i++) {
		for (int j = 0; j < COLUNM; j++) {
			if (board[i][j] == num) {
				ArrayList<Integer> node = new ArrayList<Integer>();
				node.add(i);
				node.add(j);
				result.add(node);
			}
		}
	}
	return result;
}


public Object clone(){
	State copy=null;
	try{
		copy=(State)super.clone();
		
	}catch(CloneNotSupportedException e){
		e.printStackTrace();
	}
	copy.player_1=(StateInfo)this.player_1.clone();
	copy.player_2=(StateInfo)this.player_2.clone();
	copy.board= new int[ROW][COLUNM];
	for(int i=0; i<ROW;i++){
		for(int j =0; j< COLUNM;j++){
			copy.board[i][j] = this.board[i][j]; 
		}
	}
	
	copy.player_1MoveList=this.getPlayer_1MoveList();
	copy.player_2MoveList=this.getPlayer_2MoveList();
	return copy;
	
}

public static void main(String args[]) {
		State m = new State(5,5);
		m.printBoard();
		System.out.println("----------------");
		m.player_1.printArray();
		
		m.player_2.printArray();
		System.out.println("----------------");
		System.out.println(m.getPlayer_1MoveList());
		System.out.println(m.getPlayer_2MoveList());
		//Action act = new Action(1,1,1,1);
		//m.afterAction(act);
		System.out.println(m.getBoardValue(0, 0));
		System.out.println("----------------");
		ArrayList<ArrayList<Integer>> mm = m.getValidMoveNode(0,0,0,0);
		m.getActionList(1);
		System.out.println("----------------");
		m.getActionList(2);
		System.out.println(m.getActionList(1));
		System.out.println(m.getActionList(2));
		System.out.println("----------------");
		//m.stateAfterAction(new Action(2,1,0,1,1), m.board);
		m.printBoard();
		System.out.println("----------------");
	//	m.stateAfterAction(new Action(1,0,2,2,-1), m.board);
		m.printBoard();
		//System.out.println(m.getOneNodeActionList(1, new Action(3,2,1,2,1)));
		m.stateAfterAction(new Action(3,2,2,2,1), m);
		m.stateAfterAction(new Action(1,0,3,2,-1), m);
		//System.out.println(m.getPlayer_2MoveList(m.board));
		System.out.println(m.getCaptureMoveList(m.board, 3, 2, 2, 2));
		m.printBoard();
	}
}
