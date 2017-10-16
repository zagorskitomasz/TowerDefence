package objects.strategy.damager;

import java.util.List;

import objects.api.Damager;
import objects.api.GameObject;

// Obiekt nikomu nie robi krzywdy.

public class Peacefull implements Damager {

	@Override
	public void hitIt(GameObject hitter, List<GameObject> listOfEverything) {}

}
