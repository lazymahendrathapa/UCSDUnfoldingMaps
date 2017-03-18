package custom;

import processing.core.PApplet;
import processing.core.PImage;

public class GUIUsingPApplet extends PApplet{

	private static final long serialVersionUID = -6129887672181449966L;

	private static int width = 200;
	private static int height = 200;
	
	private String fileName = "../data/palmTrees.jpg";
	private PImage backgroundImg;
	
	public void setup(){
		size(width,height);
		backgroundImg = loadImage(fileName, "jpg");
	}

	public void draw(){
		backgroundImg.resize(0, height);
		image(backgroundImg,0,0);
		int[] color = sunColorSet(second());
		fill(color[0],color[1],color[2]);
		ellipse(width/4, height/5, width/4, height/5);
	}

	private int[] sunColorSet(float seconds){
	
		int[] rgb = new int[3];
		
		float  diffFrom30 = Math.abs(30-seconds);
		float ratio = diffFrom30/30;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		
		return rgb;
	}
}
