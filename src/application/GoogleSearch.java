package application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

// Performing Google search and retrieving data about the search using JSoup
public class GoogleSearch {
	
	
	String searchString;
	String url;
	String userAgent;
	long NumberOfResults;
	
	// Setting up and performing the google search on a string
	public GoogleSearch(String searchString) {
		
		// Setting the string we are searching on
		this.searchString = searchString;
		
		// Setting user agent
		this.userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36";
		
		// Setting the url
		try {
			this.url = "https://www.google.com/search?q=" + URLEncoder.encode(this.searchString, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Setting number of results
		this.NumberOfResults = this.getNumberOfResults();
		
		
	}
	
	// Getting the number of results on the google search of the string
	public long getNumberOfResults() {
		
		// Creating document and getting the HTML
		Document document = null;
		
		try {
			document = Jsoup.connect(url).userAgent(this.userAgent).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Getting the div with the result stats
		Element divResultStats = document.select("div#result-stats").first();
		
		if (divResultStats==null) {
		    throw new RuntimeException("Unable to find results stats.");
		}
		
		String resultStatsText = divResultStats.text();
		
		// Creating a list of words based on the result text
		List<String> resultStatsList = new ArrayList<String>(Arrays.asList(resultStatsText.split(" ")));
		
		// Creating a list with only the number of results
		List<String> numberOfResultsList = resultStatsList.subList(1, resultStatsList.size() - 3);
		
		String numberOfResultsString = "";
		
		// Combining the strings of numbers to get the number of results as a string
		for (String element : numberOfResultsList) {
			numberOfResultsString = numberOfResultsString + element;
		}
		
		// Returning the number as a long
		return Long.parseLong(numberOfResultsString);
	}
}
