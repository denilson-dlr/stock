package br.com.deni.stock.resources;

import br.com.deni.stock.core.domain.Invoice;
import br.com.deni.stock.core.services.StockBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/invoices")
public class InvoiceResource {

    @Autowired
    private StockBranchService stockBranchService;


}