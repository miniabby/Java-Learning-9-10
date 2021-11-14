/*
 * NAME: Kechen Zhao
 * PID: A16139826
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HashTableTester {

    private HashTable hashTable;
    private HashTable emptyTable;

    /**
     * Standard Test Fixture.
     */
    @Before
    public void setup() {
        emptyTable = new HashTable(10);
        hashTable = new HashTable(10, "empty");
        hashTable.insert("a");
        hashTable.insert("b");
        hashTable.insert("c");
        hashTable.insert("d");
        hashTable.insert("e");
        hashTable.insert("f");
        hashTable.insert("g");
        hashTable.insert("h");
        hashTable.insert("i");
        hashTable.insert("j");
        hashTable.insert("k");
        hashTable.insert("l");
    }


    /**
     * test case for getSize()
     */
    @Test
    public void testgetSize() {
        assertEquals(12, hashTable.getSize());
        hashTable.insert("hello");
        assertEquals(13, hashTable.getSize());
        assertEquals(0, emptyTable.getSize());
    }

    /**
     * test case for insert()
     */
    @Test
    public void testInsert() {
        assertTrue(hashTable.insert("abigail"));
        assertFalse(hashTable.insert("a"));
        assertFalse(hashTable.insert("k"));
    }

    @Test (expected = NullPointerException.class)
    public void testInsertNPE() {
        hashTable.insert(null);
        emptyTable.insert(null);
    }

    /**
     * test case for delete()
     */
    @Test
    public void testDelete() {
        assertTrue(hashTable.delete("k"));
        assertFalse(hashTable.delete("hello"));
        assertFalse(hashTable.delete("m"));
    }

    @Test (expected = NullPointerException.class)
    public void testDeleteNPE() {
        hashTable.delete(null);
        emptyTable.delete(null);
    }

    /**
     * test case for lookUp()
     */
    @Test
    public void testLookup() {
        assertTrue(hashTable.lookup("c"));
        assertTrue(hashTable.lookup("l"));
        assertFalse(hashTable.lookup("hello"));
        assertFalse(emptyTable.lookup("a"));
    }

    @Test (expected = NullPointerException.class)
    public void testLookupNPE() {
        hashTable.lookup(null);
        emptyTable.lookup(null);
    }

    /**
     * test case for printTable()
     */
    @Test
    public void testprintTable() {
        hashTable.printTable();
        emptyTable.printTable();
    }

    @Test
    public void testStatistic() {
        hashTable.printStatistics();
    }
}

