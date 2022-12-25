package backtrack;

import java.util.*;

public class BackTrackLeet {
    //for pb 17
    Map<Character, List<Character>> map = new HashMap<>();
    List<String> res = new ArrayList<>();


    /////////////Level 2 problems--------


    //17. Letter Combinations of a Phone Number
    //Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
    //A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
    public List<String> letterCombinations(String digits) {

        //edge case
        if(digits.isEmpty())
            return res;
        //buildmap -- to store number- chars mapping
        buildMap(digits);
        backtracking (0, digits, new StringBuilder());
        return res;
    }
    private void backtracking(int i, String digits, StringBuilder sb) {
        if(i == digits.length()) {
            res.add(sb.toString());
            return;
        }

        for(Character ch : map.get(digits.charAt(i))) {
            sb.append(ch);
            backtracking(i+1, digits, sb);
            sb.setLength(sb.length() -1);
        }
    }
    private void buildMap(String digits) {
        for(int i = 0; i < digits.length(); i++) {
            char numb = digits.charAt(i);
            if(numb == '2')
                map.put('2', Arrays.asList('a', 'b', 'c'));

            if(numb == '3')
                map.put('3', Arrays.asList('d', 'e', 'f'));
            if(numb == '4')
                map.put('4', Arrays.asList('g', 'h', 'i'));
            if(numb == '5')
                map.put('5', Arrays.asList('j', 'k', 'l'));
            if(numb == '6')
                map.put('6', Arrays.asList('m', 'n', 'o'));
            if(numb == '7')
                map.put('7', Arrays.asList('p', 'q', 'r', 's'));
            if(numb == '8')
                map.put('8', Arrays.asList('t', 'u', 'v'));
            if(numb == '9')
                map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        }
    }

    // 90. Subsets II
    //  Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
    //The solution set must not contain duplicate subsets. Return the solution in any order.
    // alg backtracking approch
    public List<List<Integer>> generateSubsetWithOutDups(int[] nums) {
        // cal all combos of all lengths using backtracking
        Arrays.sort(nums); // to avoid dups
        List<List<Integer>> res = new ArrayList<>();
        for(int i =0; i < nums.length; i++) {
            backtracking(nums, res, i, 0, new ArrayList<Integer>());
        }
        return  res;
    }
    private void backtracking(int[] nums, List<List<Integer>> res, int length, int start, List<Integer> currSubset) {
        if(currSubset.size() == length) {
            //end recursion for the current subset
            res.add(currSubset);
        }
        for(int i = start; i < nums.length; i++) {
            //avoid dups
            if(i != 0 && nums[i-1] == nums[i])
                continue;
            currSubset.add(nums[i]);
            backtracking(nums, res, length, i+1, currSubset);
            currSubset.remove(currSubset.size() -1);
        }

    }
}
