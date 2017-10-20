package map;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Vertex {

	public static double distance(Vertex one, Vertex two) {
		
		return Point.distance(one.getLocation(), two.getLocation());
	}
	
	private Point location;
	private List<Edge> roads;
	private double distToStart;
	
	public Vertex(Point location) {
		
		this.location = location;
		this.roads = new LinkedList<>();
	}
	
	public Vertex(Point location, Edge...edges) {
		
		this(location);
		this.roads.addAll(Arrays.asList(edges));
		this.distToStart = 0;
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public int getNumRoads() {
		
		return this.roads.size();
	}
	
	public List<Edge> getRoads(){
		
		return roads; 
	}
	
	public Edge addRoad(Vertex destination) {
		
		Edge newRoad = new Edge(this, destination);
		
		roads.add(newRoad);
		destination.getRoads().add(newRoad);
		
		return newRoad;
	}
	
	public void resetDist(){
		distToStart = Double.POSITIVE_INFINITY;
	}
	
	public double getDistToStart(){
		return distToStart;
	}
	
	public void setDistToStart(double d){
		distToStart = d;
	}
	
	public Edge getRoad(Vertex to) {
		
		for(Edge road : roads) {
			
			if(road.getDestination(this)==to)
				return road;
		}
		
		return null;
	}
}
