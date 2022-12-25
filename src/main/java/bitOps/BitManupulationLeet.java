package bitOps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BitManupulationLeet {

    // 191. Number of 1 Bits
    // Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
    // input- n = 00000000000000000000000000001011 o/p 3
    // input- n = 00000000000000000000000010000000 o/p 1
    // input- n = 11111111111111111111111111111101 o/p 31   (-2)
    public int hammingWeight(int n) {
        int sum =0;
        while(n != 0) {
            sum++;
            n = n & n-1; // it takes off last significant 1 i.e 8(1000) & 7(0111) == 0
        }
        return sum;
    }

    //136. Single Number
    // Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
    // use xor  x^x =0, x^0 = x
    public int singleNumber(int[] nums) {
        int res =0;
        for(int num : nums)  {
            res ^= num;
        }
        return res;
    }

    // 90. Subsets II
    //  Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
    //The solution set must not contain duplicate subsets. Return the solution in any order.
    // alg bit masking approch
    public List<List<Integer>> generateSubsetWithOutDups(int[] nums) {
        // we use 0 to 2^n subsets for all the combos of nums
        int maxSubSet = (int) Math.pow(2, nums.length); //nums length is limited
        List<List<Integer>> res = new ArrayList<>();
        Set<String> hash = new HashSet<>();
        for(int currSubsetIndex =0; currSubsetIndex < maxSubSet; currSubsetIndex++) {
            // if n =5  00000 to 11111 (for our knapsack approch)
            List<Integer> currSubset = new ArrayList<>();
            StringBuilder sb = new StringBuilder();

            for(int i =0; i < nums.length; i++) {
                //add all nums[i] if 1 is in currSubsetIndex
                int mask = 1 << i;
                int isTaking = currSubsetIndex & mask; // these 2 lines are for getting 1's (binary) in int val
                if(isTaking != 0) {
                    currSubset.add(nums[i]);
                    sb.append(nums[i]).append(',');
                }
            }
            if(!hash.contains(sb.toString())) {
                hash.add(sb.toString());
                res.add(currSubset);
            }

        }
        return  res;
    }

}
