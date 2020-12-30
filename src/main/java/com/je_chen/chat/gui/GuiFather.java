package com.je_chen.chat.gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GuiFather extends JFrame {

    protected String windowName;

    public GuiFather(String windowName) {
        this.windowName = windowName;
        setTitle(windowName);
        setSize(500,500);
        closeEvent();
    }

    protected void closeEvent() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println(windowName + " Frame Closed");
                GuiFather.this.dispose();
            }
        });
    }

}
