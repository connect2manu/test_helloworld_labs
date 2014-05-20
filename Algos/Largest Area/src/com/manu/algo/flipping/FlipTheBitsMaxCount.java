package com.manu.algo.flipping;

import java.util.Scanner;

/**
 * @author mmehr1
 *
 */
public class FlipTheBitsMaxCount {
	
    /**
     * Solution Approach:
     * #1: Take the input string and convert the input binary string into the an int array.
     * #2: Iterate over int array and replace all the 0's with 1's and existing 1's into -1. 
     * #3: That decomposes the problem into minimizing the sum of the array after flipping i.e. Min sum = most -1 => i.e. Most original 1's.
     * #4: Now find the max sub range which results max output
     *  
     * @param args
     */
    public static void main(String[] args) {
        String inputBits = "";
        System.out.println("Please enter the input binary string of N chars where 1 <= N <= 100000");
        Scanner in = new Scanner(System.in);
        inputBits = in.nextLine();
        
        inputBits.replaceAll(" ", "");
        
        System.out.println("inputBits = "+inputBits);
        int[] binaryArr = new int[inputBits.length()];

        for (int i = 0; i < inputBits.length(); i++) {
        	binaryArr[i] = Character.digit(inputBits.charAt(i), 10);
        }
        
	    int total = 0;
	
	    // Count the 1's in the original array and
	    // 0 -> 1 and 1 -> -1 transformation
	    for(int i = 0; i < binaryArr.length; i++) {
	        total = total + binaryArr[i];
	        binaryArr[i] = binaryArr[i] == 0 ? 1 : -1;        
	    }
	
	    // find the maximum subarray
	    for (int x = 0; x < binaryArr.length; x++) {
	        // update y
	        if (Pointer.value + binaryArr[x] >= 0) {
	            Pointer.value += binaryArr[x];
	        } else {
	            Pointer.left = x + 1;
	            Pointer.value = 0;
	        }
	        
	        // Update range
	        if (Range.value < Pointer.value) {
	            Range.LEFT = Pointer.left;
	            Range.RIGHT = x;
	            Range.value = Pointer.value;
	        }
	    }
	
	    // convert the result back
	    System.out.println("Output = " + (total + Range.value) + " in range [" + Range.LEFT + ", " + Range.RIGHT + "]");
    }
    
    /**
     * @author mmehr1
     *
     */
    static class Range {
    	static int value = 0;
    	static int LEFT = 0;
    	static int RIGHT = 0; 
    }
    
    /**
     * @author mmehr1
     *
     */
    static class Pointer {
    	static int value = 0;
		static int left = 0; 
    }


}
