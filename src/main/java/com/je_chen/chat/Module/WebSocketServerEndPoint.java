package com.je_chen.chat.Module;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint(value = "/websocket")
public class WebSocketServerEndPoint {

    private static Session user;
    private static List<Session> sessionArrayList = new ArrayList<>();
    private static List<String> messageArrayList = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        user = session;
        System.out.println("onOpen");
        sessionArrayList.add(session);
        System.out.println(session.getId() + " connected");
        if (!messageArrayList.isEmpty()) {
            for (String beforeMessage : messageArrayList) {
                try {
                    session.getBasicRemote().sendText(beforeMessage);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Send all message to " + session.getId());
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, "Server Error"));
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose");
        sessionArrayList.remove(session);
        System.out.println(session.getId() + " Leave Now,  " + sessionArrayList.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received : " + message);
        if (!message.equals("Close")) {
            messageArrayList.add(message);
            broadcast(message);
        } else {
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcast(String message) {
        for (Session user : sessionArrayList) {
            try {
                user.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String beforeMessage : messageArrayList) {
                System.out.print(beforeMessage + " ");
            }
            System.out.println();
        }
    }

    public void sendText(String message){
        try {
            user.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearMessage(){
        messageArrayList.clear();
    }

    public void clearUser(){
        sessionArrayList.clear();
    }

}
