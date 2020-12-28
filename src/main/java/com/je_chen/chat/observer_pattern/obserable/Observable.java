package com.je_chen.chat.observer_pattern.obserable;


import com.je_chen.chat.observer_pattern.observer.Observer;

public interface Observable {

    public void register(Observer observer);

    public void remove(Observer observer);

    public void notifyAllWatcher(String message);

}
