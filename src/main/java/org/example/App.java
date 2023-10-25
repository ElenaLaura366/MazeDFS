package org.example;

import backend.Graph;
import frontend.Maze;

import javax.swing.*;
import java.util.Stack;

public class App 
{
    public static void main(String[] args) {

        Maze maze = new Maze();
        int[][] matrix = maze.getMatrix();
        Graph matrixDFS = new Graph(matrix);
        Stack<Integer> path = matrixDFS.DFS(maze.getRowStart(), maze.getColStart(), maze.getRowEnd(), maze.getColEnd());
        maze.setGreenCells(path);
        JFrame frame = new JFrame("Labyrinth"); // make a frame
        frame.add(maze);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make the program to stop when the frame is  closed
        frame.setSize(800, 700); // the size of the frame
        frame.setLocationRelativeTo(null); // open the frame in the center of the screen
        frame.setVisible(true); // make the frame visible
    }
}
