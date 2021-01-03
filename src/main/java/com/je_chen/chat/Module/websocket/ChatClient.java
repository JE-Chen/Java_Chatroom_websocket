package com.je_chen.chat.Module.websocket;


import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import javax.swing.*;
import java.net.URI;
import java.util.Map;

public class ChatClient extends org.java_websocket.client.WebSocketClient{

    private JTextArea chatText;

    public ChatClient(URI ServerUri, Draft draft) {
        super(ServerUri, draft);
    }

    public ChatClient(URI ServerUri) {
        super(ServerUri);
    }

    public ChatClient(URI ServerUri, JTextArea chatText) {
        this(ServerUri);
        this.chatText = chatText;
    }

    public ChatClient(URI ServerUri, Map<String, String> HttpHeaders) {
        super(ServerUri, HttpHeaders);
    }


    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("Connect");
    }

    @Override
    public void onMessage(String Message) {
        System.out.println("Received Message : " + Message);
        chatText.append(Message + "\n");
    }

    @Override
    public void onClose(int code, String Reason, boolean Remote) {
        System.out.println("Connection closed by " + (Remote ? "remote peer" : "us") + " Code: " + code + " Reason: " + Reason);
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }
}
