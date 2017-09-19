package processing;

import behaviors.CountAllWords;
import behaviors.IgnoreSymbols;
import behaviors.Symbols;
import behaviors.Words;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** Class will process words of a given text file. 
 * @author Brandon Blaschke 
 * @version 2/14/17
 */
public class WordProcessor {
    
    /** Symbol behavior.*/
    private Symbols mySymbolBehavior;
    
    /** Words behavior. */
    private Words myWordBehavior; 
    
    /** Total number of words. */
    private int myTotalWords; 
    
    /** Map. */
    private Map<String, Word> myMap;
    
    /** Constructor for WrodProcessor. */
    public WordProcessor() {
        
        mySymbolBehavior = new IgnoreSymbols();
        myWordBehavior = new CountAllWords();
        myMap = new HashMap<String, Word>();
        myTotalWords = 0;
    }
    
    /** Processes the file. 
     * @param theFilePath File to process.
     * @param theUseTree Map to map Words to.*/
    public void processFile(final String theFilePath, final boolean theUseTree) {
        
        final File file = new File(theFilePath);
        
        //Try and scan file 
        try {
            
            final Scanner scan = new Scanner(file);
            
            //Read each line
            while (scan.hasNext()) {
                
                final String temp = scan.next();
                mySymbolBehavior.processSymbols(temp, myMap);
                myWordBehavior.processWords(temp, myMap);
            }
            
            scan.close();
            
        } catch (final FileNotFoundException e) {
            
            System.out.println("File not found");
        }
    }
    
    /** Get array list of Words. 
     * @param theTopWords Amount of top words to see.
     * @return Sublist of the Arraylist of the top words. 
     * */
    public Collection<Word> getTopValues(final int theTopWords) {
        
        final List<Word> values = new ArrayList<Word>(myMap.values());
        Collections.sort(values);
        
        //Get the total amount of words/characters 
        final Iterator<Word> iterator = values.iterator();
        
        while (iterator.hasNext()) {
            final Word temp = (Word) iterator.next();
            myTotalWords += temp.getCount();
        }
        
        return values;
    }
    
    /** Resets all values from the map and total words. */
    public void reset() {
        
        myMap.clear();
        myTotalWords = 0;
    }
    
    /** Set the symbol behavior for processing. 
     * @param theSymbol Type of behavior to be changed. 
     * */
    public void setSymbols(final Symbols theSymbol) {
        mySymbolBehavior = theSymbol; 
    }
    
    /** Get the total amount of words, This will vary depending on
     * the type of symbol behavior used.
     * @return The total words or characters. 
     */
    public int getTotalWords() {
        return myTotalWords; 
    }
    
    /** Set the word processing behavior. 
     * @param theWord Word behavior to be changed. 
     */
    public void setWords(final Words theWord) {
        myWordBehavior = theWord;
    }
    
    /** Set the map to be used on the word processing. 
     * @param theMap Map type to be used. 
     */
    public void setMap(final Map<String, Word> theMap) {
        myMap = theMap;
    }

}
