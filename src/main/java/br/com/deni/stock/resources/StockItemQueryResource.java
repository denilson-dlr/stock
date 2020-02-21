package br.com.deni.stock.resources;

import br.com.deni.stock.core.services.StockItemQueryService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/stock-items")
@Api(value = "Stock Item Queries", description = "Stock Item Query Events Endpoint", tags = "Stock Item Queries")
public class StockItemQueryResource {

    private final StockItemQueryService service;

    public StockItemQueryResource(StockItemQueryService service){
        this.service = service;
    }

    @GetMapping("/{name}/events")
    public List<Object> lisEventsForItems(@PathVariable(value = "name") String name){
        return service.listEventsForStockItem(name);
    }
}
