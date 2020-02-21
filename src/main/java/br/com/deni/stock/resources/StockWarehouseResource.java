package br.com.deni.stock.resources;

import br.com.deni.stock.core.domain.Invoice;
import br.com.deni.stock.core.domain.StockWarehouse;
import br.com.deni.stock.core.domain.dto.InvoiceNewDTO;
import br.com.deni.stock.core.domain.dto.StockWarehouseNewDTO;
import br.com.deni.stock.core.services.InvoiceService;
import br.com.deni.stock.core.services.StockWarehouseService;
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
@RequestMapping(value = "/warehouses")
@Api(value = "Estoque do Centro Distribuidor",description = "Estoque do Centro Distribuidor Endpoint", tags = "Estoque Centro Distribuidor")
public class StockWarehouseResource {

    @Autowired
    private StockWarehouseService service;

    @Autowired
    private InvoiceService invoiceService;

    @ApiOperation(value = "consultar-estoque-centro-distribuidor", nickname = "Consultar os Centros Distribuidores cadastrados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockWarehouse> find(@PathVariable Integer id) {
        StockWarehouse obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "inserir-centro-distribuidor", nickname = "Inserir novo Centro Distribuidor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insert(@Valid @RequestBody StockWarehouseNewDTO objDto){
        StockWarehouse stockWarehouse = service.fromDTO(objDto);
        stockWarehouse = service.insert(stockWarehouse);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stockWarehouse.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "creditar-item-estoque-centro-distribuicao", nickname = "Creditar item em estoque do CD.")
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

    @ApiOperation(value = "debitar-item-estoque-centro-distribuidor", nickname = "Debitar item de Estoque do CD.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(value = "/invoices", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> remove(@Valid @RequestBody InvoiceNewDTO objDto){
        Invoice invoice = invoiceService.fromDTO(objDto);
        service.creditItem(invoice);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(invoice.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
