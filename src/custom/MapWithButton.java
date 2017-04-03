package custom;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class MapWithButton extends PApplet {
	
	private static final long serialVersionUID = -3137596582366696864L;
	
	private UnfoldingMap map;

	public void setup() {

		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
	}

	public void draw() {

		map.draw();
		this.drawButtons();
		
	}
	
	private void drawButtons(){
		
		fill(255,255,255);
		rect(100, 100, 25, 25);
		fill(100,100,100);
		rect(100,150,25,25);
	}
	
	public void mouseReleased(){
		
		if (mouseX > 100 && mouseX < 125 && mouseY > 100 && mouseY < 125)
			background(255, 255, 255);
		else if (mouseX > 100 && mouseX < 125 && mouseY > 150 && mouseY < 175)
			background(100, 100, 100);
	}
}


