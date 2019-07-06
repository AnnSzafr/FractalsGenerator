package fractalsDrawing;

import java.util.ArrayList;

public class DragonPath {
	
	ArrayList<Integer> dragonList = new ArrayList<>();
	ArrayList<Integer> dragonListPrevious = new ArrayList<>(); // helping list

	public void buildDragonList(int n) {
		
		dragonList.add(0,1);

		for (int i=0; i<n; i++) {
			// ---- create the dragonListPrevious as the dragonList from early iteration ---- //
			dragonListPrevious.addAll(dragonList);  
			
			dragonList.add(dragonList.size(),1);
			
			for (int j=0; j<dragonListPrevious.size(); j++) {
				if (j == (dragonListPrevious.size()-1)/2) {
					dragonListPrevious.set(j,0); 				
				}
			}
			dragonList.addAll(dragonListPrevious);
			dragonListPrevious.clear();
		}
	}
}
