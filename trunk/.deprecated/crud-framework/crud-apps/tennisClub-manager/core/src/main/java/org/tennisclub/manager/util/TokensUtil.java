package org.tennisclub.manager.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public final class TokensUtil {

	public static ArrayList<String> extractSpacedTokens(String cadena) {		
		return extractTokenList(cadena, " ");
	}
	
	public static ArrayList<String> extractTokenList(String cadena, String delim) {
		final StringTokenizer spacesTokenizer = new StringTokenizer(cadena, delim);
		final ArrayList<String> tokens = new ArrayList<String>();
		while (spacesTokenizer.hasMoreTokens()) {
			tokens.add(spacesTokenizer.nextToken()) ;
	    }
		return (tokens != null && tokens.size() > 0)?tokens:null;
	}
	
	public static String extractTokenTime(String cadena, String delim) {
		final StringTokenizer timeTokenizer = new StringTokenizer(cadena, delim);
		String tokenTime = null;
		if (timeTokenizer.hasMoreTokens()) {
			tokenTime = timeTokenizer.nextToken() ;
	    }
		return tokenTime;
	}

}
