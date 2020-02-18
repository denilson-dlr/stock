package br.com.deni.stock.resources;

import br.com.deni.stock.core.domain.Product;
import br.com.deni.stock.core.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Page<Product>> findPage(
            @RequestParam(value="sku", defaultValue="") Integer sku,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction) {
        Page<Product> list = service.search(sku, page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }
}
