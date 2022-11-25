package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringLeet {
    // 1427 Perform String Shifts
    // You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [directioni, amounti]:
    //
    //directioni can be 0 (for left shift) or 1 (for right shift).
    //amounti is the amount by which string s is to be shifted.
    //A left shift by 1 means remove the first character of s and append it to the end.
    //Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
    //Return the final string after all operations.
    // Input: s = "abc", shift = [[0,1],[1,2]] output "cab"
    //alg- preccompute the total shifts and do string change just once
    public String stringShift(String s, int[][] shift) {
        //use leftshifts
        int leftShifts =0;
        for(int[] move : shift) {
            if(move[0] == 1)
                leftShifts -= move[1];
            else
                leftShifts += move[1];
        }
        //left shift within the range
        leftShifts = Math.floorMod(leftShifts, s.length());  // mod with -ve is more complicated.. could have used left nd right shift and just moved the bigger one
        return s.substring(leftShifts) + s.substring(0, leftShifts);
    }

    public List<String> findRepeatedDnaSequence(String s) {
        Set<String> occuredDna = new HashSet<>();
        int left =0;
        Set<String> res = new HashSet<>(); //res should have duplicates
        for(int right = 10; right <= s.length(); right++) {
            if(occuredDna.contains(s.substring(left, right))) {
                res.add(s.substring(left, right));
            } else {
                occuredDna.add(s.substring(left, right));
            }
            left++;
        }
        return new ArrayList<>(res);
    }
}
