
public class Button implements DormGUI {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	protected PApplet processing;
	private float[] position;
	protected String label;

	public Button(float x, float y, PApplet processing) {
		this.processing = processing;
		this.position = new float[2];
		this.label = "Button";
		this.position[0] = x;
		this.position[1] = y;
	}
	
	/**
	 * The update() method within the CreateSofaButton class creates the actual rectangle and adjusts colors 
	 * of the rectangles and text written inside the rectangle utilizing the fill() method. 
	 */
	public void update() {
		if(isMouseOver()) {
			processing.fill(100); //dark gray
		} else { 
			processing.fill(200); //gray
		}
		processing.rect(this.position[0] - WIDTH/2, this.position[1] + HEIGHT/2, this.position[0] + WIDTH/2, this.position[1] - HEIGHT/2);
		processing.fill(0); //black
		processing.text(this.label, this.position[0], this.position[1]);
	}
	
	public void mouseDown(Furniture[] furniture) {
		if(isMouseOver()) {
			System.out.print("A button was pressed.");
		}
	}
	
	public void mouseUp() {}
	
	/**
	 * The isMouseOver method detects if the mouse is hovering over a particular button or not
	 */
	public boolean isMouseOver() {
		int mouseX = processing.mouseX;
		int mouseY = processing.mouseY;

		if(this.position != null && mouseX >= (this.position[0] - WIDTH/2) && mouseX <= (this.position[0] + WIDTH/2)
				&& mouseY >= (this.position[1] - HEIGHT/2) && mouseY <= (this.position[1] + HEIGHT/2)) {
			//if statement checks if the mouse is within the parameters of the "Create Sofa" button
			return true;
		}
		return false; }
}