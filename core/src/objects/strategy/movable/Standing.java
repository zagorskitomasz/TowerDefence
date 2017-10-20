package objects.strategy.movable;

import java.util.List;

import com.badlogic.gdx.Input;

import map.MapGraph;
import objects.api.GameObject;
import objects.api.Movable;
import objects.util.Direction;

// Obiekt nieruchomy, więc move() nic nie robi

public class Standing implements Movable {

	@Override
	public Direction move(GameObject moved, Input input, MapGraph map, List<GameObject> listOfEverything) {

		return new Direction(0,0);
	}
}
