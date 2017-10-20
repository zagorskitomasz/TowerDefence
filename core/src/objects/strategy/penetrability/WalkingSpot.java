package objects.strategy.penetrability;

import map.MapGraph;
import map.Point;
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

	public boolean isBarricaded() {
		
		return false;
	}
	
	public void blockRoad(MapGraph map, Point one, Point two) {}
	
	public void unBlockRoad() {}
}
