package frontend;

import backend.Graph;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Maze extends JPanel {
    private int[][] matrix;
    private int rowStart, rowEnd, colStart, colEnd;
    private Stack<Integer> greenCells;
    private Timer animationTimer;
    public Maze() {
        this.readFromFile();

        int rows = matrix.length;
        int columns = (matrix.length == 0) ? 0 : matrix[0].length;

        if (columns == 0) {
            System.err.println("The matrix is empty. Please check the file content or the file reading logic.");
            return;
        }

        // Traverse the matrix to set the start and end points
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (matrix[r][c] == 2) {
                    this.rowStart = r;
                    this.colStart = c;
                } else if (matrix[r][c] == 3) {
                    this.rowEnd = r;
                    this.colEnd = c;
                }
            }
        }

        // Calculate the DFS path after you've set the start and end points
        Graph dfs = new Graph(matrix);
        this.greenCells = dfs.DFS(rowStart, colStart, rowEnd, colEnd);

        this.setLayout(new GridLayout(rows, columns));

        // Go through the matrix again to color the cells
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                JPanel panel = new JPanel();

                if (matrix[r][c] == 0) {
                    panel.setBackground(Color.BLACK);
                } else if (matrix[r][c] == 1) {
                    panel.setBackground(Color.WHITE);
                } else if (matrix[r][c] == 2) {
                    panel.setBackground(Color.RED);
                } else {
                    panel.setBackground(Color.BLUE);
                }

                //panel.setBorder(new LineBorder(Color.BLACK, 1));
                this.add(panel);
            }
        }
        animationTimer = new Timer(600, e -> updateMazeAnimation());
        animationTimer.start();
    }

    private void updateMazeAnimation() {
        if (greenCells.isEmpty()) {
            animationTimer.stop();
            return;
        }

        int cellIndex = greenCells.pop();

        Component component = getComponent(cellIndex);
        if (component instanceof JPanel) {
            component.setBackground(Color.GREEN);
        }

        repaint();
    }

    public void setGreenCells(Stack<Integer> cells) {
        this.greenCells = cells;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void readFromFile() {
        final String SPACE_SEPARATOR = " ";
        String filePath = "labirint.txt";
        List<int[]> tempList = new ArrayList<>();

        try (BufferedReader bufferFile = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferFile.readLine()) != null) {
                int[] row = Arrays.stream(line.split(SPACE_SEPARATOR))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                tempList.add(row);
            }

            matrix = tempList.toArray(new int[tempList.size()][]);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getRowStart() {
        return rowStart;
    }

    public int getRowEnd() {
        return rowEnd;
    }

    public int getColStart() {
        return colStart;
    }

    public int getColEnd() {
        return colEnd;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
