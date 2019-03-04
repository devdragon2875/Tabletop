import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class Deck{
	protected ArrayList<Card> deck = new ArrayList<Card>();
	public static final float DECK_WIDTH= 120;
	public static final float DECK_HEIGHT = 160;
	protected PApplet drawer;
	private boolean isFaceDown;
	private boolean isMovable = false;
	protected float x;
	protected float y;
	
	public boolean isMovable() {
		return isMovable;
	}

	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

	public Deck(PApplet drawer, ArrayList<Card> deck, float x, float y, boolean isFaceDown) {
		this.x = x;
		this.y = y;
		this.drawer = drawer;
		if(deck != null) {
			this.deck.addAll(deck);
		}
		this.isFaceDown= isFaceDown;
		
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
		System.out.println("shuffled");
	}
	
	public Card move() {
		System.out.println("moved");
		if(deck.size()==0) {
			return null;
		}
		return deck.remove(deck.size());
	}
	public boolean hasPoint(float pX, float pY) {
		
		return pX >= x && pY >= y && pX<= x+DECK_WIDTH && pY<= y+DECK_HEIGHT;
		
	}
	public void draw() {
		drawer.rect(x, y, DECK_WIDTH, DECK_HEIGHT);
		for(int i = 0; i < deck.size(); i++) {
			deck.get(i).setX(x);
			deck.get(i).setY(y);
		}
		if(deck.size() > 0) {
			deck.get(deck.size()-1).draw();;
		}
	}

	public boolean isFaceDown() {
		return isFaceDown;
	}

	public void setFaceDown(boolean isFaceDown) {
		this.isFaceDown = isFaceDown;
	}

	//Getters and Setters
	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
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
	
	
	
	
}
