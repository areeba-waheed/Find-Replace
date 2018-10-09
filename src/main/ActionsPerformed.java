package main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ActionsPerformed extends Component {

    private ArrayList<String> wordList;

    public ActionsPerformed(ArrayList<String> wordList){
        this.wordList = wordList;
    }

    public void findWords(File file, String word, JCheckBox wholeCase) throws IOException {
        wordList = new ArrayList<>();
        try {
            String s="";
            int count =0;
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while( (s=bf.readLine() )!=null) {
                count++;
                if(wholeCase.isSelected()) {
                    boolean found = Arrays.asList(s.split(" ")).contains(word);
                    if(found) {
                        wordList.add("Line number: " + count + " " + s + "\n");
                    }
                }
                else {
                    if (s.contains(word)) {
                        wordList.add("Line number: " + count + " " + s + "\n");
                    }
                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public int getWordListSize() {
        return wordList.size();
    }

    public void printTextArea(JTextArea area, File selectedFile) {
        if(wordList.isEmpty()) {
            area.append("\nWord not Found in file: "+selectedFile.getAbsolutePath()+"\n\n");
        }
        else {
            area.append("\nWord found in file: "+selectedFile.getName()+"\n");
            for (int i = 0; i < wordList.size(); i++) {
                area.append(wordList.get(i));
            }
        }
    }


    public int popUp() {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Attention", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    public void replaceWords(File selectedFile, String findStringTab2, String replaceStringTab2, JCheckBox wholeCase) throws IOException {
        File fileToBeModified = selectedFile;
        String oldContent="";
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        String line = reader.readLine();
        String newContent = "";
        while(line != null){
            oldContent = oldContent+line+System.lineSeparator();
            line = reader.readLine();
        }
        if(wholeCase.isSelected()) {
            String temp = "\\b" + findStringTab2 + "\\b";
            newContent = oldContent.replaceAll(temp, replaceStringTab2);
        }
        else {
             newContent= oldContent.replaceAll(findStringTab2, replaceStringTab2);
        }

        FileWriter writer = new FileWriter(fileToBeModified);
        writer.write(newContent);
        reader.close();
        writer.close();
    }
}
