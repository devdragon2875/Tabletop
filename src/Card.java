import processing.core.PApplet;
import processing.core.PImage;

public class Card {
	private PApplet drawer;
	private String upImg;
	private String downImg;
	private float x;
	private float y;
	private boolean isFaceDown;
	
	public Card(PApplet drawer, String upImg, String downImg, float x, float y, boolean facedown) {
		this.upImg = upImg;
		this.downImg = downImg;
		this.x = x;
		this.y = y;
		isFaceDown = facedown;
		this.drawer = drawer;
	}
	
	
	public void flip() {
		isFaceDown = !isFaceDown;
	}
	
	public void draw() {
		if(isFaceDown) {
			PImage facedown = drawer.loadImage(downImg);
			drawer.image(facedown, x, y, Deck.DECK_WIDTH, Deck.DECK_HEIGHT);
		} else {
			PImage faceup = drawer.loadImage(upImg);
			drawer.image(faceup, x, y, Deck.DECK_WIDTH, Deck.DECK_HEIGHT);
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
