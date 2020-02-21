package br.com.deni.stock.ports;

public class StockItemSoldEvent extends BaseEvent<String> {

    public final Integer quantity;

    public StockItemSoldEvent(String id, Integer quantity) {
        super(id);
        this.quantity = quantity;
    }
}
