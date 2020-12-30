package com.je_chen.chat.gui.client;

import com.je_chen.chat.gui.GuiFather;

import javax.swing.*;
import java.awt.event.*;

import static com.je_chen.chat.Main.Main.mainGUI;

public class ClientChatGUI extends GuiFather {
    private JButton exitButton;
    private JTextField chatTextField;
    private JButton enterButton;
    private JTextArea chatText;
    private JPanel jPanel;

    public ClientChatGUI(String windowName) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);

        chatTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    chatText.setText(chatText.getText() + chatTextField.getText() + "\n");
                    chatTextField.setText("");
                }
            }
        });

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatText.setText(chatText.getText() + chatTextField.getText() + "\n");
                chatTextField.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientChatGUI.this.dispatchEvent(new WindowEvent(ClientChatGUI.this, WindowEvent.WINDOW_CLOSING));
            }
        });

    }

    @Override
    protected void closeEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println(windowName + " Frame Closed");
                mainGUI.setVisible(true);
            }
        });
    }
}
