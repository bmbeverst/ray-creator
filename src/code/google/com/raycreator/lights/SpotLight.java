/**
 * Brooks Beverstock bmb2gf
 * Nov 21, 2011
 * SpotLight.java
 */
package code.google.com.raycreator.lights;

import code.google.com.raycreator.RayGenMain;
import code.google.com.raycreator.util.ThreeFloat;

/**
 * @author brooks Nov 21, 2011
 */
public class SpotLight implements Light {

	/**
	 * This defines a spot point light in the scene. (r,g,b) gives the color of
	 * the light. In this ray tracer, use this single light color for diffuse
	 * and specular contributions at each surface. Do not compute ambient
	 * contributions from each light, but rather use the global ambient light
	 * defined by the ambient directive. Each instance must appear before any
	 * Group or Shape directives. (px,py,pz) gives the position of the light in
	 * world coordinates, and (dx,dy,dz) is a unit vector describing the
	 * direction of the light. The attenuation of the light with distance from
	 * its position is given by ca, la, and qa which define the constant, linear
	 * and quadratic components of the attenuation factor. If d is the distance
	 * from the light to the surface, then the light's color at the surface is
	 * given by (r,g,b) *1.0/ (ca + la*d + qa*d*d). Each coeficient must be
	 * positive or equal to zero. Note: to achieve no attenuation use a
	 * (ca,la,qa) of (1.0,0.0,0.0). The spot light cutoff is given by cs and
	 * defines the half angle of divergence of the light cone. It can be
	 * measured as the angle from the center axis to the edge of the spot cone.
	 * It should be less than pi/2 radians. The fall off in intensity from the
	 * center axis to the cone edge is given by the spot drop-off factor, sd. It
	 * can take values from 0.0 to 128.0, where 0.0 indicated constant intensity
	 * across the cone, and 128.0 yields a sharp fall-off. The cosine of the
	 * angle between light direction and the direction of a ray from (px,py,pz)
	 * to the point being lit, raised to the power of sd will yield the correct
	 * result.
	 */
	private final String light_spot = "#light_spot ";

	private ThreeFloat color, position, direction, attenuation;
	private float cutoff, dropoff;

	/**
	 * @param color
	 * @param position
	 * @param direction
	 * @param attenuation
	 * @param cutoff
	 * @param dropoff
	 */
	public SpotLight(ThreeFloat color, ThreeFloat position,
			ThreeFloat direction, ThreeFloat attenuation, float cutoff,
			float dropoff) {
		super();
		this.color = color;
		this.position = position;
		this.direction = direction;
		this.attenuation = attenuation;
		this.cutoff = cutoff;
		this.dropoff = dropoff;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see code.google.com.RayGen.lights.Light#getRayRep()
	 */
	@Override
	public String getRayFileData() {
		return "\n" + light_spot + color.getRayFileData() + position.getRayFileData()
				+ direction.getRayFileData() + attenuation.getRayFileData()
				+ RayGenMain.indent + cutoff + " "	+ dropoff + "\n";
	}

}
