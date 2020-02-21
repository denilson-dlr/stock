package br.com.deni.stock.core.services;

import br.com.deni.stock.adapters.SellStockItemCommand;
import br.com.deni.stock.adapters.TransferStockItemCommand;
import br.com.deni.stock.core.domain.dto.StockItemSellDTO;
import br.com.deni.stock.core.domain.dto.StockItemTransferDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class StockItemCommandService {

    private final CommandGateway commandGateway;

    public StockItemCommandService(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String>  sellStockItem(StockItemSellDTO objDto){
        return commandGateway.send(new SellStockItemCommand(UUID.randomUUID().toString(), objDto.getQuantity()));
    }

    public CompletableFuture<String> transferStockItem(StockItemTransferDTO objDto){
        return commandGateway.send(new TransferStockItemCommand(UUID.randomUUID().toString(), objDto.getQuantity(), objDto.getReceiver()));
    }
}
