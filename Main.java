import java.io.File;
import java.util.ArrayList;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P02 Dorm Designer
// Files:           (a list of all source files used by that program)
// Course:          CS 300
//
// Author:          Kiara Mutschler
// Email:           kjmutschler@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   ---
// Lecturer's Name: ---
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __ Write-up states that pair programming is allowed for this assignment.
//   __ We have both read and understand the course Pair Programming Policy.
//   __ We have registered our team prior to the team registration deadline.
//
//////////////////////////////////////////////////////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:    
//					(P2)
//
//					At Wendt tutoring services from a tutor named Ben. He helped me realize
// 					to use data.dragBedIndex for array to make it work for all indexes
//
//					Alex Pletta on Piazza told me how to deal with
//					initializing the bedImage and backgroundImage to data instead of a PImage     
//
// Online Sources:  (identify each URL and describe their assistance in detail)
//
////////////////////////////////////////////////////////////////////////////////

public class Main {
	private PApplet processing;
	private PImage backgroundImage; 
	private CreateFurnitureButton newBed;
	private CreateFurnitureButton newSofa;
	private CreateFurnitureButton newDresser;
	private CreateFurnitureButton newDesk;
	private CreateFurnitureButton newSink;
	private ClearButton newClear;
	private SaveButton newButton;
	private LoadButton newLoad;
	private ArrayList<DormGUI> guiObjects;

	/**
	 * The Main constructor class initializes variables and fills the furniture array
	 * with all null values.
	 */
	public Main(PApplet processing) {
		this.processing = processing;
		this.guiObjects = new ArrayList<DormGUI>();
		newBed = new CreateFurnitureButton("bed", 50, 24, processing);
		newDresser = new CreateFurnitureButton("dresser", 250, 24, processing);
		newDesk = new CreateFurnitureButton("desk", 350, 24, processing);
		newSink = new CreateFurnitureButton("sink", 450, 24, processing);
		newSofa = new CreateFurnitureButton("sofa", 150, 24, processing);
		newButton = new SaveButton(650, 24, processing);
		newLoad = new LoadButton(750, 24, processing);
		newClear = new ClearButton(550, 24, processing);

		guiObjects.add(newBed);
		guiObjects.add(newSofa);
		guiObjects.add(newButton);
		guiObjects.add(newDresser);
		guiObjects.add(newDesk);
		guiObjects.add(newSink);
		guiObjects.add(newLoad);
		guiObjects.add(newClear);
		
		this.backgroundImage = processing.loadImage("images" + File.separatorChar + "background.png");
	}

	/**
	 * In this program, the main uses a program that runs automatically.
	 */
	public static void main(String[] args) {
		Utility.startApplication();
	}

	// Max number of furniture that LoadButton will be allowed to load at once.    
	private final static int MAX_LOAD_FURNITURE = 100;        
	/**
	 * This method creates a new Furniture[] for the old mouseDown() methods
	 * to make use of.  It does so by copying all Furniture references from
	 * this.guiObjects into a temporary array of size MAX_LOAD_FURNITURE.
	 * @return that array of Furniture references.
	 */
	private Furniture[] extractFurnitureFromGUIObjects() {
		Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE];
		int nextFreeIndex = 0;
		for(int i=0;i<guiObjects.size() && nextFreeIndex < furniture.length;i++)
			if(guiObjects.get(i) instanceof Furniture)
				furniture[nextFreeIndex++] = (Furniture)guiObjects.get(i);        
		return furniture;        
	}    
	/**
	 * This method first removes all Furniture references from this.guiObjects,
	 * and then adds back in all of the non-null references from it's parameter.
	 * @param furniture contains the only furniture that will be left in 
	 *   this.guiObjects after this method is invoked (null references ignored).
	 */
	private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
		for (int i = 0;i<guiObjects.size();i++)
			if(guiObjects.get(i) instanceof Furniture)
				guiObjects.remove(i--);
		for (int i=0;i<furniture.length;i++)
			if(furniture[i] != null)
				guiObjects.add(furniture[i]);
	}

	/**
	 * The update in the Main class creates the color of the background as well as the image 
	 * of the dorm. Then it calls the update methods from other classes to detect whether
	 * or not there is intended to be a new bed or sofa, or if a layout should be saved or loaded.
	 */	
	public void update() {
		processing.background(100,150,250);
		processing.image(backgroundImage, processing.width / 2, processing.height / 2);
		for(int i = 0; i < guiObjects.size(); i++) {
			guiObjects.get(i).update();
		}
	}

	/**
	 * The mouseDown() method is automatically called when the user presses their computer mouse down. 
	 * Then, the method checks if the mouse was clicked on a piece of furniture or a button.
	 * 
	 */
	public void mouseDown() {
		Furniture[] furniture = extractFurnitureFromGUIObjects();
		for(int i = 0; i < guiObjects.size(); i++) {
			guiObjects.get(i).mouseDown(furniture);
		}
		replaceFurnitureInGUIObjects(furniture);
	}

	/**
	 * The mouseUp() method updates the position of a particular piece
	 * of furniture within the furniture array if the mouse was clicked
	 * on said piece of furniture.
	 */
	public void mouseUp() {
		for(int i = 0; i < guiObjects.size(); i++) {
			guiObjects.get(i).mouseUp();
		}
	}

	/**
	 * Checks whether the 'D/d' key or 'R/r' key was pressed while over a piece of furniture
	 * and if 'D/d' was pressed, that furniture is deleted. If 'R/r was pressed, that furniture is 
	 * rotated
	 */
	public void keyPressed() {
		for(int i = 0; i < guiObjects.size(); i++) {
			if(guiObjects.get(i) instanceof Furniture) {
				if(guiObjects.get(i).isMouseOver() && (processing.key == 'd' || processing.key == 'D')) {
					guiObjects.remove(i);
					break;
				}
				if(guiObjects.get(i).isMouseOver() && (processing.key == 'r' || processing.key == 'R')) {
					((Furniture) guiObjects.get(i)).rotate(); //FIXME
				}
			}
		}
	}
}
