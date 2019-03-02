import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card>();
	private float x;
	private float y;
	public static final float DECK_WIDTH= 120;
	public static final float DECK_HEIGHT = 160;
	PApplet drawer;
	
	public Deck(PApplet drawer, ArrayList<Card> deck, float x, float y) {
		this.drawer = drawer;
		if(deck != null) {
			this.deck.addAll(deck);
		}
		this.x = x;
		this.y = y;
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
		System.out.println("shuffled");
	}
	
	public Card move() {
		System.out.println("moved");
		return deck.remove(deck.size());
	}
	public boolean hasPoint(float pX, float pY) {
		
		return pX >= x && pY >= y && pX<= pX+DECK_WIDTH && pY<= pY+DECK_HEIGHT;
		
	}
	public void draw() {
		drawer.rect(x, y, DECK_WIDTH, DECK_HEIGHT);
	}

	//Getters and Setters
	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	
	
	
	
}
