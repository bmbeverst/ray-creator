package code.google.com.raycreator.shapes;

import java.util.ArrayList;

import code.google.com.raycreator.util.Matrix;

public class Tranformation implements Shape {

	/**
	 * This pair of directives defines a node that will be added to the current
	 * scene-graph with the specified transformation context. All objects within
	 * the group_begin .. and associated group_end directive are subject to the
	 * 4x4 transformation matrix given in the group_begin declaration. Groups
	 * may be nested, permitting the specification of a transformation
	 * heirarchy. Shapes within nested groups are subject, in order, to the
	 * transformation contexts of all their enclosing groups. The total
	 * transformation context of a given shape is determined, then, by starting
	 * with the matrix of the root enclosing group, and concatenating additional
	 * matrices on the right as we decend into nested groups, until we reach the
	 * shape. The transformation context of a group is applicable only to its
	 * shapes and sub groups, so we must remove matrices from the right as we
	 * ascend back up the heirarchy. The matrix elements appear as follows and
	 * are intended to operate on column vectors: 
	 * |m11 m21 m31 m41|
	 * |m12 m22 m32 m42|
	 * |m13 m23 m33 m43|
	 * |m14 m24 m34 m44| Note: When the .ray file is
	 * initially parsed the root scene-graph node is instantiated with the
	 * identity matrix, so that Shapes specified outside any Group directives
	 * are still valid.
	 */
	private final String group_begin = "#group_begin ";
	private final String group_end = "#group_end ";
	
	private ArrayList<String> shapes = new ArrayList<String>();
	private Matrix transformaton;
	
	@Override
	public String getRayFileData() {
		// TODO Auto-generated method stub
		return null;
	}

}
