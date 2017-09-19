package behaviors;

import java.util.Map;

import processing.Word;

/** Counts all words and only words for behavior Words.
 * @author Brandon Blaschke
 * @version 2/14/17
 *
 */
public class CountAllWords implements Words {
    
    /** Max string builder. */
    private static final int MAX_STRING = 30; 
    
    /** ASCII Value. */
    private static final int CAP_A = 65;
    
    /** ASCII Value. */
    private static final int CAP_Z = 90;
    
    /** ASCII Value. */
    private static final int LOW_A = 97;
    
    /** ASCII Value. */
    private static final int LOW_Z = 122;
    
    /** Constructor for CountAllWords. */
    public CountAllWords() {
      
        //Empty Constructor 
    }

    @Override
    public void processWords(final String theString, final Map<String, Word> theMap) {
        
        boolean isWord = true; 
        
        final StringBuilder builder = new StringBuilder(MAX_STRING);
        
        //Go through string and append only letters 
        for (int i = 0; i < theString.length(); i++) {
            
            final char letter = theString.charAt(i);
            
            //check if letter is ASCII letter 
            if (letter >= CAP_A && letter <= CAP_Z 
                || letter >= LOW_A && letter <= LOW_Z) {

                isWord = true; 
            } else {
                
                isWord = false; 
            }
            
            //Append letter to word 
            if (isWord) {
                builder.append(letter);
            }
        }
        
        final Word word = new Word(builder.toString(), 1);
        if (theMap.containsKey(builder.toString())) {
            
            theMap.get(builder.toString()).addOne();
        } else {
            theMap.put(builder.toString(), word);
        }
        
        builder.delete(0, builder.capacity());
    }


}
