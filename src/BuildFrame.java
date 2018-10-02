import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.io.*;

/*
* Areeba Waheed
* Find/Replace
*
* */

public class BuildFrame extends JFrame {

    private JPanel panel;
    private JLabel findLabel;
    private JLabel replaceLabel;
    private JLabel filterLabel;
    private JLabel searchLabel;
    private JButton findButton;
    private JButton findNext;
    private JButton replaceButton;
    private JButton cancel;
    private  JButton directory;

    private ActionListener listener;
    private File file;
    private JFileChooser chooser;
    private JTextArea tab1TextArea;
    private JTextArea tab2TextArea;
    private JTextArea tab3TextArea;

    private JCheckBox caseCheckBox;
    private JCheckBox wholeCheckBox;

    private JTabbedPane tabbedPane;
    private JPanel findPanel;
    private JPanel replacePanel;
    private JPanel findAllPanel;

    private JTextField findString;
    private JTextField findFirstTabString;
    private JTextField replaceString;
    private JTextField filterString;

    private KeyListener keyListener;

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;

    public BuildFrame() {
        chooser = new JFileChooser();
        createListeners();
       // createTextField();
        makePanels();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createListeners() {
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e){}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        };
        listener = new ClickListener();
    }
    private void createTextField() {
        findString = new JTextField(22);
        replaceString = new JTextField(20);
        //Font bigFont = findString.getFont().deriveFont(Font.PLAIN, 40f);
        //Font bigFont2 = replaceString.getFont().deriveFont(Font.PLAIN, 40f);

       // findString.setFont(bigFont);
        //replaceString.setFont(bigFont2);

        findString.addKeyListener(keyListener);
        replaceString.addKeyListener(keyListener);
    }


    private void makePanels() {

        tabbedPane = new JTabbedPane();
        getContentPane().add(tabbedPane);
        findPanel = new JPanel();
        replacePanel = new JPanel();
        findAllPanel = new JPanel();

        findTabPanel();
        replaceTabPanel();
        findAllTabPanel();

        tabbedPane.addTab("Find", findPanel);
        tabbedPane.addTab("Replace", replacePanel);
        tabbedPane.addTab("Find in All", findAllPanel);
    }

    private void findTabPanel(){
        tab1TextArea = new JTextArea(5,20);
        tab1TextArea.setEditable(false);
        findLabel = new JLabel("Find: ");
        findLabel.setVisible(true);

        findFirstTabString = new JTextField(20);
        findFirstTabString.addKeyListener(keyListener);


        caseCheckBox = new JCheckBox("Match Case");
        wholeCheckBox = new JCheckBox("Whole Words");

       // caseCheckBox.addActionListener(listener);
        //wholeCheckBox.addActionListener(listener);

        findButton = new JButton("Find");
        findButton.setBackground(Color.WHITE);
        findButton.addActionListener(listener);

        findNext = new JButton("Find Next");
        findNext.setBackground(Color.WHITE);
        findNext.addActionListener(listener);
        //findButton.addKeyListener(keyListener);

        findPanel.add(findLabel);
        findPanel.add(findFirstTabString);
        findPanel.add(findButton);
        //findPanel.add(findNext);
        findPanel.add(caseCheckBox);
        findPanel.add(wholeCheckBox);
        findPanel.add(tab1TextArea);

    }
    private void replaceTabPanel(){
        tab2TextArea = new JTextArea(5,20);
        tab2TextArea.setEditable(false);
        findLabel = new JLabel("Find: ");
        replaceLabel = new JLabel("Replace: ");
        findLabel.setVisible(true);
        replaceLabel.setVisible(true);

        findString = new JTextField(20);
        findString.addKeyListener(keyListener);
        replaceString = new JTextField(20);
        replaceString.addKeyListener(keyListener);

        caseCheckBox = new JCheckBox("Match Case");
        wholeCheckBox = new JCheckBox("Whole Words");

        findButton = new JButton("Find");
        findButton.setBackground(Color.WHITE);
        findButton.addActionListener(listener);

        replaceButton = new JButton("Replace");
        replaceButton.setBackground(Color.WHITE);
        replaceButton.addActionListener(listener);

        replacePanel.add(findLabel);
        replacePanel.add(findString);
        replacePanel.add(findButton);
        replacePanel.add(replaceLabel);
        replacePanel.add(replaceString);
        replacePanel.add(replaceButton);
        replacePanel.add(caseCheckBox);
        replacePanel.add(wholeCheckBox);
        replacePanel.add(tab2TextArea);
    }
    private void findAllTabPanel(){
        tab3TextArea = new JTextArea(5,20);
        tab3TextArea.setEditable(false);
        findLabel = new JLabel("Find: ");
        replaceLabel = new JLabel("Replace: ");
        filterLabel = new JLabel("Filters: ");
        findLabel.setVisible(true);
        replaceLabel.setVisible(true);
        filterLabel.setVisible(true);

        findString = new JTextField(20);
        findString.addKeyListener(keyListener);
        replaceString = new JTextField(20);
        replaceString.addKeyListener(keyListener);
        filterString = new JTextField(20);
        filterLabel.addKeyListener(keyListener);

        caseCheckBox = new JCheckBox("Match Case");
        wholeCheckBox = new JCheckBox("Whole Words");

        findButton = new JButton("Find");
        findButton.setBackground(Color.WHITE);
        findButton.addKeyListener(keyListener);

        replaceButton = new JButton("Replace");
        replaceButton.setBackground(Color.WHITE);
        replaceButton.addKeyListener(keyListener);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.WHITE);
        cancel.addKeyListener(keyListener);

        directory = new JButton("Browse");
        directory.setBackground(Color.WHITE);
        directory.addKeyListener(keyListener);

        findAllPanel.add(findLabel);
        findAllPanel.add(findString);
        findAllPanel.add(directory);
        findAllPanel.add(replaceLabel);
        findAllPanel.add(replaceString);
        findAllPanel.add(replaceButton);
        findAllPanel.add(filterLabel);
        findAllPanel.add(filterString);
        findAllPanel.add(cancel);
        findAllPanel.add(caseCheckBox);
        findAllPanel.add(wholeCheckBox);
        findAllPanel.add(tab3TextArea);
    }


    private void doFindText(String p) {
        String word ="";
        if(wholeCheckBox.isSelected()) {
             word = " " +p + " ";
        }
        else {
            word = p;
        }

        File selectedFile = new File("/Users/Maaz/Downloads/Find-Replace/src/BuildFrame.java");
        //find the word in the file
        try {
            String s="";
            int count =0;
            BufferedReader bf = new BufferedReader(new FileReader(selectedFile));
            //s = bf.readLine();
            //tab1TextArea.setText(word);
            while( (s=bf.readLine() )!=null) {
                count++;
                if(s.contains(word)){
                    tab1TextArea.setText("Line number: " +count+" "+ s);
                    break;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //tab1TextArea.setText("Line number: " ); //that line number, plus the whole line
        //tab2TextArea.setText("Line number: " );
        //file = new File(System.getProperty("user.dir"));
        //chooser.setCurrentDirectory(file);
        //file = chooser.getSelectedFile();
        //tab1TextArea.setText("Searching for "+ word +" in the text file named " +file );

        /*chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser. setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setFileFilter(new FileNameExtensionFilter("Text Files", ".txt"));
        chooser.setFileFilter(new FileNameExtensionFilter("CFG", ".cfg"));
        chooser.setFileFilter(new FileNameExtensionFilter("Java Files", ".java"));
        chooser.setFileFilter(new FileNameExtensionFilter("Html", ".html"));
        chooser.setFileFilter(new FileNameExtensionFilter("CSS", ".css"));

        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            tab1TextArea.setText("Searching for "+ word +" in the text file named " +selectedFile.getAbsolutePath() );
        }
*/
        //textarea: found this text on this line

    }
    private void doReplaceText(String from, String to) {

        //replace this word to that word
        //display on textarea
        //save that file
    }
    private void exitActionPerformed() {System.exit(0);}

    public class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand() == "Find")
                doFindText(findFirstTabString.getText());
            else if(e.getActionCommand() == "Replace")
                doReplaceText(findString.getText(), replaceString.getText());
            else if(e.getActionCommand() == "Cancel")
                exitActionPerformed();
        }
    }
}
