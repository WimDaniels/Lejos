package route;

/**
 * The Map class keeps lists of cities and roads. 
 * It also contains several methods, the most important ones are to plan a route.
 * cities: an array of cities
 * roads: an array of roads between cities
 * addCity - method to add a City to the map
 * addRoad - method to add a Road to the map
 * planRoute - method that builds a Route
 * And then some helper methods
 * 
 * @author Arqetype
 *
 */

import java.util.ArrayList;
import java.util.Stack;

public class Map {
	String mapname;
	ArrayList<City> cities;
	ArrayList<Road> roads;
	
	Stack<City> pathStack = new Stack<City>();
	
	// the constructor for a Map
	public Map(String name) {
		mapname = name;
		cities = new ArrayList<City>();
		roads = new ArrayList<Road>();
	}
	
	// method to add a city to the map
	public void addCity(City city) {
		cities.add(city);
	}
	
	// method to add a road to the map
	public void addRoad(Road road) {
		roads.add(road);
	}
	
	// method to display the city in a string format
	public String toString() {
		String result = new String();
		
		result = "Map of " + this.mapname + "\n" + "Cities and towns: \n";
		for (int i = 0; i < this.cities.size(); i++) {
			result = result + "- " + this.cities.get(i) + "\n";
		}
		result = result + "Roads:\n";
		for (int i = 0; i < this.roads.size(); i++) {
			result = result + "- " + this.roads.get(i) + "\n";
		}
		return result;
	}
	
	// method to plot out a route using a depth first search algorithm
	public void planDeep(City start, City finish) {
		boolean matched;
		City next;
		
//		System.out.println("Search a route from " + start.cityname);
		
		// check if the current start connects to the finish
		matched = match(start, finish);
		// if it does we've found a route to the destination
		if (matched) {
			// the cities are stored in a stack, let's print them out properly
			String result = new String("The path goes along these towns:\n");
			result = result + pathStack.get(0).cityname;
			for (int i = 1; i < pathStack.size()-1; i++) {
				result = result + ", " + pathStack.get(i).cityname;
			}
			result = result + " and " + pathStack.get(pathStack.size()-1).cityname + ".";
			System.out.println(result);
			return;
		}
		// if we have not reached the destination...
		else {
			// look for the next city
			next = findNext(start);
			// when we find another city...
			if (next != null) {
				// repeat the search using this new city as starting point
				planDeep(next, finish);
				return;
			}
			// when we don't find another city, we're at a dead end...
			else {
				// so we'll backtrack by popping a city from our stack...
				next = pathStack.pop();
				System.out.println("Geen stad gevonden...");
				// and use it for another search
				planDeep(next, finish);
				return;
			}
		}
	}
	
	public boolean match(City start, City finish) {
		// iterate through the list of roads, start at the end
		for (int i = roads.size() - 1; i >= 0; i--) {
			// if from = start && to = finish we have a match
			if(roads.get(i).from.equals(start) && roads.get(i).to.equals(finish)) {
				System.out.println(roads.get(i).to.cityname);
				// it the last road so store both from an to in the pathStack
				pathStack.push(start);
				pathStack.push(finish);
				return true;
			}
		}
		// no match found
		return false;
	}
	
	public City findNext(City start) {
		// iterate through the list of roads to find one we can reach from the current city
		for (int i = 0; i < roads.size(); i++) {
			// find the next city but exclude roads we already tried before
			if (roads.get(i).from.equals(start) && !roads.get(i).visited) {
				City found = roads.get(i).to;
				System.out.println(found.cityname);
				// mark this road so we don't try it again
				roads.get(i).visited = true;
				// store the start town in pathStack
				pathStack.push(start);
				return found;
			}
		}
		
		return null;
	}
}