
public class CreateFurnitureButton extends Button {
	private String type;
	private char firstLetter;
	private String lastSection;
	private String newType;
	
	public CreateFurnitureButton(String type, float x, float y, PApplet processing) {
		super(x,y,processing);
		this.type = type;
		newType = type.toUpperCase();
		firstLetter = newType.charAt(0);
		lastSection = type.substring(1);
		super.label = "Create " + firstLetter + lastSection;
	}
	
	public void mouseDown(Furniture[] furniture) {
		if(isMouseOver()) {
			for(int i = 0; i < furniture.length; i++) {	//checks for a null place within array to fill a sofa with
				if(furniture[i] == null) {
					furniture[i] = new Furniture(type.toLowerCase(), processing);
					break;
				}
			}
		}
	}
}
