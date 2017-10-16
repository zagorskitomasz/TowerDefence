package objects.strategy.penetrability;

import objects.api.Penetrability;

public class WalkingSpot implements Penetrability {

	@Override
	public boolean canIWalkHere() {
		
		return true;
	}

	@Override
	public boolean canIBuildHere() {
		
		return false;
	}

}
