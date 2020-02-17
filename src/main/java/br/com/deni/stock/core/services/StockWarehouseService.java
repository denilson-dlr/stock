package br.com.deni.stock.core.services;


import br.com.deni.stock.core.domain.Invoice;
import br.com.deni.stock.core.domain.Item;
import br.com.deni.stock.core.domain.Product;
import br.com.deni.stock.core.domain.ProductStock;
import br.com.deni.stock.core.domain.ProductStockPK;
import br.com.deni.stock.core.domain.StockWarehouse;
import br.com.deni.stock.core.domain.dto.StockWarehouseNewDTO;
import br.com.deni.stock.core.repositories.ProductStockRepository;
import br.com.deni.stock.core.repositories.StockWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class StockWarehouseService {

    @Autowired
    private StockWarehouseRepository repository;

    @Autowired
    private ProductStockRepository productStockRepository;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemService itemService;


    public StockWarehouse find(Integer id){
        StockWarehouse stockWarehouse = repository.findByWarehouseCode(id);
        return stockWarehouse;
    }


    public StockWarehouse insert(StockWarehouse warehouse){
        warehouse = repository.save(warehouse);
        return warehouse;
    }

    @Transactional
    public void creditItem(Invoice invoice){
        itemService.insertAll(invoice.getItems());
        invoiceService.insert(invoice);
        StockWarehouse stockWarehouse = find(invoice.getStockCode());
        for(Item item : invoice.getItems()){
            Product product = productService.find(item.getSku());
            if (Objects.isNull(product)){
                Product newProduct = addProduct(item);
                addProductStock(stockWarehouse, newProduct, item);
                creditStockQuantity(stockWarehouse, item);
            } else {
                ProductStock productStock = findPK(stockWarehouse, product);
                ProductStock newProductStock = productStock;
                creditProductQuantity(item, newProductStock, productStock);
                creditStockQuantity(stockWarehouse, item);
                productStockRepository.save(newProductStock);
            }

        }
    }

    public ProductStock findPK(StockWarehouse stockWarehouse, Product newProduct){
        ProductStockPK productStockPK = new ProductStockPK();
        productStockPK.setStock(stockWarehouse);
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
            StockWarehouse stockWarehouse,
            Product product,
            Item item
    ){
        ProductStock newProductStock = new ProductStock();
        newProductStock.setStock(stockWarehouse);
        newProductStock.setProduct(product);
        newProductStock.setQuantity(item.getQuantity());
        stockWarehouse.getProducts().add(newProductStock);
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

    public void creditStockQuantity(StockWarehouse stockWarehouse, Item item){
        StockWarehouse newStockWarehouse = stockWarehouse;
        newStockWarehouse.setQuantity(stockWarehouse.getQuantity()+item.getQuantity());
        repository.save(newStockWarehouse);
    }

    public StockWarehouse fromDTO(StockWarehouseNewDTO objDto) {
        StockWarehouse stockWarehouse = new StockWarehouse(null, objDto.getQuantity(),"WAREHOUSE",objDto.getWarehouseCode());
        return stockWarehouse;
    }
}
