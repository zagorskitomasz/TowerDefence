package objects.strategy.penetrability;

import objects.api.Penetrability;

public class Blockade implements Penetrability {

	@Override
	public boolean canIWalkHere() {
		
		return false;
	}

	@Override
	public boolean canIBuildHere() {
		
		return false;
	}

}
