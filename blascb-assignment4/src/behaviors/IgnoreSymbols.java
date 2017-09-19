package behaviors;

import java.util.Map;

import processing.Word;

/** Counts symbols for a line of text. 
 * @author Brandon Blaschke
 * @version 2/14/17
 * */
public final class IgnoreSymbols implements Symbols {
    
    /** Constructor for IgnoreSymbols.*/
    public IgnoreSymbols() {
        
        //Left Empty 
    }

    @Override
    public void processSymbols(final String theString, final Map<String, Word> theMap) {
        
        //Empty Method since we do not need to count any symbols like 
        // / !@#$%^&*() etc. 
    }

    

}
