package objects.util;

public class Direction {

	private int xDir;
	private int yDir;
	
	public Direction(int x, int y) {
		this.xDir = x;
		this.yDir = y;
	}
	
	public int getXDir() {
		return this.xDir;
	}
	
	public int getYDir() {
		return this.yDir;
	}
	
	public void reduce(int value) {
		
		float factor;
		
		if(Math.abs(xDir)>=Math.abs(yDir))
			factor = Math.abs(xDir/value);
		else
			factor = Math.abs(yDir/value);
		
		xDir = Math.round(xDir/factor);
		yDir = Math.round(yDir/factor);
	}
}
