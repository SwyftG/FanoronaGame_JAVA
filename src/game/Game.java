package game;

import java.util.ArrayList;

import state.State;
import utility.Action;
import utility.GoalTest;
import utility.Result;
/**
 * 
 * Class processing the main logic of AI agent 
 * @author Liang Gao
 * 
 */
public class Game {
	
	public static int POSINFINITY=65535;    //positive infinity
	public static int NEGINFINITY=-65535;	//negative infinity

	public State game_state;		//state maintain the state of game
	private int level;
	public int row;
	public int col;
	public static int times =1;
	public static int flag = 0;
	public int printDepth;
	public long start;
	public long end;
	public int cutOff;
	/**
	 * construct function of Game class
	 * initialize the parameters: level of AI and who go first
	 * @param level the level of AI
	 * @param first determine who go first
	 * @param row the row of game board
	 * @param col the col of game board
	 */
	public Game(int level, int first, int row, int col){              //
		//create a new state when game is construct
		this.row = row;
		this.col = col;
		game_state=new State(row, col);
		this.level=level;
	
		//when the player choose that computer go first, make the first step of the AI agent
		if(first==2){
			//call makeDecision method to make a decision for AI agent
			Action act=makeDecision(1);
			//update the game state for the action AI agent choose
			game_state=getResult(game_state,act);
			while(act.getCaptureKind() != 0) {
				
				act = makeCaptureDecision(1, act);
				if (act.getCaptureKind() == 500) {
					break;
				}
				System.out.println("CaoNiMa");
				game_state = getResult(game_state,act);
			}
		}		
	}
	/**
	 * method called by the upper level 
	 * given an action player choose
	 * give a result including the statue of game and a decision AI agent make
	 * 
	 * @param act the action that player take
	 */
	public Result play(Action act){
		int xstart = act.getXStart();
		int ystart = act.getYStart();
		int playerNo = game_state.getBoardValue(xstart, ystart);
		int enemyNo = 0;
		if (playerNo == 1) {
			enemyNo = 2;
		} 
		if (playerNo == 2){
			enemyNo = 1;
		}
		//test if player take the action, the game come to an end or not
		int flag=GoalTest.isGoal(game_state, act);/////////
		//player win
		//create a result and return with the action
		if(flag==1)
			return new Result(act,1);
		//if game does not come to an end after the player take this action
		//update the state of game 
		game_state=getResult(game_state,act);

	
		//AI agent make a desion to choose which grid should be filled
		act=makeDecision(enemyNo);

		//test if the AI agent take action, the game come to an end or not
		flag=GoalTest.isGoal(game_state, act);
		//AI agent win
		//create a result and return with the action
		if(flag==1)
			return new Result(act,-1);
		//if game does not come to an end after the AI agent take this action
		game_state=getResult(game_state,act);
	
		//game does not come to an end
		//create a result with draw and return with the action
		return new Result(act,9);
			
	}

	/**
	 * Player make a capture move
	 * return an result of after action
	 *
	 * @param act is the action
	 */
	public Result playCapturePlayer(Action act) {
		int xstart = act.getXStart();
		int ystart = act.getYStart();
		int playerNo = game_state.getBoardValue(xstart, ystart);
		int enemyNo = 0;
		if (playerNo == 1) {
			enemyNo = 2;
		} 
		if (playerNo == 2){
			enemyNo = 1;
		}
		int flag=GoalTest.isGoal(game_state, act);
		if (flag == 1) {
			return new Result(act,1);
		}
		game_state = getResult(game_state,act);
		return new Result(act, 9);
	}

	/**
	 * AI make a capture move
	 * return an result of chaging after an action 
	 *
	 * @param act the action of AI made
	 */
	public Result playCaptureAI(Action act) {
		int xstart = act.getXStart();
		int ystart = act.getYStart();
		int playerNo = game_state.getBoardValue(xstart, ystart);
		int enemyNo = 0;
		if (playerNo == 1) {
			enemyNo = 2;
		} 
		if (playerNo == 2){
			enemyNo = 1;
		}
		act=makeDecision(enemyNo);

		//test if the AI agent take action, the game come to an end or not
		flag=GoalTest.isGoal(game_state, act);
		//AI agent win
		//create a result and return with the action
		if(flag==1)
			return new Result(act,-1);
		//if game does not come to an end after the AI agent take this action
		game_state=getResult(game_state,act);
	
		//game does not come to an end
		//create a result with draw and return with the action
		return new Result(act,9);
	}

	/**
	 * function that return a state after the given action
	 * 
	 * @param pre the state before the action take
	 * @param act action taken by the player or the AI agent
	 * @param playerNo a flag that mark if the player is human or AI agent
	 * @return a state transfered by the previous state after taking the action
	 */
	public static State getResult(State pre, Action act){
		State result=(State)pre.clone();
		result.stateAfterAction(act, result);
		return result;
		
	}
	
	/**
	 * main function processing the alpha beta search
	 * return an action that AI agent choose
	 *
	 * @return return an action that the AI agent determines by alpha-beta search
	 */
	public Action makeDecision(int num){
		start=System.currentTimeMillis();
		//System.out.println("MakeADection");
		//game_state.printBoard();
		int depth=0;	//initialize the depth to 0 when the function begins, using to record the depth reached
		Action result=null;
		int v=NEGINFINITY;	//initialize the v to negative infinity
		int playerNo = num;
		int enemyNo = 0;
		if (playerNo == 1) {
			enemyNo = 2;
		} else {
			enemyNo = 1;
		}
		printDepth = 0;
		cutOff = 0;
		
		
		//get the action list that available
		ArrayList<Action> list=game_state.getActionList(playerNo);
	//	System.out.println(list);
	//	System.out.println(list);
		//for each action, search the best action using alpha beta search
		for(int i=0;i<list.size();i++)
		{   
			int value=minValue(game_state,list.get(i),NEGINFINITY,POSINFINITY,depth+1, enemyNo);
		//	System.out.println(value);
			if(value>v)
			{
				result=list.get(i);
				v=value;
			}
			
			
		}
		//return the action AI agent chooses
		end=System.currentTimeMillis();
		System.out.println("FINAL result:" + result + " time:"+(end-start) + " Depth:" + printDepth + " CutOff? " + cutOff);
		
		return result;
	}
	
	/**
	 * It is continue capture move
	 * return an action
	 * 
	 * @param num the player No.
	 * @param act the player action
	 */
	public Action makeCaptureDecision(int num, Action act){

		long start=System.currentTimeMillis();
		int depth=0;	//initialize the depth to 0 when the function begins, using to record the depth reached
		Action result=null;
		int v=NEGINFINITY;	//initialize the v to negative infinity
		int playerNo = num;
		int enemyNo = 0;
		if (playerNo == 1) {
			enemyNo = 2;
		} else {
			enemyNo = 1;
		}
		
		//get the action list that available
		ArrayList<Action> list=game_state.getOneNodeActionList(playerNo, act);
		if (list.size() == 0) {
			return new Action(500,500,500,500,500);
		}
	//	System.out.println(list);
	//	System.out.println(list);
		//for each action, search the best action using alpha beta search
		for(int i=0;i<list.size();i++)
		{   
			int value=minValue(game_state,list.get(i),NEGINFINITY,POSINFINITY,depth+1, enemyNo);
//			System.out.println(value);
			if(value>v)
			{
				result=list.get(i);
				v=value;
			}
			
			
		}
		//return the action AI agent chooses
		long end=System.currentTimeMillis();
		System.out.println("Capture FINAL result: " + result + " time:"+(end-start));
		return result;
	}
	
	
	/**
	 * minValue function determine the best action taken by opposite
	 * @param state the state before taking the action to reach this level
	 * @param act the action taken to reach this level
	 * @param alpha	the value of alpha in the search algorithm
	 * @param beta the value of beta in the search algorithm
	 * @param depth	used to record which depth reached 
	 * @return a value of v in the search algorithm
	 */
	public int minValue(State state, Action act, int alpha,int beta,int depth, int num){
		
		int playerNo = num;
		int enemyNo = 0;
		if (playerNo == 1) {
			enemyNo = 2;
		} else {
			enemyNo = 1;
		}
		printDepth = Math.max(printDepth, depth);
		int utility=GoalTest.isGoal(state, act);
		if(utility==1)
			return 153;
		int v=POSINFINITY;
		State r=getResult(state,act);
		if((depth==level) ||((System.currentTimeMillis() - start) == 9999)){
			cutOff = 1;
			return GoalTest.cutOff(r, enemyNo);
			
		}
		ArrayList<Action> list=r.getActionList(playerNo);
		for(int i=0;i<list.size();i++){
			v=Math.min(v, maxValue(r,list.get(i),alpha,beta,depth+1, enemyNo));
			if(v<=alpha)
				return v;
			beta=Math.min(beta, v);		
		}
		return v;
	}
	
	/**
	 * maxValue function determine the best action taken by AI agent
	 * 
	 * @param state the state before taking the action to reach this level
	 * @param act the action taken to reach this level
	 * @param alpha the value of alpha in the search algorithm
	 * @param beta the value of beta in the search algorithm
	 * @param depth used to record which depth reached
	 * @return a value of v in the search algorithm
	 */
	public int maxValue(State state, Action act, int alpha,int beta,int depth, int num){
		int playerNo = num;
		int enemyNo = 0;
		if (playerNo == 1) {
			enemyNo = 2;
		} else {
			enemyNo = 1;
		}		
		int utility=GoalTest.isGoal(state, act);
		if(utility==1)
			return -153;
		int v=NEGINFINITY;
		State r=getResult(state,act);
		printDepth = Math.max(printDepth, depth);
		if((depth==level)||((System.currentTimeMillis() - start) == 9999)){
			cutOff = 1;
			return GoalTest.cutOff(r, enemyNo);
		}		
		ArrayList<Action> list=r.getActionList(playerNo);
		
		for(int i=0;i<list.size();i++){
			v=Math.max(v, minValue(r,list.get(i),alpha,beta,depth+1, enemyNo));
			if(v>=beta)
				return v;
			alpha=Math.max(alpha, v);
			
		}
		return v;
	}
	

	public static void main(String[] args){
		long start=System.currentTimeMillis();
	//	System.out.println(start);
		Game g=new Game(29,2,5,5);
		g.game_state.printBoard();
		System.out.println(g.game_state.getPlayer_2MoveList(g.game_state.board));
		
		long end=System.currentTimeMillis();
		
	/*	for(int i=1;i<3;i++)
		{
			for(int j=1;j<3;j++)
				System.out.print(g.game_state.board[i][j]+" ");
			System.out.println();
		}*/
		System.out.println("time:   "+(end-start));
		/*System.out.println(g.play(new Action(1,1)));
		for(int i=1;i<5;i++)
		{
			for(int j=1;j<6;j++)
				System.out.print(g.game_state.board[i][j]+" ");
			System.out.println();
		}*/

	}
	
}
