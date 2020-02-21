package br.com.deni.stock.ports;

public class StockItemShippingEvent extends BaseEvent<String> {

    public final Integer quantity;

    public StockItemShippingEvent(String id, Integer quantity) {
        super(id);
        this.quantity = quantity;
    }
}
