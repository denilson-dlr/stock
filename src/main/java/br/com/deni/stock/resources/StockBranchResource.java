package br.com.deni.stock.resources;

import br.com.deni.stock.core.domain.Invoice;
import br.com.deni.stock.core.domain.StockBranch;
import br.com.deni.stock.core.domain.dto.InvoiceNewDTO;
import br.com.deni.stock.core.domain.dto.StockBranchNewDTO;
import br.com.deni.stock.core.services.InvoiceService;
import br.com.deni.stock.core.services.StockBranchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/branches")
@Api(value = "Estoque do Centro Distribuidor",description = "Estoque do Centro Distribuidor Endpoint", tags = "Estoque Centro Distribuidor")
public class StockBranchResource {

    @Autowired
    private StockBranchService service;

    @Autowired
    private InvoiceService invoiceService;

    @ApiOperation(value = "consultar-estoque-loja", nickname = "Consultar as Lojas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockBranch> find(@PathVariable Integer id) {
        StockBranch obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "inserir-loja", nickname = "Inserir nova Loja")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody StockBranchNewDTO objDto){
        StockBranch stockBranch = service.fromDTO(objDto);
        stockBranch = service.insert(stockBranch);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stockBranch.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "creditar-item-estoque-loja", nickname = "Creditar item em estoque da Loja.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(value = "/invoices", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody InvoiceNewDTO objDto){
        Invoice invoice = invoiceService.fromDTO(objDto);
        service.creditItem(invoice);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(invoice.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "debitar-item-estoque-loja", nickname = "Debitar item de Estoque da Loja.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(value = "/invoices", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> remove(@Valid @RequestBody InvoiceNewDTO objDto){
        Invoice invoice = invoiceService.fromDTO(objDto);
        service.debitItem(invoice);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(invoice.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
