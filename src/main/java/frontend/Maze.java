package frontend;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Maze extends JPanel {
    private final List<List<Integer>> matrix;

    public Maze() {
        this.matrix = new ArrayList<>();

        this.readFromFile();

        int rows = matrix.size();
        int columns = 0;
        if (!matrix.isEmpty() && !matrix.get(0).isEmpty()) {
            columns = matrix.get(0).size();
        }
        else{
            System.err.println("The matrix is empty. Please check the file content or the file reading logic.");
            return;
        }
        this.setLayout(new GridLayout(rows, columns));

        for (List<Integer> integers : matrix) {
            for (Integer integer : integers) {
                JPanel panel = new JPanel();
                if (integer == 0) {
                    panel.setBackground(Color.BLACK);
                } else if(integer == 1){
                    panel.setBackground(Color.WHITE);
                } else if(integer == 2){
                    panel.setBackground(Color.RED);
                }
                else{
                    panel.setBackground(Color.BLUE);
                }
                panel.setBorder(new LineBorder(Color.BLACK, 1));
                panel.setSize(20, 20);
                this.add(panel);
            }
        }

    }

    public void readFromFile() {
        final String SPACE_SEPARATOR = " ";
        String filePath = "labirint.txt";

        try (BufferedReader bufferFile = new BufferedReader(new FileReader(filePath))) {
            bufferFile.lines()
                    .map(line -> Arrays.stream(line.split(SPACE_SEPARATOR)).map(Integer::parseInt)
                            .collect(Collectors.toList()))
                    .forEach(this.matrix::add);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}

