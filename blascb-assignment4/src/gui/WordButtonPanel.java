
package gui;

import behaviors.CountAllWords;
import behaviors.CountWordsNoCase;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import processing.WordProcessor;

/**
 * Word buttons Panel for GUI.
 * 
 * @author Brandon Blaschke
 * @version 2/22/17
 * 
 */
public class WordButtonPanel extends JPanel {

    /** Generated serial version ID.*/
    private static final long serialVersionUID = 7701243401044406739L;
    
    /** The Word processor. */
    private final WordProcessor myWordPro;

    /**
     * Constructor for class.
     * 
     * @param theWP Word Processor.
     */
    public WordButtonPanel(final WordProcessor theWP) {

        super();
        myWordPro = theWP;
        this.setLayout(new FlowLayout());
        addButtons();
    }

    /** Add buttons to the panel. */
    private void addButtons() {
        
        final JLabel wordTitle = new JLabel("Word Behavior: ");
        this.add(wordTitle);
        
        final ButtonGroup wordGroup = new ButtonGroup();

        final JRadioButton countAllWordsBut = new JRadioButton("Count all words");
        countAllWordsBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {

                myWordPro.setWords(new CountAllWords());
            }

        });

        final JRadioButton countAllWordsCaseBut = new JRadioButton("Not case sensitive");
        countAllWordsCaseBut.setSelected(true);
        countAllWordsCaseBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {

                myWordPro.setWords(new CountWordsNoCase());
            }

        });
        
        wordGroup.add(countAllWordsCaseBut);
        wordGroup.add(countAllWordsBut);
        
        this.add(countAllWordsBut);
        this.add(countAllWordsCaseBut);
        
    }
}
