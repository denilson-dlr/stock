package br.com.deni.stock.resources;

import br.com.deni.stock.core.domain.Invoice;
import br.com.deni.stock.core.domain.StockBranch;
import br.com.deni.stock.core.services.StockBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/stockBranch")
public class StockBranchResource {

    @Autowired
    private StockBranchService service;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<StockBranch> find(@PathVariable Integer id) {
        StockBranch obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody StockBranch stockBranch){
        stockBranch = service.insert(stockBranch);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stockBranch.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Invoice invoice){
        service.creditItem(invoice);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(invoice.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
