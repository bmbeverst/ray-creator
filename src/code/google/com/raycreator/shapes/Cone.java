/**
 * Brooks Beverstock bmb2gf
 * Nov 21, 2011
 * Cone.java
 */
package code.google.com.raycreator.shapes;

import code.google.com.raycreator.RayGenMain;
import code.google.com.raycreator.util.ThreeFloat;

/**
 * @author brooks
 * Nov 21, 2011
 */
public class Cone implements Shape {
	/**
	 * This defines a cone, with a central axis parallel to the y-axis, and
	 * centered at the point (cx,cy,cz). The radius and height are given by r
	 * and h, respectively. The cone is a closed surface (i.e. it's base is
	 * capped) The base and apex of the cone lie at y=cy-h/2 and y=cy+h/2,
	 * respectively.
	 */
	private final String shape_cone = "#shape_cone\n";
	
	private ThreeFloat center;
	private float radius, height;
	
	/**
	 * @param center
	 * @param radius
	 * @param height
	 */
	public Cone(ThreeFloat center, float radius, float height) {
		super();
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
	public Cone(float x, float y, float z, float radius, float height) {
		super();
		this.center = new ThreeFloat(x,y,z);
		this.radius = radius;
		this.height = height;
	}

	/* (non-Javadoc)
	 * @see code.google.com.RayGen.shapes.Shape#getRayRep()
	 */
	@Override
	public String getRayFileData() {
		return "\n" + shape_cone + center.getRayFileData() + RayGenMain.indent + radius + " " + height + "\n";
	}
	
}
