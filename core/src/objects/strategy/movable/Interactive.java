package objects.strategy.movable;

import java.util.List;

import com.badlogic.gdx.Input;

import map.MapGraph;
import objects.api.GameObject;
import objects.api.Movable;
import objects.util.Direction;

// Ten obiekt może być przesuwany po mapie przez gracza

public class Interactive implements Movable {

	@Override
	public Direction move(GameObject moved, Input input, MapGraph map, List<GameObject> listOfEverything) {
		
		moved.setPosition(input.getX(), input.getY());
		
		return new Direction(0,0);
	}

}
