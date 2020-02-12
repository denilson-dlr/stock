package br.com.deni.stock.resources;

import br.com.deni.stock.core.domain.Invoice;
import br.com.deni.stock.core.domain.StockBranch;
import br.com.deni.stock.core.domain.dto.InvoiceNewDTO;
import br.com.deni.stock.core.domain.dto.StockBranchNewDTO;
import br.com.deni.stock.core.services.InvoiceService;
import br.com.deni.stock.core.services.StockBranchService;
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
@RequestMapping(value = "/stockBranch")
public class StockBranchResource {

    @Autowired
    private StockBranchService service;

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<StockBranch> find(@PathVariable Integer id) {
        StockBranch obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody StockBranchNewDTO objDto){
        StockBranch stockBranch = service.fromDTO(objDto);
        stockBranch = service.insert(stockBranch);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stockBranch.getId()).toUri();
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
