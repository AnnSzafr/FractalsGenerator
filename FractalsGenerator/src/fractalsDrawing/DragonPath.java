package fractalsDrawing;

/*************************************
 * @author Anna Szafrañska (@AnnSzafr)
 * @created 06 July 2019
 *************************************/
/* 
 * the class build the binary list of dragon path
 * which will represent the direction of building lines
 * 0 - go to the right
 * 1 - go to the left 
 */

import java.util.ArrayList;

public class DragonPath {
	
	ArrayList<Integer> dragonList = new ArrayList<>();
	ArrayList<Integer> dragonListPrevious = new ArrayList<>(); // helping list

	public void buildDragonList(int numberOfDragons) {
		
		dragonList.add(0,1);

		for (int i=0; i<numberOfDragons; i++) {
			// ---- create the dragonListPrevious as the dragonList from early iteration ---- //
			dragonListPrevious.addAll(dragonList);  
			
			dragonList.add(dragonList.size(),1);
			
			for (int j=0; j<dragonListPrevious.size(); j++) {
				if (j == (dragonListPrevious.size()-1)/2) {
					dragonListPrevious.set(j,0); 				
				}
			}
			// ---- adding the size 
			dragonList.addAll(dragonListPrevious);
			dragonListPrevious.clear();
		}
	}
}
