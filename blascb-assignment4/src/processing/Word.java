package processing;

import java.util.Objects;

/** Represents a word in a text file. 
 * @author Brandon Blaschke 
 * @version 2/14/17
 */
public class Word implements Comparable<Word> {
    
    /** String for the word. */
    private final String myString;
    
    /** Count for the word. */
    private int myCount; 
    
    /**Constructor for Word. 
     * @param theString String for the word.
     * @param theCount Amount of times word shows up. 
     * */
    public Word(final String theString, final int theCount) {
        
        myString = theString;
        myCount = theCount; 
    }
    
    /** Add one to the count. */
    public void addOne() {
        myCount++;
    }
    
    /** Get the count of the Word. 
     * @return Amount the Word has appeared. 
     */
    public int getCount() {
        return myCount;
    }
    
    @Override 
    public String toString() {
        return myString + " --------- " + myCount;
    }

    @Override
    public int compareTo(final Word theWord) {
        
        int result = 0; 
        
        if (myCount > theWord.getCount()) {
            
            result = 1;
        } else if (myCount < theWord.getCount()) {
            
            result =  -1; 
        } else {
            
            result =  0;
        }
        
        return result;
    }
    
    @Override
    public boolean equals(final Object theObject) {
        
        boolean result = false; 
        
        if (theObject instanceof Word) {
            
            final Word temp = (Word) theObject;
            
            if (temp.getCount() == myCount) {
                result =  true;
            }
        } 
        
        return result; 
    }
    
    @Override 
    public int hashCode() {
        return Objects.hash(myCount);
    }
}
