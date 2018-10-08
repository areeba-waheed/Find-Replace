import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ActionsPerformed extends Component {

    private ArrayList<String> wordList;
    //private File f;


    public ActionsPerformed(ArrayList<String> wordList){
        this.wordList = wordList;
    }

    public void replace() {

    }

    public void findInDirectory(File directory, JTextArea area, JTextField text) throws IOException {

        File[] files = new File(directory.getAbsolutePath()).listFiles();
        for(File file: files) {
            if(file.isFile()){
                //f = file;
                findWords(file, text.getText());
                printTextArea(area, file);
            }
        }
    }



    public void findWords(File file, String word) throws IOException {
        wordList = new ArrayList<>();
        try {
            String s="";
            int count =0;
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while( (s=bf.readLine() )!=null) {
                count++;
                if (s.contains(word)) {
                    wordList.add("Line number: " + count + " " + s + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printTextArea(JTextArea area, File selectedFile) {
        if(wordList.isEmpty()) {
            area.append("Word not Found in file: "+selectedFile.getAbsolutePath()+"\n");
        }
        else {
            area.append("Word found in file: "+selectedFile.getName()+"\n");
            for (int i = 0; i < wordList.size(); i++) {
                area.append(wordList.get(i));
            }
        }
    }


    public int popUp() {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Attention", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }

    public void replaceWords(File selectedFile, JTextField findStringTab2, JTextField replaceStringTab2) throws IOException {
        //Writer writer = new BufferedWriter();

        File fileToBeModified = selectedFile;
        String oldContent="";
        BufferedReader reader = new BufferedReader(new FileReader(fileToBeModified));
        String line = reader.readLine();
        while(line != null){
            oldContent = oldContent+line+System.lineSeparator();
            line = reader.readLine();
        }
        String newContent = oldContent.replaceAll(findStringTab2.getText(), replaceStringTab2.getText());

        FileWriter writer = new FileWriter(fileToBeModified);
        writer.write(newContent);
        reader.close();
        writer.close();





    }





}
