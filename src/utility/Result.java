package utility;
/**
 * Result class used to create a result 
 * recording the statue of game after an action
 * and the action make the game to the statue
 * 
 * @author Liang Gao
 *
 */
public class Result {
	
	public Action act;		//action that make the game to the statue
	public int result;		//statue of game after an action
	
	/**
	 * construct the Result
	 * @param a action in the result
	 * @param r integer referring the type of the result
	 */
	public Result(Action a,int r){
		act=a;
		result=r;
	}
}
