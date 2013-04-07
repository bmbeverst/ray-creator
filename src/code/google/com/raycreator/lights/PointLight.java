/**
 * Brooks Beverstock bmb2gf
 * Nov 21, 2011
 * PointLight.java
 */
package code.google.com.raycreator.lights;

import code.google.com.raycreator.util.ThreeFloat;

/**
 * @author brooks Nov 21, 2011
 */
public class PointLight implements Light {

	/**
	 * This defines a point light in the scene. (r,g,b) gives the color of the
	 * light. In this ray tracer, use this single light color for diffuse and
	 * specular contributions at each surface. Do not compute ambient
	 * contributions from each light, but rather use the global ambient light
	 * defined by the ambient directive. Each instance must appear before any
	 * Group or Shape directives. (px,py,pz) gives the position of the light in
	 * world coordinates. The attenuation of the light with distance from its
	 * position is given by ca, la, and qa which define the constant, linear and
	 * quadratic components of the attenuation factor. If d is the distance from
	 * the light to the surface, then the light's color at the surface is given
	 * by (r,g,b) *1.0/ (ca + la*d + qa*d*d). Each coeficient must be positive
	 * or equal to zero. Note: to achieve no attenuation use a (ca,la,qa) of
	 * (1.0,0.0,0.0).
	 */
	private final String light_point = "#light_point ";

	private ThreeFloat color, position, attenuation;

	/**
	 * @param color
	 * @param position
	 * @param attenuation
	 */
	public PointLight(ThreeFloat color, ThreeFloat position,
			ThreeFloat attenuation) {
		super();
		this.color = color;
		this.position = position;
		this.attenuation = attenuation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see code.google.com.RayGen.lights.Light#getRayRep()
	 */
	@Override
	public String getRayFileData() {
		return "\n" + light_point + color.getRayFileData() + position.getRayFileData()
				+ attenuation.getRayFileData() + "\n";
	}

}
