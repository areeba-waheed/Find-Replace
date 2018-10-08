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
    private String word;
    private JTextArea area;

    private static void failIfInterrupted() throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("Interrupted while searching files");
        }
    }

    public FileSearch(File directory, String word, JTextArea area) {
        this.directory = directory;
        this.word = word;
        this.area = area;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        int matches =0;
        publish("Listing all files under the directory " + directory);

        String[] children = directory.list();
        final List<File> files = new ArrayList<>(FileUtils.listFiles(directory, new SuffixFileFilter(".txt"), TrueFileFilter.TRUE));
        for(int i=0; i<files.size(); i++) {
            FileSearch.failIfInterrupted();
            final File file = files.get(i);
            publish ("Searching file: "+ file);
            String text = FileUtils.readFileToString(file);
            matches += StringUtils.countMatches(text, word);
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
