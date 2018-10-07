import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindReplace extends JFrame {

    private JFrame frame;
    private JTabbedPane tabbedPane;

    private KeyListener keyListener;
    private ActionListener actionListener;

    private JFileChooser chooser;
    private File selectedFile;

    //textfields
    private JTextField findStringTab1;
    private JTextField findStringTab2;
    private JTextField findStringTab3;

    private JTextField replaceStringTab2;
    private JTextField replaceStringTab3;

    private JTextField filter;


    //buttons
    private JButton findButtonTab1;
    private JButton findButtonTab2;
    private JButton findButtonTab3;

    private JButton replaceButtonTab2;
    private JButton replaceButtonTab3;

    private JButton browse;
    private JButton cancel;

    //textArea
    private JTextArea textAreaTab1;
    private JTextArea textAreaTab2;
    private JTextArea textAreaTab3;

    //panes
    private JScrollPane paneTab1;
    private JScrollPane paneTab2;
    private JScrollPane paneTab3;


    //caseBox
    private JCheckBox matchCaseTab1;
    private JCheckBox matchCaseTab2;
    private JCheckBox matchCaseTab3;

    private JCheckBox wholeCaseTab1;
    private JCheckBox wholeCaseTab2;
    private JCheckBox wholeCaseTab3;

    //panels
    private JPanel findTabPanel;
    private JPanel findReplacePanel;
    private JPanel findAllPanel;

    //arrays
    private ArrayList<String> wordList;

    private FileSearch fileSearch;



    public FindReplace(){

        createListeners();
        createTextFields();
        createButtons();
        createTextAreas();
        createCheckBoxes();
        buildPanels();
        setSize(500,300);

    }

    private void createListeners(){
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e){}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        };
        actionListener = new FindReplace.ClickListener();
    }

    public class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "Search") {
                openFile();
                try {
                    findWords(selectedFile, false, findStringTab1.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                printTextArea(textAreaTab1);
            }
            else if(e.getActionCommand() == "Find") {
                openFile();
                try {
                    findWords(selectedFile, false, findStringTab2.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                printTextArea(textAreaTab2);
            } else if(e.getActionCommand() == "Replace") {
                int res = popUp();
                if(res == JOptionPane.OK_OPTION) {
                    try {
                        findWords(selectedFile, true, findStringTab2.getText());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        replaceWords();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    textAreaTab2.setText("File updated");
                }
            } else if(e.getActionCommand() == "Browse") {
                openDirectory();
                textAreaTab3.setText("Searching for word '" + findStringTab3.getText() + "' in text files under: " + selectedFile.getAbsolutePath() + "\n");
                fileSearch = new FileSearch(selectedFile, findStringTab3.getText(), textAreaTab3);

        }      }
    }

    private void openDirectory() {
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getCurrentDirectory();
        }


    }
    private int popUp() {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Attention", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }
    private void printUpdatedFile(JTextArea area) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(selectedFile));
        String line = null;
        wordList = new ArrayList<>();
        while((line = br.readLine()) != null) {
            wordList.add("Line number: " + line.indexOf(line) + " "+line+"\n");
        }

    }

    private void replaceWords() throws IOException {
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

    private void openFile() {
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
             //findWords(selectedFile);
            //tab1TextArea.setText("Searching for "+ word +" in the text file named " +selectedFile.getAbsolutePath() );
        }

    }

    private void printTextArea(JTextArea area) {
        for(int i =0; i< wordList.size(); i++) {
            area.append(wordList.get(i));
        }
    }

    private void findWords(File file, Boolean flag, String word) throws IOException {
        //JTextArea textArea = area;
        //String word = findStringTab1.getText();
        wordList = new ArrayList<>();
        //FileReader fr = new FileReader(file);
        try {
            String s="";
            int count =0;
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while( (s=bf.readLine() )!=null) {
                count++;
                //if (wholeCaseTab1.isSelected()) {
                    //word = word + " ";
                    if (s.contains(word)) {

                        wordList.add("Line number: " + count + " " + s + "\n");
                  //  }
                //}
                //else {
                    //if (s.contains(word)) {

                        //wordList.add("Line number: " + count + " " + s + "\n");
                  //  }
                }
               //textArea.append("Line number: " +count+" "+ s +"\n");

                    /*if(flag == true) {
                        s.replaceAll(word, replaceStringTab2.getText());

                    }
                    else{
                        wordList.add("Line number: " + count + " "+s+"\n");
                    }*/

                    //lineNumber.add(count);
                }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createTextFields(){
        wordList = new ArrayList<>();

        findStringTab1 = new JTextField(25);
        findStringTab2 = new JTextField(25);
        findStringTab3 = new JTextField(23);
        replaceStringTab2 = new JTextField(25);
        replaceStringTab3 = new JTextField(22);
        filter = new JTextField(15);

       /* findStringTab1.getDocument().addDocumentListener(new MyListener());
        Preferences prefs = Preferences.userRoot().node("value");
        findStringTab1.setText(prefs.get("key", ""));*/

        findStringTab1.addActionListener(actionListener);
        findStringTab2.addActionListener(actionListener);
        findStringTab3.addActionListener(actionListener);
        replaceStringTab2.addActionListener(actionListener);
        replaceStringTab3.addActionListener(actionListener);
        filter.addActionListener(actionListener);
    }

    class MyListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {

        }

        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            final Document document = e.getDocument();
            Preferences prefs = Preferences.userRoot().node("value");
            try{
                prefs.put("key", document.getText(0, document.getLength()));
            }catch(BadLocationException event) {
                event.printStackTrace();
            }
        }
    }

    private void createButtons(){

        chooser = new JFileChooser();

        findButtonTab1 = new JButton("Search");
        findButtonTab2 = new JButton("Find");
        //findButtonTab3 = new JButton("Find");

        replaceButtonTab2 = new JButton("Replace");
        replaceButtonTab3 = new JButton("Replace");

        browse = new JButton("Browse");
        cancel = new JButton("Cancel");

        findButtonTab1.addActionListener(actionListener);
        findButtonTab2.addActionListener(actionListener);
        //findButtonTab3.addActionListener(actionListener);
        replaceButtonTab2.addActionListener(actionListener);
        replaceButtonTab3.addActionListener(actionListener);
        browse.addActionListener(actionListener);
        cancel.addActionListener(actionListener);

    }

    private void createTextAreas() {
        textAreaTab1 = new JTextArea(8,30);
        textAreaTab1.setEditable(false);
        paneTab1 = new JScrollPane(textAreaTab1);
        paneTab1.createVerticalScrollBar();
        textAreaTab2 = new JTextArea(8,35);
        textAreaTab2.setEditable(false);
        paneTab2 = new JScrollPane(textAreaTab2);
        paneTab2.createVerticalScrollBar();
        textAreaTab3 = new JTextArea(8,35);
        textAreaTab3.setEditable(false);
        paneTab3 = new JScrollPane(textAreaTab3);
        paneTab3.createVerticalScrollBar();
    }

    private void createCheckBoxes() {
        matchCaseTab1 = new JCheckBox("Match Case");
        matchCaseTab2 = new JCheckBox("Match Case");
        matchCaseTab3 = new JCheckBox("Match Case");
        wholeCaseTab1= new JCheckBox("Whole Words");
        wholeCaseTab2= new JCheckBox("Whole Words");
        wholeCaseTab3= new JCheckBox("Whole Words");
    }

    private void buildPanels() {
        tabbedPane = new JTabbedPane();
        getContentPane().add(tabbedPane);

        findTabPanel = new JPanel();
        findTabPanel();
        findReplacePanel = new JPanel();
        findReplaceTabPanel();
        findAllPanel = new JPanel();
        findAllPanel();


        tabbedPane.addTab("Find", findTabPanel);
        tabbedPane.addTab("Find & Replace", findReplacePanel);
        tabbedPane.addTab("Find in All", findAllPanel);

        //frame.add(tabbedPane);
    }

    private void findTabPanel(){
        JLabel findLabel = new JLabel("Find: ");
        findLabel.setVisible(true);

        findTabPanel.add(findLabel, BorderLayout.WEST);

        findTabPanel.add(findStringTab1, BorderLayout.CENTER);

        findTabPanel.add(findButtonTab1, BorderLayout.EAST);

        findTabPanel.add(matchCaseTab1, BorderLayout.CENTER);
        findTabPanel.add(wholeCaseTab1, BorderLayout.CENTER);

        findTabPanel.add(paneTab1, BorderLayout.PAGE_END);
    }

    private void findReplaceTabPanel() {
        JLabel findLabel = new JLabel("Find: ");
        findLabel.setVisible(true);
        JLabel replaceLabel = new JLabel("Replace: ");
        replaceLabel.setVisible(true);

        findReplacePanel.add(findLabel, BorderLayout.WEST);

        findReplacePanel.add(findStringTab2, BorderLayout.CENTER);

        findReplacePanel.add(findButtonTab2, BorderLayout.EAST);

        findReplacePanel.add(replaceLabel, BorderLayout.WEST);

        findReplacePanel.add(replaceStringTab2, BorderLayout.CENTER);

        findReplacePanel.add(replaceButtonTab2, BorderLayout.EAST);

        findReplacePanel.add(matchCaseTab2, BorderLayout.CENTER);
        findReplacePanel.add(wholeCaseTab2, BorderLayout.CENTER);

        findReplacePanel.add(paneTab2, BorderLayout.PAGE_END);

    }

    private void findAllPanel(){

        JLabel findLabel = new JLabel("Find: ");
        findLabel.setVisible(true);
        JLabel replaceLabel = new JLabel("Replace: ");
        replaceLabel.setVisible(true);
        JLabel filterLabel = new JLabel("Filter: ");
        filterLabel.setVisible(true);



        findAllPanel.add(findLabel, BorderLayout.WEST);

        findAllPanel.add(findStringTab3, BorderLayout.CENTER);
        findAllPanel.add(browse, BorderLayout.EAST);

        //findAllPanel.add(findButtonTab3, BorderLayout.EAST);

        findAllPanel.add(replaceLabel, BorderLayout.WEST );

        findAllPanel.add(replaceStringTab3, BorderLayout.CENTER);

        findAllPanel.add(replaceButtonTab3, BorderLayout.EAST);

        findAllPanel.add(filterLabel, BorderLayout.WEST);

        findAllPanel.add(filter, BorderLayout.WEST);

        findAllPanel.add(matchCaseTab3, BorderLayout.CENTER);
        findAllPanel.add(wholeCaseTab3, BorderLayout.CENTER);

        findAllPanel.add(paneTab3, BorderLayout.PAGE_END);

    }
}
