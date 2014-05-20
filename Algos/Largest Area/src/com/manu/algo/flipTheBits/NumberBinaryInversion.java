package com.manu.algo.flipTheBits;

public class NumberBinaryInversion {
	
	private static String binaryVal = "";
	
    public static void main(String[] args) {
        int number = 50; 
        
        System.out.println("Integer.toBinaryString("+number+") = "+Integer.toBinaryString(number));
        
//        Scanner in = new Scanner(System.in);
//
//        System.out.println("Enter a positive integer");
//        number=in.nextInt();

        if (number <0)
            System.out.println("Error: Not a positive integer");
        else { 
        	binaryform(number);
        	System.out.println("");
            System.out.println("Binary value of "+number+" is: " + binaryVal);
        }
        
//        int newDecimalVal = 
        @SuppressWarnings("null")
		char [] binaryArr = binaryVal.toCharArray();
        for(int i=0; i<binaryArr.length; i++) {
        	binaryArr[i] = binaryArr[i] == '0' ? '1' : '0';
        }
        
        String invertedBinaryVal = String.valueOf(binaryArr);
        System.out.println("Binary inversion of "+number+" is: "+invertedBinaryVal);
        
        System.out.println("Complement of "+number+" is: "+Integer.parseInt(invertedBinaryVal, 2));
        System.out.println("Conventional Method:: Complement of "+number+" is: "+decimlToBinary(invertedBinaryVal));
    }

    private static String binaryform(int number) {
        int remainder;

        if (number <= 1) {
        	binaryVal += number+"";
            System.out.print(number);
            return null;   // KICK OUT OF THE RECURSION
        } else {
	        remainder = number % 2; 
	        binaryform(number >> 1);
	        binaryVal += remainder+"";
	        System.out.print(remainder);
	        return "";
	    }
    }
    
    public static int decimlToBinary(String decimalStr) {
    	double j = 0;
    	for(int i=0; i < decimalStr.length(); i++) {
    		if(decimalStr.charAt(i) == '1') {
    			j = j + Math.pow(2, decimalStr.length() - (1+i));
    		}
    	}
    	return (int) j;
    }

}
