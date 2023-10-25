package backend;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Graph {
    private final int rows;
    private final int cols;
    private final List<Integer>[] graph;
    private final int[][] matrix;

    public Graph(int[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.graph = new LinkedList[rows * cols];

        for (int i = 0; i < rows * cols; i++) {
            graph[i] = new LinkedList<>();
        }

        buildGraph();
    }

    private void buildGraph() {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (matrix[r][c] == 1 || matrix[r][c] == 2 || matrix[r][c] == 3) {
                    for (int[] dir : dirs) {
                        int newRow = r + dir[0];
                        int newCol = c + dir[1];
                        if (isValid(newRow, newCol) && (matrix[newRow][newCol] == 1 || matrix[newRow][newCol] == 3)) {
                            graph[getIndex(r, c)].add(getIndex(newRow, newCol));
                        }
                    }
                }
            }
        }
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    private int getIndex(int r, int c) {
        return r * cols + c;
    }

    private boolean DFSUtil(int start, int end, boolean[] visited, Stack<Integer> path) {
        if (start == end) {
            path.push(start);
            return true;
        }

        visited[start] = true;
        for (int neighbor : graph[start]) {
            if (!visited[neighbor]) {
                if (DFSUtil(neighbor, end, visited, path)) {
                    path.push(start);
                    return true;
                }
            }
        }

        return false;
    }

    public Stack<Integer> DFS(int startRow, int startCol, int endRow, int endCol) {
        int start = getIndex(startRow, startCol);
        int end = getIndex(endRow, endCol);
        boolean[] visited = new boolean[rows * cols];
        Stack<Integer> path = new Stack<>();

        DFSUtil(start, end, visited, path);

        return path;
    }
}
