package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.Stock;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StockWarehouseRepository extends StockBaseRepository<Stock> {
}
