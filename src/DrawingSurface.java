import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {
	float prevX;
	float prevY;
	public static final int NONE = 0;
	public static final int CARD = 1;
	public static final int DECK = 2;
	int lastClickedType = NONE;
	int lastIndex;
	Item item;
	Menu menu;
	ArrayList<Deck> decks = new ArrayList<Deck>();
	ArrayList<Card> cards = new ArrayList<Card>();

	public void settings() {
		size(1600, 1000);
	}

	public void setup() {

	}

	public void draw() {
		background(0, 0, 0);
		for (int i = 0; i < decks.size(); i++) {
			decks.get(i).draw();
		}
		for (Card c : cards) {
			c.draw();
		}
		if (menu != null) {
			menu.draw();
		}
	}

	public void mousePressed() {
		prevX = mouseX;
		prevY = mouseY;
		if (mouseButton == LEFT) {
			for (int i = cards.size() - 1; i >= 0; i--) {
				if (lastClickedType == NONE && cards.get(i).hasPoint(mouseX, mouseY)) {
					item = cards.get(i);
					lastClickedType = CARD;
					lastIndex = i;
				}
			}
			for (int i = decks.size() - 1; i >= 0; i--) {
				if (lastClickedType == NONE && decks.get(i).hasPoint(mouseX, mouseY)) {
					item = decks.get(i);
					lastClickedType = DECK;
					lastIndex = i;
				}
			}

			if (menu != null) {
				if (menu.getType() == Menu.MENU_DECK) {
					if (menu.clicked(mouseX, mouseY) == 1) {
						menu.getDeck().shuffle();
						menu = null;
					} else if (menu.clicked(mouseX, mouseY) == 2) {
						menu.getDeck().move();
						menu = null;
					} else if (menu.clicked(mouseX, mouseY) == 3) {
						menu = null;
					}
				} else if (menu.getType() == Menu.MENU_GENERAL) {
					if (menu.clicked(mouseX, mouseY) == 1) {
						decks.add(new Deck(this, null, menu.getX() + Menu.MENU_WIDTH / 2 - Deck.DECK_WIDTH / 2,
								menu.getY() + Menu.MENU_HEIGHT - Deck.DECK_HEIGHT / 2, true));
						menu = null;
					} else if (menu.clicked(mouseX, mouseY) == 2) {
						cards.add(new Card(this, "faceup.png", "facedown.png",
								menu.getX() + Menu.MENU_WIDTH / 2 - Deck.DECK_WIDTH / 2,
								menu.getY() + Menu.MENU_HEIGHT - Deck.DECK_HEIGHT / 2, false));
						menu = null;
					} else if (menu.clicked(mouseX, mouseY) == 3) {
						selectFolder("Select a Folder to import as a Deck:", "folderSelected");
					}

				}
				
				
			}

		} else if (mouseButton == RIGHT) {
			menu = new Menu(this, mouseX, mouseY, Menu.MENU_GENERAL, null);
			for (int i = decks.size() - 1; i >= 0; i--) {
				if (decks.get(i).hasPoint(mouseX, mouseY)) {
					menu = new Menu(this, mouseX, mouseY, Menu.MENU_DECK, decks.get(i));
				}
			}

		}

	}

	public void mouseDragged() {
		float xShift = mouseX - prevX;
		float yShift = mouseY - prevY;
		if (lastClickedType != NONE) {
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
					if (decks.get(i).isFaceDown() && !cards.get(lastIndex).getIsFaceDown()) {
						cards.get(lastIndex).flip();
					}
					decks.get(i).getDeck().add(cards.remove(lastIndex));
					satisfied = true;

				}
			}
		} else if (lastClickedType == DECK) {
			decks.add(decks.remove(lastIndex));
		}
		lastClickedType = NONE;
	}

	public void folderSelected(File selection) {
		if (selection == null) {
			println("Window was closed or the user hit cancel.");
		} else {
			println("User selected " + selection.getAbsolutePath());
			File folder = new File(selection.getAbsolutePath());
			File[] listOfFiles = folder.listFiles();
			Deck deck = new Deck(this, null, menu.getX() + Menu.MENU_WIDTH / 2 - Deck.DECK_WIDTH / 2,
					menu.getY() + Menu.MENU_HEIGHT - Deck.DECK_HEIGHT / 2, true);
			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
			    System.out.println("File " + listOfFiles[i].getName());
			    Card card = new Card(this, listOfFiles[i].getAbsolutePath(), "facedown.png",
						menu.getX() + Menu.MENU_WIDTH / 2 - Deck.DECK_WIDTH / 2,
						menu.getY() + Menu.MENU_HEIGHT - Deck.DECK_HEIGHT / 2, false);
			    deck.getDeck().add(card);
			  } 
			  //else if (listOfFiles[i].isDirectory()) {
			    //System.out.println("Directory " + listOfFiles[i].getName());
			  //}
			}
			decks.add(deck);
			menu = null;
		}
	}

}
