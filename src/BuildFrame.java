import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    private JButton replaceButton;
    private JButton cancel;
    private  JButton directory;

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
    private JTextField replaceString;
    private JTextField filterString;

    private KeyListener keyListener;

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;

    public BuildFrame() {
        createKeyListener();
       // createTextField();
        createFrame();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createKeyListener() {
        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e){}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        };
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

    private void createFrame(){

        makePanels();




        /*panel = new JPanel();
        panel.setBackground(Color.gray);

        findButton = new JButton("Find");
        replaceButton = new JButton("Replace");

        findButton.setBackground(Color.WHITE);
        replaceButton.setBackground(Color.WHITE);

        add(panel);

        findLabel = new JLabel("Find: ");
        findLabel.setBackground(Color.BLACK);
        findLabel.setVisible(true);
       /* searchLabel = new JLabel("Search: ");
        searchLabel.setBackground(Color.BLACK);
        searchLabel.setVisible(true);*/
        /*replaceLabel = new JLabel("Replace: ");
        replaceLabel.setBackground(Color.BLACK);
        replaceLabel.setVisible(true);

        panel.add(findLabel);
        panel.add(findString);
        panel.add(findButton);
        //panel.add(searchLabel);
        panel.add(replaceLabel);
        panel.add(replaceString);
        panel.add(replaceButton);*/


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

        findString = new JTextField(20);
        findString.addKeyListener(keyListener);

        caseCheckBox = new JCheckBox("Match Case");
        wholeCheckBox = new JCheckBox("Whole Words");

        findButton = new JButton("Find");
        findButton.setBackground(Color.WHITE);
        findButton.addKeyListener(keyListener);

        findPanel.add(findLabel);
        findPanel.add(findString);
        findPanel.add(findButton);
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
        findButton.addKeyListener(keyListener);

        replaceButton = new JButton("Replace");
        replaceButton.setBackground(Color.WHITE);
        replaceButton.addKeyListener(keyListener);

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
        findAllPanel.add(findButton);
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

}
