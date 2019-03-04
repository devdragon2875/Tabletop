import processing.core.PApplet;

public class CardViewer {

	private Card card;
	private PApplet drawer;
	
	public CardViewer(PApplet drawer) {
		card = null;
		this.drawer = drawer;
	}
	
	public CardViewer(Card card, PApplet drawer) {
		this.card = new Card(card);
		this.drawer = drawer;
	}
	
	public void draw() {
		if(card != null) {
			if(card.getIsFaceDown()) {
				drawer.image(card.getDownImg(),drawer.width-Deck.DECK_WIDTH*2,drawer.height/2-Deck.DECK_HEIGHT,Deck.DECK_WIDTH*2,Deck.DECK_HEIGHT*2);
			} else {
				drawer.image(card.getUpImg(),drawer.width-Deck.DECK_WIDTH*2,drawer.height/2-Deck.DECK_HEIGHT,Deck.DECK_WIDTH*2,Deck.DECK_HEIGHT*2);
			}
		}
	}
	
	public void setCard(Card card) {
		this.card = new Card(card);
	}
}
