/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.memetix.mst.examples;

import com.memetix.mst.language.Language;
import com.memetix.mst.language.SpokenDialect;
import com.memetix.mst.speak.Speak;
import com.memetix.mst.translate.Translate;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * TranslationGuiExample
 *
 * @author griggs.jonathan
 * @date Jun 2, 2011
 * @since 0.4 Jun 2, 2011
 */
public class TranslationGuiExample extends JFrame {
    final String[] langs = {"AUTO_DETECT","English","French"};
    
    private JComboBox localeCombo;
    private JComboBox toLanguage;
    private JPanel panel;
    private JTextArea toTextArea;
    private JTextArea fromTextArea;
    private JButton speakButton;
    
    public TranslationGuiExample() {
        Translate.setKey("YOUR_API_KEY_HERE");
        setTitle("microsoft-translator-java-api GUI Example");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buildGui();
    }
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TranslationGuiExample ex = new TranslationGuiExample();
                ex.setVisible(true);
            }
        });
    }
    
    public final void buildGui() {
        panel = new JPanel();
        this.getContentPane().add(panel);
        this.setJMenuBar(buildMenuBar());
        
        //panel.setLayout(null);
        
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(quitAction);
        
        JButton speakButton = new JButton("Speak");
        speakButton.addActionListener(speakAction);
        
        panel.add(quitButton);
        panel.add(speakButton);
        
        JScrollPane pane = new JScrollPane();
        fromTextArea =  buildFromTextArea();
        fromTextArea.addKeyListener(fromKeyListener);
        toTextArea =    buildToTextArea();
        pane.getViewport().add( fromTextArea);
        toLanguage = buildComboBox();
        panel.add( toLanguage );
        panel.add( toTextArea);
        panel.add(pane);
        
        localeCombo = buildComboBox();
        panel.add(localeCombo);
    }
    
    private JTextArea buildToTextArea() {
        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setColumns(40);
        area.setRows(5);
        area.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        return area;
    }
    
    private JTextArea buildFromTextArea() {
        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setColumns(40);
        area.setRows(5);
        area.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        return area;
    }
    
    public final JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        JMenuItem menuItem = new JMenuItem("Exit Application");
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setToolTipText("Exit the translator application");
        menuItem.addActionListener(quitAction);
        file.add(menuItem);
        menuBar.add(file);
        return menuBar;
    }
    
    public final JComboBox buildComboBox() {
        JComboBox comboBox = new JComboBox();
        try {
            ComboBoxModel model = new DefaultComboBoxModel(Language.values(Language.ENGLISH).values().toArray());
            comboBox.setModel(model);
            comboBox.setSelectedIndex(0);
            comboBox.addActionListener(localeChange);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(panel, "Error Loading Languages: " + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        comboBox.setPreferredSize(new Dimension(140,22));
        comboBox.setMaximumSize(new Dimension(140,22));
        return comboBox;
    }
    
    KeyAdapter fromKeyListener = new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    toTextArea.setText(Translate.execute(fromTextArea.getText().trim(), Language.ENGLISH, Language.FRENCH));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Performing Localization: " + ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
                }
                evt.consume();
            }
        }
    };
    
    ActionListener localeChange = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            final JComboBox cb = (JComboBox)event.getSource();
            Language localeCode = (Language)cb.getSelectedItem();
            try {
                ComboBoxModel model = new DefaultComboBoxModel(Language.values(localeCode).values().toArray());
                cb.setModel(model);
            } catch (Exception e){
                JOptionPane.showMessageDialog(panel, "Performing Localization: " + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    };
    
    ActionListener quitAction = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    };
    
    ActionListener speakAction = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            triggerAudio();
        }
    };
    
    private void triggerAudio() {
        try {
            String sWavUrl = Speak.execute(fromTextArea.getText(), SpokenDialect.ENGLISH_INDIA);
            // Now, makes an HTTP Connection to get the InputStream
            final URL waveUrl = new URL(sWavUrl);
            final HttpURLConnection uc = (HttpURLConnection) waveUrl.openConnection();

            // Pass the input stream to the playClip method
            playClip(uc.getInputStream());
        } catch (Exception e){
            JOptionPane.showMessageDialog(panel, "Playing Speech: " + e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void playClip(InputStream is) throws Exception {
        class AudioListener implements LineListener {
            private boolean done = false;
            @Override public synchronized void update(LineEvent event) {
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
