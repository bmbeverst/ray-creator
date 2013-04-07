package code.google.com.raycreator.util;

import code.google.com.raycreator.RayGenMain;
import code.google.com.raycreator.shapes.Shape;

/**
 * @author brooks
 * Oct 3, 2012
 */
public class Matrix implements Shape {
	
	private float[] matrix = new float[16];
	
	@Override
	public String getRayFileData() {
		String returned = "\n";
		for(int index = 0; index < 16; index++) {
			if(index%4 == 0) {
				returned += "\n" + RayGenMain.indent;
			}
			returned += matrix[index] + " ";
		}
		return returned;
	}

}
