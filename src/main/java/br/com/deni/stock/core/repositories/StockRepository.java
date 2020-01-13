package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StockRepository extends StockBaseRepository<Stock> {
}
