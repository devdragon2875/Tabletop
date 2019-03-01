
public class Card {
	private String imgpath;
	private float x;
	private float y;
	private boolean isFaceDown;
	
	public Card(String imgpath, float x, float y, boolean facedown) {
		this.imgpath = imgpath;
		this.x = x;
		this.y = y;
		isFaceDown = facedown;
	}
	
	
	public void flip() {
		isFaceDown = !isFaceDown;
	}
	
	//Getters and Setters
	public String getImgpath() {
		return imgpath;
	}
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
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
