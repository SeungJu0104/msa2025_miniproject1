package org.kosa.mini.util;

import java.util.regex.Pattern;

public class Validate {
	
    public static boolean isValid(String regex, String input) {
        return Pattern.matches(regex, input);
    }
    
    public static void main(String [] args) {
    	System.out.println(isValid("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,10}$", "abcdef1234"));
    }

}
