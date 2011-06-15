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

import com.memetix.mst.language.SpokenDialect;
import com.memetix.mst.speak.Speak;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;


/**
 * SpeakTextExample
 * 
 * Shows how to send a string of Text to the Microsoft Translator Speak service and have it generate
 * a URL to a WAV file. Then, play the spoken audio in a platform independent manner.
 * 
 * @author griggs.jonathan
 * @date Jun 1, 2011
 * @since 0.3 June 1, 2011
 */
public class SpeakTextExample {
    
    public static void main( String[] args ) throws Exception
    {
        Speak.setKey("YOUR_API_KEY_HERE");
        // Calls the speak service with text to be spoken and specifies the dialect in which to speak it
        String sWavUrl = Speak.execute("Did you enjoy the 2011 Cricket World Cup?", SpokenDialect.ENGLISH_INDIA);
        
        //Print the URL returned from the Speak service
        System.out.println(sWavUrl);
        
        // Now, makes an HTTP Connection to get the InputStream
        final URL waveUrl = new URL(sWavUrl);
        final HttpURLConnection uc = (HttpURLConnection) waveUrl.openConnection();
        
        // Pass the input stream to the playClip method
        playClip(uc.getInputStream());
    }
    
    private static void playClip(InputStream is) throws Exception {
        class AudioListener implements LineListener {
            private boolean done = false;
            
            public synchronized void update(LineEvent event) {
              Type eventType = event.getType();
              if (eventType == Type.STOP || eventType == Type.CLOSE) {
                done = true;
                notifyAll();
              }
            }
            public synchronized void waitUntilDone() throws InterruptedException {
              while (!done) { wait(); }
            }
        }
        
        AudioListener listener = new AudioListener();
        
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(is);
        
          try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);
            try {
              clip.start();
              listener.waitUntilDone();
            } finally {
              clip.close();
            }
          } finally {
            audioInputStream.close();
          }
    }
}
