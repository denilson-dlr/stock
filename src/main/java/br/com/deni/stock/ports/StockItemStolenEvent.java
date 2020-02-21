package br.com.deni.stock.ports;

public class StockItemStolenEvent extends BaseEvent<String> {

    public final Integer quantity;

    public StockItemStolenEvent(String id, Integer quantity) {
        super(id);
        this.quantity = quantity;
    }
}
