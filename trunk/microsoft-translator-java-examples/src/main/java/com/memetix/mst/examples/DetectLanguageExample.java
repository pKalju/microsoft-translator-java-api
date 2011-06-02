/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.memetix.mst.examples;

import com.memetix.mst.detect.Detect;
import com.memetix.mst.language.Language;

/**
 * DetectLanguageExample
 * 
 * Calls Microsoft to determine origin language of provided text.
 * 
 * Shows how to turn the two character response code into a language and how to localize the Language name
 *
 * @author griggs.jonathan
 * @date Jun 1, 2011
 */
public class DetectLanguageExample {
    public static void main( String[] args ) throws Exception
    {
        Detect.setKey("MY_API_KEY");
        
        //Detect returns a String representing the language code (Note: this should change in 0.4, it should return a full-blown Language enum instance)
        String detectedLanguage = Detect.execute("Bonjour le monde");
        
        // Prints out the language code
        System.out.println(detectedLanguage);
        
        // Gets the Language Enum instance from the code string
        Language lDetected = Language.fromString(detectedLanguage);
        
        //Prints the Language name in English - "French"
        System.out.println(lDetected.getName(Language.ENGLISH));
        
        //Prints the Language name in French - "Français"
        System.out.println(lDetected.getName(Language.FRENCH));
        
        //Prints the Language name in German - "Französisch"
        System.out.println(lDetected.getName(Language.GERMAN));
    }
}
