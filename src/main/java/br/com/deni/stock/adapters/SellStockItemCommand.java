package br.com.deni.stock.adapters;

public class SellStockItemCommand extends BaseCommand<String> {

    public final Integer quantity;

    public SellStockItemCommand(String id, Integer quantity) {
        super(id);
        this.quantity = quantity;
    }
}
