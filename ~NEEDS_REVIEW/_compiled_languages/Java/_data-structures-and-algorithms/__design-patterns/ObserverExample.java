package com.example;

import java.util.*;

/**
 * OUTPUT:
 * <p>
 * ObserverA received: hello
 * ObserverB received: hello
 * ObserverC received: hello
 */
public class ObserverExample {
    public static void main(String[] args) {
        Observable observable = new Observable();
        observable.addObserver(new ObserverA());
        observable.addObserver(new ObserverB());
        observable.addObserver(new ObserverC());
        observable.update("hello");
    }
}

//=====================================
interface Observer {
    void update(String data);
}

//=====================================
class ObserverA implements Observer {
    String data;

    public ObserverA() {
    }

    @Override
    public void update(String data) {
        System.out.println("ObserverA received: " + data);
        this.data = data;
    }
}

//=====================================
class ObserverB implements Observer {
    String data;

    public ObserverB() {
    }

    @Override
    public void update(String data) {
        System.out.println("ObserverB received: " + data);
        this.data = data;
    }
}

//=====================================
class ObserverC implements Observer {
    String data;

    public ObserverC() {
    }

    @Override
    public void update(String data) {
        System.out.println("ObserverC received: " + data);
        this.data = data;
    }
}

//=====================================
class Observable {
    private List<Observer> observers;

    public Observable() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void update(String data) {
        for (Observer o : this.observers) {
            o.update(data);
        }
    }
}

//=====================================
