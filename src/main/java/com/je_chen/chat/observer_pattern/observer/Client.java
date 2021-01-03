package com.je_chen.chat.observer_pattern.observer;


import com.je_chen.chat.observer_pattern.AbstractObserver;
import com.je_chen.chat.observer_pattern.obserable.Observable;

public class Client extends AbstractObserver implements Observer {

    private Observable channel;

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
        System.out.println(message);
    }

    @Override
    public void clean() {
        channel.remove(this);
    }
}
