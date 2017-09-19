package behaviors;

import java.util.Map;

import processing.Word;

/** Words is a behavior that other class will use
 * to process words in different ways for a given line of 
 * text. 
 * @author Brandon Blaschke 
 * @version 2/14/17
 */
public interface Words {
    
    /**
     * Process the Words for a line of text.
     * @param theString String to be processed.
     * @param theMap Map to be mapped to for the words.
     */
    void processWords(final String theString, final Map<String, Word> theMap);
}

