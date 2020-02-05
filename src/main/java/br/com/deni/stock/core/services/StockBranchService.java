package br.com.deni.stock.core.services;


import br.com.deni.stock.core.domain.*;
import br.com.deni.stock.core.repositories.ItemStockRepository;
import br.com.deni.stock.core.repositories.StockBranchRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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


    public StockBranch insert(StockBranch branch){
        branch = repository.save(branch);
        return branch;
    }

    @Transactional
    public void creditItem(Invoice invoice, Integer stockCode){
        StockBranch stockBranch = find(stockCode);
        for(Item item : invoice.getItems()){
            ItemStockPK itemStockPK = new ItemStockPK();
            itemStockPK.setStock(stockBranch);
            itemStockPK.setItem(item);
            ItemStock itemStock = itemStockRepository.findById(itemStockPK);
            if (Objects.isNull(itemStock)){
                itemStock = new ItemStock();
                itemStock.setStock(stockBranch);
                itemStock.setItem(item);
                itemStock.setQuantity(item.getQuantity());
                stockBranch.getItems().add(itemStock);
                item.getItems().add(itemStock);
                itemStockRepository.save(itemStock);
            } else {
                    ItemStock newItemStock = itemStock;
                    newItemStock.setQuantity(item.getQuantity()+itemStock.getQuantity());
                    itemStockRepository.save(newItemStock);
            }
        }
    }

    public void updateData(StockBranch newStockBranch, Item item, Item newItem){
        newStockBranch.setQuantity(newStockBranch.getQuantity() + item.getQuantity());
        newItem.setQuantity(item.getQuantity()+newItem.getQuantity());
    }
}
