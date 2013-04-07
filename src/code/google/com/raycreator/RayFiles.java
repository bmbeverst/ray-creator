/**
 * Brooks Beverstock bmb2gf
 * Nov 20, 2011
 * rayFiles.java
 */
package code.google.com.raycreator;

import java.io.File;
import java.io.FileFilter;

/**
 * @author brooks
 * Nov 20, 2011
 */
public class RayFiles implements FileFilter {
	
	/* (non-Javadoc)
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File arg0) {
		return arg0.getName().endsWith(".ray");
	}
	
}
