/**
 * Brooks Beverstock bmb2gf
 * Nov 20, 2011
 * Shape.java
 */
package code.google.com.raycreator.shapes;

/**
 * @author brooks
 * Nov 20, 2011
 */
public interface Shape {
	
	/**
	 * Returns a string that is correctly formated so that the ray tracer can render it.
	 * @return String A formated string from the ray tracer.
	 */
	public String getRayFileData();
	
}
