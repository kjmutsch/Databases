
public class ClearButton extends Button {
	public ClearButton(float x, float y, PApplet processing) {
		super(x,y,processing);
		super.label = "Clear Room";
	}
	
	@Override
	public void mouseDown(Furniture[] furniture) {
		if(isMouseOver()) {
			for(int i = 0; i < furniture.length; i++) {
				furniture[i] = null;
			}
		}
	}
}
