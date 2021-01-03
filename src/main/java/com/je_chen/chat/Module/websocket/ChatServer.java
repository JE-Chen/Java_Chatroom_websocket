package com.je_chen.chat.Module.websocket;


import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.swing.*;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;

public class ChatServer extends WebSocketServer {

    private JTextArea chatText;

    public ChatServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
    }

    public ChatServer(int port, JTextArea chatText) {
        this(new InetSocketAddress(port));
        this.chatText = chatText;
    }

    public ChatServer(InetSocketAddress address) {
        super(address);
    }

    public ChatServer(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        broadcast("new connection: " + handshake.getResourceDescriptor());
        chatText.append("new connection: " + handshake.getResourceDescriptor() + "\n");
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!");
        chatText.append(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " entered the room!" + "\n");
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        broadcast(conn + " has left the room!");
        System.out.println(conn + " has left the room!");
        chatText.append(conn + " has left the room!" + "\n");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        broadcast(message);
        System.out.println(conn + ": " + message);
        chatText.append(message + "\n");
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        broadcast(message.array());
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }

}