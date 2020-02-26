import java.io.File;

/**
 * Whatever the type of furniture may be, the Furniture class loads up an image
 * of the furniture and creates an array that holds the position
 * and keeps track of whether the furniture is rotated and how many times it has been
 * rotate (orientation).
 */
public class Furniture implements DormGUI {
	private PApplet processing;
	private String furnType;
	private PImage image;
	private float[] position;
	private boolean isDragging;
	private int rotations;

	/**
	 * This Furniture constructor initializes the field of a new bed object that
	 * is positioned in the center of the dorm room display
	 */
	public Furniture(String furnType, PApplet processing) { 
		this.furnType = furnType;
		this.processing = processing;
		this.image = processing.loadImage("images" + File.separatorChar + "" + this.furnType + ".png");
		this.isDragging = false;
		this.rotations = 0;
		this.position = new float[2];
		this.position[0] = processing.width/2;
		this.position[1] = processing.height/2;
	}

	/**
	 * This update method draws a bed at its current position
	 * and orientation
	 */
	public void update() { 
		if(isDragging) {
			this.position[0] = processing.mouseX;
			this.position[1] = processing.mouseY;
		}
		processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
	}

	/**
	 * The mouseDown method begins dragging the bed when the mouse is
	 * hovering over a bed and when the mouse is being pressed
	 */
	public void mouseDown(Furniture[] furniture) {
		this.isDragging = isMouseOver();
	}

	public String furnitureType() {
		return this.furnType;
	}

	public float xPosition() {
		return this.position[0];
	}

	public float yPosition() {
		return this.position[1];
	}

	public int numRotations() {
		return this.rotations;
	}

	public void setFurnType(String furnType) {
		this.furnType = furnType;
	}

	public void setXPosition(Float xPos) {
		this.position[0] = xPos;
	}

	public void setYPosition(Float yPos) {
		this.position[1] = yPos;
	}

	public void setRotations(int rot) {
		this.rotations = rot;
	}

	/**
	 * The mouseUp method indicates that the bed is no longer being dragged
	 */
	public void mouseUp() { 
		this.isDragging = false;
	}

	/**
	 * This isMouseOver method is a helper method that determines whether
	 * the mouse is currently over a certain bed or not
	 */
	public boolean isMouseOver() {
		int mouseX = processing.mouseX;
		int mouseY = processing.mouseY;
		if((rotations % 2) == 0 && position != null && mouseX >= (position[0] - image.width/2) && mouseX <= (position[0] + image.width/2)
				&& mouseY >= (position[1] - image.height/2) && mouseY <= (position[1] + image.height/2)) {
			//if statement checks if the mouse is within the parameters of each bed
			return true;
		}
		if((rotations % 2) != 0 && position != null && mouseX >= (position[0] - image.height/2) && mouseX <= (position[0] + image.height/2)
				&& mouseY >= (position[1] - image.width/2) && mouseY <= (position[1] + image.width/2)) {
			return true;
		}
		return false;
	}

	/**
	 * Each time the rotate() method is called, the rotation variable counts
	 * another rotation, then rotates the image 90 degrees, clockwise
	 */
	public void rotate() {
		this.rotations += 1;
		processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
	}
}
