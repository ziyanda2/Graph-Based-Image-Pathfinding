import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
	private CustomList<Node> nodes;
	private Node[][] nodeMatrix;
	
	public Graph(NodeMatrix nodeMatrix) {
		this.nodeMatrix = nodeMatrix.getMatrix();
		nodes = new CustomList<>();
		
		for(int i = 0; i < nodeMatrix.getRow(); i++) {
			for(int x = 0; x < nodeMatrix.getCol(); x++) {
				Node node = nodeMatrix.getNode(i, x);
				if(node != null) {
					this.addNode(node);
				}
			}
		}
	}
	
	public Node[][] getNodeMatrix(){
		return nodeMatrix;
	}
	public void setNodeMatrix(Node[][] nodeMatrix) {
		this.nodeMatrix = nodeMatrix;
	}
	
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	public void addEdge(Node from, Node to, double distance) {
		Edge edge = new Edge(from,  to,  distance);
		from.addEdge(edge);
	}
	
	public CustomList<Node> getNodes(){
		return nodes;
	}
	
	public int getNodeCount() {
		return nodes.size();
	}
	
	public Node getNodeById(int nodeID) {
		for(int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			if(node.getID() == nodeID) return node;
		}
		return null;
	}
	
	public void resetNodes() {
		for(int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			node.setVisited(false);
			node.setDistance(Double.MAX_VALUE);
			node.setPrevious(null);
		}
	}
	
	public List<Node> dijkstra(Node start, Node target){
		resetNodes();
		Map<Node,Double> distances = new HashMap<>();
		Map<Node,Node> previous = new HashMap<>();
		PriorityQueue<NodeDistance> queue = new PriorityQueue<>();
		
		for(int i = 0; i < nodes.size(); i++) {
			Node node = nodes.get(i);
			distances.put(node, Double.POSITIVE_INFINITY);
		}
		
		distances.put(start, 0.0);
		queue.add(new NodeDistance(start, 0.0));
		
		while(!queue.isEmpty()) {
			NodeDistance current = queue.poll();
			Node currentNode = current.node;
			
			if(currentNode.equals(target)) {
				break;
			}
			
			for(Edge edge : currentNode.getEdges()) {
				Node neighborNode = edge.getTo();
				double newDistance = distances.get(currentNode) + edge.getWeight();
				
				if(newDistance < distances.get(neighborNode)) {
					distances.put(neighborNode, newDistance);
					previous.put(neighborNode, currentNode);
					queue.add(new NodeDistance(neighborNode, newDistance));
				}
			}
		}
		
		List<Node> path = new ArrayList<>();
		Node step = target;
		while(previous.containsKey(step)) {
			path.add(0, step);
			step = previous.get(step);
		}
		
		if(!path.isEmpty() && step.equals(start)) {
			path.add(0, start);
		}
		return path;
		
	}
	
	private static class NodeDistance implements Comparable<NodeDistance>{
		Node node;
		double distance;
		
		NodeDistance(Node node, double distance){
			this.node = node;
			this.distance = distance;
		}
		
		public Node getNode() {
			return node;
		}
		
		public double getDistance() {
			return distance;
		}
		
		@Override
		public int compareTo(NodeDistance ot) {
			return Double.compare(this.distance, ot.distance);
		}
	}
	

}
