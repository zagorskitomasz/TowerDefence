package objects.api;

import java.util.List;

import com.badlogic.gdx.Input;

import map.MapGraph;
import objects.util.Direction;

public interface Movable {

	public Direction move(GameObject moved, Input input, MapGraph map, List<GameObject> listOfEverything);

}
