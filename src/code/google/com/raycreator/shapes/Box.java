/**
 * Brooks Beverstock bmb2gf
 * Nov 21, 2011
 * Box.java
 */
package code.google.com.raycreator.shapes;

import code.google.com.raycreator.util.ThreeFloat;

/**
 * @author brooks
 * Nov 21, 2011
 */
public class Box implements Shape {
	
	/**
	 * This defines an axis aligned box, centered at the point (cx,cy,cz). The
	 * length of the x, y, and z axis aligned sides is given by lx, ly, and lz,
	 * respectively. The box extends from x=cx-lx/2 to x=cx+lx/2, y=cy-ly/2 to
	 * y=cy+ly/2, and z=cz-lz/2 to z=cz+lz/2.
	 */
	private final String shape_box = "#shape_box\n";
	private ThreeFloat center, lengths;
	
	/**
	 * @param center
	 * @param lengths
	 */
	public Box(ThreeFloat center, ThreeFloat lengths) {
		super();
		this.center = center;
		this.lengths = lengths;
	}
	
	/**
	 * @param x 
	 * @param y 
	 * @param z 
	 * @param lx 
	 * @param ly 
	 * @param lz 
	 */
	public Box(float x, float y, float z, float lx, float ly, float lz) {
		super();
		this.center = new ThreeFloat(x,y,z);
		this.lengths = new ThreeFloat(lx,ly,lz);
	}

	/* (non-Javadoc)
	 * @see code.google.com.RayGen.shapes.Shape#getRayRep()
	 */
	@Override
	public String getRayFileData() {
		return "\n" + shape_box + center.getRayFileData() + lengths.getRayFileData() + "\n";
	}
	
}
