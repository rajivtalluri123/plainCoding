package dp;

public class dpLeet {


    /////////////Level 2 problems--------

    //213. House Robber II
    //You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
    //Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

    //sol- use dp to sol the prob-- but due to circle condition- get the max of (0 to n-2) and (1 to n-1) as 0 and n-1 together cannt include in the rob
    public int rob(int[] nums) {
        int n = nums.length;
        //base cases
        if(n ==0)
            return 0;
        if(n==1)
            return nums[0];
        return Math.max(rob(nums, 0, n-2), rob(nums, 1, n-1));
    }
    private int rob(int[] nums, int start, int end) {
        //use 2 pointers t1, t2 instead of dp array as we only req last 2 computed vals all the time
        int t1 =0, t2 =0;
        for(int i =start; i <= end; i++) {
            int curr = Math.max(nums[i] +t2, t1);
            t2 = t1;
            t1 = curr;
        }
        return t1;
    }
}
