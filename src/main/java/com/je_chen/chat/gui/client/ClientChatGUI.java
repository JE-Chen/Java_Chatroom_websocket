package com.je_chen.chat.gui.client;

import com.je_chen.chat.Module.websocket.ChatClient;
import com.je_chen.chat.gui.GuiFather;

import javax.swing.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URISyntaxException;

public class ClientChatGUI extends GuiFather implements Runnable {
    private JButton exitButton;
    private JTextField chatTextField;
    private JButton enterButton;
    private JTextArea chatText;
    private JPanel jPanel;
    private ChatClient client;
    private final int port;
    private final String serverURL;

    public ClientChatGUI(String windowName, int port, String serverURL) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);
        this.port = port;
        this.serverURL = serverURL;
        chatText.setEditable(false);
        try {
            client = new ChatClient(new URI("ws://" + serverURL + ":" + port + "/websocket"), chatText);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        chatTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    client.send(chatTextField.getText());
                    chatTextField.setText("");
                }
            }
        });

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.send(chatTextField.getText());
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
            }
        });
    }

    @Override
    public void run() {
        client.connect();
        try {
            System.out.println("連線至: " + new URI("ws://" + serverURL + ":" + port + "/websocket").toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
