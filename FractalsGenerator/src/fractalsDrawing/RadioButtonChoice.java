package fractalsDrawing;

/*************************************
 * @author Anna Szafrañska (@AnnSzafr)
 * @created 06 July 2019
 *************************************/

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class RadioButtonChoice extends VBox {
		
	Label questionLabel;
	RadioButton[] responseButtons;
	
	RadioButtonChoice(String questionString, String[] choices, int activeChoice){
		setAlignment(Pos.CENTER);
		setSpacing(10);
		
		responseButtons  = new RadioButton[choices.length];
		questionLabel = new Label(questionString);
		questionLabel.setStyle("-fx-font-size: 14");
		
		getChildren().add(questionLabel);
		
		HBox buttonsPane = new HBox(10);
		buttonsPane.setSpacing(20);
		buttonsPane.setAlignment(Pos.CENTER);
		ToggleGroup tg = new ToggleGroup();
		for(int i=0; i<choices.length; i++) {
			responseButtons[i] = new RadioButton(choices[i]);
			responseButtons[i].setToggleGroup(tg);
			buttonsPane.getChildren().add(responseButtons[i]);
		}
		responseButtons[activeChoice].setSelected(true);
		getChildren().add(buttonsPane);
	}
}
