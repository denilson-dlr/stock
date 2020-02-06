package br.com.deni.stock.core.services;

import br.com.deni.stock.core.domain.Product;
import br.com.deni.stock.core.domain.StockBranch;
import br.com.deni.stock.core.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
