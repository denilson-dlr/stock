package br.com.deni.stock.resources;

import br.com.deni.stock.core.domain.Invoice;
import br.com.deni.stock.core.domain.StockWarehouse;
import br.com.deni.stock.core.domain.dto.InvoiceNewDTO;
import br.com.deni.stock.core.domain.dto.StockWarehouseNewDTO;
import br.com.deni.stock.core.services.InvoiceService;
import br.com.deni.stock.core.services.StockWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/warehouses")
public class StockWarehouseResource {

    @Autowired
    private StockWarehouseService service;

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<StockWarehouse> find(@PathVariable Integer id) {
        StockWarehouse obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody StockWarehouseNewDTO objDto){
        StockWarehouse stockWarehouse = service.fromDTO(objDto);
        stockWarehouse = service.insert(stockWarehouse);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stockWarehouse.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody InvoiceNewDTO objDto){
        Invoice invoice = invoiceService.fromDTO(objDto);
        service.creditItem(invoice);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(invoice.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
