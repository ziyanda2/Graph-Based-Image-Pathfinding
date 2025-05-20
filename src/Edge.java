
public class Edge {
		private Node from; //Starting node of the edge
		private Node to; 
		private double weight;
		
		public Edge(Node from, Node to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		public Node getFrom() {
			return from;
		}
		
		public Node getTo() {
			return to;
		}
		
		public void setFrom(Node from) {
			this.from = from;
		}
		
		public void setTo(Node to) {
			this.to = to;
		}
		
		public void setWeight(double weight) {
			this.weight = weight;
		}
		
		public double getWeight() {
			return weight;
		}

		public String toString() {
			return "Edge[" + from.getID()+ "->" + to.getID() + "] weight=" + weight;
		}
}
