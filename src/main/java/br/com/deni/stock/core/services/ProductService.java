package br.com.deni.stock.core.services;

import br.com.deni.stock.core.domain.Product;
import br.com.deni.stock.core.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product find(Integer id){
        Optional<Product> product = repository.findById(id);
        return product.orElse(null);
    }

    public Product insert(Product product){
        product = repository.save(product);
        return product;
    }

    public Product update(Product product){
        Product newProduct = find(product.getSku());
        updateData(newProduct, product);
        return repository.save(newProduct);
    }

    public void updateData(Product product, Product newProduct){
        newProduct.setName(product.getName());
        newProduct.setSku(product.getSku());
    }


    public Page<Product> search(Integer sku, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Product product = find(sku);
        return repository.findProductInStocksById(product.getSku(), pageRequest);
    }
}
