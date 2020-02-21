package br.com.deni.stock.resources;

import br.com.deni.stock.core.domain.dto.StockItemSellDTO;
import br.com.deni.stock.core.domain.dto.StockItemTransferDTO;
import br.com.deni.stock.core.services.StockItemCommandService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/stock-items")
@Api(value = "Stock Item Commands", description = "Stock Item Commands Related Endpoints", tags = "Stock Item Commands")
public class StockItemCommandResource {

    private final StockItemCommandService service;

    public StockItemCommandResource(StockItemCommandService service){
        this.service = service;
    }

    @PostMapping
    public CompletableFuture<String> sellItem(@RequestBody StockItemSellDTO dto){
        return service.sellStockItem(dto);
    }

    @PutMapping
    public CompletableFuture<String> transferItem(@RequestBody StockItemTransferDTO dto){
        return service.transferStockItem(dto);
    }
}
