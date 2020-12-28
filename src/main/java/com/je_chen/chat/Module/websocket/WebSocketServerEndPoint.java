package com.je_chen.chat.Module.websocket;

import com.je_chen.chat.observer_pattern.obserable.Server;
import com.je_chen.chat.observer_pattern.observer.Client;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ServerEndpoint(value = "/websocket")
public class WebSocketServerEndPoint {

    private static Server server = new Server();
    private static List<Client> clients = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen");
        Client client = new Client(session);
        client.subscribe(server);
        clients.add(client);
        System.out.println(session.getId() + " connected");
    }

    @OnError
    public void onError(Session session, Throwable throwable){
        try {
            session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, "Server Error"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose");
        for(Client client : clients)
            if (client.getSession().equals(session)) {
                client.unsubscribe();
                clients.remove(client);
            }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received : " + message);
        server.changeState(message);
    }
}

