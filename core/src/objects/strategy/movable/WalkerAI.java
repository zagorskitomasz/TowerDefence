package objects.strategy.movable;

import java.util.List;
import java.util.Set;

import com.badlogic.gdx.Input;

import map.MapGraph;
import map.Point;
import objects.api.GameObject;
import objects.api.Movable;
import objects.util.Collisions;
import objects.util.Direction;

//Obiekt decyduje gdzie chce iść, korzysta z dróg, musi omijać przeszkody.

public class WalkerAI implements Movable {

	private int speed;
	private Point destination;
	private Point currentDest;
	private Point previousDest;
	
	public WalkerAI(int speed, Point destination) {
		
		this.speed = speed;
		this.destination = destination;
		this.currentDest = null;
		this.previousDest = null;
	}
	
	@Override
	public Direction move(GameObject moved, Input input, MapGraph map, List<GameObject> listOfEverything) {
		
		if(this.currentDest==null) {
			currentDest = findFirstDest(moved, map);
			previousDest = currentDest;
		}
		
		if(Collisions.check(moved, destination)) {
			moved.destroy();
			return new Direction(0, 0);
		}
		
		if(Collisions.check(moved, currentDest, 10)) {
			previousDest = currentDest;
			currentDest = findNextDest(currentDest, map);
		}
		
		return finallyMove(moved, listOfEverything, map);
	}

	private Point findFirstDest(GameObject moved, MapGraph map) {
		
		Set<Point> allPoints = map.getPoints();
		
		Point nearestPoint = null;
		double distToNearest = Double.POSITIVE_INFINITY;
		
		for(Point current : allPoints) {
			double distToCurrent = Point.distance(moved.getPosition(), current);
			
			if(distToCurrent < distToNearest) {
				
				distToNearest = distToCurrent;
				nearestPoint = current;
			}
		}
		
		return nearestPoint;
	}
	
	private Point findNextDest(Point start, MapGraph map) {
		
		List<Point> path = map.findRoute(start, destination);
		
		if(path.size()==1)
			return destination;
		
		else
			return path.get(1);
	}
	
	private Direction finallyMove(GameObject moved, List<GameObject> listOfEverything, MapGraph map) {
		
		Direction direction = new Direction(currentDest.getLat()-moved.getX(), currentDest.getLon()-moved.getY());
		direction.reduce(speed);
		
		GameObject barricade = isThereBarricade(new Point(moved.getX()+direction.getXDir(), moved.getY()+direction.getYDir()), moved, listOfEverything);
		if(barricade!=null) {
			blockCurrentRoad(barricade, map);
			this.currentDest = this.previousDest;
			return direction;
		}
		
		if(canIMoveThere(new Point(moved.getX()+direction.getXDir(), moved.getY()+direction.getYDir()), moved, listOfEverything)) {
			moved.setPosition(moved.getX()+direction.getXDir(), moved.getY()+direction.getYDir());
			return direction;
		}
		else if(canIMoveThere(new Point(moved.getX()+direction.turnLeft().getXDir(), moved.getY()+direction.turnLeft().getYDir()), moved, listOfEverything)) {
			moved.setPosition(moved.getX()+direction.turnLeft().getXDir(), moved.getY()+direction.turnLeft().getYDir());
			return direction.turnLeft();
		}
		else if(canIMoveThere(new Point(moved.getX()+direction.turnRight().getXDir(), moved.getY()+direction.turnRight().getYDir()), moved, listOfEverything)) {
			moved.setPosition(moved.getX()+direction.turnRight().getXDir(), moved.getY()+direction.turnRight().getYDir());
			return direction.turnRight();
		}
		
		return direction;
	}
	
	private boolean canIMoveThere(Point point, GameObject mover, List<GameObject> listOfEverything) {
		
		for(GameObject object : listOfEverything) {
			if(object!=mover && Collisions.check(object, point) && !object.canBeWalkedThrough()) {
				return false;
			}
		}
		
		return true;
	}
	
	private GameObject isThereBarricade(Point point, GameObject mover, List<GameObject> listOfEverything) {
		
		for(GameObject object : listOfEverything) {
			if(object!=mover && Collisions.check(object, point) && object.isBarricade()) {
				return object;
			}
		}
		
		return null;
	}
	
	private void blockCurrentRoad(GameObject barricade, MapGraph map) {
		
		barricade.blockRoad(map, previousDest, currentDest);
	}
}
