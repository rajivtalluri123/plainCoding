package traversal;

import java.util.LinkedList;
import java.util.Queue;

public class MatrixTraversalLeet {
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  //left, right, down, up

    //286. Walls and Gates
    // You are given an m x n grid rooms initialized with these three possible values.
    //-1 A wall or an obstacle.
    //0 A gate.
    //INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
    //Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
    //ex - rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
    //o/p - [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]

    //alg- start bfs (traverse all the 4 directions) from the gates and cont with each visited empty node untill to touched all that u can
    public void wallsAndGate(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
        for(int i =0; i < rooms.length; i++) {
            for(int j =0; j < rooms[i].length; j++) {
                if(rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        //bfs
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i =0; i < size; i++) {
                int[] curr = queue.poll();
                for(int[] dir : directions) {
                    int nextRow = curr[0] + dir[0];
                    int nextCol = curr[1] + dir[1];
                    if(nextRow >=0 && nextRow < rooms.length && nextCol >= 0 && nextCol < rooms[nextRow].length
                            && rooms[nextRow][nextCol] > rooms[curr[0]][curr[1]] +1) {
                        //when teh next cell is within boarder and value needs to update
                        rooms[nextRow][nextCol] = level +1;
                        queue.add(new int[] {nextRow, nextCol});
                    }
                }
            }
            level++;
        }
        //all possible rooms are updated by now
    }
}
