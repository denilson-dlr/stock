package br.com.deni.stock.ports;

public class BaseEvent<T> {

    public final T id;

    public BaseEvent (T id){
        this.id = id;
    }
}
