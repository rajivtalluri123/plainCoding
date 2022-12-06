package stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class StackLeet {
    //for pb 394. Decode String
    int index =0;


    // 84. Largest Rectangle in Histogram
    // Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
    // return the area of the largest rectangle in the histogram.
    // Input: heights = [2,1,5,6,2,3] Output: 10
    // Alg-- for every index find small index on left and right side using stacks for O(N) sol
    public int largestRectangleArea(int[] heights) {
        //keep the elements in stack in incrasing order-- as we want next small value
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        int[] leftMins = new int[heights.length];
        int[] rightMins = new int[heights.length];
        for(int i =0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                leftMins[i] = 0; // no small value for this index-- so rectangle will have all histograms on left
            } else {
                leftMins[i] = stack.peek() +1;
            }
            stack.push(i); // use index
        }
        stack.clear(); // use same stack for right mins
        for(int i = heights.length -1; i >=0; i--) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i])
                stack.pop();
            if(stack.isEmpty()) {
                rightMins[i] = stack.peek() -1;
            } else {
                rightMins[i] = heights.length -1;
            }
            stack.push(i);
        }
        //find out rec areas for each index and find max out of them
        for(int i =0; i < heights.length; i++) {
            max = Math.max(max, (rightMins[i] - leftMins[i] +1)*heights[i]); //rec area formula
        }

        return max;

    }
    //862. Shortest Subarray with Sum at Least K
    // 862. Shortest Subarray with Sum at Least K
    // Given an integer array nums and an integer k,
    // return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.
    // ip- [1, 2] , 4 o/p- -1 | [2,-1,2], 3 o/p- 3
    //alg- due to -ve numb we cannt use straight forward 2 pointer slidingwindow technique. Instead we use prefixSum with montonic inc queue to be
    // able to mmake prefixSum only increasing and be able to use slidingwindow where we can inc left pointer like pb 209
    public int shortestSubArray(int[] nums, int k) {
        //make a prefixSum array
        int[] prefixSum = new int[nums.length +1];
        for(int i=0; i < nums.length; i++)
            prefixSum[i+1] = prefixSum[i] + nums[i];
        Deque<Integer> deque = new LinkedList<>();
        int min = nums.length +1;
        for(int i =0; i < nums.length; i++) {
            while(!deque.isEmpty() && prefixSum[deque.peek()] >= prefixSum[i]) {
                deque.removeLast(); //make the deque monotic in
            }
            //do the min cal
            while(!deque.isEmpty() && prefixSum[i] - prefixSum[deque.getFirst()] >= k) {
                min = Math.min(min, i- deque.removeFirst());
            }
            deque.add(i);
        }
        return min;
    }


    //907. Sum of Subarray Minimums


    // 394. Decode String
    // Given an encoded string, return its decoded string.
    //The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
    //You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
    //The test cases are generated so that the length of the output will never exceed 10^5.
    // Input: s = "3[a]2[bc]"  | "3[a2[c]]"
    //Output: "aaabcbc"        |  accaccacc
    // alg- can be done completely using stack. but using recursion-- decode inner string and then outter one
    public String decodeString(String encode) {
        StringBuilder sb = new StringBuilder();
        //2 nd condition is for inner recursions but outter is also handled
        while(index < encode.length() && encode.charAt(index) != ']') {
            if(!Character.isDigit(encode.charAt(index))) {
                sb.append(encode.charAt(index++));
            } else {
                //build k
                int k =0;
                while(index < encode.length() && Character.isDigit(encode.charAt(index))) {
                    k = encode.charAt(index++) - '0' + k*10;
                }
                index++; //ignore[
                String inner = decodeString(encode);
                index++; //ignore ]
                while(k > 0) {
                    sb.append(inner);
                }
            }
        }
        return new String(sb);

    }

}
