package fractalsDrawing;

/*************************************
 * @author Anna Szafrañska (@AnnSzafr)
 * @created 06 July 2019
 *************************************/

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;

import java.util.Random;

public class DragonCurve extends BorderPane{
	// ---- mane panels ---- //
	BorderPane holderCanvas;
	GridPane settingsElements;

	CanvasDragon canvas;
	RadioButtonChoice radioButtonChoice;	
	BorderPane sliderLinePane;
	BorderPane sliderRecursionsPane;
	
	static final String[] responsesNumberOfDragons = {"1","2","3","4"};
	static final int beginChoice = 0;
	Label labelLineLength = new Label("Length of drawing line");
	Label labelRecursions = new Label("Number of recursions");
	Slider[] sliderLineLength = new Slider[responsesNumberOfDragons.length];
	Slider[] sliderRecursions = new Slider[responsesNumberOfDragons.length];
	Button colorChange = new Button("Change the color of dragons");
	Button colorReturn = new Button("Return to begin colors");
	
	int width;
	int height;
	final int numberOfDragons = 1;
	final int lengthOfDrawingLine = 5;
	final int numberOfRecursions = 5;
	final Color[] color = {Color.rgb(255,50,255,1),Color.rgb(100,0,250,1),Color.rgb(250,0,100,1),Color.rgb(100,250,0,1)};
	
	Random rand = new Random();
	
	DragonCurve(double width1, double height1){
		width = (int)(2*width1/3);
		height = (int)(height1-100);
		
		// ---- drawing fractal panel ---- //
		dragonCanvas();
		
		// ---- settings panel ---- //
		settingsPanel();		
		
		setCenter(holderCanvas);
		setRight(settingsElements);
	}
	
	// ---- setting the drawing panel ---- //
	private final void dragonCanvas() {
		holderCanvas = new BorderPane();
			holderCanvas.setStyle("-fx-background-color: rgb(64,64,64);");
			redrawCanvas(numberOfDragons,lengthOfDrawingLine,numberOfRecursions);
			// ---- we need to add canvas to holderCanvas as getChildren() to be resizable ----//
			holderCanvas.getChildren().add(canvas);
	}
	
	// ---- setting the settings panel ----//
	private final void settingsPanel() {
		settingsElements = new GridPane();
		settingsElements.setStyle("-fx-padding: 20,10,10,10; -fx-vgap: 10");
		settingsElements.setMinWidth(4*width/3-width);
		
		Separator separator = new Separator();
		separator.setValignment(VPos.CENTER);
		separator.setMinWidth(settingsElements.getMinWidth());
		Separator separator1 = new Separator();
		separator1.setValignment(VPos.CENTER);
		separator1.setMinWidth(settingsElements.getMinWidth());
	
		Label label = new Label("DRAGON SETTINGS");
		label.setStyle("-fx-text-fill: blue; -fx-font-size: 18");
	
    	radioButtonChoice = new RadioButtonChoice("How many dragons?",responsesNumberOfDragons,beginChoice);
    	
    	// ---- stylization of slider's label ---- //
    	labelLineLength.setStyle("-fx-font-size: 14");
    	labelRecursions.setStyle("-fx-font-size: 14");
    	
    	// ---- create sliders of begin view ---- //
    	sliderLinePane = new BorderPane();
    	sliderRecursionsPane = new BorderPane();
    	
    	sliderLineLength[0] = new CreateSlider(0,30,5,5,10f);
    	sliderLinePane.setCenter(sliderLineLength[0]);
    	sliderListener(sliderLineLength[0],1);
    	
    	sliderRecursions[0] = new CreateSlider(0,20,5,5,5f);
    	sliderRecursionsPane.setCenter(sliderRecursions[0]);
    	sliderListener(sliderRecursions[0],2);
		
    	// ---- create color buttons ---- //
		colorChange.setMaxWidth(Double.MAX_VALUE);
		colorChange.setStyle("-fx-background-radius: 20,20,20,20; -fx-font-size: 14; -fx-text-fill: darkblue");
		colorReturn.setMaxWidth(Double.MAX_VALUE);
		colorReturn.setStyle("-fx-background-radius: 20,20,20,20; -fx-font-size: 14; -fx-text-fill: darkblue");

		GridPane.setHalignment(label,HPos.CENTER);
    	settingsElements.add(label,0,0);
    	settingsElements.add(radioButtonChoice, 0, 1);
    	GridPane.setHalignment(labelLineLength,HPos.CENTER);
    	settingsElements.add(labelLineLength,0,3);
    	settingsElements.add(sliderLinePane,0,4);
    	GridPane.setHalignment(labelRecursions,HPos.CENTER);
    	settingsElements.add(labelRecursions,0,6);
    	settingsElements.add(sliderRecursionsPane,0,7);

		settingsElements.addRow(2,separator);
		settingsElements.addRow(5,separator1);

		GridPane.setMargin(colorChange,new Insets(20,5,5,5));
		settingsElements.add(colorChange,0,8);
		GridPane.setMargin(colorReturn,new Insets(10,5,5,5));
		settingsElements.add(colorReturn,0,9);
		
		// ---- configuration of radio button and buttons action ---- //	
		for(int j=0; j<responsesNumberOfDragons.length; j++) {
			radioButtonAction(radioButtonChoice.responseButtons[j]);
		}
		buttonAction(colorChange);
		buttonAction(colorReturn);

	}
	
	public void redrawCanvas(int numberOfDragons,int lengthOfDrawingLine,int numberOfRecursions) {
		
		canvas = new CanvasDragon();
		canvas.setNumberOfDragons(numberOfDragons);
		canvas.setLengthOfDrawingLine(lengthOfDrawingLine);
		canvas.setNumberOfRecursions(numberOfRecursions);
		for(int i=0; i<numberOfDragons; i++)
			canvas.setColor(color[i],i);
		
		canvas.widthProperty().bind(holderCanvas.widthProperty());
		canvas.heightProperty().bind(holderCanvas.heightProperty());
		
	}
	
	// ---- setting the actions ---- //
	private void buttonAction(Button button) {
		button.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent event) {
		    	if (event.getSource() == colorChange) {
		    		for(int i=0; i<numberOfDragons; i++) {
		    			canvas.setColor(Color.rgb(rand.nextInt(254),rand.nextInt(254),rand.nextInt(254)),i);
		    		}
		    	} else if (event.getSource() == colorReturn) {
		    		Color[] color = {Color.rgb(255,50,255,1),Color.rgb(100,0,250,1),Color.rgb(250,0,100,1),Color.rgb(100,250,0,1)};
		    		for(int i=0; i<numberOfDragons; i++)
		    			canvas.setColor(color[i],i);
		    	}
		    }
		});
	}
	
	private void radioButtonAction(RadioButton rb) {
		rb.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	for (int i=0; i<radioButtonChoice.responseButtons.length; i++) {
	        		if(event.getSource() == radioButtonChoice.responseButtons[i]) {
	        			// ---- need to clear the settings panel from children to not overdraw sliders ---- //
	        			settingsElements.getChildren().remove(sliderLinePane);
	        			settingsElements.getChildren().remove(sliderRecursionsPane);
	        			
	        			redrawCanvas(i+1,lengthOfDrawingLine,numberOfRecursions);
	        			holderCanvas.getChildren().clear();
	        			holderCanvas.getChildren().add(canvas);
	        			
	        			// create sliders for next views ---- //
	        			sliderLineLength[i] = new CreateSlider(0,30,5,5,10f);
	        			sliderListener(sliderLineLength[i],1);
	        			sliderLinePane.setCenter(sliderLineLength[i]);
	        			
	        			sliderRecursions[i] = new CreateSlider(0,20,10,5,5f);
	        			sliderListener(sliderRecursions[i],2);
	        			sliderRecursionsPane.setCenter(sliderRecursions[i]);
	        			
	        			settingsElements.add(sliderLinePane,0,4);
	        			settingsElements.add(sliderRecursionsPane,0,7);
	        			
	        		}
	           	}
	        }
		});
	}
	
	private void sliderListener(Slider slider,int whichSlider) {
		slider.valueProperty().addListener(new ChangeListener<Number>() {
	         @Override
	         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	        	int intValue = newValue.intValue();
	        	if(whichSlider == 1)
	        		canvas.setLengthOfDrawingLine(intValue);
	        	else 
	        		canvas.setNumberOfRecursions(intValue);
	         }
	      });
	}
	
}

class CanvasDragon extends Canvas {
	 
    CanvasDragon() {
      // ---- Redraw canvas when size of the window changes ---- //
        widthProperty().addListener(it -> draw());
        heightProperty().addListener(it -> draw());
    }
 
    @Override
    public boolean isResizable() {
      return true;
    }
 
    @Override
    public double prefWidth(double height) {
      return getWidth();
    }
 
    @Override
    public double prefHeight(double width) {
      return getHeight();
    }
  
    private int numberOfDragons;
	private int lengthOfDrawingLine;
	private int numberOfRecursions;
	private Color[] color = new Color[4];
	
	public void setNumberOfDragons(int numberOfDragons){
		this.numberOfDragons = numberOfDragons;
		draw();
	}
	public void setLengthOfDrawingLine(int lengthOfDrawingLine) {
		this.lengthOfDrawingLine = lengthOfDrawingLine;
		draw();
	}
	
	public void setNumberOfRecursions(int numberOfRecursions) {
		this.numberOfRecursions = numberOfRecursions;
		draw();
	}
	
	public void setColor(Color colorNew, int whichColor) {
		for (int i=0; i<color.length; i++) {
			if (whichColor == i) {
				this.color[i] = colorNew;
				draw();
			}
		}
	}
	
    public void draw() {
	    
	    GraphicsContext dragonGC = getGraphicsContext2D();
	    dragonGC.clearRect(0,0,getWidth(),getHeight());
	    
	    double xmin, xmax, ymin, ymax;
		int size = lengthOfDrawingLine;
		
		// ---- getting the dragon instruction ---- //
		DragonPath dragonPath = new DragonPath();
		dragonPath.buildDragonList(numberOfRecursions);
		
		Integer[] rotationList = {0,0,0,0};
		Color[] dragonColor = {color[0],color[1],color[2],color[3]};
		
		dragonGC.setLineWidth(1);
		
		for (int k=0; k<numberOfDragons; k++) {
			dragonGC.setStroke(dragonColor[k]);
			rotationList[k] = size;
			
			// ---- initial lines of dragon curve ---- //
			xmin = getWidth()/2+0.5;
			ymin = getHeight()/2+0.5;
			xmax = getWidth()/2+rotationList[0]-rotationList[2]+0.5;
			ymax = getHeight()/2-rotationList[1]+rotationList[3]+0.5;
			
			dragonGC.strokeLine(xmin,ymin,xmax,ymax);
			
			for (int i=0; i<dragonPath.dragonList.size(); i++) {
				
				if (dragonPath.dragonList.get(i)==0) {
					if (ymin == ymax) {
						if (xmin < xmax) {
							dragonGC.strokeLine(xmax, ymax, xmax, ymax+size);
							xmin = xmax;
							ymin = ymax;
							ymax = ymax+size;
						} else if (xmin > xmax) {
							dragonGC.strokeLine(xmax, ymax, xmax, ymax-size);
							xmin = xmax;
							ymin = ymax;
							ymax = ymax-size;
						}
					} else if (xmin == xmax) {
						if (ymin < ymax) {
							dragonGC.strokeLine(xmax, ymax, xmax-size, ymax);
							xmin = xmax;
							xmax = xmax-size;
							ymin = ymax;
						} else if (ymin > ymax) {
							dragonGC.strokeLine(xmax, ymax, xmax+size, ymax);
							xmin=xmax;
							xmax = xmax+size;
							ymin = ymax;
						}
					}
				} else if (dragonPath.dragonList.get(i)==1) {
					if (ymin == ymax) {
						if (xmin < xmax) {
							dragonGC.strokeLine(xmax, ymax, xmax, ymax-size);
							xmin = xmax;
							ymin = ymax;
							ymax = ymax-size;
						} else if (xmin > xmax) {
							dragonGC.strokeLine(xmax, ymax, xmax, ymax+size);
							xmin = xmax;
							ymin = ymax;
							ymax = ymax+size;
						}
					} else if (xmin == xmax) {
						if (ymin < ymax) {
							dragonGC.strokeLine(xmax, ymax, xmax+size, ymax);
							xmin = xmax;
							xmax = xmax+size;
							ymin = ymax;
						} else if (ymin > ymax) {
							dragonGC.strokeLine(xmax, ymax, xmax-size, ymax);
							xmin = xmax;
							xmax = xmax-size;
							ymin = ymax;
						}
					}
				}
			}
			rotationList[k] = 0;
		}
		
	}
    
}
