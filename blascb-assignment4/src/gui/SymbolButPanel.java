package gui;

import behaviors.CountSymbols;
import behaviors.IgnoreSymbols;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import processing.WordProcessor;

/** Symbol buttons Panel for GUI. 
 * @author Brandon Blaschke 
 * @version 2/22/17
 * 
 */
public class SymbolButPanel extends JPanel {
    
    /** Generated serial version ID.*/
    private static final long serialVersionUID = -2292599685113555037L;
    
    /** The Word processor. */
    private final WordProcessor myWordPro; 
    
    /** Constructor for class. 
     * @param theWP Word Processor. 
     * */
    public SymbolButPanel(final WordProcessor theWP) {
        
        super(); 
        myWordPro = theWP;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        addButtons();
    }
    
    /** Add buttons to panel. */
    private void addButtons() {
        
        final ButtonGroup symGroup = new ButtonGroup();

        final JLabel symTitle = new JLabel("Symbol Behavior: ");
        this.add(symTitle);

        // CountSymbols
        final JRadioButton countSymbolsBut = new JRadioButton("Count symbols");
        countSymbolsBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {

                myWordPro.setSymbols(new CountSymbols());
            }

        });

        // Ignore symbols
        final JRadioButton ignoreSymbolsBut = new JRadioButton("Ignore symbols");
        ignoreSymbolsBut.setSelected(true);
        ignoreSymbolsBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {

                myWordPro.setSymbols(new IgnoreSymbols());
            }

        });

        symGroup.add(ignoreSymbolsBut);
        symGroup.add(countSymbolsBut);

        this.add(countSymbolsBut);
        this.add(ignoreSymbolsBut);
    }
}
