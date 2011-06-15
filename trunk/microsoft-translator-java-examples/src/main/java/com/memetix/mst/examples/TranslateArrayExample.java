/*
 * microsoft-translator-java-api-examples
 * 
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

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * TranslateArrayExample
 * 
 * Demonstrates the ability to have Microsoft Translator perform bulk translations via the 
 * TranslateArray service
 * 
 * REQUIRES v0.4 of the microsoft-translator-java-api library
 * 
 * @author griggs.jonathan
 * @date Jun 2, 2011
 * @since 0.4 June 2, 2011
 */
public class TranslateArrayExample 
{
    public static void main( String[] args ) throws Exception {
        // Set the API key once per JVM. It is set statically and applies to all services
        Translate.setKey("YOUR_API_KEY_HERE");
        
        //Create your array of texts to be translated
        // NOTE: The source language of all texts must be the same. For instance, I cannot translate
        // a Spanish string, French string, and English string into German during a single call
        String[] sourceTexts = 
            {
                "This is a sentence, translate me.",
                "I would like to be translated",
                "How are you doing today?"
            };
        
        
        // Call the translate.execute method, passing an array of source texts
        String[] translatedTexts = Translate.execute(sourceTexts, Language.ENGLISH, Language.FRENCH);
        
        //Print the results!
        for(String text : translatedTexts) {
            System.out.println(text);
        }
    }
}
