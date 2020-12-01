package com.je_chen.chat.gui.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainGUI {
    private static MainGUI Instance;
    private String windowName;
    private JFrame jFrame;
    private JPanel jPanel;
    private JButton ConnectButton;
    private JTextField textField1;
    private JLabel ServerUrl;

    private MainGUI() {
        ConnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, textField1.getText());
            }
        });
    }

    public static synchronized MainGUI getInstance(String windowName) {
        if (Instance == null) {
            Instance = new MainGUI();
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
