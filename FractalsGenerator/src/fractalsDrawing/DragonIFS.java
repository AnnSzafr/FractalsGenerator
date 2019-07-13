package fractalsDrawing;

import java.util.ArrayList;
import java.util.Random;

public class DragonIFS {
	ArrayList<Double> xList = new ArrayList<>();
	ArrayList<Double> yList = new ArrayList<>();

	public void buildDragonIFSList(int n,Double[] xParam1,Double[] yParam1,Double[] xParam2,Double[] yParam2){
		
		AddSystems addSystems = new AddSystems();
		
		double x0 = 0, y0 = 0;
		xList.add(x0);
		yList.add(y0);

		for (int i=1; i<=n; i++) {
			int nE = numberEquation();
			
			if (nE == 1) 
				addSystems.System(xParam1,yParam1, x0, y0);
			else 
				addSystems.System(xParam2,yParam2, x0, y0);
			
			x0 = addSystems.lista[0];
			y0 = addSystems.lista[1];
			xList.add(x0);
			yList.add(y0);
		}
	}

	private Double probabilityNumber;
	
	public void setProbabilityNumber(Double probabilityNumber) {
		//System.out.println("set: "+probabilityNumber);
		this.probabilityNumber = probabilityNumber;
		//numberEquation();
		//buildDragonIFSList();
	}

	private Integer numberEquation() {
		//System.out.println("D: "+probabilityNumber);
		Random rand = new Random();
		double randValue = 100*rand.nextDouble();
		if (randValue <= probabilityNumber*100) 
			return 2;
		return 1;
	
	}

}