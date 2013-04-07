/***
 * Brooks Beverstock bmb2gf Nov 20, 2011 main.java
 */
package code.google.com.raycreator;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import code.google.com.raycreator.lights.DirectionalLight;
import code.google.com.raycreator.lights.Light;
import code.google.com.raycreator.shapes.Material;
import code.google.com.raycreator.shapes.Shape;
import code.google.com.raycreator.util.Camera;
import code.google.com.raycreator.util.ThreeFloat;

/***
 * @author brooks Nov 20, 2011
 */
public class RayGenMain {

	/**
	 * 
	 */
	private static final String[] XYZ_STRING_ARRAY = new String[] { "x", "y", "z" };

	/**
	 * 
	 */
	private static final String[] RGB_STRING_ARRAY = new String[] { "r", "g", "b" };

	public final static String indent = "    ";

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
	private final String background = "#background ";
	/**
	 * Sets the global ambient light level. The first ambient definition is
	 * used, and subsequent instances are ignored. It must appear before any
	 * Group or Shape directives. If not defined, a value of black is assumed.
	 */
	private final String ambient = "#ambient ";
	/**
	 * The total number of lights in the scene is given by this command. This
	 * command must appear before any light_* definitions. It must appear before
	 * any Group or Shape directives. If there are no lights in the scene (only
	 * global ambient light), light_num 0 need not be declared.
	 */
	private final String light_num = "#light_num ";
	/**
	 * The total number of textures used in the scene is given by this command.
	 * The command must appear before any texture AND/OR material definitions.
	 * It must appear before any Group or Shape directives. If there are no
	 * textures used, texture_num 0 need not be declared.
	 */
	private final String texture_num = "#texture_num ";
	/**
	 * Each texture used in the scene is declared by the texture directive using
	 * the filename of the texture. The first texture declared will take the
	 * interger identifier 0, and subsequent textures will follow in order (i.e.
	 * 1, 2, 3, ...). This integer handle is used in material definitions to
	 * indicate that the material includes the respective texture. Textures need
	 * to be declared before materials. This declaration must appear before any
	 * Group or Shape directives.
	 */
	private final String texture = "#texture ";
	/**
	 * The total number of vertices to be used in the scene. The command must
	 * appear before any vertex definitions. This declaration must appear before
	 * any Group or Shape directives.
	 */
	private final String vertex_num = "#vertex_num ";

	/**
	 * The total number of materials used in the scene is given by this command.
	 * The command must appear before any material definitions. This declaration
	 * must appear before any Group or Shape directives.
	 */
	private final String material_num = "#material_num ";

	/**
	 * The total number of .ray files used in the scene is given by this
	 * command. The command must appear before any ray_file definition. This
	 * declration must appear before any Group or Shape directives.
	 */
	private final String ray_file_num = "#ray_file_num  ";
	/**
	 * Each ray_file used in the scene is declared by the ray_file directive.
	 * The first ray_file declared will take the identifier 0, and subsequent
	 * ray_files will follow in order (i.e. 1,2,3, ...). This integer handle is
	 * used in scene-graph definitions to indicate that the scene-graph
	 * specified by the file should occur as a node to the current scene-graph.
	 * This declaration must appear before any Group or Shape directives.
	 */
	private final String ray_file = "#ray_file ";

	/**
	 * This specifies the index of the scene-graph, that is to be added as a
	 * node to the current scene-graph.
	 */
	private final String ray_file_instance = "#ray_file_instance ";

	private HashMap<String, File> files = new HashMap<String, File>();

	private ArrayList<Light> lights = new ArrayList<Light>();

	private HashMap<String, Shape> shapes = new HashMap<String, Shape>();

	private ArrayList<String> rayShapes = new ArrayList<String>();

	private ArrayList<Material> materials = new ArrayList<Material>();

	private Scanner scanIn = new Scanner(System.in);

	private boolean rayFile = false;
	private Camera camera;
	private ThreeFloat backgroundV = new ThreeFloat(0.2f, 0.2f, 0.2f);
	private ThreeFloat ambientV = new ThreeFloat(0.5f, 0.5f, 0.5f);

	/**
	 * @param args
	 *            void
	 */
	public static void main(String[] args) {
		new RayGenMain().start();

	}

	private void start() {
		loadFiles();
		getNewRayFile();
		getLights();
		getMaterial();
		getRayShapes();
		consule();
		try {
			System.out.println("File name? ");
			makeRayFile(scanIn.next());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadFiles() {
		File currentDir = new File(".");
		File[] currentFiles = currentDir.listFiles(new RayFiles());
		for (File temp : currentFiles) {
			System.out.println(temp.getName());
			files.put(temp.getName(), temp);
		}
	}

	private void getNewRayFile() {
		if (getYesNo("New ray file?")) {
			camera = getCamera();

			System.out.println("Default ambient and background? y|n");
			String input = scanIn.next();
			if (!(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"))) {
				System.out.println("background");
				backgroundV = getThreeFloat(RGB_STRING_ARRAY);
				System.out.println("ambient");
				ambientV = getThreeFloat(RGB_STRING_ARRAY);
			} else {
				backgroundV = new ThreeFloat(0.2f, 0.2f, 0.2f);
				ambientV = new ThreeFloat(0.5f, 0.5f, 0.5f);
			}
			rayFile = true;
		}
		System.out.println("Ray file done");
	}

	private Camera getCamera() {
		ThreeFloat position = new ThreeFloat(0, 10, 10);
		ThreeFloat direction = new ThreeFloat(0, -1, -1);
		ThreeFloat up = new ThreeFloat(0, 1, -1);
		float heighAngle = 0.523f;
		if (getYesNo("Default camera?")) {
			System.out.println("position");
			position = getThreeFloat(XYZ_STRING_ARRAY);
			System.out.println("direction");
			direction = getThreeFloat(XYZ_STRING_ARRAY);
			System.out.println("up");
			up = getThreeFloat(XYZ_STRING_ARRAY);
			heighAngle = getFloat("Height Angle");
		}

		System.out.println("Camera done");
		return new Camera(position, direction, up, heighAngle);
	}

	private void getLights() {
		boolean running = true;
		ThreeFloat color, direction;
		if (getYesNo("Default lights?")) {
			lights.add(new DirectionalLight(new ThreeFloat(1, 1, 1),
					new ThreeFloat(-1, -1, 0)));
			running = false;
		}

		while (running) {
			String input = scanIn.next();

			if (input.charAt(0) == 'd' || input.charAt(0) == 'D') {
				System.out.println("Color");
				color = getThreeFloat(RGB_STRING_ARRAY);
				System.out.println("Direction");
				direction = getThreeFloat(XYZ_STRING_ARRAY);
				lights.add(new DirectionalLight(color, direction));
			} else if (input.charAt(0) == 'p' || input.charAt(0) == 'P') {
				System.out.println("Color");
				color = getThreeFloat(RGB_STRING_ARRAY);
				System.out.println("Position");
				direction = getThreeFloat(XYZ_STRING_ARRAY);
				System.out.println("Position");

			} else if (input.charAt(0) == 's' || input.charAt(0) == 'S') {
				System.out.println("Color");
				color = getThreeFloat(RGB_STRING_ARRAY);
				System.out.println("Direction");
				direction = getThreeFloat(XYZ_STRING_ARRAY);
			} else if (input.equalsIgnoreCase("x")
					|| input.equalsIgnoreCase("q")
					|| input.equalsIgnoreCase("quit")
					|| input.equalsIgnoreCase("exit")) {
				running = false;
			}
		}
		System.out.println("Lights done");
	}

	/**
	 * void
	 */
	private void getRayShapes() {

		boolean running = true;
		if (getYesNo("Include shape files?")) {
			for (String temp : files.keySet()) {
				System.out.println(temp.subSequence(0, temp.lastIndexOf('.')));
			}
			System.out.println("Enter x|q|quit|exit to stop: ");
		} else {
			running = false;
		}
		String file;
		System.out.println("Enter shape file to sreach for: ");
		do {
			String input = scanIn.next();
			for (String temp : files.keySet()) {
				if (temp.contains(input)) {
					System.out.println("Added file: " + temp);
					rayShapes.add(temp);
				}
			}

			if (input.equalsIgnoreCase("x") || input.equalsIgnoreCase("q")
					|| input.equalsIgnoreCase("quit")
					|| input.equalsIgnoreCase("exit")) {
				running = false;
			}
		} while (running);

		System.out.println("Shape files done.");
	}

	/**
	 * void
	 */
	private void getMaterial() {

		boolean running = true;
		if (getYesNo("Us default material?")) {
			materials.add(new Material(new ThreeFloat(0), new ThreeFloat(0),
					new ThreeFloat(0.25f), new ThreeFloat(1), 128.0f,
					new ThreeFloat(0.75f), 1.05f, -1));
			running = false;
		}
		String file;
		System.out.println("Enter shape file to sreach for: ");
		do {
			String input = scanIn.next();
			
			
			if (input.equalsIgnoreCase("x") || input.equalsIgnoreCase("q")
					|| input.equalsIgnoreCase("quit")
					|| input.equalsIgnoreCase("exit")) {
				running = false;
			}
		} while (running);

		System.out.println("Shape files done.");
	}

	/**
	 * void
	 */
	private void consule() {

		boolean running = true;

		while (running) {

			String input = scanIn.next();
			if (input.equalsIgnoreCase("x") || input.equalsIgnoreCase("q")
					|| input.equalsIgnoreCase("quit")
					|| input.equalsIgnoreCase("exit")) {
				running = false;
			}
		}
		System.out.println("Consule done");
	}

	private void makeRayFile(String name) throws IOException {

		FileWriter fstream = new FileWriter(name);
		BufferedWriter out = new BufferedWriter(fstream);
		if (rayFile) {
			out.write(camera.getRayFileData());
			out.write(background + backgroundV.getRayFileData());
			out.write(ambient + ambientV.getRayFileData());
			out.write(light_num + lights.size() + "\n");
			for (Light temp : lights) {
				out.write(temp.getRayFileData());
			}
		}
		out.write(material_num + materials.size() + "\n");
		for (Material temp : materials) {
			out.write(temp.getRayFileData());
		}
		out.write(ray_file_num + rayShapes.size() + "\n");
		for (String temp : rayShapes) {
			out.write(ray_file + temp + "\n");
		}
		for (int index = 0; index < rayShapes.size(); index++) {
			out.write(ray_file_instance + index + "\n");
		}
		out.write("\n");
		// Close the output stream
		out.close();

	}

	private void getMaterial(Color emmisive, Color ambient, Color diffuse,
			Color specular) {

	}

	private ThreeFloat getThreeFloat(String[] values) {

		boolean falseInput = true;
		float[] input = new float[3];

		for (int index = 0; index < values.length; index++) {
			System.out.println("Enter value for " + values[index] + " : ");
			do {
				if (scanIn.hasNextFloat()) {
					input[index] = scanIn.nextFloat();
					falseInput = false;
				} else {
					System.out.println("That is not a float");
					scanIn.next();
				}

			} while (falseInput);
		}

		return new ThreeFloat(input[0], input[1], input[2]);
	}

	private float getFloat(String values) {

		boolean falseInput = true;
		float input = 0;

		System.out.println("Enter value for " + values + " : ");
		do {
			if (scanIn.hasNextFloat()) {
				input = scanIn.nextFloat();
				System.out.println(input);
				falseInput = false;
			} else {
				System.out.println("That is not a float");
				scanIn.next();
			}

		} while (falseInput);

		return input;
	}

	private boolean getYesNo(String values) {

		boolean returned = false;
		String input;

		System.out.print(values + "y|n: ");

		input = scanIn.next();
		if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")
				|| input.contains("y") || input.contains("Y")) {
			returned = true;
		}
		return returned;
	}
}
