package fractalsDrawing;

/*************************************
 * @author Anna Szafrañska (@AnnSzafr)
 * @created 06 July 2019
 *************************************/

import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class FractalChoice {
	
	BorderPane mainPanel = new BorderPane();
	BorderPane beginFractalPane;
	int width, height;
	
	DragonCurve dragonCurve = new DragonCurve(width,height);
	//DragonIFSDraw dragonIFS = new DragonIFSDraw(width,height);
	BorderPane[] fractalPanes = new BorderPane[2];
	
	static final String[] buttonName = {"Dragon","Dragon IFS","Fern IFS","Mandelbrot Set","Julia Set"};
	static Button[] buttonFractal = new Button[buttonName.length];
	
	FractalChoice(double width, double height){
		this.width = (int) width;
		this.height = (int) height;
		
		beginFractalPane = new BorderPane();
		
		// button's panel
		HBox buttonChoicePane = new HBox(30);
			buttonChoicePane.setStyle("-fx-alignment: center; -fx-padding: 15");
			//buttonFractalChoice.setPadding(new Insets(15));
			//buttonFractalChoice.setAlignment(Pos.CENTER);
		
			// ---- object dragonCurve is needed, because of initial view ---- // 
			 fractalPanes[0] = dragonCurve;
			 fractalPanes[1] = new DragonIFSDraw(this.width,this.height);
			//DragonIFSDrawNew dragonIFSDraw = new DragonIFSDrawNew(width,height);
			//FernIFSDrawNew fernIFSDraw = new FernIFSDrawNew(width,height);
			//MandelbrotDrawNew mandelbrotDraw = new MandelbrotDrawNew(width,height);
			//JuliaDrawNew juliaDraw = new JuliaDrawNew(width,height);
		
			//,new DragonIFS(width,height)};//,fernIFSDraw,mandelbrotDraw,juliaDraw};
		
			for (int i=0; i<buttonName.length;i++) {
				buttonFractal[i] = new Button(buttonName[i]);
				buttonFractal[i].setStyle("-fx-background-radius: 10,10,10,10; "
						+ "-fx-background-color: #a6b5c9,\r\n" + 
						"        linear-gradient(#303842 0%, #3e5577 20%, #375074 100%),\r\n" + 
						"        linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%); "
						+ "-fx-font-size: 15; "
						//+ "-fx-font-weight: bold;"
						+ " -fx-text-fill: white;"
						+ "-fx-font-family: \"Helvetica\" ");
				buttonChoicePane.getChildren().add(buttonFractal[i]);
				
				// ---- set action to choice buttons ---- //
				buttonAction(buttonFractal[i]);
			}
		
		beginFractalPane.setCenter(dragonCurve);
		mainPanel.setTop(buttonChoicePane);
		mainPanel.setCenter(beginFractalPane);
	}
	
	private final void buttonAction(Button button) {
		button.setOnAction((ActionEvent ae) -> {
			for (int j=0; j<fractalPanes.length; j++) {
				if (ae.getSource() == buttonFractal[j]) {
					beginFractalPane.getChildren().clear();
					buttonFractal[j].setStyle("-fx-background-radius: 10,10,10,10; "
							+ "-fx-background-color: #a6b5c9,\r\n" + 
							"        linear-gradient(#303842 0%, #3e5577 20%, #375074 100%),\r\n" + 
							"        linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%); "
							+ "-fx-font-size: 15; "
							+ "-fx-border-color: yellow;"
							+ "-fx-border-radius: 10,10,10,10; "
							//+ "-fx-font-weight: bold;"
							+ " -fx-text-fill: white;"
							+ "-fx-font-family: \"Helvetica\" ");
				beginFractalPane.setCenter(fractalPanes[j]);
			}else {
				buttonFractal[j].setStyle("-fx-background-radius: 10,10,10,10; "
						+ "-fx-background-color: #a6b5c9,\r\n" + 
						"        linear-gradient(#303842 0%, #3e5577 20%, #375074 100%),\r\n" + 
						"        linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%); "
						+ "-fx-font-size: 15; "
						//+ "-fx-font-weight: bold;"
						+ " -fx-text-fill: white;"
						+ "-fx-font-family: \"Helvetica\" ");
				}
			}
		});
	}
	
}