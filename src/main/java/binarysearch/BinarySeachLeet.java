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

    // 1060. Missing Element in Sorted Array
    // Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k,
    // return the kth missing number starting from the leftmost number of the array.
    //i/p -- nums= [4,7,9,10] k =3 o/p = 8
    //alg-- using additional space is not a good idea here because the difference can be very big--there is an alt approch without using space
    //find missingEle for every index -- we need last index where the missing ele are less than k-- yeah bs can be applied here
    public int missingElement(int[] nums, int k) {
        int missingCountOfLastIndex = missingCount(nums, nums.length -1);
        if(missingCountOfLastIndex < k) {
            //missing ele is out of the nums
            return nums[nums.length -1] + k - missingCountOfLastIndex;
        }

        //use binary search for finding index which has missingcount < k
        //L L L L L L E/G G G G G (find Equal/Greater and use -1 index for the alg)
        int left =0, right = nums.length -1;
        while(left < right) {
            int mid = (left + right)/2;
            if(missingCount(nums, mid) < k) {
                left = mid +1;
            } else {
                right = mid;
            }
        }
        return nums[right -1] + k - missingCount(nums, right -1) ;
    }

    private int missingCount(int[] nums, int index) {
        return nums[index] - nums[0] - index;  //nums is in sorted order
    }
}
