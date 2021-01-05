package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// KeywordSelector based on the file "TopUSSearches.txt"
public class StaticKeywordSelector implements KeywordSelector {

	@Override
	public List<String> keywordList() {
		
		List<String> keywords = new ArrayList<String>();
		
	    Scanner scanner = new Scanner(getClass().getResourceAsStream("TopUSSearches.txt"));
	    while (scanner.hasNextLine()) {
	        String data = scanner.nextLine();
	        // Adding all the keywords into a list
	        keywords.add(data);
	      }
	      scanner.close();
		return keywords;
	}

}
