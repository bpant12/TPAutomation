package com.teachingpark.uiautomation.utility;

import java.util.List;
import java.util.Random;

public class GenericUtil {

	/**
	 * Check if a number is in order or not
	 * @param numbers
	 * @param ascending
	 * @return
	 */
	public static boolean isSortedNumber(List<Float> numbers,boolean ascending) {
	    for (int i = 1; i < numbers.size(); i++) {
	        if (numbers.get(i-1) == numbers.get(i)) {
	            continue;
	        }
	        if ((numbers.get(i-1)  > numbers.get(i)) == ascending) {
	            return false;
	        }
	    }
	    return true;
	}

	/**
	 * Return a random integer
	 * @return
	 */
    public static int getRandom() {
        return new Random().nextInt();
    }
	
}
