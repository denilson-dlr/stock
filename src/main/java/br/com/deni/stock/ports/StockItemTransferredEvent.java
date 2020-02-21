package br.com.deni.stock.ports;

public class StockItemTransferredEvent extends BaseEvent<String> {

    public final Integer quantity;

    public StockItemTransferredEvent(String id, Integer quantity) {
        super(id);
        this.quantity = quantity;
    }
}
