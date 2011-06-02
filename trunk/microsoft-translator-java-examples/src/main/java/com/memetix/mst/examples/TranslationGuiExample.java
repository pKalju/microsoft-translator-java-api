/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.memetix.mst.examples;

import com.memetix.mst.language.Language;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
        JPanel panel = new JPanel();
        this.getContentPane().add(panel);
        this.setJMenuBar(buildMenuBar());
        
        //panel.setLayout(null);
        
        JButton quitButton = new JButton("Quit");
        //quitButton.setBounds(50,60,80,30);
        quitButton.addActionListener(quitAction);
        
        panel.add(quitButton);
        
        JScrollPane pane = new JScrollPane();
        pane.getViewport().add( buildTextArea());
        panel.add(pane);
        
        panel.add(buildComboBox());
    }
    
    ActionListener quitAction = new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    };
    
    public final JTextArea buildTextArea() {
        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
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
            ComboBoxModel model = new DefaultComboBoxModel(Language.values(Language.ENGLISH).keySet().toArray());
            comboBox.setModel(model);
            comboBox.setSelectedIndex(0);
        } catch(Exception e) {
            //Error!
        }
        comboBox.setPreferredSize(new Dimension(140,22));
        comboBox.setMaximumSize(new Dimension(140,22));
        return comboBox;
    }
}
