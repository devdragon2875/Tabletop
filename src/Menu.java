import processing.core.PApplet;
import processing.core.PFont;

public class Menu {
	private float x;
	private float y;
	private int type;
	private Deck deck;
	private PApplet drawer;
	public static final float MENU_WIDTH = 120;
	public static final float MENU_HEIGHT = 150;
	private static final float BORDER_WIDTH = 15;
	private static final float SPACE = 6;
	private static final float NUM = 5;
	public static final int MENU_GENERAL = 1;
	public static final int MENU_DECK = 2;
	private PFont p;

	public Menu(PApplet drawer, float x, float y, int menuDeck, Deck deck) {
		this.x = x - MENU_WIDTH / 2;
		this.y = y - MENU_HEIGHT;
		this.type = menuDeck;
		this.deck = deck;
		this.drawer = drawer;
		p = drawer.createFont("Sitka Heading Bold", 20);
	}

	public int clicked(float pX, float pY) {
		if (pX >= x && pX <= x + MENU_WIDTH) {
			for (int i = 0; i < NUM; i++) {
				if (pY >= y + i * MENU_HEIGHT / NUM && pY <= y + (i+1) * MENU_HEIGHT / NUM -SPACE) {
					return i+1;
				}
			}
		}
		return 0;
	}

	public void draw() {
		if (type == MENU_DECK) {
			drawer.fill(255);
			drawer.rect(x - BORDER_WIDTH, y - BORDER_WIDTH, MENU_WIDTH + BORDER_WIDTH * 2, MENU_HEIGHT + BORDER_WIDTH, 20);
			
			for(int i = 0; i < NUM; i++) {
				
				drawer.fill(200);
				drawer.rect(x, y + i*MENU_HEIGHT/NUM, MENU_WIDTH, MENU_HEIGHT / NUM- SPACE, 5);
				drawer.textAlign(drawer.CENTER, drawer.CENTER);
				drawer.textFont(p);
				//drawer.textSize(27);
				if(i+1 == 1) {
					drawer.fill(255);
					drawer.text("SHUFFLE", x+MENU_WIDTH/2, y + i*MENU_HEIGHT/NUM +MENU_HEIGHT/(2*NUM)-SPACE);
				}
				if(i+1 == 2) {
					drawer.fill(255);
					drawer.text("UMM", x+MENU_WIDTH/2, y + i*MENU_HEIGHT/NUM+MENU_HEIGHT/(2*NUM)-SPACE);
				}
				if(i+1 == 3) {
					drawer.fill(255);
					drawer.text("MOVE", x+MENU_WIDTH/2, y + i*MENU_HEIGHT/NUM+MENU_HEIGHT/(2*NUM)-SPACE);
				}
				if(i+1 == 4) {
					drawer.fill(255);
					drawer.text("Other 1", x+MENU_WIDTH/2, y + i*MENU_HEIGHT/NUM+MENU_HEIGHT/(2*NUM)-SPACE);
				}
				if(i+1 == 5) {
					drawer.fill(255);
					drawer.text("Other 2", x+MENU_WIDTH/2, y + i*MENU_HEIGHT/NUM+MENU_HEIGHT/(2*NUM)-SPACE);
				}
			}
			

			drawer.fill(255);
		} else if (type == MENU_GENERAL) {
			drawer.fill(255);
			drawer.rect(x - BORDER_WIDTH, y - BORDER_WIDTH, MENU_WIDTH + BORDER_WIDTH * 2, MENU_HEIGHT + BORDER_WIDTH, 20);
			for(int i = 0; i < NUM; i++) {
				
				drawer.fill(200);
				drawer.rect(x, y + i*MENU_HEIGHT/NUM, MENU_WIDTH, MENU_HEIGHT / NUM - SPACE, 5);
				drawer.textAlign(drawer.CENTER, drawer.CENTER);
				drawer.textFont(p);
				//drawer.textSize(27);
				if(i+1 == 1) {
					drawer.fill(255);
					drawer.text("Create Deck", x+MENU_WIDTH/2, y + i*MENU_HEIGHT/NUM +MENU_HEIGHT/(2*NUM)-SPACE);
				}
				if(i+1 == 2) {
					drawer.fill(255);
					drawer.text("Create Card", x+MENU_WIDTH/2, y + i*MENU_HEIGHT/NUM+MENU_HEIGHT/(2*NUM)-SPACE);
				}
				if(i+1 == 3) {
					drawer.fill(255);
					drawer.text("Import", x+MENU_WIDTH/2, y + i*MENU_HEIGHT/NUM+MENU_HEIGHT/(2*NUM)-SPACE);
				}
				if(i+1 == 4) {
					drawer.fill(255);
					drawer.text("Other 1", x+MENU_WIDTH/2, y + i*MENU_HEIGHT/NUM+MENU_HEIGHT/(2*NUM)-SPACE);
				}
				if(i+1 == 5) {
					drawer.fill(255);
					drawer.text("Other 2", x+MENU_WIDTH/2, y + i*MENU_HEIGHT/NUM+MENU_HEIGHT/(2*NUM)-SPACE);
				}
			}
			

			

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
