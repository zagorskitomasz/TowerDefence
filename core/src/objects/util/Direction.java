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
	
	public Direction turnLeft() {
		
		if(this.xDir==0 && this.yDir>0)
			return new Direction(this.yDir*(-1), 0);
		else if(this.xDir>0 && this.yDir>0)
			return new Direction(this.yDir*(-1), this.xDir);
		else if(this.xDir>0 && this.yDir==0)
			return new Direction(0, this.xDir);
		else if(this.xDir>0 && this.yDir<0)
			return new Direction(this.yDir*(-1), this.xDir);
		else if(this.xDir==0 && this.yDir<0)
			return new Direction(this.yDir*(-1), 0);
		else if(this.xDir<0 && this.yDir<0)
			return new Direction(this.yDir*(-1), this.xDir);
		else if(this.xDir<0 && this.yDir==0)
			return new Direction(0, this.xDir);
		else if(this.xDir<0 && this.yDir>0)
			return new Direction(this.yDir*(-1), this.xDir);
		else
			return new Direction(0,0);
	}
	
	public Direction turnRight() {
		
		if(this.xDir==0 && this.yDir>0)
			return new Direction(this.yDir, 0);
		else if(this.xDir>0 && this.yDir>0)
			return new Direction(this.yDir, this.xDir*(-1));
		else if(this.xDir>0 && this.yDir==0)
			return new Direction(0, this.xDir*(-1));
		else if(this.xDir>0 && this.yDir<0)
			return new Direction(this.yDir, this.xDir*(-1));
		else if(this.xDir==0 && this.yDir<0)
			return new Direction(this.xDir, 0);
		else if(this.xDir<0 && this.yDir<0)
			return new Direction(this.yDir, this.xDir*(-1));
		else if(this.xDir<0 && this.yDir==0)
			return new Direction(0, this.xDir);
		else if(this.xDir<0 && this.yDir>0)
			return new Direction(this.yDir, this.xDir*(-1));
		else
			return new Direction(0,0);
	}
}
