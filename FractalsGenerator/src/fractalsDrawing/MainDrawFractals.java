package fractalsDrawing;

/*************************************
 * @author Anna Szafrañska (@AnnSzafr)
 * @created 06 July 2019
 *************************************/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainDrawFractals extends Application{
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) {
		long startTime = System.currentTimeMillis();
		
		primaryStage.setWidth(950);
		primaryStage.setHeight(700);
		
		FractalChoice fractalChoice = new FractalChoice(primaryStage.getWidth(),primaryStage.getHeight());
		Scene scene = new Scene(fractalChoice.mainPanel);
		
		fractalChoice.mainPanel.prefWidthProperty().bind(primaryStage.widthProperty());
		fractalChoice.mainPanel.prefHeightProperty().bind(primaryStage.heightProperty());
		
		System.out.println(System.currentTimeMillis() - startTime);
		primaryStage.setTitle("FRACTAL DRAWING");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		System.out.println("Koniec: "+(System.currentTimeMillis() - startTime));
	}

}