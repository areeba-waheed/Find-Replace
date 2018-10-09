package main;

import javax.swing.*;

/**
 *  Areeba Waheed
 *  OCT 9, 2018
 *  Comp 585: GUI
 *  Project 2: Find/Replace Finder
 *  Main Class
 **/
public class Main {

    public static void main(String[] args) {

        JFrame frame = new FindReplace();
        frame.setTitle("Find & Replace");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
