package code.google.com.raycreator.shapes;

import code.google.com.raycreator.util.ThreeFloat;

/**
 * @author brooks
 * Oct 3, 2012
 */
public class Vertex implements Shape {

	/**
	 * This defines a verex at position (px,py,pz) with normal (nx,ny,nz) and
	 * texture coordinate (ts,tt). Each vertex used in the scene is declared by
	 * the vertex directive. The first vertex declared will take the interger
	 * identifier 0, and subsequent vertices will follow in order (i.e. 1, 2, 3,
	 * ...). This integer handle is used in triangle definitions to indicate
	 * which vertices make up the triangle. This declaration must appear before
	 * any Group or Shape directives.
	 */
	private final String vertex = "#vertex\n";

	private ThreeFloat position, normal;
	private float u, v;

	/**
	 * @param position
	 * @param normal
	 * @param u
	 * @param v
	 */
	public Vertex(ThreeFloat position, ThreeFloat normal, float u, float v) {
		super();
		this.position = position;
		this.normal = normal;
		this.u = u;
		this.v = v;
	}

	@Override
	public String getRayFileData() {
		return "\n" + vertex + position.getRayFileData() + normal.getRayFileData() + u
				+ " " + v + "\n";
	}

}
