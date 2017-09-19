package behaviors;

import java.util.Map;

import processing.Word;

/** Symbols is a behavior that will be 
 * used by other classes to count the 
 * symbols in a line of text. 
 * @author Brandon Blaschke 
 * @version 2/14/17
 */
public interface Symbols {
    
    /**
     * Processes symbols for a given line of text and adds the a map.
     * @param <K>
     * @param theString String to be processed.
     * @param theMap Map to be mapped to for the words.
     */
    void processSymbols(final String theString, final Map<String, Word> theMap);
}
