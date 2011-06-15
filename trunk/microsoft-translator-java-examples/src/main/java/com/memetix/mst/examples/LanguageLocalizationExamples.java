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
import java.util.Map;

/**
 * LanguageLocalizationExamples
 * 
 * Shows how to deal with the Language Enum, getting lists of all the values and getting 
 * language lists with localized language names
 * 
 * @author griggs.jonathan
 * @date Jun 1, 2011
 * @since 0.3 June 1, 2011
 */
public class LanguageLocalizationExamples {
    public static void main( String[] args ) throws Exception
    {
        //Don't forget to set the API Key
        Language.setKey("YOUR_API_KEY");
        
        //Get the all the values in the enum and print the non-localized name (i.e. the literal name of the Enum constant)
        // this is the default way of dealing with lists of enums, but not the most user friendly way to construct a list
        System.out.println("\nLanguage Names as Enum Constants\n");
        for(Language lang : Language.values()) {
            System.out.println(lang.name() + " : " + lang.toString());
        }
        
        //You could also call our getName() method to get the language name localized to the language of your choosing...
        //...for instance, in Spanish
        System.out.println("\nLanguage Names localized to Spanish\n");
        for(Language lang : Language.values()) {
            System.out.println(lang.getName(Language.SPANISH) + " : " + lang.toString());
        }
        
        //...or, in French
        System.out.println("\nLanguage Names localized to French\n");
        for(Language lang : Language.values()) {
            System.out.println(lang.getName(Language.FRENCH) + " : " + lang.toString());
        }
        
        /*  This is a perfectly valid way to display language lists...
         * 
         *  ...BUT...
         * 
         *  ...the list is not in alphabetical order once localized.
         * 
         * 
         *  The best way to get an alphabetized list of localized language names (to populate select lists, for example)
         *  is to call the Language.values() method, passing a locale.
         * 
         *  NOTE: The following code will only work with version 0.4 of the microsoft-translator-java-api library
         */
        
        System.out.println("\nLanguage Names localized to French AND in alphanumeric order\n");
        Map<String,Language> localizedMap = Language.values(Language.FRENCH);
        for(String langName : localizedMap.keySet()) {
            System.out.println(langName + " : " + localizedMap.get(langName).toString());
        }
    }
}
