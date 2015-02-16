package utility;

import java.util.ArrayList;

/**
 * Action class record the action player or AI agent take
 * consists of two integer variables
 * @author Liang Gao
 *
 */
public class Action {
	private int xstart;
	private int xend;
	private int ystart;
	private int yend;
	private int capturekind; // 0 is no capture, 1 is capture forward, -1 is capture back.



	public Action(int a, int b, int c, int d, int e) {
		xstart = a;
		xend = b;
		ystart = c;
		yend = d;
		capturekind = e;
	}


	public void setCaptureKind(int m) {
		this.capturekind = m;
	}

	public int getCaptureKind() {
		return capturekind;
	}

	public void setStartNode(int x, int y) {
		this.xstart = x;
		this.ystart = y;
	}

	public void setEndNode(int x, int y) {
		this.xend = x;
		this.yend = y;
	}

	

	public int getXEnd() {
		return xend;
	}

	public int getYEnd() {
		return yend;
	}

	public int getXStart() {
		return xstart;
	}

	public int getYStart() {
		return ystart;
	}

	public String toString() {
		int x = getXStart();
		int y = getYStart();
		int x1 = getXEnd();
		int y1 = getYEnd();
		int e = getCaptureKind();
		String m = String.valueOf(x) + String.valueOf(y) +"," + String.valueOf(x1) + String.valueOf(y1) +"," + String.valueOf(e);
		return m;
	}


}
