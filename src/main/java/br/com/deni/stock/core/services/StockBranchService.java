package br.com.deni.stock.core.services;


import br.com.deni.stock.core.domain.*;
import br.com.deni.stock.core.repositories.ProductStockRepository;
import br.com.deni.stock.core.repositories.StockBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class StockBranchService {

    @Autowired
    private StockBranchRepository repository;

    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;


    public StockBranch find(Integer id){
        StockBranch stockBranch = repository.findByBranchCode(id);
        return stockBranch;
    }


    public StockBranch insert(StockBranch branch){
        branch = repository.save(branch);
        return branch;
    }

    @Transactional
    public void creditItem(Invoice invoice, Integer stockCode){
        invoiceService.insert(invoice);
        StockBranch stockBranch = find(stockCode);
        for(Item item : invoice.getItems()){
            Product product = productService.find(item.getSku());
            if (Objects.isNull(product)){
                Product newProduct = addProduct(item);
                ProductStock productStock = findPK(stockBranch, newProduct);
                if (Objects.isNull(productStock)){
                    addProductStock(stockBranch, newProduct, item);
                    creditStockQuantity(stockBranch, item);
                } else {
                    ProductStock newProductStock = productStock;
                    creditProductQuantity(item, newProductStock, productStock);
                    creditStockQuantity(stockBranch, item);
                    productStockRepository.save(newProductStock);
                }
            } else {

            }

        }
    }

    public ProductStock findPK(StockBranch stockBranch, Product newProduct){
        ProductStockPK productStockPK = new ProductStockPK();
        productStockPK.setStock(stockBranch);
        productStockPK.setProduct(newProduct);
        return productStockRepository.findById(productStockPK);
    }

    public Product addProduct(Item item){
        Product newProduct = new Product();
        newProduct.setSku(item.getSku());
        newProduct.setName(item.getName());
        return productService.insert(newProduct);
    }

    public void addProductStock(
                                StockBranch stockBranch,
                                Product product,
                                Item item
                              ){
        ProductStock newProductStock = new ProductStock();
        newProductStock.setStock(stockBranch);
        newProductStock.setProduct(product);
        newProductStock.setQuantity(item.getQuantity());
        stockBranch.getProducts().add(newProductStock);
        product.getProducts().add(newProductStock);
        productStockRepository.save(newProductStock);
    }

    public void creditProductQuantity(
                            Item item,
                            ProductStock newProductStock,
                            ProductStock productStock
                            ){
        newProductStock.setQuantity(item.getQuantity()+ productStock.getQuantity());
    }

    public void creditStockQuantity(StockBranch stockBranch, Item item){
        StockBranch newStockBranch = stockBranch;
        newStockBranch.setQuantity(stockBranch.getQuantity()+item.getQuantity());
        repository.save(newStockBranch);
    }
}
