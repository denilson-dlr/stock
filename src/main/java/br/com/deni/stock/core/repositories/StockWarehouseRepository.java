package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.Stock;
import br.com.deni.stock.core.domain.StockWarehouse;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StockWarehouseRepository extends StockBaseRepository<Stock> {

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    StockWarehouse findByWarehouseCode(Integer warehouseCode);

}
