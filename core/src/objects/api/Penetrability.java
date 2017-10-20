package objects.api;

import map.MapGraph;
import map.Point;

public interface Penetrability {
	
	public boolean canIWalkHere();
	public boolean canIBuildHere();
	public boolean isBarricaded();
	public void blockRoad(MapGraph map, Point one, Point two);
	public void unBlockRoad();
}
