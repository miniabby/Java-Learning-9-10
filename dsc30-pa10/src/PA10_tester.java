/*
 * NAME: Kechen Zhao
 * PID: A16139826
 */

import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

import static org.junit.Assert.*;

public class PA10_tester {

    @Test
    public void testMergeList() {
        System.out.println((int) Math.log(2+1)%10);
        LinkedList list1 = new LinkedList();
        list1.add(1);
        list1.add(3);
        list1.add(5);
        list1.add(6);
        LinkedList list2 = new LinkedList();
        list2.add(2);
        list2.add(4);
        LinkedList output = new LinkedList();
        output.add(1);
        output.add(2);
        output.add(3);
        output.add(4);
        output.add(5);
        output.add(6);
        LinkedList empty = new LinkedList();
        LinkedList list3 = new LinkedList();
        list3.add(1);
        LinkedList list4 = new LinkedList();
        list4.add(2);
        list4.add(3);
        LinkedList output2 = new LinkedList();
        output2.add(1);
        output2.add(2);
        output2.add(3);
        assertEquals(output, PA10.mergeLists(list1, list2));
        assertEquals(list1, PA10.mergeLists(list1, empty));
        assertEquals(list2, PA10.mergeLists(list2, empty));
        assertEquals(output2, PA10.mergeLists(list3, list4));
    }

    @Test
    public void testParenthesis() {
        String string1 = "()()()";
        String string2 = "(())(";
        String string3 = "((())())";
        assertTrue(PA10.paranChecker(string1));
        assertTrue(PA10.paranChecker(string3));
        assertFalse(PA10.paranChecker(string2));
    }

    @Test
    public void teststackMax() {
        Stack stack1 = new Stack();
        stack1.add(1);
        stack1.add(2);
        stack1.add(3);
        stack1.add(7);
        stack1.add(4);
        assertEquals(7, PA10.stackMax(stack1));
        assertEquals(4, stack1.size());
        Stack stack2 = new Stack();
        stack2.add(1);
        stack2.add(7);
        stack2.add(2);
        stack2.add(7);
        stack2.add(3);
        stack2.add(7);
        stack2.add(4);
        assertEquals(7, PA10.stackMax(stack2));
        assertEquals(4, stack2.size());
        Stack empty = new Stack();
        assertEquals(0, PA10.stackMax(empty));
    }

    @Test
    public void testelemNumHeap() {
        PriorityQueue minHeap1 = new PriorityQueue();
        minHeap1.add(1);
        minHeap1.add(2);
        minHeap1.add(3);
        minHeap1.add(4);
        minHeap1.add(5);
        minHeap1.add(6);
        assertEquals(0, PA10.elemNumHeap(minHeap1, 1));
        assertEquals(3, PA10.elemNumHeap(minHeap1, 4));
        assertEquals(6, PA10.elemNumHeap(minHeap1, 10));
    }

    @Test
    public void testfindMissing() {
        int[] array1 = {1, 1, 2, 2};
        ArrayList output1 = new ArrayList();
        output1.add(3);
        output1.add(4);
        assertEquals(output1, PA10.findMissing(array1));
        int[] array2 = {1, 1, 2, 6, 3, 5};
        ArrayList output2 = new ArrayList();
        output2.add(4);
        assertEquals(output2, PA10.findMissing(array2));
        int[] array3 = {1, 2, 3, 4, 5};
        ArrayList output3 = new ArrayList();
        assertEquals(output3, PA10.findMissing(array3));
    }


}
