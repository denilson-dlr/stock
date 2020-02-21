package br.com.deni.stock.core.domain;

import br.com.deni.stock.adapters.SellStockItemCommand;
import br.com.deni.stock.adapters.TransferStockItemCommand;
import br.com.deni.stock.ports.StockItemShippingEvent;
import br.com.deni.stock.ports.StockItemSoldEvent;
import jdk.net.SocketFlow;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class StockItemAggregate {

    @AggregateIdentifier
    private String id;

    private Integer quantity;

    private String status;

    public StockItemAggregate(){

    }

    @CommandHandler
    public StockItemAggregate(SellStockItemCommand command){
        AggregateLifecycle.apply(new StockItemSoldEvent(command.id, command.quantity));
    }

    @EventSourcingHandler
    protected void on(StockItemSoldEvent event){
        this.id = event.id;
        this.quantity = event.quantity;
        this.status = "VENDIDO";
    }

    @CommandHandler
    public void handle(TransferStockItemCommand command){
        AggregateLifecycle.apply(new StockItemShippingEvent(command.id, command.quantity));
    }

    @EventSourcingHandler
    protected void on(StockItemShippingEvent event){
        this.id = event.id;
        this.quantity = event.quantity;
        this.status = "EM TRANSITO";
    }
}
