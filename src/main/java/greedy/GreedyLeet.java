package greedy;

public class GreedyLeet {

    // Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
    //Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
    // i/p s = "abccccdd" o/p = 7 (dccaccd)
    //alg- keep the count of occurence of each char in a string. palindrome can be formwd using all evens nd 1 odd . add that odd greedily
    public int longestPalindrome(String s) {
        int count =0;
        char[] array = new char[128];
        for(int i =0; i < s.length(); i++)
            array[s.charAt(i)]++;
        for(int charCount : array) {
            //make the curr count even
            count += (charCount/2) *2;
            if(count % 2 ==0 && charCount % 2 ==1)
                count++;  // only one is possible -- so we added one for a random char
        }
        return count;
    }
}
