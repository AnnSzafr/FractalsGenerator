package fractalsDrawing;

/*************************************
 * @author Anna Szafrañska (@AnnSzafr)
 * @created 06 July 2019
 *************************************/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainDrawFractals extends Application{
	
	final int primaryStageWidth = 950;
	final int primaryStageHeight = 700;
	String stageTitle = new String("FRACTAL DRAWING");
	
	private void setPrimaryStageSize(Stage stage) {
		stage.setWidth(primaryStageWidth);
		stage.setHeight(primaryStageHeight);
	}
	
	private void setTitle(Stage stage) {
		stage.setTitle(stageTitle);
	}
	
	@Override
	public void start(Stage primaryStage) {
		long startTime = System.currentTimeMillis();
		
		setPrimaryStageSize(primaryStage);
		setTitle(primaryStage);
		
		System.out.println(System.currentTimeMillis() - startTime);
		
		CreateScene createScene = new CreateScene(primaryStage);
		createScene.create();
		primaryStage.show();
		
		System.out.println("Koniec: "+(System.currentTimeMillis() - startTime));
	}
	
	public static void main(String[] args) {
		launch();
	}
	
}

class CreateScene {
	private Stage stage;
	FractalChoice fractalChoice;
	
	CreateScene(Stage stage) {
		this.stage = stage;
	}
	private void scenePaneSettings() {
		fractalChoice = new FractalChoice(stage.getWidth(),stage.getHeight()); 
		fractalChoice.mainPanel.prefWidthProperty().bind(stage.widthProperty());
		fractalChoice.mainPanel.prefHeightProperty().bind(stage.heightProperty());
	}
	public void create() {
		scenePaneSettings();
		Scene scene = new Scene(fractalChoice.mainPanel);
		stage.setScene(scene);
	}
}