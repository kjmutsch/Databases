import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The SaveButton class saves the layout of a dorm when the "Save" button is pressed
 * by writing in a file called RoomData.ddd
 */
public class SaveButton extends Button implements DormGUI {
	private PrintWriter writer;

	/**
	 * The SaveButton constructor initializes variables needed
	 */
	public SaveButton(float x, float y, PApplet processing) {
		super(x,y,processing);
		super.label = "Save Room";
		this.writer = null;
	}

	/**
	 * If the button is clicked, saves the types, positions, orientations of 
	 * all of the furniture inside the dorm
	 * @return 
	 * @Override
	 */
	public void mouseDown(Furniture[] furniture) {
		if(isMouseOver()) {
			try {
				writer = new PrintWriter("RoomData.ddd");
				for (int i = 0; i < furniture.length; i++) {
					if (furniture[i] != null) {
						writer.write(furniture[i].furnitureType() + ":" + furniture[i].xPosition() + "," + furniture[i].yPosition()
								+ "," + furniture[i].numRotations() + "\n");
					}
				}
				writer.close();
			} catch (FileNotFoundException e) {
				System.out.print("WARNING: Could not save room contents to file RoomData.ddd.");
			}
			return;
		}
	}	
}
