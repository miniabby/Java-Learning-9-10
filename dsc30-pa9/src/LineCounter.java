/*
 * NAME: Kechen Zhao
 * PID: A16139826
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

public class LineCounter {

    private static int DEFAULT_SIZE = 10;

    /**
    * Method to print the filename to console
    */
    public static void printFileName(String filename) {
        System.out.println("\n" + filename + ":");
    }

    /**
    * Method to print the statistics to console
    */
    
    public static void printStatistics(String compareFileName, int percentage) {
        System.out.println(percentage + "% of lines are also in " + compareFileName);
    }
    
    public static void main(String[] args) {
        
        if (args.length < 2) {
            System.err.println("Invalid number of arguments passed");
            return;
        }

        // number of files need to be compared
        int numArgs = args.length;
        
        //Create a hash table for every file
        HashTable[] tableList = new HashTable[numArgs];
        // store the number of lines of each file
        int[] numLine = new int[numArgs];
        //Preprocessing: Read every file and create a HashTable
        
        for (int i = 0; i < numArgs; i++) {
            // create a new hash table
            tableList[i] = new HashTable(DEFAULT_SIZE);
            numLine[i] = 0;
            try {
                FileReader fr = new FileReader(args[i]);
                Scanner scanner = new Scanner(fr);
                // scan the file line by line
                // insert each line into
                while (scanner.hasNextLine()) {
                    numLine[i]++;
                    String line = scanner.nextLine();
                    tableList[i].insert(line);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
            }
        }
        
        //Find similarities across files
        
        for (int i = 0; i < numArgs; i++) {
            String fileName = args[i];
            printFileName(fileName);
            for (int j = 0; j < numArgs; j++) {
                int sameLine = 0;
                if (j != i) {
                    try {
                        FileReader fr = new FileReader(args[i]);
                        Scanner scanner = new Scanner(fr);
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (tableList[j].lookup(line)) {
                                //loop up for the same line
                                sameLine++;
                            }
                        }
                        scanner.close();
                    } catch (FileNotFoundException e) {
                    }
                    float ratio = (float) (((double) sameLine / numLine[i]) * 100);
                    printStatistics(args[j], (int) ratio);
                }
            }
        }
    }
}
