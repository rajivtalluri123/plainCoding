package slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//solved leet code graph pbs here
public class SlidingWindowLeet {

    //1004. Max Consecutive Ones III
    //Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

    //alg-- flipping makes this prob little tricky- we can flip k zeros, lets flip k zeros and keep it sliding
    //ex- [1,1,1,0,0,0,1,1,1,1,0], k = 2
    public int longestOnes(int[] nums, int k) {
        int left =0;
        int max = 0;
        for(int right =0; right < nums.length; right++) {

            if(nums[right] == 0)
                k--;
            //make the slide valid
            while(k < 0) {
                if(nums[left] == 0) {
                    k++;
                }
                left++;
            }
            max = Math.max(max, right -left +1);
        }
        return max;
    }

    //239. Sliding Window Maximum
    //You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
    // You can only see the k numbers in the window. Each time the sliding window moves right by one position.
    //
    //Return the max sliding window.
    //Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
    //Output: [3,3,5,5,6,7]

    //Alg- use queue with monotonic stack approch and maintain max val at the front and remove whenever it is out of scope-- for that
    //use index in queue.
    public int[] maxSlidingWindow(int[] nums, int k) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        int[] res = new int[nums.length -k +1];

        //init queue
        for(int i =0; i < k; i++) {
            while(!ad.isEmpty() && ad.peekLast() < nums[i]) {
                ad.removeLast();
            }
            ad.addLast(i);
        }
        res[0] = nums[ad.peekFirst()];

        for(int i =k; i < nums.length; i++) {
            //remove out of scope
            if(i- ad.peekFirst() == k) {
                ad.removeFirst();
            }
            while(!ad.isEmpty() && ad.peekLast() < nums[i]) {
                ad.removeLast();
            }
            ad.addLast(i);
            res[i -k+1] = ad.peekFirst();

        }
        return res;
    }

    //  76. Minimum Window Substring
    //Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every
    // character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
    //Input: s = "ADOBECODEBANC", t = "ABC"
    //o/p : BANC

    //alg-- use sliding window technique using left and right pointer-- inc right pointer till u got desired window
    // and then dec left untill wundow is desired
    //repeat the process and upate the min res string in the process
    public String minWindow(String S, String T) {
        if(S==null||S.isEmpty()||T==null||T.isEmpty()) return "";
        int left =0;

        //have map of chars in T and build it
        Map<Character, Integer> mapT = new HashMap<>();
        for(int i =0; i < T.length(); i++) {
            int count = mapT.getOrDefault(T.charAt(i), 0);
            mapT.put(T.charAt(i), count+1);
        }
        //MAP FOR slinding window
        Map<Character, Integer> mapS = new HashMap<>();

        String res ="";
        int min = Integer.MAX_VALUE;
        int formed =0;
        for(int right =0; right < S.length(); right++) {

            mapS.put(S.charAt(right), mapS.getOrDefault(S.charAt(right), 0) +1);
            if(mapT.containsKey(S.charAt(right)) && mapT.get(S.charAt(right)) >= mapS.get(S.charAt(right))) {
                formed++;  // use all teh chars in T including dups
            }

            //slide left till window is not desired
            while(formed == T.length()) {
                //update latest min
                if(right-left +1 < min) {
                    res= S.substring(left, right+1);
                    min = right -left +1;
                }
                //update window map
                mapS.put(S.charAt(left),mapS.get(S.charAt(left)) -1 );
               if( mapT.containsKey(S.charAt(left)) && mapS.get(S.charAt(left)) <  mapT.get(S.charAt(left))) {
                   formed--;
               }
               left++;
            }
        }

        return res;

    }

    // 1151. Minimum Swaps to Group All 1's Together
    // Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.
    // Input: data = [1,0,1,0,1] Output: 1, Input: data = [1,0,1,0,1,0,0,1,1,0,1] Output: 3
    // alg- problem boils down to finding out window with max ones (window of size total one's count)
    public int minSwaps(int[] data) {
        int totalOnesCount = Arrays.stream(data).sum(); //this is slower than simple for loop
        int left =0, currOnes =0, maxOnes =0;
        for(int right =0; right < data.length; right++) {
            currOnes += data[right];
            //maintain window size
            if(right -left +1 > totalOnesCount) {
                currOnes -= data[left];
                left++;
            }
            maxOnes = Math.max(currOnes, maxOnes);
        }
        return  totalOnesCount - maxOnes; // no of min swaps = toalonesCount - maxOnesWindow
    }

}
