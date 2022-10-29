package binarysearch;

public class BinarySeachLeet {

    ///////////////Level 2 problems--------

    //34. Find First and Last Position of Element in Sorted Array
    //Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
    //If target is not found in the array, return [-1, -1].
    //You must write an algorithm with O(log n) runtime complexity.

    //sol -- using bs template 11 for finding start index and end index
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0)
            return new int[]{-1, -1};
        int start = findstartOrEndIndex(nums, target, true);
        if(  nums[start] != target)
            return new int[]{-1, -1};
        int end = findstartOrEndIndex(nums, target, false);
        return new int[]{start, end-1};
    }
    private int findstartOrEndIndex(int[] nums, int target, boolean start) {
        int left =0, right = (start) ? nums.length -1 : nums.length; // c
        while(left < right) {
            int mid = (left + right)/2;
            boolean condition = (start) ? nums[mid] >= target : nums[mid] > target;
            if(condition) {
                right = mid;
            } else {
                left = mid +1;
            }
        }
        return right; //or left
    }
}
