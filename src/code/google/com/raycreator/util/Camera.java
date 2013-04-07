/**
 * Brooks Beverstock bmb2gf
 * Nov 20, 2011
 * Camera.java
 */
package code.google.com.raycreator.util;

import code.google.com.raycreator.RayGenMain;
import code.google.com.raycreator.shapes.Shape;

/**
 * @author brooks
 * Nov 20, 2011
 */
public class Camera implements Shape {
	
	
	/**
	 * This defines a perspective camera in the scene. (px,py,pz) is the
	 * position of the camera in world coordinates, (dx,dy,dz) is a vector
	 * describing the direction of the camera, and (ux,uy,uz) is a vector in the
	 * up direction. The height angle of the viewing frustum is given by ha,
	 * such that the width angle can be found by ha*ar, where ar is the
	 * width-over-height aspect ratio of the output image (given on the command
	 * line). The camera command must appear before of any Group or Shape
	 * directives. The first camera found in the file will be used, and
	 * subsequent camera definitions are ignored.
	 */
	private final String camera = "#camera\n";
	

	ThreeFloat position, direction, up;
	float heighAngle;
	
	
	
	/**
	 * @param position
	 * @param direction
	 * @param up
	 * @param hieghAngle
	 */
	public Camera(ThreeFloat position, ThreeFloat direction, ThreeFloat up, float hieghAngle) {
		this.position = position;
		this.direction = direction;
		this.up = up;
		this.heighAngle = hieghAngle;
	}



	/* (non-Javadoc)
	 * @see code.google.com.RayGen.shapes.Shape#getRayFile()
	 */
	@Override
	public String getRayFileData() {
		return "\n" + camera + position.getRayFileData() + direction.getRayFileData() + up.getRayFileData() + RayGenMain.indent + heighAngle + "\n";
	}
	
}
