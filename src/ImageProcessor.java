
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class ImageProcessor {
	private Node[][] nodeMatrix;
	
	public Graph processImage(String imagePath) {
		try {
			BufferedImage image = ImageIO.read(new File(imagePath));
			if(image == null) {
				System.out.println("Image not found or could not be read: " + imagePath);
				return null;
			}
		
			int rows = image.getWidth();
			int cols = image.getHeight();
			
			nodeMatrix = new Node[rows][cols];
			
			int count = 0;
			
			for(int i = 0; i < rows; i++) {
				for(int x = 0; x < cols; x++) {
					int rgb = image.getRGB(x, i);
					int grayValue = (rgb >> 16) & 0xFF;
					Node node = new Node(count++, x, i, grayValue);
					nodeMatrix[i][x] = node;
				}
			}
			
			//Creates NodeMatrix object
			NodeMatrix matrix = new NodeMatrix(nodeMatrix);
			
			//Create graph from node matrix
			Graph graph = new Graph(matrix);
			
			//Add edges based on pixel similarity
			for(int i = 0; i < rows; i++) {
				for(int x = 0; x < cols; x++) {
					Node currentNode = nodeMatrix[i][x];
					int currentGray = (image.getRGB(x, i)>> 16) & 0xFF;
					
					if(x + 1 < cols) {
						Node right = nodeMatrix[i][x + 1];
						int rightGray = (image.getRGB(x + 1, i) >> 16) & 0xFF;
						double distance = Math.abs(currentGray - rightGray);
						graph.addEdge(currentNode, right, distance);
						graph.addEdge(right, currentNode, distance);
					}
					
					if(i + 1 < rows) {
						Node bottom = nodeMatrix[i + 1][x];
						int bottomGray = (image.getRGB(x, i + 1) >> 16) & 0xFF;
						double distance = Math.abs(currentGray - bottomGray);
						graph.addEdge(currentNode, bottom, distance);
						graph.addEdge(bottom, currentNode, distance);
					}
				}
			}
			return graph;
			
		} catch (Exception e) {
			System.out.println("Error processing image: " + e.getMessage());
			return null;
		}
		
	}
	
	public Node[][] getNodeMatrix(){
		return nodeMatrix;
	}
}
