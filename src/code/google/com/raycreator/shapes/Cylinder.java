/**
 * Brooks Beverstock bmb2gf
 * Nov 21, 2011
 * Cylinder.java
 */
package code.google.com.raycreator.shapes;

import code.google.com.raycreator.RayGenMain;
import code.google.com.raycreator.util.ThreeFloat;

/**
 * @author brooks
 * Nov 21, 2011
 */
public class Cylinder implements Shape {
	/**
	 * This defines a cylinder, with a central axis parallel to the y-axis, and
	 * centered at the point (cx,cy,cz). The radius and height are given by r
	 * and h, respectively. The cylinder is a closed surface (i.e. it has end
	 * caps.) The ends lie at y=cy-h/2 and y=cy+h/2.
	 */
	private final String shape_cylinder = "#shape_cylinder ";
	
	private ThreeFloat center;
	private float radius, height;
	
	/**
	 * @param center
	 * @param radius
	 * @param height
	 */
	public Cylinder(ThreeFloat center, float radius, float height) {
		this.center = center;
		this.radius = radius;
		this.height = height;
	}
	
	/**
	 * @param x
	 * @param y
	 * @param z
	 * @param radius
	 * @param height
	 */
	public Cylinder(float x, float y, float z, float radius, float height) {
		this.center = new ThreeFloat(x,y,z);
		this.radius = radius;
		this.height = height;
	}

	/* (non-Javadoc)
	 * @see code.google.com.RayGen.shapes.Shape#getRayRep()
	 */
	@Override
	public String getRayFileData() {
		return "\n" + shape_cylinder + center.getRayFileData() + RayGenMain.indent + radius + " " + height + "\n";
	}
	
}
