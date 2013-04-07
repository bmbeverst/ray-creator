/**
 * Brooks Beverstock bmb2gf
 * Nov 21, 2011
 * DirectionalLight.java
 */
package code.google.com.raycreator.lights;

import code.google.com.raycreator.util.ThreeFloat;

/**
 * @author brooks
 * Nov 21, 2011
 */
public class DirectionalLight implements Light {
	
	
	/**
	 * This defines a directional light in the scene. (r,g,b) gives the color of
	 * the light. In this ray tracer, use this single light color for diffuse
	 * and specular contributions at each surface. Do not compute ambient
	 * contributions from each light, but rather use the global ambient light
	 * defined by the ambient directive. Each instance must appear before any
	 * Group or Shape directives. (dx,dy,dz) is a vector giving the direction of
	 * the light in the scene. Note that attenuation makes no sense for
	 * directional lights, and so is not a parameter of this directive.
	 */
	private final String light_dir = "#light_dir\n";
	private ThreeFloat color, direction;
	
	
	/**
	 * @param color
	 * @param direction
	 */
	public DirectionalLight(ThreeFloat color, ThreeFloat direction) {
		this.color = color;
		this.direction = direction;
	}
	
	/**
	 * @param r
	 * @param g
	 * @param b
	 * @param x
	 * @param y
	 * @param z
	 */
	public DirectionalLight(float r, float g, float b, float x, float y, float z) {
		this(new ThreeFloat(r, g, b), new ThreeFloat(x, y, z));
	}


	/* (non-Javadoc)
	 * @see code.google.com.RayGen.lights.Light#getRayFile()
	 */
	@Override
	public String getRayFileData() {
		return "\n" + light_dir + color.getRayFileData() + direction.getRayFileData() + "\n";
	}
	
}
