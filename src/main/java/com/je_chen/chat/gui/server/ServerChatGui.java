package com.je_chen.chat.gui.server;

import com.je_chen.chat.Module.websocket.ChatServer;
import com.je_chen.chat.gui.GuiFather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class ServerChatGui extends GuiFather implements Runnable {

    private JPanel jPanel;
    private JButton exitButton;
    private JTextArea chatText;
    private JButton enterButton;
    private JTextField chatTextField;
    private final int port;
    private final ChatServer chatServer;

    public ServerChatGui(String windowName, int port) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);
        this.port = port;
        chatText.setEditable(false);
        chatServer = new ChatServer(this.port, chatText);

        chatTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    chatServer.broadcast(chatTextField.getText());
                    chatText.append(chatTextField.getText() + "\n");
                    chatTextField.setText("");
                }
            }
        });

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatServer.broadcast(chatTextField.getText());
                chatText.append(chatTextField.getText() + "\n");
                chatTextField.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerChatGui.this.dispatchEvent(new WindowEvent(ServerChatGui.this, WindowEvent.WINDOW_CLOSING));
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
                try {
                    chatServer.stop();
                } catch (IOException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    @Override
    public void run() {
        try {
            chatServer.start();
            System.out.println("開啟: " + this.port);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
