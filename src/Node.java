
public class Node {
	private int x, y; //x and y coordinates of the pixel
	private int nodeID; //could be a pixel id in an image
	private boolean visited;
	private double distance;
	private Node previous;
	private int grayValue;
	private CustomList<Edge> edges; //Connections(edges) to other fire pixels
	
	//Constructor to set the pixel's coordinates 
	public Node(int nodeID, int x, int y, int grayValue) {
		this.nodeID = nodeID;
		this.x = x;
		this.y = y;
		this.grayValue = grayValue;
		this.distance = Double.MAX_VALUE;
		this.previous = null;
		this.edges = new CustomList<>();
	}
	
	//Getters and setters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getGrayValue() {
		return grayValue;
	}
	
	public void setID(int nodeID) {
		this.nodeID = nodeID;
	}
	
	public int getID() {
		return nodeID;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public CustomList<Edge> getEdges(){
		return edges;
	}
	
	public void setPrevious(Node previous) {
		this.previous = previous;
	}
	
	public Node getPrevious() {
		return previous;
	}
	
	
	//Add a connection (edge) to another node
	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}
	
	//Checks if two nodes are the same based on their coordinates
	public boolean equals(Object object) {
		if(this == object) {
			return true;
		}
		if(!(object instanceof Node)) return false;
		Node temp = (Node) object;
		return this.nodeID == temp.nodeID;
	}
	
	//Hash code for comparing nodes
	public int hashCode() {
		return nodeID;
	}
	
	//For printing and debugging
	public String toString() {
		return "Node[" + nodeID + "] (" + x + "," + y + ")";
	}
	
}
