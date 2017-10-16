package objects.strategy.damager;

import java.util.List;

import objects.api.Damager;
import objects.api.GameObject;
import objects.util.Collisions;

// Zadaje dmg pierwszemu napotkanemu celowi i sam się niszczy

public class SingleTarget implements Damager {

	private int damage;
	
	public SingleTarget(int damage) {
		this.damage = damage;
	}
	
	@Override
	public void hitIt(GameObject hitter, List<GameObject> listOfEverything) {
		for(GameObject object : listOfEverything) {
			
			if(object.isVulnerable() && hitter.getFaction()!=object.getFaction() && Collisions.check(hitter, object)) {
				object.damageIt(listOfEverything, damage);
				hitter.destroy();
				return;
			}
		}
	}
}
