/**
 * Brooks Beverstock bmb2gf
 * Nov 21, 2011
 * Triangle.java
 */
package code.google.com.raycreator.shapes;

/**
 * @author brooks Nov 21, 2011
 */
public class Triangle implements Shape {

	/** This defines a triangle with vertices indexed by i1, i2, and i3. */
	private final String shape_triangle = "#shape_triangle ";

	private Vertex i1, i2, i3;

	/**
	 * @param i1
	 * @param i2
	 * @param i3
	 */
	public Triangle(Vertex i1, Vertex i2, Vertex i3) {
		this.i1 = i1;
		this.i2 = i2;
		this.i3 = i3;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see code.google.com.RayGen.shapes.Shape#getRayRep()
	 */
	@Override
	public String getRayFileData() {
		return "\n" + shape_triangle + i1.getRayFileData() + i2.getRayFileData()
				+ i3.getRayFileData() + "\n";
	}

}
