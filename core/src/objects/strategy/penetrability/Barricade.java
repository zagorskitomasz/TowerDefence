package objects.strategy.penetrability;

import map.Edge;
import map.MapGraph;
import map.Point;
import objects.api.Penetrability;

public class Barricade implements Penetrability {

	private Edge road;
	private Edge roadBack;
	
	@Override
	public boolean canIWalkHere() {
		
		return false;
	}

	@Override
	public boolean canIBuildHere() {
		
		return false;
	}

	public boolean isBarricaded() {
		
		return true;
	}
	
	public void blockRoad(MapGraph map, Point one, Point two) {
		
		road = map.getRoad(one, two);
		roadBack = map.getRoad(two, one);
		
		if(road!=null)
			road.blockRoad();
		if(roadBack!=null)
			roadBack.blockRoad();
	}
	
	public void unBlockRoad() {
		
		if(this.road!=null)
			this.road.unBlockRoad();
		if(this.roadBack!=null)
			this.roadBack.unBlockRoad();
	}
}
