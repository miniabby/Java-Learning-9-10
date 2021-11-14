/*
 * NAME: Kechen Zhao
 * PID: A16139826
 */

import java.util.*;

/**
 * Title: PA10 class
 * Description: Class that contains PA10 methods
 */
public class PA10 {

    public static LinkedList<Integer> mergeLists(LinkedList<Integer> list1,
                                                  LinkedList<Integer> list2) {
        if (list1.isEmpty()) {
            return list2;
        } else if (list2.isEmpty()) {
            return list1;
        }
        int length = list1.size() + list2.size();
        LinkedList newList = new LinkedList();
        int left = 0;
        int right = 0;
        list1.add(Integer.MAX_VALUE);
        list2.add(Integer.MAX_VALUE);
        for (int i = 0; i < length; i++) {
            if ((list1.get(left) <= list2.get(right))) {
                newList.add(list1.get(left));
                left++;
            } else if ((list1.get(left) > list2.get(right))) {
                newList.add(list2.get(right));
                right++;
            }
        }
        return newList;
    }

    public static boolean paranChecker(String exp) {
        ArrayList paran1 = new ArrayList();
        ArrayList paran2 = new ArrayList();
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) == '(') {
                paran1.add(exp.charAt(i));
            } else if (exp.charAt(i) == ')') {
                paran2.add(exp.charAt(i));
            }
        }
        if (paran1.size() == paran2.size()) {
            return true;
        } else {
            return false;
        }
    }

    public static int stackMax(Stack<Integer> stack) {
        if (stack.empty()) {
            return 0;
        }
        int maxInt = 0;
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i) > maxInt) {
                maxInt = stack.get(i);
            }
        }
        for (int j = 0; j < stack.size(); j++) {
            if (stack.get(j) == maxInt) {
                stack.remove(j);
            }
        }
        return maxInt;
    }

    public static int elemNumHeap(PriorityQueue<Integer> minHeap, int val) {
        Iterator iter = minHeap.iterator();
        int number = 0;
        while (iter.hasNext()) {
            if ((int) iter.next() < val) {
                number++;
            }
        }
        return number;
    }


    public static ArrayList<Integer> findMissing(int [] input) {
        int size = input.length;
        int[] list = new int[size + 1];
        for (int i = 0; i < input.length; i++) {
            list[i] = 0;
        }
        for (int i = 0; i < input.length; i++) {
            int index = input[i];
            list[index] = 1;
        }
        ArrayList numberList = new ArrayList();
        for (int i = 1; i < input.length + 1; i++) {
            if (list[i] == 0) {
                numberList.add(i);
            }
        }
        return numberList;
    }

    public boolean isPolySentance(Stack <Character> stack1, Stack <Character> stack2) {
        if (stack1.size() % 2 == 1) {
            for (int i = 0; i < (stack1.size() - 1)/2; i++) {
                stack1.pop();
                stack2.add(stack1.pop());
            }
            stack1.pop();
            for (int i = 0; i < stack1.size(); i++) {
                if (stack1.pop() != stack2.pop()) {
                    return false;
                }
            }
        } else {
            for (int j = 0; j < (stack1.size()/2); j++) {
                stack1.pop();
                stack2.add(stack1.pop());
            }
            for (int j = 0; j < stack1.size(); j++) {
                if (stack1.pop() != stack2.pop()) {
                    return false;
                }
            }
        }
        return true;
    }

}
