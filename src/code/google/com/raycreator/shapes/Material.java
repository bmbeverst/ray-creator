/**
 * Brooks Beverstock bmb2gf Nov 21, 2011 Material.java
 */
package code.google.com.raycreator.shapes;

import code.google.com.raycreator.util.ThreeFloat;

/**
 * @author brooks Nov 21, 2011
 */
public class Material implements Shape {
	
	/**
	 * Each material used in the scene is declared by the material directive.
	 * The first material declared will take the interger identifier 0, and
	 * subsequent materials will follow in order (i.e. 1, 2, 3, ...). This
	 * integer handle is used in shape definitions to indicate that the
	 * rendering should occur with the appropriate material. This declaration
	 * must appear before any Group or Shape directives. The colors (er,eg,eb),
	 * (ar,ag,ab), (dr,dg,db), and (sr,sg,sb) are the emmisive, ambient,
	 * diffuse, and specular colors of the material, respectively. The emmisive
	 * material color acts independently of any light source, the ambient
	 * material color is used in ambient light calculation with the global
	 * ambient light color, and the diffuse and specular material colors are
	 * used in diffuse and specular lighting computations with the color of each
	 * light in the scene. The exponent sfo defines the specular 'shininess' (or
	 * 'fall-off') of the material, and takes values from 0.0 to 128.0. The
	 * cosine of the angle between the ray direction and the specular reflection
	 * direction raised to the power of sfo gives the specular highlight factor.
	 * sr, sg, and sb define the reflection coefficient for recursive reflection
	 * rays. The index of refraction is given by ir and is used in Snell's Law
	 * computations for refraction direction. For non-closed surfaces, such as
	 * triangles, it is assumed that ir is the index of refraction on the
	 * backside of the surface. For closed surfaces, such as cones, it is
	 * assumed that ir is the index of refraction on the inside of the surface.
	 * To assign a texture to this material, tn should be the integer handle of
	 * the appropriate texture declared in the file. At rendering time, it is
	 * assumed that the color of the appropriate texture pixel will modulate the
	 * color computed by the lighting equation. If no texture is to be
	 * associated with the material, then a value of -1 must be indicated. The
	 * final parameter is a string delimited by exclaimation marks. The string
	 * must not contain any control characters, or unpredicatable behaviour
	 * could occur. This field is provided for the users to assign material
	 * properties not defined by this file format. For instance, the numeric
	 * constants describing a Perlin Noise 3D solid texture could be put in this
	 * field to instruct the rendered to how to texture shapes which are drawn
	 * with this material. If no unsupported parameters are required, empty
	 * exclamation marks must conclude the material declaration as follows: !!
	 */
	private final String material = "#material\n";
	
	private ThreeFloat emmisive, ambient, diffuse, specular, transparent;
	private float sfo, ir, tn;
	
	/**
	 * @param emmisive
	 * @param ambient
	 * @param diffuse
	 * @param specular
	 * @param sfo
	 * @param transparent
	 * @param ir
	 * @param tn
	 */
	public Material(ThreeFloat emmisive, ThreeFloat ambient,
			ThreeFloat diffuse, ThreeFloat specular, float sfo,
			ThreeFloat transparent, float ir, float tn) {
		this.emmisive = emmisive;
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.transparent = transparent;
		this.sfo = sfo;
		this.ir = ir;
		this.tn = tn;
	}
	
	/**
	 * @param emmisiveR
	 * @param emmisiveG
	 * @param emmisiveB
	 * @param ambientR
	 * @param ambientG
	 * @param ambientB
	 * @param diffuseR
	 * @param diffuseG
	 * @param diffuseB
	 * @param specularR
	 * @param specularG
	 * @param specularB
	 * @param sfo
	 * @param ir
	 * @param tn
	 */
	public Material(float emmisiveR, float emmisiveG, float emmisiveB,
			float ambientR, float ambientG, float ambientB, float diffuseR,
			float diffuseG, float diffuseB, float specularR, float specularG,
			float specularB, float sfo, float ir, float tn) {
		//TODO
	}
	
	/*
	 * (non-Javadoc)
	 * @see code.google.com.RayGen.shapes.Shape#getRayRep()
	 */
	@Override
	public String getRayFileData() {
		return "\n" + material + emmisive.getRayFileData() + ambient.getRayFileData()
				+ diffuse.getRayFileData() + specular.getRayFileData() + sfo
				+ transparent.getRayFileData() + ir + tn + "!!\n";
	}
	
}
