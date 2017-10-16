package objects.strategy.damager;

import java.util.List;

import objects.api.Damager;
import objects.api.GameObject;
import objects.util.Collisions;

// Rośnie aż osiągnie final radius zadając dmg wszystkiemu w zasięgu

public class GrowingAreaDamage implements Damager {

	private int damage;
	private int finalRadius;
	private int radius;
	private long prevTime;
	private int speed;
	
	public GrowingAreaDamage(int damage, int finalRadius, int speed) {
		this.damage = damage;
		this.finalRadius = finalRadius;
		this.radius = 1;
		this.speed = speed;
		this.prevTime = System.currentTimeMillis()-speed;
	}
	
	@Override
	public void hitIt(GameObject hitter, List<GameObject> listOfEverything) {
		if(prevTime+speed<=System.currentTimeMillis()) {
			prevTime = System.currentTimeMillis();
			
			for(GameObject object : listOfEverything) {
				if(Collisions.check(hitter, object, radius) && hitter.getFaction()!=object.getFaction()) {
					object.damageIt(listOfEverything, damage);
				}
			}
			if(radius>=finalRadius)
				hitter.destroy();
			else
				radius++;
		}
	}
}
