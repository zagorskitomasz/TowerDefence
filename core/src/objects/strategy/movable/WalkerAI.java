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
	
	public WalkerAI(int speed, Point destination) {
		
		this.speed = speed;
		this.destination = destination;
		this.currentDest = null;
	}
	
	@Override
	public Direction move(GameObject moved, Input input, MapGraph map) {
		
		if(this.currentDest==null) {
			currentDest = findFirstDest(moved, map);
		}
		
		if(Collisions.check(moved, destination)) {
			moved.destroy();
			return new Direction(0, 0);
		}
		
		if(Collisions.check(moved, currentDest)) {
			currentDest = findNextDest(currentDest, map);
		}
		
		return finallyMove(moved);
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
	
	private Direction finallyMove(GameObject moved) {
		
		Direction direction = new Direction(currentDest.getLat()-moved.getX(), currentDest.getLon()-moved.getY());
		direction.reduce(speed);
		moved.setPosition(moved.getX()+direction.getXDir(), moved.getY()+direction.getYDir());
		
		return direction;
	}
}
