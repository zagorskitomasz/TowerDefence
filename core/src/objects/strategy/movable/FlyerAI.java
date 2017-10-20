package objects.strategy.movable;

import java.util.List;

import com.badlogic.gdx.Input;

import map.MapGraph;
import map.Point;
import objects.api.GameObject;
import objects.api.Movable;
import objects.util.Collisions;
import objects.util.Direction;

//Obiekt latający, sam decyduje o kierunku, przelatuje nad przeszkodami, nie musi korzystać z dróg.

public class FlyerAI implements Movable {

	private int speed;
	private Direction direction;
	private Point destination;
	
	public FlyerAI(int speed, Point destination) {
		
		this.speed = speed;
		this.destination = destination;
	}
	
	@Override
	public Direction move(GameObject moved, Input input, MapGraph map, List<GameObject> listOfEverything) {
		
		direction = new Direction(destination.getLat()-moved.getX(), destination.getLon()-moved.getY());
		direction.reduce(speed);
		moved.setPosition(moved.getX()+direction.getXDir(), moved.getY()+direction.getYDir());
		
		if(Collisions.check(moved, destination))
			moved.destroy();
		
		return direction;
	}
	

}
