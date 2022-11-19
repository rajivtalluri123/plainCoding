package traversal;

import java.util.*;

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

    //417. pacific and atlantic water flow
    // There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
    // The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
    //The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights
    // where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
    //The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height.
    // Water can flow from any cell adjacent to an ocean into the ocean.
    //
    //Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

    //i/p - heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
    // o/p - [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
    //alg- do dfs from both oceans side and see how many cells are reachable to pacific and how many are reachable to atlantic-- cell with both oceans reachable is our guy
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;

        int[][] pacific = new int[rows][cols];
        int[][] atlantic = new int[rows][cols];

        //do dfs from left side and right side
        for(int i =0; i < rows; i++) {
            dfs(pacific, i, 0, heights);
            dfs(atlantic, i, cols -1, heights);
        }
        // do dfs from top and bottom side
        for(int i =0; i < cols; i++) {
            dfs(pacific, 0, i, heights);
            dfs(atlantic, rows -1, i, heights );
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i =0; i < rows; i++) {
            for(int j =0; j < cols; j++) {
                if(pacific[i][j] == 1 && atlantic[i][j] == 1)
                    res.add(Arrays.asList(i, j));
            }
        }
        return res;
    }
    private void dfs(int[][] reachable, int row, int col, int[][] heights) {
        reachable[row][col] = 1;
        for(int[] dir : directions) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if(nextRow >=0 && nextRow < heights.length && nextCol >=0 && nextCol < heights[0].length
                    && heights[nextRow][nextCol] >= heights[row][col]) {
                //as we started dfs from oceans the check is reverse >= next height should be gt to  have water flowing downstream into ocean
                dfs(reachable, nextRow, nextCol, heights);
            }
        }
    }
}
