package com.je_chen.chat.Main;

import com.je_chen.chat.Module.WebSocketServerEndPoint;
import com.je_chen.chat.gui.main.MainGUI;
import org.glassfish.tyrus.server.Server;

import javax.websocket.DeploymentException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    static WebSocketServerEndPoint webSocketServerEndPoint;

    public static void main(String[] argv) {
        webSocketServerEndPoint = new WebSocketServerEndPoint();
        MainGUI.getInstance("test");
        Run();
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
                String[] commandArray = Control_String.split(" ");
                String command = commandArray[0];
                if(command.equals("send")){
                    try {
                        webSocketServerEndPoint.sendText(commandArray[1]);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } while (!Control_String.equals("exit"));
        } catch (IOException | DeploymentException e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }

}
