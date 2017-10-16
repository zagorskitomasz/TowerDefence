package objects.strategy.attackable;

import objects.api.Attackable;
import objects.api.GameObject;

// Ten obiekt zapewnia nieśmiertelność, dmg nic nie robi.

public class Immortal implements Attackable {

	@Override
	public void takeDmg(GameObject attacked, int dmgValue) {}

}
