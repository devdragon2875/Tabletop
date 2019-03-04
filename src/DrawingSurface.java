import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class DrawingSurface extends PApplet {
	//Client c;
	
	float prevX;
	float prevY;
	public static final int NONE = 0;
	public static final int CARD = 1;
	public static final int DECK = 2;
	int lastClickedType = NONE;
	int lastIndex;
	Item item;
	Menu menu;
	Hand hand;
	// MyThread thread = new MyThread();
	ArrayList<Deck> newDecks = new ArrayList<Deck>();
	ArrayList<Deck> decks = new ArrayList<Deck>();
	ArrayList<Card> cards = new ArrayList<Card>();
	
	CardViewer cardViewer;

	public void settings() {
		//size(1600, 1040);
		fullScreen();
	}

	public void setup() {
		noStroke();
		//String[] fontList = PFont.list();
		//printArray(fontList);
		hand = new Hand(this, null, width/2-Deck.DECK_WIDTH*5, height-Deck.DECK_HEIGHT);
		
		cardViewer = new CardViewer(this);
		//c = new Client("127.0.0.1", 4444);
		//c.connect();

	}

	public void draw() {
		background(0, 180, 0);
		for (int i = 0; i < decks.size(); i++) {
			if (decks.get(i).isMovable()) {
				decks.get(i).setX(mouseX - Deck.DECK_WIDTH / 2);
				decks.get(i).setY(mouseY - Deck.DECK_HEIGHT / 2);
			}
			decks.get(i).draw();
		}
		hand.draw();
		for (Card c : cards) {
			c.draw();
		}
		
		if (menu != null) {
			menu.draw();
		}
		
		//CardViewer Stuff
		cardViewer.draw();
		for(int i = cards.size()-1; i >=0; i--) {
			Card c = cards.get(i);
			if(c.hasPoint(mouseX, mouseY)) {
				cardViewer.setCard(c);
				break;
			}
			cardViewer.setCard(null);
		}
		
		
		/*String serializedObject = c.read();
		if (serializedObject != null) {
			 deserialize the object
			
			
		}
		if (newDecks != null) {
			for (int i = 0; i < newDecks.size(); i++) {
				if (newDecks.get(i).isMovable()) {
					newDecks.get(i).setX(mouseX - Deck.DECK_WIDTH / 2);
					newDecks.get(i).setY(mouseY - Deck.DECK_HEIGHT / 2);
				}
				newDecks.get(i).draw();

			}
		}*/
	}

	public void mousePressed() {

		prevX = mouseX;
		prevY = mouseY;
		if (mouseButton == LEFT) {
			for (int i = cards.size() - 1; i >= 0; i--) {
				if (lastClickedType == NONE  && cards.get(i).hasPoint(mouseX, mouseY)) {
					item = cards.get(i);
					lastClickedType = CARD;
					lastIndex = i;
				}
			}
			for (int i = hand.getDeck().size() - 1; i >= 0; i--) {
				if (lastClickedType == NONE  && hand.getDeck().get(i).hasPoint(mouseX, mouseY)) {
					cards.add(hand.getDeck().remove(i));
					item = cards.get(cards.size()-1);
					lastClickedType = CARD;
					lastIndex = cards.size()-1;
				}
			}
			for (int i = decks.size() - 1; i >= 0; i--) {
				if (lastClickedType == NONE && menu == null && decks.get(i).hasPoint(mouseX, mouseY)
						&& decks.get(i).getDeck().size() > 0) {
					cards.add(decks.get(i).getDeck().remove(decks.get(i).getDeck().size() - 1));
					item = cards.get(cards.size() - 1);
					lastIndex = cards.size() - 1;
					lastClickedType = CARD;
				}
				if (lastClickedType == DECK && decks.get(i).isMovable()) {
					decks.get(i).setX(mouseX - Deck.DECK_WIDTH / 2);
					decks.get(i).setY(mouseY - Deck.DECK_HEIGHT / 2);
					decks.get(i).setMovable(false);
					decks.add(decks.remove(i));

					

					
						// serialize the object
					//String serializedObject = "D";

					//c.write(serializedObject);
					
					
					lastClickedType = NONE;
				}

			}
			
			

			if (menu != null) {
				if (menu.getType() == Menu.MENU_DECK) {
					if (menu.clicked(mouseX, mouseY) == 1) {
						menu.getDeck().shuffle();
						menu = null;
					} else if (menu.clicked(mouseX, mouseY) == 2) {
						menu.getDeck().setFaceDown(!menu.getDeck().isFaceDown());
						for(int i = 0; i < menu.getDeck().getDeck().size(); i++) {
							menu.getDeck().getDeck().get(i).flip();
						}
						menu = null;
					} else if (menu.clicked(mouseX, mouseY) == 3) {
						menu.getDeck().setMovable(true);
						lastClickedType = DECK;
						menu = null;
					} else {
						menu = null;
					}
				} else if (menu.getType() == Menu.MENU_GENERAL) {
					if (menu.clicked(mouseX, mouseY) == 1) {
						decks.add(new Deck(this, null, menu.getX() + Menu.MENU_WIDTH / 2 - Deck.DECK_WIDTH / 2,
								menu.getY() + Menu.MENU_HEIGHT - Deck.DECK_HEIGHT / 2, true));
						for (int j = 0; j < decks.size(); j++) {// serialize the object
							//String serializedObject = "";

							//c.write(serializedObject);
						}
						menu = null;
					} else if (menu.clicked(mouseX, mouseY) == 2) {
						cards.add(new Card(this, "faceup.png", "facedown.png",
								menu.getX() + Menu.MENU_WIDTH / 2 - Deck.DECK_WIDTH / 2,
								menu.getY() + Menu.MENU_HEIGHT - Deck.DECK_HEIGHT / 2, false));
						menu = null;
					} else if (menu.clicked(mouseX, mouseY) == 3) {
						selectFolder("Select a Folder to import as a Deck:", "folderSelected");
					} else {
						menu = null;
					}

				} else if (menu.getType() == Menu.MENU_CARD) {
					if (menu.clicked(mouseX, mouseY) == 1) {
						menu.getCard().flip();
						menu = null;
					} else if (menu.clicked(mouseX, mouseY) == 2) {
						
						menu = null;
					} else if (menu.clicked(mouseX, mouseY) == 3) {
						
						menu = null;
					} else {
						menu = null;
					}
				}

			} 
		} else if (mouseButton == RIGHT) {
			menu = new Menu(this, mouseX, mouseY, Menu.MENU_GENERAL, (Deck)null);
			for (int i = decks.size() - 1; i >= 0; i--) {
				if (decks.get(i).hasPoint(mouseX, mouseY)) {
					menu = new Menu(this, mouseX, mouseY, Menu.MENU_DECK, decks.get(i));
				}
			}
			for (int i = cards.size() - 1; i >= 0; i--) {
				if (cards.get(i).hasPoint(mouseX, mouseY)) {
					menu = new Menu(this, mouseX, mouseY, Menu.MENU_CARD, cards.get(i));
				}
			}

		}

	}

	public void mouseDragged() {
		float xShift = mouseX - prevX;
		float yShift = mouseY - prevY;
		if (lastClickedType != NONE && lastClickedType != DECK) {
			item.setX(item.getX() + xShift);
			item.setY(item.getY() + yShift);
		}
		prevX = mouseX;
		prevY = mouseY;

	}

	public void mouseReleased() {
		if (lastClickedType == CARD) {
			boolean satisfied = false;
			for (int i = decks.size() - 1; i >= 0; i--) {
				if (!satisfied && decks.get(i).hasPoint(mouseX, mouseY)) {
					if (decks.get(i).isFaceDown() != cards.get(lastIndex).getIsFaceDown()) {
						cards.get(lastIndex).flip();
					}
					decks.get(i).getDeck().add(cards.remove(lastIndex));
					satisfied = true;
					cardViewer.setCard(null);
				}
			}
			if (!satisfied && hand.hasPoint(mouseX, mouseY)) {
				if (hand.isFaceDown() != cards.get(lastIndex).getIsFaceDown()) {
					cards.get(lastIndex).flip();
				}
				hand.getDeck().add(cards.remove(lastIndex));
				satisfied = true;
				cardViewer.setCard(null);
			}
			//if(!satisfied) {
				//cards.add(cards.remove(lastIndex));
			//}
			lastClickedType = NONE;
		}

	}

	public void folderSelected(File selection) {
		if (selection == null) {
			println("Window was closed or the user hit cancel.");
		} else {
			println("User selected " + selection.getAbsolutePath());
			File folder = new File(selection.getAbsolutePath());
			File[] listOfFiles = folder.listFiles();
			Deck deck = new Deck(this, null, menu.getX() + Menu.MENU_WIDTH / 2 - Deck.DECK_WIDTH / 2,
					menu.getY() + Menu.MENU_HEIGHT - Deck.DECK_HEIGHT / 2, false);
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println("File " + listOfFiles[i].getName());
					Card card = new Card(this, listOfFiles[i].getAbsolutePath(), "facedown.png",
							menu.getX() + Menu.MENU_WIDTH / 2 - Deck.DECK_WIDTH / 2,
							menu.getY() + Menu.MENU_HEIGHT - Deck.DECK_HEIGHT / 2, false);
					deck.getDeck().add(card);
				}
				// else if (listOfFiles[i].isDirectory()) {
				// System.out.println("Directory " + listOfFiles[i].getName());
				// }
			}
			decks.add(deck);
		}
		menu = null;
	}

}
