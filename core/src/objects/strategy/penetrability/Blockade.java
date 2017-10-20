package objects.strategy.penetrability;

import map.MapGraph;
import map.Point;
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
	
	public boolean isBarricaded() {
		
		return false;
	}
	
	public void blockRoad(MapGraph map, Point one, Point two) {}
	
	public void unBlockRoad() {}
}
