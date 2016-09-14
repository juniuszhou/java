package Google;

import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int row;
    int col;
    public Pair(int i, int j) {row = i; col = j;}
}

public class Solution3 {
    boolean[][] visit;
    int row;
    int col;

    public int numIslands(char[][] grid) {
        return findSeed(grid);
    }

    public void bfs(char[][] grid, Pair seed) {
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(seed);
        while (!queue.isEmpty()) {
            Pair s = queue.poll();
            visit[s.row][s.col] = true;
            if (s.row > 0 && !visit[s.row-1][s.col] && grid[s.row-1][s.col] == '1') queue.add(new Pair(s.row-1, s.col));
            if (s.row < row-1 && visit[s.row+1][s.col] && grid[s.row+1][s.col] == '1') queue.add(new Pair(s.row+1, s.col));
            if (s.col > 0 && visit[s.row][s.col-1] && grid[s.row][s.col-1] == '1') queue.add(new Pair(s.row, s.col-1));
            if (s.col < col-1 && visit[s.row][s.col+1] && grid[s.row][s.col+1] == '1') queue.add(new Pair(s.row, s.col+1));
        }
    }

    public int findSeed(char[][] grid) {
        int result=0;
        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++) {
                if (grid[i][j] == '1' && !visit[i][j]) {
                    bfs(grid, new Pair(i, j));
                    result++;
                }
            }
        return result;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.poll());
        queue.peek();
        queue.add(1);
    }
}
