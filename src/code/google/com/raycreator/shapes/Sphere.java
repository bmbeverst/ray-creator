/**
 * Brooks Beverstock bmb2gf
 * Nov 20, 2011
 * Sphere.java
 */
package code.google.com.raycreator.shapes;

import code.google.com.raycreator.RayGenMain;
import code.google.com.raycreator.util.ThreeFloat;

/**
 * @author brooks
 * Nov 20, 2011
 */
public class Sphere implements Shape {

	
	/**
	 * This defines a sphere, situated at the point (cx,cy,cz) with a radius
	 * given by r.
	 */
	private final String shape_sphere = "#shape_sphere \n";
	
	private ThreeFloat position;
	private float radius;
	
	/**
	 * @param position
	 * @param radius
	 */
	public Sphere(ThreeFloat position, float radius) {
		this.position = position;
		this.radius = radius;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param radius
	 */
	public Sphere(float x, float y, float z, float radius) {
		this(new ThreeFloat(x,y,z), radius);
	}

	/* (non-Javadoc)
	 * @see code.google.com.RayGen.Shape#getRayFile()
	 */
	@Override
	public String getRayFileData() {
		return "\n" + shape_sphere + position.getRayFileData() + RayGenMain.indent + radius + "\n";
	}

	
}
