/*
 * Copyright 2011 Jonathan Griggs <jonathan.griggs at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
 * @since 0.3 June 1, 2011
 */
public class DetectLanguageExample {
    public static void main( String[] args ) throws Exception
    {
        Detect.setKey("YOUR_API_KEY_HERE");
        
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
