import processing.core.PApplet;

public class Region {
	private PApplet drawer;
	private float x;
	private float y;
	private float height = 120;
	private float width = 90;
	
	public Region(PApplet drawer, float x, float y, float width, float height) {
		this.drawer = drawer;
		this.x = x;
		this.y = y;
		
		if(width > 90) {
			this.width = width;
		}
		if(height > 120) {
			this.height = height;
		}
	}
	
	public void draw() {
		drawer.rect(x, y, width, height);
	}

}
