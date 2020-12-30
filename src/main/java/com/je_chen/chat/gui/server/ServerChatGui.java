package com.je_chen.chat.gui.server;

import com.je_chen.chat.gui.GuiFather;
import com.je_chen.chat.gui.main.MainGUI;

import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static com.je_chen.chat.Main.Main.mainGUI;

public class ServerChatGui extends GuiFather {

    private JPanel jPanel;

    public ServerChatGui(String windowName) {
        super(windowName);
        setContentPane(jPanel);
        setVisible(true);
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
