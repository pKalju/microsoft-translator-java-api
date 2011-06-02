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

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Hello world!
 * 
 * A short, sweet demo on how to translate a String using microsoft-translator-java-api
 * 
 * @author griggs.jonathan
 * @date Jun 1, 2011
 */
public class HelloWorld 
{
    public static void main( String[] args ) throws Exception
    {
        Translate.setKey("YOUR_API_KEY_HERE");
        String translatedText = Translate.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);
        System.out.println(translatedText);
    }
}
