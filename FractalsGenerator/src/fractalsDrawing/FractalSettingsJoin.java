package fractalsDrawing;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class FractalSettingsJoin extends BorderPane {
	
	FractalSettingsJoin(ImageView image, Label label, GridPane settingsPanel, int width, int height){
		// ---- dragon panel ----
		BorderPane holderImage = new BorderPane();
		holderImage.setStyle("-fx-background-color: rgb(64,64,64);");
		holderImage.getChildren().add(image);
		image.setPreserveRatio(false);
		image.fitWidthProperty().bind(holderImage.widthProperty());
		image.fitHeightProperty().bind(holderImage.heightProperty());
				
		// ---- settings panel for drawing the dragon 
		GridPane settings = new GridPane();
		label.setStyle("-fx-padding: 10px; -fx-font-size: 14; -fx-alignment: center");
		settings.setMinWidth(4*width/3 - width);
		settings.add(label,0,0);
		settings.add(settingsPanel, 0, 1);
				
		setCenter(holderImage);
		setRight(settings);				
	}
}