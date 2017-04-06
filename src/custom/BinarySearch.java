package custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

	public String findAirportCode(List<Airport> airports, String toFind){
	
		int low = 0;
		int high = airports.size();
		
		while(low <= high){
	
			int mid = low + (high - low)/2;
		
			int compare = toFind.compareTo(airports.get(mid).getCity());
			
			if(compare < 0){
				high = mid - 1;
			}else if(compare > 0){
				low = mid + 1;
			}else{
				return airports.get(mid).getCode();
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {

		List<Airport> airports = new ArrayList<>();
		airports.add(new Airport("Agra", "India", "AGR"));
		airports.add(new Airport("Beijing", "China", "PEK"));
		airports.add(new Airport("Chicago", "USA", "ORD"));
		airports.add(new Airport("Essen", "Germany", "ESS"));
		airports.add(new Airport("Lagos", "Nigeria", "LOS"));
		airports.add(new Airport("Montreal", "Canada", "YMX"));
		airports.add(new Airport("Quito", "Ecuador", "UIO"));
		airports.add(new Airport("Sydeny", "Australia", "SYD"));
		
		Collections.sort(airports);
	
		BinarySearch bs = new BinarySearch();
		
		System.out.println(bs.findAirportCode(airports, "Beijing"));
		System.out.println(bs.findAirportCode(airports, "Sydeny"));
	}
	
}
