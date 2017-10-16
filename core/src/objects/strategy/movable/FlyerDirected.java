package objects.strategy.movable;

import com.badlogic.gdx.Input;

import map.MapGraph;
import objects.api.GameObject;
import objects.api.Movable;
import objects.util.Direction;

// Obiekt leci w ustalonym kierunku (dla pocisków)

public class FlyerDirected implements Movable {

	private Direction direction;
	
	public FlyerDirected(int speed, Direction direction) {
		this.direction = direction;
		this.direction.reduce(speed);
	}
	
	@Override
	public Direction move(GameObject moved, Input input, MapGraph map) {
		 
		moved.setPosition(moved.getX()+direction.getXDir(), moved.getY()+direction.getYDir());
		
		return direction;
	}
}
