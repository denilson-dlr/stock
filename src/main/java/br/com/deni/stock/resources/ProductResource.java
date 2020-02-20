package br.com.deni.stock.resources;

import br.com.deni.stock.core.domain.Product;
import br.com.deni.stock.core.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    @ApiOperation(value = "consulta-paginada-produto", nickname = "Consultar SKU de produto em todos os estoques")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = ""),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Failure", response = Exception.class)})
    @RequestMapping(method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Product>> findPage(
            @RequestParam(value="sku", required = true, defaultValue="") Integer sku,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
        Page<Product> list = service.search(sku, page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
}
