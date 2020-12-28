package com.je_chen.chat.gui.client;

import com.je_chen.chat.gui.GuiFather;

import javax.swing.*;

public class ClientChatGUI extends GuiFather {
    private JButton ExitButton;
    private JTextField textField1;
    private JButton EnterButton;
    private JLabel chatText;
    private JPanel jPanel;

    public ClientChatGUI(String windowName) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);
    }

}
