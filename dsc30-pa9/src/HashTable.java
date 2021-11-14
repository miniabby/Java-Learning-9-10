/*
 * NAME: Kechen Zhao
 * PID: A16139826
 */

/**
 * Title: HashTable.java
 * Description: File that can create a hash table.
 */

import javax.print.DocFlavor;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A class that implements a hash table and its associated methods
 */
public class HashTable implements IHashTable {
    private LinkedList<String>[] array; //Array that stores linkedlists
    private int numElem; //Number of element stored in the hash table
    private int expand; //Times that the table is expanded
    private int collision; //Times of collisions occurs
    private String statsFileName;
    private boolean printStats = false;
    private int size; //the size of HashTable
    //private static DecimalFormat df2 = new DecimalFormat("#.##");
    //private int loadFactor = numElem/size;
    private int THRESHOLD = 2 / 3;
    private int RESIZE = 2;

    private int loadFactor() {
        if (size != 0) {
            return numElem / size;
        } else {
            return 0;
        }
    }
    /**
     * Constructor for hash table
     */
    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        array = new LinkedList[size];
        this.size = size;
        numElem = 0;
        expand = 0;
        collision = 0;
    }
    
    @SuppressWarnings("unchecked")
    public HashTable(int size, String fileName) {
        array = new LinkedList[size];
        this.size = size;
        this.statsFileName = fileName;
        numElem = 0;
        expand = 0;
        collision = 0;
    }

    /**
     * Insert the string value into the hash table
     * @param value value to insert
     * @return true if the value was inserted, false if the value was already
     * present
     */
    @Override
    public boolean insert(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int hashValue = this.hashString(value);
        //if value is already in the hash table
        if (array[hashValue] == null) {
            array[hashValue] = new LinkedList<>();
        }
        if (array[hashValue].contains(value)) {
            return false;
        } else if (!array[hashValue].contains(value)) {
            //count the number of collision
            if (array[hashValue].size() != 0) {
                collision++;
            }
            //add the value into table and return true
            array[hashValue].add(value);
            numElem++;
            //resize the hash table
            int loadFactor = this.loadFactor();
            if (loadFactor > THRESHOLD) {
                rehash();
                expand++;
            }
            return true;
        }
        return false;
    }

    /**
     * Delete the given value from the hash table
     * @param value value to delete
     * @return true if the value was deleted, false if the value was not found
     */
    @Override
    public boolean delete(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int hashValue = this.hashString(value);
        //if value not exists in hash table
        if (array[hashValue] == null || !array[hashValue].contains(value)) {
            return false;
        } else {
            //remove this value from hash table and return true
            array[hashValue].remove(value);
            numElem--;
            if (array[hashValue].size() > 0) {
                collision--;
            }
            return true;
        }
    }

    /**
     * Check if the given value is present in the hash table
     * @param value value to look up
     * @return true if the value was found, false if the value was not found
     */
    @Override
    public boolean lookup(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int hashValue = this.hashString(value);
        if (array[hashValue] != null && array[hashValue].contains(value)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Print the contents of the hash table to stdout.
     */
    @Override
    public void printTable() {
        for (int i = 0; i < this.size; i++) {
            String line = i + ": ";
            if (array[i] == null) {
                System.out.println(line);
            } else {
                for (int j = 0; j < array[i].size(); j++) {
                    String element = array[i].get(j).toString();
                    if (j != array[i].size() - 1) {
                        line = line + element + ", ";
                    } else {
                        line = line + element;
                    }
                }
                System.out.println(line);
            }
        }
    }
    
    @Override
    public int getSize() {
        return this.numElem;
    }


    private int hashString(String value) {
        int hashValue = 0;
        //add all ASCII values of each character
        for (int i = 0; i < value.length(); i++) {
            char chara = value.charAt(i);
            int ascii = chara & 0xff;
            hashValue = ascii + hashValue;
        }
        return hashValue % this.size;
    }
    
    /**
     * Expands the array and rehashes all values.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        //double the capacity of hash table and rehash all elements
        this.printStatistics();
        LinkedList[] newArray = new LinkedList[size * RESIZE];
        int originSize = this.size;
        this.size = originSize * RESIZE;
        expand++;
        collision = 0;
        for (int i = 0; i < originSize; i++) {
            if (array[i] != null) {
                for (int j = 0; j < array[i].size(); j++) {
                    int hashValue = hashString(array[i].get(j));
                    if (newArray[hashValue] == null) {
                        newArray[hashValue] = new LinkedList();
                    }
                    if (newArray[hashValue].size() != 0) {
                        collision++;
                    }
                    newArray[hashValue].add(array[i].get(j));
                    int loadFactor = this.loadFactor();
                    if (loadFactor > THRESHOLD) {
                        rehash();
                        expand++;
                    }
                }
            }
        }
        array = newArray;
    }
    
    /**
     * Print statistics to the given file.
     * @return True if successfully printed statistics, false if the file
     *         could not be opened/created.
     */
    //@Override
    public boolean printStatistics() {
        if (this.statsFileName == null) {
            printStats = false;
        } else {
            try {
                printStats = true;
                PrintStream printFile = new PrintStream(statsFileName);
                int loadFactor = this.loadFactor();
                int count = 0;
                int maxLenght = 0;
                for (int i = 0; i < array.length; i++) {
                    if (array[i] != null && array[i].size() > maxLenght) {
                        maxLenght = array[i].size();
                    }
                }
                for (int j = 0; j < array.length; j++) {
                    if (array[j] != null && array[j].size() == maxLenght) {
                        count++;
                    }
                }
                DecimalFormat df2 = new DecimalFormat("#.##");
                printFile.print(expand + "resizes, load factor "
                        + df2.format(loadFactor) + ", " + collision
                        + "collisions, " + count + " longest chain");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return printStats;
            }
        }
        return printStats;
    }
}
