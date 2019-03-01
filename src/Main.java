import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Card> deck = new ArrayList<Card>(30);
		
		
		for(int i = 0; i < 30; i++) {
			deck.add(new Card("Card " + i, 4, 5, true));	
		}
		
		Deck d = new Deck(deck);
		d.shuffle();
		
		for(int i = 0; i < 30; i++) {
			System.out.println(d.getDeck().get(i).getImgpath());	
		}
	}

}
