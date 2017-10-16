package objects.strategy.attacking;

import java.util.List;

import com.badlogic.gdx.Input;

import objects.api.Attacking;
import objects.api.GameObject;
import objects.util.Collisions;

// Bezpośredni atak na 1 wybrany cel

public class Melee implements Attacking {

	private int damage;
	private int speed;
	private long prevTime;
	
	public Melee(int damage, int speed) {
		this.damage = damage;
		this.speed = speed;
		this.prevTime = System.currentTimeMillis()-speed;
	}
	
	@Override
	public void attack(GameObject attacker, List<GameObject> listOfEverything, Input input) {
		
		if(prevTime+speed < System.currentTimeMillis()) {
			
			GameObject target = chooseTarget(attacker, listOfEverything);
			
			if(target!=null) {
				prevTime = System.currentTimeMillis();
				target.damageIt(listOfEverything, damage);
			}
		}
	}

	private GameObject chooseTarget(GameObject attacker, List<GameObject> listOfEverything) {
		for(GameObject object : listOfEverything) {
			if(attacker.getFaction()!=object.getFaction() && Collisions.check(attacker, object))
				return object;
		}
		return null;
	}
}
