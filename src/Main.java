

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	private Label lblResult = new Label("Select an image to process.");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Graph-Based Image Processor");
		
		//Button to select image
		Button selectImageBTN = new Button("Select Image");
		
		//Image preview
		ImageView imageView = new ImageView();
		ImageView outputView = new ImageView();
		outputView.setFitWidth(300);
		outputView.setPreserveRatio(true);
		imageView.setFitWidth(300);
		imageView.setPreserveRatio(true);
		
		//On button click
		selectImageBTN.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Image File");
			File file = fileChooser.showOpenDialog(primaryStage);
			if(file != null) {
				try {
					
				    processImage(file.getAbsolutePath());
				    imageView.setImage(new Image(file.toURI().toString()));
				    File output = new File("output_path.png");
				    if(output.exists()) {
					    outputView.setImage(new Image(output.toURI().toString()));
				   }
				}catch (Exception ex) {
					ex.printStackTrace();
					lblResult.setText("Error processing image: " + ex.getMessage());
				}
			}
		});
		
		//Layout using GridPane
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(20));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		gridPane.add(selectImageBTN, 0, 0);
		gridPane.add(imageView, 0, 1);
		gridPane.add(lblResult, 0, 2);
		gridPane.add(outputView, 1, 1);
		
		Scene scene = new Scene(gridPane, 600, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void processImage(String imagePath) throws IOException {
		BufferedImage image = ImageIO.read(new File(imagePath));
		int width = image.getWidth();
		int height = image.getHeight();
		
		BufferedImage grayscale = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		Graphics graphics = grayscale.getGraphics();
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		
		Node[][] matrix = new Node[height][width];
		NodeMatrix nodeMatrix = new NodeMatrix(matrix);
		for(int i = 0; i < height; i++) {
			for(int x = 0; x < width; x++) {
				int color = new Color(grayscale.getRGB(x, i)).getRed();
				int nodeID = i * width + x;
				nodeMatrix.addNode(i, x, nodeID, color);
			}
		}
		
		Graph graph = new Graph(nodeMatrix);
		Node start = nodeMatrix.getNode(0, 0);
		Node end = nodeMatrix.getNode(height - 1, width - 1);
		List<Node> path = graph.dijkstra(start, end);
	}
	
	public static void main(String[] args) {
		//System.load("C:/Users/bulel/Downloads/opencv/build/java/x64/opencv_java4110.dll");
		launch(args);
	}

	

}
