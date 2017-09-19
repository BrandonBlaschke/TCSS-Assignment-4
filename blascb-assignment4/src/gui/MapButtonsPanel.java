package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import processing.Word;
import processing.WordProcessor;

/** Map buttons Panel for GUI. 
 * @author Brandon Blaschke 
 * @version 2/22/17
 * 
 */
public class MapButtonsPanel extends JPanel {
    
    /** Generated serial version ID.*/
    private static final long serialVersionUID = 2881671303009121208L;
    
    /** The Word processor. */
    private final WordProcessor myWordPro; 
    
    /** Constructor for class. 
     * @param theWP Word Processor. 
     * */
    public MapButtonsPanel(final WordProcessor theWP) {
        
        super(); 
        myWordPro = theWP;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        addButtons();
    }
    
    /** Add buttons to the panel. */
    private void addButtons() {
        
        final ButtonGroup mapButtons = new ButtonGroup();
        
        final JRadioButton hashButton = new JRadioButton("Hash Map");
        hashButton.setSelected(true);
        hashButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                myWordPro.setMap(new HashMap<String, Word>());
            }
            
        });
        
        final JRadioButton treeButton = new JRadioButton("Tree Map");
        treeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theE) {
                myWordPro.setMap(new TreeMap<String, Word>());
            }
            
        });
        
        mapButtons.add(hashButton);
        mapButtons.add(treeButton);
        
        this.add(new JLabel("Map Used: "));
        this.add(hashButton);
        this.add(treeButton);
    }
}
