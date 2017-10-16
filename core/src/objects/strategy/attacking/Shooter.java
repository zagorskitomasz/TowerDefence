package objects.strategy.attacking;

import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;

import objects.api.Attacking;
import objects.api.GameObject;
import objects.util.Collisions;
import objects.util.Direction;
import objects.util.Factory;

// W odpowiednich odstępach czasu wysyła nowe pociski w wyznaczonym kierunku

public class Shooter implements Attacking {

	private int damage;
	private int range;
	private int speed;
	private String missileType;
	private long prevTime;
	
	public Shooter(int damage, int range, int speed, String missileType) {
		this.damage = damage;
		this.range = range;
		this.speed = speed;
		this.prevTime = TimeUtils.millis()-speed;
		this.missileType = missileType;
	}
	
	@Override
	public void attack(GameObject attacker, List<GameObject> listOfEverything, Input input) {
		
		if(prevTime+speed < TimeUtils.millis()) {
			GameObject target = chooseTarget(attacker, listOfEverything);
			
			if(target!=null) {
				prevTime = TimeUtils.millis();
				Direction missileDir = setDirection(attacker, target);
				
				Factory.createMissile(missileType, attacker.getFaction(), damage,
						attacker.getPosition(), missileDir);
			}
		}	
	}

	private GameObject chooseTarget(GameObject attacker, List<GameObject> listOfEverything) {
		for(GameObject object : listOfEverything) {
			if(object.isVulnerable() && 
					attacker.getFaction()!=object.getFaction() && 
					Collisions.check(attacker, object, range))
				return object;
		}
		return null;
	}
	
	private Direction setDirection(GameObject attacker, GameObject target) {
		
		int xInterval = target.getX() - attacker.getX();
		int yInterval = target.getY() - attacker.getY();
		
		Direction result = new Direction(xInterval, yInterval);
		
		return result;
	}
}
