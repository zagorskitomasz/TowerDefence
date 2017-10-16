package objects.api;

import java.util.List;

import com.badlogic.gdx.Input;

public interface Attacking {

	public void attack(GameObject attacker, List<GameObject> listOfEverything, Input input);
}
