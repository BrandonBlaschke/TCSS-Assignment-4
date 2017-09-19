package behaviors;

import java.util.Map;

import processing.Word;

/** Counts symbols for behavior Symbols.
 * @author Brandon Blaschke
 * @version 2/15/17
 *
 */
public class CountSymbols implements Symbols {
    
    /** Symbol ASCII values lowest part. */
    private static final int ASCII_1 = 33; 
    
    /** Symbol ASCII values lowest part. */
    private static final int ASCII_2 = 47; 
    
    /** Symbol ASCII values middle part. */
    private static final int ASCII_3 = 58; 
    
    /** Symbol ASCII values middle part. */
    private static final int ASCII_4 = 64; 
    
    /** Symbol ASCII values last part. */
    private static final int ASCII_5 = 123; 
    
    /** Symbol ASCII values last part. */
    private static final int ASCII_6 = 126;
    
    /** Max string builder. */
    private static final int MAX_STRING = 30; 
    
    
    /** Constructor for CountSymbols.*/
    public CountSymbols() {
     
            //Empty Constructor 
    }

    @Override
    public void processSymbols(final String theString, final Map<String, Word> theMap) {
        
        boolean isSymbol = true; 
        
        final StringBuilder builder = new StringBuilder(MAX_STRING);
        
        //Go through string and append only letters 
        for (int i = 0; i < theString.length(); i++) {
            
            final char sym = theString.charAt(i);
            
            //check if letter is ASCII letter 
            if (sym >= ASCII_1 && sym <= ASCII_2 
                || sym >= ASCII_3 && sym <= ASCII_4
                || sym >= ASCII_5 && sym <= ASCII_6) {

                isSymbol = true; 
            } else {
                
                isSymbol = false; 
            }
            
            //Append letter to word 
            if (isSymbol) {
                builder.append(sym);
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
