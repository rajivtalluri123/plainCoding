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

    // 5. Longest Palindromic Substring
    // Given a string s, return the longest palindromic substring in s.
    // Input: s = "babad" o/p = bab or aba
    //alg -- use 2d matrix to store prev indices are palindrome or not with some proper base case and check for all start to end combos -- dp
    public String longestPalindrome(String s) {
        int[][] dp = new int[s.length()][s.length()];
        int max =0;
        String res = "";
        //edge case
        if(s.length() == 0)
            return res;

        //base case
        res = s.substring(0,1);
        for(int i =0; i < s.length(); i++) {
            dp[i][i] =1;
            if(i !=0 && s.charAt(i-1) == s.charAt(i)) {
                max =2;
                res = s.substring(i-1, i+1);
                dp[i-1][i] = 1;
            }
        }
        //recursion
        for(int col = 2; col < s.length(); col++) {
            int row =0, currCol = col;
            while(currCol < s.length()) {
                if(s.charAt(row) == s.charAt(col) && dp[row+1][currCol -1] == 1) {
                    dp[row][currCol] = 1;
                    if(max < col - row +1) {
                        max = col -row +1; // this will be gt
                        res = s.substring(row, col+1);
                    }
                }
                row++; col++;
            }
        }
        return res;
    }

    // 279. Perfect Squares
    // Given an integer n, return the least number of perfect square numbers that sum to n.
    //A perfect square is an integer that is the square of an integer; in other words,
    // it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
    // Input: n = 12, Output: 3, Explanation: 12 = 4 + 4 + 4. n =13, o/p = 2 (9+4)
    //alg- this boils down to picking right combos of perfect sqs. recurse down from given numb to 0 using all possible perfect sqs and use min
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        return minSqSum(n, dp);
    }
    private int minSqSum(int n, int[] dp) {
        if(dp[n] !=0)
            return dp[n];
        if(n ==0 || n==1)
            return n;
        int min = Integer.MAX_VALUE;
        for(int i =1; i*i <= n; i++) {
            min = Math.min(min, minSqSum(n -i*i, dp));
        }
        dp[n] = min+1;
        return dp[n];
    }
}
