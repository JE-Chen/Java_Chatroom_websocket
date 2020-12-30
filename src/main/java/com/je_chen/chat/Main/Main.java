package com.je_chen.chat.Main;

import com.je_chen.chat.Module.websocket.WebSocketServerEndPoint;

import com.je_chen.chat.gui.main.MainGUI;
import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static MainGUI mainGUI;

    public static void main(String[] argv) {
         mainGUI = new MainGUI("主頁");
    }

    public static void Run() {
        Server server = new Server("localhost", 5050, "/websocket", null, WebSocketServerEndPoint.class);
        String Control_String;
        try {
            server.start();
            System.out.println("Input exit to stop the server.");
            do {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Control_String = reader.readLine();
            } while (!Control_String.equals("exit"));
        } catch (IOException | DeploymentException e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }

}
