import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet {
	float prevX;
	float prevY;
	boolean dragged = false;
	ArrayList<Deck> decks = new ArrayList<Deck>();
	ArrayList<Region> regions = new ArrayList<Region>();
	public void settings() {
		size(1600,1000);
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		background(255, 255, 255);
		if(dragged) {
			rect(prevX, prevY, mouseX-prevX, mouseY-prevY);
		}
		for(int i = 0; i < regions.size(); i++) {
			regions.get(i).draw();
		}
	}
	
	public void mousePressed() {
		  prevX = mouseX;
		  prevY = mouseY;
	}
	public void mouseReleased() {
		if(dragged) {
			Region region = new Region(this, prevX, prevY, mouseX-prevX, mouseY-prevY);	  
			regions.add(region);
			dragged = false;
		}
	}
	public void mouseDragged() 
	{
		dragged = true;
		
		
	}
}
