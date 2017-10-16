package objects.strategy.destroyable;

import objects.api.Destroyable;
import objects.api.GameObject;
import objects.util.Factory;

// Wybucha i znika.

public class Explosive implements Destroyable {

	private int damage;
	private String explosionType;
	
	public Explosive(int damage, String explosionType) {
		this.damage = damage;
		this.explosionType = explosionType;
	}
	@Override
	public boolean killYourself(GameObject toDestroy) {
		
		Factory.createExplosion(explosionType, damage, toDestroy.getX(), toDestroy.getY());
		
		return true;
	}
}
