package br.com.deni.stock.core.services;

import br.com.deni.stock.core.domain.StockWarehouse;
import br.com.deni.stock.core.repositories.StockWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockWarehouseService {

    @Autowired
    private StockWarehouseRepository  repository;

    public StockWarehouse insert(StockWarehouse warehouse){
        warehouse = repository.save(warehouse);
        return warehouse;
    }
}
