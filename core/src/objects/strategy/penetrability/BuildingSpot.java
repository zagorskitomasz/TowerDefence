package objects.strategy.penetrability;

import objects.api.Penetrability;

public class BuildingSpot implements Penetrability {

	@Override
	public boolean canIWalkHere() {
		
		return true;
	}

	@Override
	public boolean canIBuildHere() {
		
		return true;
	}

}
