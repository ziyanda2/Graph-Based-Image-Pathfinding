
public class NodeMatrix {
	private Node[][] matrix; 
	private int row, col; 
	
	public NodeMatrix(Node[][] matrix) {
		this.matrix = matrix;
		this.row = matrix.length;
		this.col = matrix[0].length;
	}
	
	public Node getNode(int row, int col) {
		return matrix[row][col];
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setNode(int row, int col, Node node) {
		matrix[row][col] = node;
	}
	
	public void addNode(int row, int col, int nodeID, int grayValue) {
		Node node = new Node(nodeID, row, col, grayValue);
		setNode(row, col, node);
	}
	
	public Node[][] getMatrix(){
		return matrix;
	}
	
	public void connectNeighbors() {
		for(int i = 0; i < matrix.length; i++) {
			for(int c = 0; c < matrix[i].length; c++) {
				Node currentNode = matrix[i][c];
				if(c < matrix[i].length - 1) {
					Node rightNeighbor = matrix[i][c + 1];
					currentNode.addEdge(new Edge(currentNode, rightNeighbor, 1.0));
				}
				if(i < matrix.length -1) {
					Node bottomNeighbor = matrix[i + 1][c];
					currentNode.addEdge(new Edge(currentNode, bottomNeighbor, 1.0));
				}
			}
		}
	}
}
