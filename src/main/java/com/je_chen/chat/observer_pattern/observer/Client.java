package com.je_chen.chat.observer_pattern.observer;


import com.je_chen.chat.observer_pattern.obserable.Observable;

import javax.websocket.Session;
import java.io.IOException;

public class Client implements Observer {

    private Observable channel;
    private final Session session;

    public Client(Session session) {
        this.session = session;
    }

    @Override
    public void subscribe(Observable observable) {
        this.channel = observable;
        channel.register(this);
    }

    @Override
    public void unsubscribe() {
        channel.remove(this);
    }

    @Override
    public void update(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Session getSession(){
        return this.session;
    }

}
