package fractalsDrawing;

public class AddSystems {
	
	Double[] lista = new Double[2]; 
	
	public Double[] System(Double[] xParam, Double[] yParam, double x0, double y0) {
		
		double x = xParam[0] * x0 + xParam[1] * y0 + xParam[2];
		double y = yParam[0] * x0 + yParam[1] * y0 + yParam[2];
		lista[0] = x;
		lista[1] = y;
		
		return lista;
	}	
}