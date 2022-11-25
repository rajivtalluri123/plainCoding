package arrays;

import java.util.HashMap;
import java.util.Map;

public class ArraysLeet {

    // 325. Maximum Size Subarray Sum Equals k
    // Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. If there is not one, return 0 instead.
    // Input: nums = [1,-1,5,-2,3], k = 3, Output: 4  Input: nums = [-2,-1,2,1], k = 1 Output: 2
    // alg -- cal the prefixSum and use hash table to find out needed pair with max dist (instead of looping twice)
    public int maxSubArrayLen(int[] nums, int k) {
        int[] prefixSum = new int[nums.length];
        int sum =0;
        for(int i =0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }
        Map<Integer, Integer> sumIndex = new HashMap<>();
        sumIndex.put(0, -1);
        int maxDiff = 0;
        for(int i =0; i < nums.length; i++) {
            if (sumIndex.containsKey(prefixSum[i] - k)) {
                maxDiff = Math.max(maxDiff, i - sumIndex.get(prefixSum[i] - k));
            }
            if (!sumIndex.containsKey(prefixSum[i]))
                sumIndex.put(prefixSum[i], i); //for max diff we dont need to save later same sum
        }
        return  maxDiff;
    }
}
