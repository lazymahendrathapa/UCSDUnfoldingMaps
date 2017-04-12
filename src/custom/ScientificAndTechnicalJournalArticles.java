package custom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class ScientificAndTechnicalJournalArticles extends PApplet {

	private static final long serialVersionUID = 2680754930003314333L;

	private UnfoldingMap map;
	private Map<String, Float> countryArticles;
	List<Feature> countries;
	List<Marker> countryMarkers;

	public void setup() {

		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 150, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);

		countryArticles = loadFromCSVFile(
				"../data/ScientificAndTechnicalJournalArticles/API_IP.JRN.ARTC.SC_DS2_en_csv_v2.csv", 57);

		countries = GeoJSONReader.loadData(this, "../data/countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		this.shadeCountries();
	}

	private Map<String, Float> loadFromCSVFile(String fileName, int column) {

		Map<String, Float> resultMap = new HashMap<>();

		String[] rows = loadStrings(fileName);

		for (int i = 5; i < rows.length; ++i) {

			String[] columns = rows[i].split("\",\"");

			try {
				float value = Float.parseFloat(columns[column]);
				resultMap.put(columns[1], value);

			} catch (NumberFormatException ex) {
				System.out.println("Error: " + columns[1] + " : " + ex.getMessage());
			}
		}

		return resultMap;
	}

	private void shadeCountries() {

		for (Marker marker : countryMarkers) {
			String countryId = marker.getId();

			if (countryArticles.containsKey(countryId)) {
				float articleRate = countryArticles.get(countryId) / 1000;

				if (articleRate < 5.17)
					marker.setColor(color(174, 189, 56));
				else if (articleRate < 19.36)
					marker.setColor(color(89, 130, 52));
				else if (articleRate < 35.54)
					marker.setColor(color(104, 130, 158));
				else if (articleRate < 72.55)
					marker.setColor(color(80, 81, 96));
				else
					marker.setColor(color(0, 128, 0));
			} else {
				marker.setColor(color(183, 184, 182));
			}

		}
	}

	public void draw() {
		map.draw();
		this.drawButtons();
		addKey();

	}

	private void drawButtons() {

		fill(255, 255, 255);
		rect(270, 60, 50, 25);
	
		fill(0);
		textAlign(LEFT, CENTER);
		textSize(12);

		text("2013 ", 280, 70);
		
		fill(255, 255, 255);
		rect(270, 100, 50, 25);
		
		
		fill(0);
		textAlign(LEFT, CENTER);
		textSize(12);

		text("2003 ", 280, 110);
		
	}

	public void mouseReleased() {

		if (mouseX > 270 && mouseX < 320 && mouseY > 60 && mouseY < 85) {

			countryArticles = loadFromCSVFile(
					"../data/ScientificAndTechnicalJournalArticles/API_IP.JRN.ARTC.SC_DS2_en_csv_v2.csv", 57);

		} else if (mouseX > 270 && mouseX < 320 && mouseY > 100 && mouseY < 125) {
			countryArticles = loadFromCSVFile(
					"../data/ScientificAndTechnicalJournalArticles/API_IP.JRN.ARTC.SC_DS2_en_csv_v2.csv", 47);

		}

		this.shadeCountries();
	}

	private void addKey() {
		fill(255, 250, 240);

		int xbase = 25;
		int ybase = 50;

		rect(xbase, ybase, 200, 300);

		fill(0);
		textAlign(LEFT, CENTER);
		textSize(12);

		text("Scientific and Technical ", xbase + 25, ybase + 25);
		text("Journal Articles (Thousands)", xbase + 25, 2 * ybase);

		int x = xbase + 35 - 5;
		int y = 3 * ybase - 10;
		fill(174, 189, 56);
		rect(x, y, 10, 10);
		fill(89, 130, 52);
		rect(x, y + 20, 10, 10);
		fill(104, 130, 158);
		rect(x, y + 40, 10, 10);
		fill(80, 81, 96);
		rect(x, y + 60, 10, 10);
		fill(0, 128, 0);
		rect(x, y + 80, 10, 10);
		fill(183, 184, 182);
		rect(x, y + 100, 10, 10);

		fill(0, 0, 0);
		textAlign(LEFT, CENTER);

		x = x + 20;
		y = y + 3;
		text("< 5.17 ", x, y);
		text("5.17 - 19.26", x, y + 20);
		text("19.26 - 35.54", x, y + 40);
		text("35.54 - 72.55", x, y + 60);
		text("> 72.55 ", x, y + 80);
		text("unknown", x, y + 100);

	}

}
