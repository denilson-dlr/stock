package br.com.deni.stock.adapters;

public class TransferStockItemCommand extends BaseCommand<String> {

    public final Integer quantity;

    public final String receiver;

    public TransferStockItemCommand(String id, Integer quantity, String receiver){
        super(id);
        this.quantity = quantity;
        this.receiver = receiver;
    }
}
