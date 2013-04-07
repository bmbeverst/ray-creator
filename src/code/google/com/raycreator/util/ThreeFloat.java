/**
 * Brooks Beverstock bmb2gf
 * Nov 20, 2011
 * ThreeF.java
 */
package code.google.com.raycreator.util;

import code.google.com.raycreator.RayGenMain;
import code.google.com.raycreator.shapes.Shape;

/**
 * @author brooks
 * Nov 20, 2011
 */
public class ThreeFloat implements Shape{
	
	float a,b,c;
	
	
	
	/**
	 * @param a
	 * @param b
	 * @param c
	 */
	public ThreeFloat(float a, float b, float c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * @param i
	 */
	public ThreeFloat(float i) {
		this.a = this.b = this.c = i;
	}

	/* (non-Javadoc)
	 * @see code.google.com.RayGen.Shape#getRayFile()
	 */
	@Override
	public String getRayFileData() {
		return RayGenMain.indent + a + " " + b + " " + c + "\n";
	}
	
}
