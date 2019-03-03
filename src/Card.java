import processing.core.PApplet;
import processing.core.PImage;

public class Card extends Item {
	private PApplet drawer;
	private PImage upImg;
	private PImage downImg;
	
	private boolean isFaceDown;
	
	public Card(PApplet drawer, String upImg, String downImg, float x, float y, boolean facedown) {
		super(x, y);
		this.upImg = drawer.loadImage(upImg);
		this.downImg = drawer.loadImage(downImg);
		
		isFaceDown = facedown;
		this.drawer = drawer;
	}
	
	
	public void flip() {
		isFaceDown = !isFaceDown;
	}
	public boolean hasPoint(float pX, float pY) {
		
		return pX >= x && pY >= y && pX<= x+Deck.DECK_WIDTH && pY<= y+Deck.DECK_HEIGHT;
		
	}
	
	public void draw() {
		if(isFaceDown) {
			drawer.image(downImg, x, y, Deck.DECK_WIDTH, Deck.DECK_HEIGHT);
		} else {
			drawer.image(upImg, x, y, Deck.DECK_WIDTH, Deck.DECK_HEIGHT);
		}
	}
	//Getters and Setters
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public boolean getIsFaceDown() {
		return isFaceDown;
	}
	public void setIsFaceDown(boolean facedown) {
		isFaceDown = facedown;
	}

}
