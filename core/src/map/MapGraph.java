package map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MapGraph {

	private HashMap<Point, Vertex> graph;
	
	public MapGraph() {
		
		graph = new HashMap<>();
	}
	
	public Set<Point> getPoints(){
		
		return graph.keySet();
	}
	
	public boolean addVertex(Point location) {
		
		if(location==null || graph.containsKey(location))
			return false;
		
		graph.put(location, new Vertex(location));
		return true;
	}
	
	public void addVertex(Point...locations) {
		
		for(Point point : locations)
			addVertex(point);
		
	}
	
	public void addRoad(Point firstEnd, Point secondEnd) throws IllegalArgumentException {

		if(!graph.containsKey(firstEnd) || !graph.containsKey(secondEnd))
			throw new IllegalArgumentException("No such points in graph");
		
		graph.get(firstEnd).addRoad(graph.get(secondEnd));
	}
	
	public List<Point> findRoute(Point start, Point goal){
		
		if(!graph.containsKey(start) || !graph.containsKey(goal))
			throw new IllegalArgumentException("No such points in graph");
		
		graph.values().forEach(vertex -> vertex.resetDist());
		
		Vertex from = graph.get(start);
		Vertex to = graph.get(goal);

		HashMap<Vertex, Vertex> parentMap = new HashMap<>();
		
		List<Point> result = aStarEngine(from, to, parentMap);
		
		if(result==null)
			throw new IllegalArgumentException("No connection between points");
			
		return result;
	}
	
	private List<Point> aStarEngine(Vertex from, Vertex to, HashMap<Vertex, Vertex> parentMap) {
		
		List<Point> result = null;
		Set<Vertex> visited = new HashSet<>();
		
		Queue<Vertex> toVisit = new PriorityQueue<Vertex>((a, b) -> 
		Double.compare(a.getDistToStart()+Vertex.distance(a, to), 
				b.getDistToStart()+Vertex.distance(b, to)));
		
		toVisit.add(from);
		visited.add(from);
		from.setDistToStart(0);
		
		while(!toVisit.isEmpty()){
			Vertex current = toVisit.remove();
			
			if (current==to) {
				result = constructPointsPath(from.getLocation(), to.getLocation(), parentMap);
				return result;
			}
			
			visited.add(current);

			List<Edge> currVertexRoads = current.getRoads();
			
			for(Edge road : currVertexRoads){
				Vertex next = graph.get(road.getDestination(current).getLocation());
				
				if (!visited.contains(next)) {
					if(current.getDistToStart()+road.getLength()<next.getDistToStart()){
						
						next.setDistToStart(current.getDistToStart()+road.getLength());
						
						if(parentMap.containsKey(next))
							parentMap.replace(next, current);
						else
							parentMap.put(next,  current);
						
						toVisit.add(next);
					}
				}
			}
		}
		
		return result;
	}
	
	private List<Point> constructPointsPath(Point start, Point goal, HashMap<Vertex, Vertex> parentMap){
		
		LinkedList<Point> path = new LinkedList<>();
		Point current = goal;
		
		while (!current.equals(start)) {
			path.addFirst(current);
			current = parentMap.get(graph.get(current)).getLocation();
		}
		
		path.addFirst(start);
		return path;
	}
	
	public void draw(ShapeRenderer renderer) {
		
		renderer.setColor(Color.BROWN);
		renderer.begin(ShapeType.Filled);
		
		for(Vertex vertex : graph.values()) {
			for(Edge road : vertex.getRoads()) {
				
				renderer.rectLine(vertex.getLocation().getLat(), 
						vertex.getLocation().getLon(), 
						road.getDestination(vertex).getLocation().getLat(), 
						road.getDestination(vertex).getLocation().getLon(), 15);
			}
		}
		
		renderer.end();
	}
}
