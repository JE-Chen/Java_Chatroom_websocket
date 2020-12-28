package com.je_chen.chat.gui.main;

import com.je_chen.chat.gui.GuiFather;

import javax.swing.*;

public class MainGUI extends GuiFather {
    private JPanel jPanel;
    private JButton button1;
    private JToolBar mainMenu;

    public MainGUI(String windowName) {
        super(windowName);
        setContentPane(jPanel);
        mainMenu.setFloatable(false);
        setVisible(true);
    }
}
