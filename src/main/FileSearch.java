package main;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class FileSearch extends SwingWorker<Integer, String> {
    private File directory;
    private JTextField word;
    private JTextField replace;
    private JTextArea area;
    private ActionsPerformed ap;
    private JCheckBox wholeCase;
    private ArrayList<String> wordList;
    private Boolean flag;
    private String[] filters;

    private static void failIfInterrupted() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("Interrupted while searching files");
        }
    }

    public FileSearch(File directory, JTextField word, JTextField replace, JTextArea area, JCheckBox c, Boolean flag, String [] filters) {
        this.directory = directory;
        this.word = word;
        this.replace = replace;
        this.area = area;
        this.wholeCase = c;
        this.flag = flag;
        this.filters = filters;
        wordList = new ArrayList<>();
        ap = new ActionsPerformed(wordList);
    }

    @Override
    protected Integer doInBackground() throws Exception {
        int matches =0;
        publish("Listing all files under the directory " + directory);

        //String[] children = directory.list();
        //new String[] {"java", "txt", "cfg", "html", "css"}

        final List<File> files = new ArrayList<>(FileUtils.listFiles(directory, filters, true));
        for(int i=0, size = files.size(); i<size; i++) {
            FileSearch.failIfInterrupted();
            final File file = files.get(i);
            //publish ("Searching file: "+ file);
            if(flag == true) {

                    ap.replaceWords(file, word.getText(), replace.getText(), wholeCase);

                //area.setText("Files Updated");
            }else {
                ap.findWords(file, word.getText(), wholeCase);
                ap.printTextArea(area, file);
            }
            //final String text = FileUtils.readFileToString(file);
            //matches += StringUtils.countMatches(text, word);
            setProgress((i+1) *100/size);
        }
        return matches;
    }

    @Override
    protected void process(final List<String> chunks) {
        // Updates the messages text area
        for (final String string : chunks) {
            area.append(string);
            area.append("\n");
        }
    }



}
