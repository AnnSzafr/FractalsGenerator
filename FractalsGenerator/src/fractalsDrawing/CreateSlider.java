package fractalsDrawing;

import javafx.scene.control.Slider;

public class CreateSlider extends Slider{
	
	CreateSlider(int minValue, int maxValue, int beginValue, int blockIncrement, float majorTickUnit){
		setMin(minValue);
		setMax(maxValue);
		setValue(beginValue);
		setBlockIncrement(blockIncrement);
		setMajorTickUnit(majorTickUnit);
        
        setShowTickLabels(true);
        setShowTickMarks(true);
	}
}
