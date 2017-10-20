package map;

public class Edge {

	private Vertex firstEnd;
	private Vertex secondEnd;
	private double length;
	private boolean blocked;
	
	public Edge(Vertex firstEnd, Vertex secondEnd) {
		
		this.firstEnd = firstEnd;
		this.secondEnd = secondEnd;
		
		this.length = Vertex.distance(firstEnd, secondEnd);
		this.blocked = false;
	}
	
	public double getLength() {
		
		if(!this.blocked)
			return this.length;
		else
			return this.length*10;
	}
	
	public Vertex getDestination(Vertex from) throws IllegalArgumentException{
		
		if(from.equals(this.firstEnd))
			return this.secondEnd;
		else if(from.equals(this.secondEnd))
			return this.firstEnd;
		
		else
			throw new IllegalArgumentException("No such vertex connected with this edge.");
	}
	
	public void blockRoad() {
		
		this.blocked = true;
	}
	
	public void unBlockRoad() {
		
		this.blocked = false;
	}
	
	public void lengthIt(int damage, double factor) {
		
		this.length += damage*10*factor;
	}
	
	public void shortIt(int damage, double factor) {
		
		this.length -= damage*10*factor;
	}
	
	public Point getFrom() {
		
		return this.firstEnd.getLocation();
	}
	
	public Point getTo() {
		
		return this.secondEnd.getLocation();
	}
}
