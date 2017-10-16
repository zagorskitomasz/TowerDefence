package objects.strategy.attackable;

import objects.api.Attackable;
import objects.api.GameObject;

public class Vulnerable implements Attackable {

	private int hp;
	
	public Vulnerable(int hp) {
		this.hp = hp;
	}
	
	@Override
	public void takeDmg(GameObject attacked, int dmgValue) {
		
		hp -= dmgValue;
		if(hp<=0)
			attacked.destroy();
	}

}
