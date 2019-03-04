import java.util.ArrayList;

import processing.core.PApplet;

public class Hand extends Deck {

	public Hand(PApplet drawer, ArrayList<Card> deck, float x, float y) {
		super(drawer, deck, x, y, false);
	}

	public boolean hasPoint(float pX, float pY) {
		
		return pX >= x && pY >= y && pX<= x+DECK_WIDTH*10 && pY<= y+DECK_HEIGHT;
		
	}
	@Override
	public void draw() {
		rearrange();
		
		drawer.fill(255, 0, 255);
		drawer.rect(x, y, DECK_WIDTH*10, DECK_HEIGHT);
		for(int i = 0; i < deck.size(); i++) {
			deck.get(i).draw();
		}
	}
	
	// sets position of cards. Please call every time amount/position of cards in hand changes.
	public void rearrange() {
		if (deck.size()>=10) {
			float shift = 0;
			for (int i = 0; i < deck.size(); i++) {
				deck.get(i).setX(x + shift);
				deck.get(i).setY(y);
				shift += (float) (10.0 * Deck.DECK_WIDTH / deck.size());
			}
		} else if(deck.size()>0) {
			/* old way of displaying cards
			float shift = Deck.DECK_WIDTH*5 - Deck.DECK_WIDTH/2;
			for (int i = 0; i < deck.size(); i++) {
				deck.get(i).setX(x + shift);
				deck.get(i).setY(y);
				deck.get(i).draw();
				
				shift -= Deck.DECK_WIDTH/2;
				
			}
			*/
			
			float offset = ((10-deck.size())*DECK_WIDTH)/2.0f;
			for(int i = 0; i < deck.size(); i++) {
				deck.get(i).setX(x + offset);
				deck.get(i).setY(y);
				offset += Deck.DECK_WIDTH;
			}

		}
	}

}
