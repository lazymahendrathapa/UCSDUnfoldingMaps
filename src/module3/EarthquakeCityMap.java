package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;
import processing.core.PGraphics;
//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Mahendra Singh Thapa.
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "../data/2.5_week.atom";

	private PGraphics pg;
	
	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 400, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 400, 50, 700, 500, new Google.GoogleMapProvider());
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    for(PointFeature pointFeature : earthquakes){
	    	markers.add(this.createMarker(pointFeature));
	    }
	    
	    
	    // Here is an example of how to use Processing's color method to generate 
	    // an int that represents the color yellow.  
	    int red = color(255,0,0);
	    int yellow = color(255, 255, 0);
	    int blue = color(0,0,255);

	    for(Marker marker : markers){
	    	
	    	float magnitude = (Float)marker.getProperty("magnitude");
	    	
	    	if(magnitude > 5.0)
	    		marker.setColor(red);
	    	else if(magnitude > 4.0)
	    		marker.setColor(yellow);
	    	else 
	    		marker.setColor(blue);
	    }
	    
	    map.addMarkers(markers);
	    pg = createGraphics(250, 200);
	}
		
	private SimplePointMarker createMarker(PointFeature feature)
	{
		return new SimplePointMarker(feature.getLocation(), feature.getProperties());
	}
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	private void addKey() 
	{	

		  pg.beginDraw();
		  pg.background(255);
		  
		  pg.fill(0,0,0);
		  pg.textSize(20);
		  pg.text("Earthquake Key", 50, 60);
		  
		  pg.fill(255, 0, 0);
		  pg.ellipse(50, 80, 20, 20);
		  
		  pg.fill(0,0,0);
		  pg.textSize(10);
		  pg.text("5.0 + Magnitude", 90, 90);
		  
		  pg.fill(255, 255, 0);
		  pg.ellipse(50, 110, 15, 15);
		  
		  pg.fill(0,0,0);
		  pg.textSize(10);
		  pg.text("4.0 + Magnitude", 90, 115);
		  
		  pg.fill(0, 0, 255);
		  pg.ellipse(50, 135, 10, 10);
		  
		  pg.fill(0,0,0);
		  pg.textSize(10);
		  pg.text("Below 4.0", 90, 135);
		  
		  pg.endDraw();
		  image(pg, 50, 60); 
		  
	}
}
