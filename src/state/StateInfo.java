package state;

import java.util.ArrayList;

public class StateInfo implements Cloneable{


	public int enemyRemain;
	public ArrayList<ArrayList<Integer>> playerToken = new ArrayList<ArrayList<Integer>>();
	
	
	public StateInfo(int ROW, int COLUNM, int num){
		
		if (num == 2) {     // 2 is black
			for (int i = 0; i < ROW / 2; i++) {
				for (int j = 0; j < COLUNM; j++) {
					ArrayList<Integer> NodeInfor = new ArrayList<Integer>();
					NodeInfor.add(i);
					NodeInfor.add(j);
					playerToken.add(NodeInfor);
				}
			}
			boolean black = true;
			for (int i = COLUNM / 2; i < COLUNM; i++) {
				if (i == COLUNM / 2) {
					continue;
			} 
				if (black) {
					ArrayList<Integer> NodeInfor = new ArrayList<Integer>();
					NodeInfor.add(ROW / 2);
					NodeInfor.add(i);
					playerToken.add(NodeInfor);
					black = false;
				} else {
					black = true;
				}
			}
			black = false;
			for (int i = COLUNM / 2 - 1; i >= 0; i--) {
				if (black) {
					ArrayList<Integer> NodeInfor = new ArrayList<Integer>();
					NodeInfor.add(ROW / 2);
					NodeInfor.add(i);
					playerToken.add(NodeInfor);
					black = false;
				} else {
					black = true;
				}
			}

		} else {
			for (int i = ROW / 2 + 1; i < ROW; i++) {
				for (int j = 0; j < COLUNM; j++) {
					ArrayList<Integer> NodeInfor = new ArrayList<Integer>();
					NodeInfor.add(i);
					NodeInfor.add(j);
					playerToken.add(NodeInfor);
				}
			}
			boolean black = true;
			for (int i = COLUNM / 2; i < COLUNM; i++) {
				if (i == COLUNM / 2) {
					continue;
			} 
				if (black) {
					black = false;
				} else {
					ArrayList<Integer> NodeInfor = new ArrayList<Integer>();
					NodeInfor.add(ROW / 2);
					NodeInfor.add(i);
					playerToken.add(NodeInfor);
					black = true;
				}
			}
			black = false;
			for (int i = COLUNM / 2 - 1; i >= 0; i--) {
				if (black) {
					black = false;
				} else {
					ArrayList<Integer> NodeInfor = new ArrayList<Integer>();
					NodeInfor.add(ROW / 2);
					NodeInfor.add(i);
					playerToken.add(NodeInfor);
					black = true;
				}
			}
		}

		enemyRemain = playerToken.size();
	}

	public int getEnemyRemain(){
		return enemyRemain;
	}

	public void setEnemyRemain(int num) {
		this.enemyRemain = num;
	}

	public int getPlayerTokenNum(){
		return playerToken.size();
	}

	public ArrayList<ArrayList<Integer>> getPlayerToken() {
		return playerToken;
	}

	public void printArray() {
		System.out.print(playerToken);
		System.out.println(enemyRemain);
		System.out.println();
	}

	public Object clone(){
		StateInfo copy=null;
		try{
			copy=(StateInfo)super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		
		copy.enemyRemain = this.getEnemyRemain();
		copy.playerToken = this.getPlayerToken();
		return copy;
	}
}
