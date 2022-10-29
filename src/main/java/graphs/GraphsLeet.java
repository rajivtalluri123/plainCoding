package graphs;

import java.awt.*;

import java.util.*;
import java.util.List;

//solved leet code graph pbs here
public class GraphsLeet {

    /////////////Level 2 problems--------

    //815. Bus Routes
    //You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
    //
    //For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
    //You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
    //
    //Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

    //sol - the idea in the sol is imagine buses as nodes (not bus stops) and link buses bi directional if they have any stop in commom. do bfs from buses which as source as a stop till u find a bus with stop target-- return the depth of bfs
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target)
            return 0;
        //think bus as a nodes-- connect nodes if 2 buses have commom stop
        Set<Integer> seen = new HashSet<>();
        Set<Integer> targetBus = new HashSet<>();

        //bfs from multiple sources (buses with source) and multiple target for shortes path.
        Queue<Point> queue = new ArrayDeque<>(); //pair of bus and depth---no of busses currently

        List<List<Integer>> graph = new ArrayList();  //graph for buses connection
        for(int i =0; i < routes.length; i++) {
            Arrays.sort(routes[i]);
            graph.add(new ArrayList<Integer>());
        }
        //build graph
        for(int i =0; i < routes.length;i++) {
            for(int j =i+1; j < routes.length; j++) {
                if(haveCommonStop(routes[i], routes[j])) {
                    //birectional
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        //add source and target buses
        for(int i=0; i < routes.length;i++) {
            if(Arrays.binarySearch(routes[i], source) >=0) {
                queue.offer(new Point(i,1));
                seen.add(i);
            }

            if(Arrays.binarySearch(routes[i], target) >=0) {
                targetBus.add(i);
            }
        }

        //start bfs to get shortest nof of buses for sour e to target
        while(!queue.isEmpty()) {
            Point info = queue.poll();
            int node = info.x, depth = info.y;
            if(targetBus.contains(node)) return depth;
            for(Integer neigh : graph.get(node)) {
                if(!seen.contains(neigh)) {
                    seen.add(neigh);
                    queue.offer(new Point(neigh, depth+1));
                }
            }
        }

        return -1;
    }
    private boolean haveCommonStop(int[] bus1, int[] bus2) {
        int i =0, j =0;
        while(i < bus1.length && j < bus2.length) {
            if(bus1[i] == bus2[j]) {
                return true;
            }
            if(bus1[i] < bus2[j])
                i++;
            else
                j++;
        }
        return false;
    }


//997. Find the Town Judge
    //In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.
// If the town judge exists, then:1. The town judge trusts nobody.2. Everybody (except for the town judge) trusts the town judge.
//There is exactly one person that satisfies properties 1 and 2.
//You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.
//Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

    //sol -- convert the graph into inwarda and outward array
    public int findJudge(int n, int[][] trust) {
        //trust lengt should be n-1 to have a judge
        if(trust.length < n-1) return -1;
        int[] outBounds = new int[n+1];
        int[] inBounds = new int[n+1];
        for(int[] pair : trust) {
            outBounds[pair[0]]++;
            inBounds[pair[1]]++;
        }
        //to have a judge outbound for judge should be 0 and inbound should be n-1
        for(int i =1; i <=n; i++) {
            if(outBounds[i] == 0 && inBounds[i] == n-1) return i;
        }

        return -1;
    }

}
