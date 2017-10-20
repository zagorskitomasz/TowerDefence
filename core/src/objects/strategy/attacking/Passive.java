package objects.strategy.attacking;

import java.util.List;

import com.badlogic.gdx.Input;

import objects.api.Attacking;
import objects.api.GameObject;

// Nikogo nie będzie atakował.

public class Passive implements Attacking {

	@Override
	public void attack(GameObject attacker, List<GameObject> listOfEverything, Input input) {}
	
	public void releaseMap() {}
}
