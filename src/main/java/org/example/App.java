package org.example;

import frontend.Maze;
import javax.swing.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Maze maze = new Maze();
        JFrame frame = new JFrame("Labyrinth"); // make a frame
        frame.add(maze);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make the program to stop when the frame is  closed
        frame.setSize(800, 700); // the size of the frame
        //frame.setLayout(null);
        frame.setLocationRelativeTo(null); // open the frame in the center of the screen
        frame.setVisible(true); // make the frame visible
    }
}
