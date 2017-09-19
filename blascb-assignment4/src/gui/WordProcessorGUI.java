package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import processing.Word;
import processing.WordProcessor;

/** GUI for the WordProcessor.
 * @author Brandon Blaschke
 * @version 2/14/17
 *
 */
public class WordProcessorGUI {
    
    /** Size of the JFrame. */
    private static final Dimension SIZE = new Dimension(400, 700);
    
    /** Default list display. */
    private static final int TOP_DISPLAY = 5;
    
    /** Size of the top list to be displayed. */
    private int myTopValues; 

    /** JFrame for GUI. */
    private final JFrame myJFrame;
    
    /** JLabel for file name. */
    private final JLabel myFileLabel; 
    
    /** File Path. */
    private String myFilePath; 
    
    /** My word processor. */
    private final WordProcessor myWordPro; 
    
    /** Constructor for GUI. */
    public WordProcessorGUI() {
        
        myJFrame = new JFrame("Word Processing");
        myFilePath = "";
        myFileLabel = new JLabel("File Name");
        myTopValues = TOP_DISPLAY; 
        myWordPro = new WordProcessor();
    }
    
    /** Sets up the GUI. */
    public void start() {
        
        //Panels 
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        //Slider Panel
        final SliderPanel sliderPanel = new SliderPanel("Words To Display");
        
        //File panel 
        final JPanel filePanel = new JPanel();
        filePanel.setLayout(new FlowLayout());
        
        //Map Panel
        final MapButtonsPanel mapPanel = new MapButtonsPanel(myWordPro);
        
        //Word Button panel
        final WordButtonPanel wordButtonPanel = new WordButtonPanel(myWordPro);
        //wordButtonPanel.setLayout(new FlowLayout());
        
        //Symbol Button Panel
        final SymbolButPanel symButtonPanel = new SymbolButPanel(myWordPro);
        
        //Top Values panel
        final JPanel topValPanel = new JPanel();
        topValPanel.setLayout(new BoxLayout(topValPanel, BoxLayout.Y_AXIS));
        
        //Open Button
        final JButton openButton = new JButton("Open Text File");
        openButton.addActionListener(new MyFileListener());
        filePanel.add(openButton);
        
        //Process Button
        final JButton processButton = new JButton("Process Text");
        processButton.addActionListener(new MyProcessFile(topValPanel, sliderPanel));
        
        //filePanel buttons
        filePanel.add(openButton);
        filePanel.add(myFileLabel);
        filePanel.add(processButton);
        
        //Add components to mainPanel
        mainPanel.add(filePanel);
        mainPanel.add(sliderPanel);
        mainPanel.add(mapPanel);
        mainPanel.add(wordButtonPanel);
        mainPanel.add(symButtonPanel);
        mainPanel.add(topValPanel);
        
        //Set up JFrame
        myJFrame.add(mainPanel, BorderLayout.NORTH);
        myJFrame.setPreferredSize(SIZE);
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myJFrame.setResizable(false);
        myJFrame.pack();
        
        //Center in screen 
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        myJFrame.setLocation((int) dim.getWidth() / 2 - myJFrame.getWidth() / 2,
                           (int) dim.getHeight() / 2 - myJFrame.getHeight() / 2);
        
        myJFrame.setVisible(true);
    }

    /**
     * File listener to get file location.
     * @author Brandon Blaschke
     * @version 2/14/17
     *
     */
    private class MyFileListener implements ActionListener {
        
        /** JFileChooser to get file name. */
        private final JFileChooser myFileWindow;
        
        /** Constructor. */
        MyFileListener() { 
            
            myFileWindow = new JFileChooser();
            myFileWindow.setCurrentDirectory(new File("."));
        }

        @Override
        public void actionPerformed(final ActionEvent theE) {
            
            final int result = myFileWindow.showOpenDialog(myJFrame);
            
            if (result == JFileChooser.APPROVE_OPTION) {
                
                myFilePath = myFileWindow.getSelectedFile().getPath();
                myFileLabel.setText(myFileWindow.getSelectedFile().getName());
            }
        }
        
    }
    
    /** Listener for process file. 
     * @author Brandon Blaschke
     * @version 2/14/17
     */
    private class MyProcessFile implements ActionListener {
        
        /** Top value panel. */
        private final JPanel myTopPanel; 
        
        /** Slider panel. */
        private final SliderPanel mySlide; 
        
        /**Creates constructor. 
         * @param theTop Top value JPanel.
         * @param theSlidePan Slide Panel. 
         * */
        MyProcessFile(final JPanel theTop, final SliderPanel theSlidePan) {
            
            myTopPanel = theTop; 
            mySlide = theSlidePan; 
        }

        @Override
        public void actionPerformed(final ActionEvent theE) {
            
            //Process file 
            myWordPro.processFile(myFilePath, false);
            
            //Start garage collector and start timing. 
            System.gc();
            final long startTime = System.nanoTime();
            
            final List<Word> words = (List<Word>) myWordPro.getTopValues(5);
            myTopPanel.removeAll();

            final JLabel totalWords =
                            new JLabel("", JLabel.CENTER);
            totalWords.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            myTopPanel.add(totalWords);
            int count = 1;
            
            //end time
            final long endTime = System.nanoTime();
            
            // Total time
            final double totalTime = (endTime - startTime) * Math.pow(10, -6);
            //Stopped time here because I got the top results here and display 
            //either 0-30 words in the loop which is I/O reading which will effect the results
            //of the time. And n = 30 is not much time on the algorithm to get the top values. 
            
            myTopValues = mySlide.getValue();
            try {
                for (int i = words.size() - 1; i > words.size() - myTopValues - 1; i--) {
                    
                    final JLabel label = new JLabel(count + ". " 
                                    + words.get(i).toString(), JLabel.CENTER);
                    count++;
                    label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                    myTopPanel.add(label);
                }
            } catch (final ArrayIndexOutOfBoundsException error) {
                JOptionPane.showMessageDialog(myJFrame, "File not found or not enough "
                                + "values to display");
            }
            
            //Set label 
            totalWords.setText("Total Words: " + myWordPro.getTotalWords() + "      Time: "
                               + totalTime);
            
            //update 
            SwingUtilities.updateComponentTreeUI(myJFrame);
            myWordPro.reset();
            
        }
        
    }
    
}
