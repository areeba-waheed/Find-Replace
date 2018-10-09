package tests;

import main.ActionsPerformed;
import org.junit.Test;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Testing {
    ActionsPerformed ap;
    File file;
    String word;
    String replace;
    JCheckBox box;
    ArrayList<String> wordList;


    @Test
    //Tests the FindWords method
    public void findWordsTesting() throws IOException {
        wordList = new ArrayList<>();
        ap = new ActionsPerformed(wordList);
        file = new File("/Users/Maaz/Desktop/poo/file.txt");
        word = "asma";
        box = new JCheckBox();
        box.setSelected(true);
        ap.findWords(file, word, box);

        assertEquals("Counts of words in a file", 1, ap.getWordListSize());
    }

    @Test
    //Tests the FindWords method
    public void findWordsTesting2() throws IOException {
        wordList = new ArrayList<>();
        ap = new ActionsPerformed(wordList);
        file = new File("/Users/Maaz/Desktop/poo/file.txt");
        word = "poop";
        box = new JCheckBox();
        box.setSelected(true);
        ap.findWords(file, word, box);

        assertEquals("Counts of words in a file", 3, ap.getWordListSize());
    }

    @Test
    public void replaceWords() throws IOException {
        wordList = new ArrayList<>();
        ap = new ActionsPerformed(wordList);
        file = new File("/Users/Maaz/Desktop/poo/file.txt");
        word = "asma";
        replace = "areeba";
        box = new JCheckBox();
        box.setSelected(true);
        ap.replaceWords(file, word, replace, box);
        ap.findWords(file, word, box);

        assertEquals("Counts of replaced word in a file now", 0, ap.getWordListSize());
    }




}
