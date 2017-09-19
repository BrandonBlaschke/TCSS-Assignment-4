package gui;

import java.awt.EventQueue;

/** Class runs the GUI. 
 * 
 * @author Brandon Blaschke 
 * @version 1/5/2017
 *
 */
public final class RunGUI {
    
    /** Creates class to run GUI. */
    private RunGUI() {
        throw new IllegalStateException();
    }
    
    /**
     * Running the GUI.
     * 
     * @param theArgs Command prompt strings (unused in program).
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new WordProcessorGUI().start();
            }
        });
    }

}