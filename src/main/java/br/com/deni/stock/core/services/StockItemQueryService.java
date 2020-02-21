package br.com.deni.stock.core.services;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockItemQueryService {

    private final EventStore eventStore;

    public StockItemQueryService(EventStore eventStore){
        this.eventStore = eventStore;
    }

    public List<Object> listEventsForStockItem(String name) {
        return eventStore.readEvents(name).asStream().map( s -> s.getPayload()).collect(Collectors.toList());
    }
}
