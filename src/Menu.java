import processing.core.PApplet;

public class Menu {
	private float x;
	private float y;
	private int type;
	private Deck deck;
	private PApplet drawer;
	public static final float MENU_WIDTH= 60;
	public static final float MENU_HEIGHT = 90;
	public static final int MENU_GENERAL = 1;
	public static final int MENU_DECK= 2;
	
	public Menu(PApplet drawer, float x, float y, int menuDeck, Deck deck) {
		this.x = x-MENU_WIDTH/2;
		this.y = y-MENU_HEIGHT;
		this.type = menuDeck;
		this.deck = deck;
		this.drawer = drawer;
	}
	
	


	public int clicked(float pX, float pY) {
		if(type == MENU_DECK) {
			if(pX >= x && pY >= y && pX<= pX+MENU_WIDTH && pY<= pY+MENU_HEIGHT/3) {
				return 1;
			} else if(pX >= x && pY >= y + MENU_HEIGHT/3 && pX<= pX+MENU_WIDTH && pY<= pY+2*MENU_HEIGHT/3) {
				return 2;
			} else if(pX >= x && pY >= y+2*MENU_HEIGHT/3 && pX<= pX+MENU_WIDTH && pY<= pY+MENU_HEIGHT) {
				return 3;
			}
		} else if(type == MENU_GENERAL) {
			if(pX >= x && pY >= y && pX<= pX+MENU_WIDTH && pY<= pY+MENU_HEIGHT/2) {
				return 1;
			} else if(pX >= x && pY >= y + MENU_HEIGHT/2 && pX<= pX+MENU_WIDTH && pY<= pY+MENU_HEIGHT) {
				System.out.println("blue");
				return 2;
			}
		}
		return 0;
	}
	
	public void draw() {
		if(type == MENU_DECK) {
			drawer.fill(0, 0, 0);
			drawer.rect(x, y, MENU_WIDTH, MENU_HEIGHT/3);
			drawer.fill(0, 180, 0);
			drawer.rect(x, y+MENU_HEIGHT/3, MENU_WIDTH, MENU_HEIGHT/3);
			drawer.fill(0, 0, 180);
			drawer.rect(x, y+2*+MENU_HEIGHT/3, MENU_WIDTH, MENU_HEIGHT/3);
			drawer.fill(255);
		} else if(type == MENU_GENERAL) {
			drawer.fill(0, 180, 0);
			drawer.rect(x, y, MENU_WIDTH, MENU_HEIGHT/2);
			drawer.fill(0, 0, 180);
			drawer.rect(x, y+MENU_HEIGHT/2, MENU_WIDTH, MENU_HEIGHT/2);
			drawer.fill(255);
		}
	}

	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public Deck getDeck() {
		// TODO Auto-generated method stub
		return deck;
	}
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
}
