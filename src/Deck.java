import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deck = new ArrayList<Card>();
	public Deck(ArrayList<Card> deck) {
		this.deck.addAll(deck);
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public Card draw() {
		return deck.remove(deck.size());
	}

	//Getters and Setters
	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	
	
	
	
}
