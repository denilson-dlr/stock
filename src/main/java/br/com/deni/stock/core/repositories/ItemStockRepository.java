package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemStockRepository extends JpaRepository<ItemStock, Integer> {

}
