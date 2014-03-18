package com.manu.algo.area.biggest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Finds the largest area in the form of dots(.) bounded by 'X's in the input map text file. 
 * 
 * <pre>
 * ................................
 * .XXXXXXXXXXXXXXX.....XXXXXXXXXX.
 * .X.....X.......X.....X........X.
 * .X.....X.......XXXXXXX........X.
 * .XXXXXXXXXXXX.................X.
 * .X....X.....X.................X.
 * .X....X.....XXXX..............X.
 * .XXXXXX........X..............X.
 * ......X........X..............X.
 * ......X........X..............X.
 * ......X........X..............X.
 * ......XXXXXXXXXXXXXXXXXXXXXXXXX.
 * ................................
 * </pre>
 * @author manu.mehrotra
 */
public class FindLargestArea {
	
	private static String FILE_NAME = "map.txt";
	
	/* Assuming that we know the rows & cols before hand,
	 * as we know maps dimension are 13 x 32.
	 */
	private static int NUMBER_OF_ROWS = 13; // 32
	
	
	/*
	 * To enable disable log/print statements.
	 */
	private static boolean ENABLE_LOGS = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		int maxArea = -1;
		try {
			maxArea = findBiggestArea(new File(FindLargestArea.class.getResource(FILE_NAME).toURI()));
			System.out.println("Largest Area = "+maxArea+" | Total time taken = " + (System.currentTimeMillis() - startTime)/1000 + " ms");
		} catch (URISyntaxException e) {
			System.err.println("Unable to locate file - map.txt. ERROR: "+e.getMessage());
		}
	}

	/**
	 * Finds the largest area in the form of dots(.) bounded by 'X's in the input map text file. 
	 * 
	 * @param inputFile
	 * @return
	 */
	private static int findBiggestArea(File inputFile) {
		char[][] rowsArray = convertFileIntoArray(inputFile);
	    int maxArea = getLargestBoundedArea(rowsArray);
		return maxArea;
	}

	/**
	 * Logic to read the file and convert it into an 2D Array, 
	 * where every array index contains contents of a line in chars.
	 * 
	 * @param inputFile
	 * @return
	 */
	private static char[][] convertFileIntoArray(File inputFile) {
		BufferedReader br = null;
		char[][] rowIdxContentsArr = null;
		
	    try{
	        FileReader fileRdr = new FileReader(inputFile);
	        br = new BufferedReader(fileRdr);

	        String content;
	        int row = 0;
	        
	        // Read every row contents line by line
	        while ((content = br.readLine()) != null) {
	        	if(rowIdxContentsArr == null) {
	        		// Initialize the 2D Array based on the length of the row data.
	        		rowIdxContentsArr = new char[NUMBER_OF_ROWS][content.length()];
	        	}
	        	//Convert every row string data into a char array and set it into every 2D array index.  
	            rowIdxContentsArr[row] = content.toCharArray();
	            logDebug("convertFileIntoArray() | row = "+row +" , line = "+content);
	            row++;
	        }
	        logDebug("File written successfully into a char array");
	    } catch(IOException e) {
	    	System.err.println("Unable to parse file - map.txt. ERROR: "+e.getMessage());
	    } finally {
	    	if(br != null) {
	    		try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    }	
	    return rowIdxContentsArr;
	}

	/**
	 * Finds the biggest bounded area based on following logic:
	 * #1: Converts 2D char array into a HashMap of bounded region counter vs associated points in Map that constitutes the bounded region. 
	 * #2: Check if regions are overlapping then merge them while preventing duplicates.
	 * #3: Check which bounded region covers the maximum number of points.
	 * 
	 * @param rowsArray
	 * @return
	 */
	private static int getLargestBoundedArea(char[][] rowsArray) {
	    HashMap<Integer, ArrayList<String>> boundedRegionPointsMap = getBoundedRegionsPlotting(rowsArray);

	    boundedRegionPointsMap = checkAndMergeIntersectedPoints(boundedRegionPointsMap);

	    int largestSize = -1;
	    
	    // Finally, iterate over the bounded regions to get the region with maximum number of points.
	    for (Integer key : boundedRegionPointsMap.keySet()) {
	        ArrayList<String> boundedPointsList = boundedRegionPointsMap.get(key);
	        logInfo("getLargestBoundedArea() | Region key = " + key + " Region points = " + boundedPointsList.size());
	        if (largestSize < boundedPointsList.size()) {
	            largestSize = boundedPointsList.size();
	        }
	    }
	    
	    logDebug("getLargestBoundedArea() | Largest region size = "+largestSize);
	    return largestSize;
	}
	
	/**
	 * Convert the 2D Char Array into HashMap, Keys tracking the counter of bounded regions and 
	 * Values being the list of points in the form of 'row:col' indexes. 
	 * 
	 * @param rowsArray
	 * @return
	 */
	private static HashMap<Integer, ArrayList<String>> getBoundedRegionsPlotting(char[][] rowsArray) {
	    HashMap<Integer, ArrayList<String>> boundedRegions = new HashMap<Integer, ArrayList<String>>();
	    int boundsRegionCounter = 0;
	    
	    // Loop through 0 - 12
	    for(int row=0; row < rowsArray.length; row++) {
	    	logDebug("\n\n+ getBoundedRegionsPlotting() | #row="+row+", rowsArray[row].length = "+rowsArray[row].length);
	    	
	    	// Loop through 0 - 31
	        for (int col=0; col < rowsArray[row].length; col++) {
	        	logDebug("++ getBoundedRegionsPlotting() | @col="+col+", current pointer = "+rowsArray[row][col]);
	        	
	        	// If current point in map is Dot(.), add it into a bounded region (either new or existing)
	            if (rowsArray[row][col] == '.') {
                
	                // If any similar existing region not available, create a new region for this dotted point (.)
	                if (!checkAndMapPointToExistingRegion(row, col, boundedRegions)) {
	                    ArrayList<String> boundedPointsPerRegionList = new ArrayList<String>();
	                    boundedPointsPerRegionList.add(row + ":" + col);
	                    boundedRegions.put(boundsRegionCounter, boundedPointsPerRegionList);
	                    boundsRegionCounter++;
	                }
	            }
	        }
	    }
	    logDebug("- getBoundedRegionsPlotting() | boundsRegionCounter="+boundsRegionCounter+", boundedRegionsMap="+boundedRegions);
	    return boundedRegions;
	}
	
	/**
	 * Checks if the current pointer falls under any existing region or not. 
	 * 
	 * @param row
	 * @param col
	 * @param boundedRegions
	 * @return
	 */
	private static boolean checkAndMapPointToExistingRegion(int row, int col, HashMap<Integer, ArrayList<String>> boundedRegions) {
		
		boolean found = false;
        
		// Iterate over the existing bounded regions and check if current point falls under adjacent point of any region
        for(Integer key : boundedRegions.keySet()) {
            ArrayList<String> boundedPointsPerRegionList = boundedRegions.get(key);
            
            if(boundedPointsPerRegionList != null) {
            	logDebug("checkAndMapPointToExistingRegion() | Is list contains -> "+row + ":" + col+", "+(row-1) + ":" + col+" OR "+row + ":" + (col-1));
            	
            	// If adjacent point, add into the list of same region otherwise create new region later.            	
                if(boundedPointsPerRegionList.contains(row + ":" + (col-1)) || boundedPointsPerRegionList.contains((row-1) + ":" + col)) {
                    boundedPointsPerRegionList.add(row + ":" + col);
                    boundedRegions.put(key, boundedPointsPerRegionList);
                    found = true;
                }
            } 
        }
        
        return found;
	}

	/**
	 * Check for the points exists in more than one area to merge them as well as prevent any duplicates.
	 * 
	 * @param boundedRegionPointsMap
	 * @return
	 */
	private static HashMap<Integer, ArrayList<String>> checkAndMergeIntersectedPoints(HashMap<Integer, ArrayList<String>> boundedRegionPointsMap) {

		// Iterate over regions one by one, to validate with rest of the regions iteratively.
	    for(Integer key1 : boundedRegionPointsMap.keySet()) {
	        ArrayList<String> list1 = boundedRegionPointsMap.get(key1);

	        for (Integer key2 : boundedRegionPointsMap.keySet()) {
	        	// Only need to check for keys (region counters) incrementally
	            if (key1 < key2) {
	                ArrayList<String> list2 = boundedRegionPointsMap.get(key2);
	                for(String point : list2) {
	                	// If there is an intersecting point in two regions, merge them.
	                    if (list1.contains(point)) {
	                        list1.addAll(list2);
	                        // Just to make sure there won't be any duplicate points introduced after the merge.
	                        Set<String> uniquePoints = new HashSet<String>(list1);
	                        boundedRegionPointsMap.put(key1, new ArrayList<String>(uniquePoints));
	                        break;
	                    }
	                }
	            }
	        }

	    }
	    return boundedRegionPointsMap;
	}
	
	private static void logDebug(String message) {
		if(ENABLE_LOGS) {
			System.out.println(message);
		}
	}
	
	private static void logInfo(String message) {
		if(ENABLE_LOGS)
			System.out.println(message);
	}

}
