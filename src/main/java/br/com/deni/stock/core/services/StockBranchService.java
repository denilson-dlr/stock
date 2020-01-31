package br.com.deni.stock.core.services;


import br.com.deni.stock.core.domain.Invoice;
import br.com.deni.stock.core.domain.Item;
import br.com.deni.stock.core.domain.ItemStock;
import br.com.deni.stock.core.domain.StockBranch;
import br.com.deni.stock.core.repositories.ItemRepository;
import br.com.deni.stock.core.repositories.ItemStockRepository;
import br.com.deni.stock.core.repositories.StockBranchRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StockBranchService {

    @Autowired
    private StockBranchRepository repository;

    @Autowired
    private ItemStockRepository itemStockRepository;


    public StockBranch find(Integer id){
        StockBranch stockBranch = repository.findByBranchCode(id);
        return stockBranch;
    }

    public StockBranch findByItem(Integer sku){
        StockBranch stockBranch = repository.findByBranchCode(sku);
        return stockBranch;
    }


    public StockBranch insert(StockBranch branch){
        branch = repository.save(branch);
        return branch;
    }

    @Transactional
    public void creditItem(Invoice invoice){

        for(Item item : invoice.getItems()){
            StockBranch stockBranch = find(item.getSku());
            for (ItemStock itemStock : stockBranch.getItems()) {
                if (itemStock.getItem().getSku().equals(item.getSku())) {

                } else {
                    itemStock.setStock(stockBranch);
                    itemStock.setItem(item);
                }
            }
            itemStockRepository.saveAll(stockBranch.getItems());
        }
    }

    public void updateData(StockBranch newStockBranch, Item item, Item newItem){
        newStockBranch.setQuantity(newStockBranch.getQuantity() + item.getQuantity());
        newItem.setQuantity(item.getQuantity()+newItem.getQuantity());
    }
}
