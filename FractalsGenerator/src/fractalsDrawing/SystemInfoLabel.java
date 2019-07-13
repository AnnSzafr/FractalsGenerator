package fractalsDrawing;
import java.text.DecimalFormat;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SystemInfoLabel extends GridPane{
	SystemInfoLabel(Double[] xParam, Double[] yParam, String function,String prob) {
		DecimalFormat df = new DecimalFormat("##.###");
		Label labelXEquation = new Label(df.format(xParam[0])+" x "+signum(xParam[1])+df.format(xParam[1])
			+" y "+signum(xParam[2])+df.format(xParam[2]));
		Label labelYEquation = new Label(df.format(yParam[0])+" x "+signum(yParam[1])+df.format(yParam[1])
			+" y "+signum(yParam[2])+df.format(yParam[2]));
		
		labelXEquation.setStyle("-fx-font-size: 14");
		labelYEquation.setStyle("-fx-font-size: 14");
		
		Label labelSign = new Label("{");
		labelSign.setStyle("-fx-font-size: 30");
		Label labelFunction = new Label(function);
		labelFunction.setStyle("-fx-font-size: 14");
		Label probabilityLabel = new Label("Probability for "+function + prob);
		probabilityLabel.setStyle("-fx-font-size: 14");
		
		VBox systemBox = new VBox();
		systemBox.setPadding(new Insets(5,10,5,5));
		systemBox.getChildren().add(labelXEquation);
		systemBox.getChildren().add(labelYEquation);
			
		add(labelFunction, 0, 0);
		add(labelSign, 1, 0);
		add(systemBox, 2, 0);
		GridPane.setColumnSpan(probabilityLabel,3);
		add(probabilityLabel,0,1);
	}
	private static String signum(double value) {
		if(value>=0)
			return "+ ";
		return " ";
	}	
}