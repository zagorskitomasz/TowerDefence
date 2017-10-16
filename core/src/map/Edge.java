package map;

public class Edge {

	private Vertex firstEnd;
	private Vertex secondEnd;
	private double length;
	
	public Edge(Vertex firstEnd, Vertex secondEnd) {
		
		this.firstEnd = firstEnd;
		this.secondEnd = secondEnd;
		
		this.length = Vertex.distance(firstEnd, secondEnd);
	}
	
	public double getLength() {
		
		return this.length;
	}
	
	public Vertex getDestination(Vertex from) throws IllegalArgumentException{
		
		if(from.equals(this.firstEnd))
			return this.secondEnd;
		else if(from.equals(this.secondEnd))
			return this.firstEnd;
		
		else
			throw new IllegalArgumentException("No such vertex connected with this edge.");
	}
}
