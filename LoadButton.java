import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileInputStream;

/**
 * The LoadButton class creates a button that says "Load Room". When clicked,
 * this button loads a room that was previously saved when "Save Room" was pressed
 * so that the dorm is set up how it was when it was saved.
 */
public class LoadButton extends Button implements DormGUI {
	private File file;
	private String typeFurniture;
	private String xPos;
	private String yPos;
	private Float xFloat;
	private Float yFloat;
	private String rotate;
	private int rotateInt;
	private Scanner sc;

	/**
	 * LoadButton constructor initializes variables utilized in the class
	 */
	public LoadButton(float x, float y, PApplet processing) {
		super(x,y,processing);
		super.label = "Load Room";
		this.file = new File("RoomData.ddd"); //FIXME
	}

	/**
	 *Checks if the mouse is clicked on the "Load Room" button and loads up the last room layout to be saved
	 * @return 
	 * @Override
	 */
	public void mouseDown(Furniture[] furniture) {
		FileInputStream stream;
		try {
			stream = new FileInputStream(file);
			sc = new Scanner(stream);

			if(isMouseOver()) {
				for(int i = 0; i < furniture.length; i++) {
					furniture[i] = null;  
				}
				for(int i = 0; i < furniture.length; i++) {
					if(file.exists()) {
						if(sc.hasNextLine()){
							String line = sc.nextLine();

							if(line.equals(""))
								sc.nextLine();

							String[] split = line.trim().split(":|,*,");

							typeFurniture = split[0];

							xPos = split[1];
							xFloat = Float.parseFloat(xPos);

							yPos = split[2];
							yFloat = Float.parseFloat(yPos);

							rotate = split[3];
							rotateInt = Integer.parseInt(rotate);

							if(typeFurniture.equals("bed") || typeFurniture.equals("sofa") || typeFurniture.equals("desk")
									|| typeFurniture.equals("dresser") || typeFurniture.equals("sink")) {
								furniture[i] = new Furniture(typeFurniture, processing);
								furniture[i].setXPosition(xFloat);
								furniture[i].setYPosition(yFloat);
								furniture[i].setRotations(rotateInt);
							}
							else {
								System.out.print("WARNING: Could not find an image for a furniture object of type: " + typeFurniture);
							}
						}
					}
					else{ System.out.print("WARNING: Could not load room contents from file RoomData.ddd."); }
				}
			}
		}
		catch(FileNotFoundException e){
			System.out.print("WARNING: Could not load room contents from file RoomData.ddd.");
		} finally {
			sc.close();
		}
		return;
	}
}