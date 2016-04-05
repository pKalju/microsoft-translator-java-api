Provides a Java wrapper around the Microsoft Translator API aka Bing Translator.

Created in an attempt to fill the void left by the deprecation of the Google Translate API announced on May 26, 2011 and scheduled for permanent shutdown on December 1, 2011.

In an effort to lessen the impact on Java developers that have previously integrated the Google Translate API into their applications, it is my goal to mimic the code structure, naming conventions, functionality, and usage patterns of the excellent and widely used [google-api-translate-java](http://code.google.com/p/google-api-translate-java/) by Rich Midwinter.


---


# Maven #

For those using Maven 2 to manage their project dependencies, the microsoft-translator-java-api is distributed via the [Maven Central](http://search.maven.org/#browse%7C458759702) repository. Simply include the following in your POM.xml to use the Microsoft Translator Java API:

```
<dependency>
    <groupId>com.memetix</groupId>
    <artifactId>microsoft-translator-java-api</artifactId>
    <version>0.6.2</version>
    <type>jar</type>
</dependency>
```


---


# Source #

The source is available on GitHub @ https://github.com/boatmeme/microsoft-translator-java-api

Find a bug? Fork it. Fix it. Issue a pull request.

```
git clone git://github.com/boatmeme/microsoft-translator-java-api
```

Contributions welcome!


---


# Issues #

Issue tracking is also on GitHub at https://github.com/boatmeme/microsoft-translator-java-api/issues.

Bug reports, Feature requests, and general inquiries welcome.


---

# Google Group #

There is a Google Group at http://groups.google.com/group/microsoft-translator-java-api for developer discussion and questions about the usage of this API.


---


# Requirements #

  * Java 1.5 or greater
  * A Windows Azure Marketplace Client ID and Client Secret - [Documentation](http://msdn.microsoft.com/en-us/library/hh454950.aspx) (follow the first 2 steps)

_Please note: If you signed up for a Bing Developer Key after March 31, 2012, you will not be able to use your App ID with this API. Older App IDs should continue to work, however it is recommended you switch to the new authentication scheme as soon as possible. Please visit the aforementioned documentation link for information on obtaining a Client Id and Secret_


---


# Quickstart #

[Download](http://code.google.com/p/microsoft-translator-java-api/downloads/list) the latest distribution jar.

```
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class Main {
  public static void main(String[] args) throws Exception {
    // Set your Windows Azure Marketplace client info - See http://msdn.microsoft.com/en-us/library/hh454950.aspx
    Translate.setClientId(/* Enter your Windows Azure Client Id here */);
    Translate.setClientSecret(/* Enter your Windows Azure Client Secret here */);

    String translatedText = Translate.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);

    System.out.println(translatedText);
  }
```


---

# Examples #

I've posted some examples in the [SVN repository](http://code.google.com/p/microsoft-translator-java-api/source/browse/#svn%2Ftrunk%2Fmicrosoft-translator-java-examples%2Fsrc%2Fmain%2Fjava%2Fcom%2Fmemetix%2Fmst%2Fexamples) on Google Code. The examples include:

  * [Translating text between two languages](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/TranslateExample.java)
  * [Detecting the native language of given text](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/DetectLanguageExample.java)
  * [Getting a list of supported languages, with localized language names](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/LanguageLocalizationExamples.java)
  * [Generating and playing a WAV of given text spoken in a chosen dialect](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/SpeakTextExample.java)
  * [Bulk translation using TranslateArray service](http://code.google.com/p/microsoft-translator-java-api/source/browse/trunk/microsoft-translator-java-examples/src/main/java/com/memetix/mst/examples/TranslateArrayExample.java)


---

# Contact #

Feel free to contact me by email (jonathan.griggs at gmail.com) or follow me on GitHub at https://github.com/boatmeme


---

# License #

The microsoft-translator-java-api is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

```
    /*
     * Copyright 2011-2015 Jonathan Griggs.
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
```

Please note that while this license does not distinguish between personal, internal or commercial use, the Microsoft Translator API itself _does_ in fact make this distinction:

> _If you intend to use the Microsoft Translator API for commercial or high volume purposes, you would need to sign a commercial license agreement and provide your appID to the Microsoft Translator team. For more details contact [mtlic@microsoft.com](mailto:mtlic@microsoft.com). This allows the Microsoft Translator team to better tune the service to the needs of our many partners, and avoid abuse._