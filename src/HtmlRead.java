import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URI;
import java.util.ArrayList;

public class HtmlRead implements ActionListener {
    private JFrame mainFrame;
    private JPanel inputPanel;
    private JPanel outputPanel;
    private JPanel startButtonPanel;
    private JPanel textPanel;
    private JMenuBar mb;
    private JMenu file, edit, help, selectLine;
    private JMenuItem cut, copy, paste, selectAll, test, clearAll, zoomIn, zoomOut, up, down, lineCopy, select, googleSearch;
    private JTextArea urlInput;
    private JTextArea search;//typing area
    private JLabel urlInputText;
    private JLabel searchText;
    private JTextArea statusLabel;
    private JTextArea outputLabel;
    JScrollPane scroll;
    private int WIDTH=800;
    private int HEIGHT=700;
    private String urlText;
    private String keyWord;
    private int errorCheck;
    private int textSize = 15;
    private int selectedLine = 0;
    private String stringLine;
    //private String printLine;
    private String query;






    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
        html.showEventDemo();

    }

    public HtmlRead() {
        prepareGUI();
        //HtmlReader();



    }

    private void prepareGUI() {
        mainFrame = new JFrame("HTML Reader");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());
        //mainFrame.setLayout(new BorderLayout(50,50));


        //menu at top
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        test = new JMenuItem("test");
        clearAll = new JMenuItem("clearAll");
        selectAll = new JMenuItem("selectAll");
        zoomOut = new JMenuItem("zoomOut");
        zoomIn = new JMenuItem("zoomIn");
        up = new JMenuItem("up");
        down = new JMenuItem("down");
        lineCopy = new JMenuItem("copy");
        //lineCut = new JMenuItem("cut");
        select = new JMenuItem("select");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        test.addActionListener(this);
        clearAll.addActionListener(this);
        zoomOut.addActionListener(this);
        zoomIn.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);
        googleSearch = new JMenuItem("Google Search");
        lineCopy.addActionListener(this);
        selectAll.addActionListener(this);
        select.addActionListener(this);
        googleSearch.addActionListener(this);





        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        selectLine = new JMenu("SelectLine");
        selectLine.addActionListener(this);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        file.add(test);
        file.add(zoomIn);
        file.add(zoomOut);
        file.add(googleSearch);
        edit.add(clearAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);
        edit.add(selectLine);
        selectLine.add(select);
        selectLine.add(up);
        selectLine.add(down);
        selectLine.add(lineCopy);
        //selectLine.add(lineCut);

        //end menu at top

        mainFrame.add(mb);  //add menu bar
        mainFrame.setJMenuBar(mb); //set menu bar

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1,2));
        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(4,1));
        searchText = new JLabel("Search Bar:");
        urlInputText = new JLabel("Insert Url:");
        search = new JTextArea();
        urlInput = new JTextArea();
        startButtonPanel = new JPanel();
        startButtonPanel.setLayout(new GridLayout(2,1));
        statusLabel = new JTextArea();
        statusLabel .setEditable(false);
        outputLabel = new JTextArea ();
        outputLabel.setEditable(false);
        scroll= new JScrollPane (outputLabel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS) ;
        outputPanel = new JPanel();
        outputLabel.setFont(new Font("Arial",Font.PLAIN,textSize));


        //controlPanel.setLayout(new BorderLayout());

        mainFrame.add(inputPanel, BorderLayout.NORTH);
        inputPanel.add(textPanel);
        textPanel.add(searchText);
        textPanel.add(search);
        textPanel.add(urlInputText);
        textPanel.add(urlInput);
        inputPanel.add(startButtonPanel);
        mainFrame.add(scroll);
        //mainFrame.add(outputLabel);
        //outputPanel.add(outputLabel);


        //outputLabel.add(scroll);

        mainFrame.setVisible(true);
    }

    private void showEventDemo() {

        JButton searchButton = new JButton("Search");
        
        searchButton.setActionCommand("SEARCH");
        
        searchButton.addActionListener(new ButtonClickListener());
        
        startButtonPanel.add(searchButton);
        startButtonPanel.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut) {
            outputLabel.setEditable(true);
            outputLabel.cut();
            outputLabel.setEditable(false);
        }
        if (e.getSource() == paste) {
            outputLabel.paste(); }
        if (e.getSource() == copy) {
            outputLabel.setEditable(true);
            outputLabel.copy();
            outputLabel.setEditable(false); }
        if (e.getSource() == selectAll)
            outputLabel.selectAll();
        if (e.getSource() == test) {
            urlInput.setText("https://www.milton.edu/");
            search.setText("Athletic");

        }
        if (e.getSource() == clearAll) {
            urlInput.setText("");
            search.setText("");
            outputLabel.setText("");
            statusLabel.setText("");

        }
        if (e.getSource() == zoomIn) {
            textSize = textSize + 5;
            System.out.println(textSize);
            

        }
        if (e.getSource() == zoomOut) {
            textSize = textSize - 5;
            System.out.println(textSize);



        }
        if (e.getSource() == select) {
            selectLine(selectedLine);
        }

        if (e.getSource() == up) {
            if (selectedLine > 0) {
                selectedLine--;
                selectLine(selectedLine);
            }
        }

        if (e.getSource() == down) {
            int lineCount = outputLabel.getLineCount();
            if (selectedLine < lineCount - 1) {
                selectedLine++;
                selectLine(selectedLine);
                //System.out.println(stringLine);
            }
        }
        if (e.getSource() == lineCopy) {
            statusLabel.copy();
            statusLabel.setText("Coppied");

        }
        // this is for my google search ability
        if (e.getSource() == googleSearch) {
            if (stringLine == null){
                query = urlInput.getText().trim();
            } else { query = stringLine.trim();}

            if (!query.isEmpty()) {
                try {
                    String googleSearchURL = query;
                    Desktop.getDesktop().browse(new URI(googleSearchURL));
                } catch (Exception ex) {
                    statusLabel.setText("Error opening browser for Google search.\n");
                }
            }
        }




    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("SEARCH")) {
                statusLabel.setText("Searching...");
                outputLabel.setText("");
                String urlText = urlInput.getText();
                String keyWord = search.getText();
                System.out.println(keyWord);
                System.out.println(urlText);
                HtmlReader(urlText, keyWord);

            }
        }
    }

    private void HtmlReader(String urlText, String keyWord){




        try {
            errorCheck = 0;
            keyWord = keyWord.toLowerCase();
            System.out.println(keyWord);


            System.out.println();
            System.out.print("hello \n");
            URL url = new URL(urlText);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            String newLine = "";
            String printLine = "";




            while ( (line = reader.readLine()) != null ) {


                //System.out.println(line);
                while (line.contains("href="))  {
                    line = line.toLowerCase();
                    int indexOfBeggining = line.indexOf("href=") + 6;
                    newLine = line.substring(indexOfBeggining);
                    int indexOfEnd = newLine.indexOf("\"") ;
                    int otherIndexOfEnd = newLine.indexOf("'");
                    //System.out.println(printLine);

                    if (indexOfEnd > -1 && otherIndexOfEnd == -1){
                        printLine = newLine.substring(0, indexOfEnd);
                        int keyWordIndex = printLine.indexOf(keyWord);
                        if(keyWordIndex > 0 || keyWord.length() <= 0) {
                            outputLabel.append(printLine + "\n");
                            outputLabel.setFont(new Font("Arial",Font.PLAIN,textSize));
                            //System.out.println(printLine);
                            errorCheck ++;
                        }






                    }
                    if (otherIndexOfEnd > -1 && indexOfEnd == -1){
                        printLine = newLine.substring(0, otherIndexOfEnd);
                        int keyWordIndex = printLine.indexOf(keyWord);
                        if(keyWordIndex > 0 || keyWord.length() <= 0) {
                            outputLabel.append(printLine + "\n");
                            outputLabel.setFont(new Font("Arial",Font.PLAIN,textSize));
                            errorCheck ++;
                        }





                    }

                    if (indexOfEnd > -1 && otherIndexOfEnd > -1){
                        if(indexOfEnd > otherIndexOfEnd){
                            printLine = newLine.substring(0, otherIndexOfEnd);
                            int keyWordIndex = printLine.indexOf(keyWord);
                            if(keyWordIndex > 0 || keyWord.length() <= 0) {
                                outputLabel.append(printLine + "\n");
                                outputLabel.setFont(new Font("Arial",Font.PLAIN,textSize));
                                errorCheck ++;
                            }




                        } else {
                            printLine = newLine.substring(0, indexOfEnd);
                            int keyWordIndex = printLine.indexOf(keyWord);
                            if(keyWordIndex > 0 || keyWord.length() <= 0) {
                                outputLabel.append(printLine + "\n");
                                outputLabel.setFont(new Font("Arial",Font.PLAIN,textSize));
                                errorCheck ++;
                            }


                        }
                    }

                    line = newLine;


                }



            }
            System.out.println("error check: " + errorCheck);
            if (errorCheck == 0){
                System.out.println("Key Word Not Found. Search Again");
                outputLabel.append("Key Word Not Found. Search Again");
                outputLabel.setFont(new Font("Arial",Font.PLAIN,textSize));
            }
            reader.close();




        }

        catch(Exception ex) {
            System.out.println(ex);
            outputLabel.append("Link Error. Try Inputing The Link Again ");
            outputLabel.setFont(new Font("Arial",Font.PLAIN,textSize));
        }



    }

    private void selectLine(int lineNumber) {
        try {
            int startOffset = outputLabel.getLineStartOffset(lineNumber);
            int endOffset = outputLabel.getLineEndOffset(lineNumber);
            System.out.println(startOffset + " " + endOffset);
            outputLabel.select(startOffset, endOffset);
            stringLine = outputLabel.getText(startOffset, endOffset - startOffset);
            System.out.println(stringLine);
            statusLabel.setText("Selected line: " + stringLine );
//            outputLabel.setFont(new Font("Arial",Font.BOLD,textSize));
//            outputLabel.replaceRange(stringLine + "replaced", startOffset, endOffset);
        } catch (Exception ex) {
            System.out.println("Error selecting line: " + ex.getMessage());
        }
    }


}
