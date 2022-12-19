package hash;

import java.util.TreeMap;

public class OrderedSet {

    TreeMap<Integer, Integer> calendar = new TreeMap<>();
    // 729. My Calendar I
    // pb- You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.
    //A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).
    //The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
    //Implement the MyCalendar class:
    //MyCalendar() Initializes the calendar object.
    //boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

    //input- ["MyCalendar", "book",     "book",     "book"]
    //        [[],          [10, 20],   [15, 25],     [20, 30]]
    //output- [null, true, false, true]

    //alg- I initially cameup with sorted list and binary search approch- but using treemap with start as key and end as val is more graceful sol here
    public boolean book(int start, int end) {
        Integer prev = calendar.floorKey(start), next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) && (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;
    }
}
