import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {
	float prevX;
	float prevY;
	int lastClickedType = 0;
	Item item;
	Menu menu;
	ArrayList<Deck> decks = new ArrayList<Deck>();
	ArrayList<Card> cards = new ArrayList<Card>();
	public void settings() {
		size(1600,1000);
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		background(0, 0, 0);
		for(int i = 0; i < decks.size(); i++) {
			decks.get(i).draw();
		}
		for(Card c : cards) {
			c.draw();
		}
		if(menu != null) {
			menu.draw();
		}
	}
	
	public void mousePressed() {
		prevX = mouseX;
		prevY = mouseY;
		if(mouseButton == LEFT) {
			for(int i = 0; i < cards.size(); i++) { 
				if(lastClickedType == 0 && cards.get(i).hasPoint(mouseX, mouseY)) {
					item = cards.get(i);
					lastClickedType = 1;
				}
			}
			for(int i = 0; i < decks.size(); i++) {
				if(lastClickedType == 0 && decks.get(i).hasPoint(mouseX, mouseY)) {
					item = decks.get(i);
					lastClickedType = 2;
				}
			}
			
			
			if(menu != null) {
				if(menu.getType() == Menu.MENU_DECK) {
					if(menu.clicked(mouseX, mouseY) == 1) {
						menu.getDeck().shuffle();
						
					}
					if(menu.clicked(mouseX, mouseY) == 2) {
						menu.getDeck().move();
					}
					if(menu.clicked(mouseX, mouseY) == 3) {
						
					}
				}
				if(menu.getType() == Menu.MENU_GENERAL) {
					if(menu.clicked(mouseX, mouseY) == 1) {
						decks.add(new Deck(this, null, menu.getX()+Menu.MENU_WIDTH/2-Deck.DECK_WIDTH/2, menu.getY()+Menu.MENU_HEIGHT-Deck.DECK_HEIGHT/2));
					}
					if(menu.clicked(mouseX, mouseY) == 2) {
						cards.add(new Card(this, "faceup.png", "facedown.png", menu.getX()+Menu.MENU_WIDTH/2-Deck.DECK_WIDTH/2, menu.getY()+Menu.MENU_HEIGHT-Deck.DECK_HEIGHT/2, false));
					}
					
				}
				menu = null;
			}
			
			
			
		} else if(mouseButton == RIGHT) {
			menu = new Menu(this, mouseX, mouseY, Menu.MENU_GENERAL, null);
			for(int i = 0; i < decks.size(); i++) {
				if(decks.get(i).hasPoint(mouseX, mouseY)) {
					menu = new Menu(this, mouseX, mouseY, Menu.MENU_DECK, decks.get(i));
				}
			}
			
		}
		
	}
	public void mouseDragged() {
		float xShift = mouseX-prevX;
		float yShift = mouseY-prevY;
		if(lastClickedType != 0) {
			item.setX(item.getX()+xShift);
			item.setY(item.getY()+yShift);
		}
		prevX = mouseX;
		prevY = mouseY;
		
	}
	public void mouseReleased() {
		lastClickedType = 0;
	}
	
}
