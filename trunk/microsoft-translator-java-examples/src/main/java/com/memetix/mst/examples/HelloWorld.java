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
