package gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** Slider Panel that will control the amount of top n words shown
 * on the GUI. 
 * @author Brandon Blaschke
 * @version 2/22/17
 */
public class SliderPanel extends JPanel {
    
    /**Generated serial version ID. */
    private static final long serialVersionUID = -6130027101812686960L;
    
    /** Initial Value. */
    private static final int INI_VAL = 5;
    
    /** Initial Value. */
    private static final int MAJ_TICKS = 5;
    
    /** Initial Value. */
    private static final int TOP_VAL = 30;
    
    /** String display. */
    private final String myDisplayText; 
    
    /** Value from slider. */
    private int myValue; 
    
    /** Constructor for SliderPanel. 
     * @param theString String for the slider text.
     * */
    public SliderPanel(final String theString) {
        
        super();
        myDisplayText = theString; 
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        createSlider();
        myValue = INI_VAL;
    }
    
    /** Create slider. */
    private void createSlider() {
        
        final JLabel sliderTitle = new JLabel(myDisplayText, JLabel.CENTER);
        sliderTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, TOP_VAL, MAJ_TICKS);
        slider.setMajorTickSpacing(MAJ_TICKS);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(final ChangeEvent theE) {
                
                final JSlider js = (JSlider) theE.getSource();
                myValue = js.getValue();
            }
            
        }); 
        
        this.add(sliderTitle);
        this.add(slider);
    }
    
    /** get value of slider. 
     * @return Slider value. 
     * */
    public int getValue() {
        
        return myValue; 
    }

}
