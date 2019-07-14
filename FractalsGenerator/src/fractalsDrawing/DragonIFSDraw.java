package fractalsDrawing;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class DragonIFSDraw extends BorderPane {
	
	BorderPane holderImage;
	ImageView image = new ImageView();
	DragonIFS dragonIFS;
	Slider slider;
	TextField textProbabilityField;
	GridPane settingsElements;
	Alert alert = new Alert(AlertType.ERROR);
	
	Double[] xParam1 = {0.82, 0.28, -1.9};
	Double[] yParam1 = { -0.3, 0.8, -0.1};
	Double[] xParam2 = {0.08, 0.52, 0.6};
	Double[] yParam2 = { -0.5, -0.3, 8.1};
	
	Label infoSliderValue;
	SystemInfoLabel systemInfoLabel1;
	SystemInfoLabel systemInfoLabel2;
	
	Button goldenDragon = new Button("Golden Dragon");
	Button randomParam = new Button("Random parameters of functions");
	Button beginParam = new Button("Begin to initial parameters");

	int width;
	int height;
	private int numberOfPoints = 10000;
	private double probabilityNumber = 0.15;
		
	DragonIFSDraw(int width1, int height1){
		width = (int)(3*width1/4);
		height = (int)(height1-100);
		
		// ---- drawing fractal panel ---- //
		dragonIFSImage();
		
		settingsPanel();
		
		setCenter(holderImage);
		setRight(settingsElements);
	}
	
	private void dragonIFSImage() {
		holderImage = new BorderPane();
			holderImage.setStyle("-fx-background-color: rgb(64,64,64);");
			redrawImage(numberOfPoints,probabilityNumber);
			holderImage.getChildren().add(image);
	}
	
	private void redrawImage(int numOfP, double probNum) {
		dragonIFS = new DragonIFS();
		setNumberOfPoint(numOfP);
		dragonIFS.setProbabilityNumber(probNum);
		image.setImage(createImage());
		image.setPreserveRatio(false);
		image.fitWidthProperty().bind(holderImage.widthProperty());
		image.fitHeightProperty().bind(holderImage.heightProperty());
	}	
	
	private void settingsPanel() {
		settingsElements = new GridPane();			
			settingsElements.setStyle("-fx-padding: 15,5,5,5; -fx-vgap: 10");
			settingsElements.setMinWidth(4*width/3 - width);
		
			Label mainLabel = new Label("HEIGHWAY DRAGON (IFS)");
			mainLabel.setStyle("-fx-text-fill: blue; -fx-font-size: 18");
			
			slider = new CreateSlider(0,100000,10000,10000,50000f);
			sliderListener(slider);
			
			HBox infoSlider = new HBox(20);
				Label infoSliderValueLabel = new Label("Current number: ");
				infoSliderValueLabel.setStyle("-fx-font-size: 14");
				infoSliderValue = new Label(Integer.toString((int)slider.getValue()));
				
				infoSlider.getChildren().add(infoSliderValueLabel);
				infoSlider.getChildren().add(infoSliderValue);
		
			HBox textProbability = new HBox(10);
				Label infoTextLabel = new Label("Set probability for F1:");
				infoTextLabel.setStyle("-fx-font-size: 14");
				
				
				textProbabilityField = new TextField("0.15");
				textProbabilityField.setMaxWidth(60);
				textFieldListener(textProbabilityField);
				
				textProbability.getChildren().addAll(infoTextLabel,textProbabilityField);
				
				systemInfoLabel1 = new SystemInfoLabel(xParam1, yParam1, "F1 = ",textProbabilityField.getText());
				systemInfoLabel2 = new SystemInfoLabel(xParam2, yParam2, "F2 = ",Double.toString(1.0-Double.parseDouble(textProbabilityField.getText())));
			
			// ---- create buttons ---- //
			goldenDragon.setMaxWidth(Double.MAX_VALUE);
			goldenDragon.setStyle("-fx-background-radius: 20,20,20,20; -fx-font-size: 14; -fx-text-fill: darkblue");
			randomParam.setMaxWidth(Double.MAX_VALUE);
			randomParam.setStyle("-fx-background-radius: 20,20,20,20; -fx-font-size: 14; -fx-text-fill: darkblue");
			beginParam.setMaxWidth(Double.MAX_VALUE);
			beginParam.setStyle("-fx-background-radius: 20,20,20,20; -fx-font-size: 14; -fx-text-fill: darkblue");
			buttonsListener(goldenDragon);
			buttonsListener(randomParam);
			buttonsListener(beginParam);
			
			GridPane.setHalignment(mainLabel,HPos.CENTER);
			settingsElements.add(mainLabel, 0, 0);
			settingsElements.add(slider, 0, 1);
			settingsElements.add(infoSlider, 0, 2);
			settingsElements.add(textProbability,0,3);
			settingsElements.add(systemInfoLabel1, 0, 4);
			settingsElements.add(systemInfoLabel2, 0, 5);
			GridPane.setMargin(goldenDragon,new Insets(20,5,5,5));
			settingsElements.add(goldenDragon, 0, 6);
			GridPane.setMargin(randomParam,new Insets(10,5,5,5));
			settingsElements.add(randomParam, 0, 7);
			GridPane.setMargin(beginParam,new Insets(10,5,5,5));
			settingsElements.add(beginParam, 0, 8);
	}
	
	// ---- setting the actions ---- //
	private void textFieldListener(TextField text) {
		text.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				redrawImage((int)slider.getValue(),Double.parseDouble(newValue));
				settingsElements.getChildren().remove(systemInfoLabel1);
				settingsElements.getChildren().remove(systemInfoLabel2);
				systemInfoLabel1 = new SystemInfoLabel(xParam1, yParam1, "F1 = ",newValue);
				systemInfoLabel2 = new SystemInfoLabel(xParam2, yParam2, "F2 = ",Double.toString(1.0-Double.parseDouble(newValue)));
				settingsElements.add(systemInfoLabel1, 0, 4);
				settingsElements.add(systemInfoLabel2, 0, 5);
			}catch (NumberFormatException nfe) {
				alert.setTitle("Wrong number format");
				alert.setHeaderText("Put apropriate number format: "+nfe.getMessage());
				alert.showAndWait();
			}
		});
	}
	
	private void sliderListener(Slider slider) {
		slider.valueProperty().addListener(new ChangeListener<Number>() {
	         @Override
	         public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	        	int intValue = newValue.intValue();
	        	redrawImage(intValue,Double.parseDouble(textProbabilityField.getText()));
	        	infoSliderValue.setText(Integer.toString(intValue));
	         }
	      });
	}
	
	private void buttonsListener(Button button) {
		button.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	if(e.getSource() == goldenDragon) {
		    		Double[] xParamNew1 = {0.62367, -0.40337, 0.0};
		    		Double[] yParamNew1 = {0.40337, 0.62367, 0.0};
		    		Double[] xParamNew2 = {-0.37633, -0.40337, 1.0};
		    		Double[] yParamNew2 = { 0.40337, -0.37633, 0.0};
		    		buttonActionSettings(xParamNew1,yParamNew1,xParamNew2,yParamNew2);
		    	} else if (e.getSource() == randomParam) {
		    		Double[] xParamNew1 = {2.0*Math.random(),-0.5 + 1.0*Math.random(),-0.5 + 1.0*Math.random()};
		    		Double[] yParamNew1 = {-1 + 1.0*Math.random(),2.0*Math.random(),-0.5 + 1.0*Math.random()};
		    		Double[] xParamNew2 = {-2.0*Math.random(),-0.5 + 1.0*Math.random(),-0.5 + 1.0*Math.random()};
		    		Double[] yParamNew2 = {-1 + 1.0*Math.random(),-2.0*Math.random(),-0.5 + 1.0*Math.random()};
		    		buttonActionSettings(xParamNew1,yParamNew1,xParamNew2,yParamNew2);
		    	} else if (e.getSource() == beginParam) {
		    		Double[] xParamNew1 = {0.82, 0.28, -1.9};
		    		Double[] yParamNew1 = { -0.3, 0.8, -0.1};
		    		Double[] xParamNew2 = {0.08, 0.52, 0.6};
		    		Double[] yParamNew2 = { -0.5, -0.3, 8.1};
		    		buttonActionSettings(xParamNew1,yParamNew1,xParamNew2,yParamNew2);
		    	}
		    	
		    	redrawImage(numberOfPoints,0.5);
		    }
		});
	}
	
	private void buttonActionSettings(Double[] xP1,Double[] yP1,Double[] xP2,Double[] yP2) {
    	setXParam1(xP1);
    	setYParam1(yP1);
    	setXParam2(xP2);
		setYParam2(yP2);
		settingsElements.getChildren().remove(systemInfoLabel1);
		settingsElements.getChildren().remove(systemInfoLabel2);
		systemInfoLabel1 = new SystemInfoLabel(xP1, yP1, "F1 = ",textProbabilityField.getText());
		systemInfoLabel2 = new SystemInfoLabel(xP2, yP2, "F2 = ",Double.toString(1.0-Double.parseDouble(textProbabilityField.getText())));
		settingsElements.add(systemInfoLabel1, 0, 4);
		settingsElements.add(systemInfoLabel2, 0, 5);

	}
	
	private void setNumberOfPoint(int numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}
	
	private void setXParam1(Double[] xParam1) {
		this.xParam1 = xParam1;
	}
	private void setYParam1(Double[] yParam1) {
		this.yParam1 = yParam1;
	}
	private void setXParam2(Double[] xParam2) {
		this.xParam2 = xParam2;
	}
	private void setYParam2(Double[] yParam2) {
		this.yParam2 = yParam2;
	}
	
	ArrayList<Double> xListScale = new ArrayList<>();
	ArrayList<Double> yListScale = new ArrayList<>();
	Integer[] distanceOrigin;
	
	private Image createImage() {
		BufferedImage bi = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D dragonIFSGC = bi.createGraphics();
		dragonIFS.buildDragonIFSList(numberOfPoints,xParam1,yParam1,xParam2,yParam2);
		
		ListScale();
	    
		for (int i=0; i<xListScale.size(); i++) {
			Line2D.Double line = new Line2D.Double(xListScale.get(i),yListScale.get(i),xListScale.get(i)+0.1,yListScale.get(i)+0.1);
			//dragonIFSGC.setColor(new Color(distanceOrigin[i]*15,255-distanceOrigin[i]*15,distanceOrigin[i]*15));
			dragonIFSGC.setColor(new Color(0,255,100));
			dragonIFSGC.draw(line);
		}
		xListScale.clear();
		yListScale.clear();
		
		// connecting swing with JavaFX
		WritableImage image = SwingFXUtils.toFXImage(bi, null);
        dragonIFSGC.dispose();
        return image;
	}
	
    private void ListScale() {
		distanceOrigin = new Integer[dragonIFS.xList.size()];
		
		for (int i=0; i< dragonIFS.xList.size(); i++) {
			distanceOrigin[i] = (int)Math.sqrt(Math.pow(dragonIFS.xList.get(i),2) + Math.pow(dragonIFS.yList.get(i),2));
			xListScale.add(i,dragonIFS.xList.get(i)*50+(width/2));
			yListScale.add(i,-dragonIFS.yList.get(i)*50+height-50);
		}
	}
}
