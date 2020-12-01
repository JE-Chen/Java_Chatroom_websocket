package com.je_chen.chat.gui.chat;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatGUI {
    private static ChatGUI Instance;
    private String windowName;
    private JFrame jFrame;
    private JButton ExitButton;
    private JTextField textField1;
    private JButton EnterButton;
    private JLabel chatText;
    private javax.swing.JPanel jPanel;

    public static synchronized ChatGUI getInstance(String windowName) {
        if (Instance == null) {
            Instance = new ChatGUI();
            Instance.show(windowName);
        }
        return Instance;
    }

    private void show(String windowName) {
        this.windowName = windowName;
        jFrame = new JFrame(windowName);
        jFrame.setContentPane(getInstance(windowName).jPanel);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setSize(500,500);
        closeEvent();
        jFrame.setVisible(true);
    }

    private void closeEvent() {
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Instance = null;
                System.out.println(windowName + " Frame Closed");
                System.exit(0);
            }
        });
    }
}
