package com.je_chen.chat.observer_pattern.observer;


import com.je_chen.chat.observer_pattern.obserable.Observable;

public interface Observer {

    public void subscribe(Observable observable);

    public void unsubscribe();

    public void update(String message);

}
