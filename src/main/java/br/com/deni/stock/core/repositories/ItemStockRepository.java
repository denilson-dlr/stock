package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.ItemStock;
import br.com.deni.stock.core.domain.ItemStockPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemStockRepository extends JpaRepository<ItemStock, Integer> {

    //@org.springframework.transaction.annotation.Transactional(readOnly = true)
    //ItemStock findByItemStock();

    //@org.springframework.transaction.annotation.Transactional(readOnly = true)
    //ItemStock findAllByItemStock(ItemStockPK itemStockPK);

    @Transactional(readOnly=true)
    ItemStock findById(ItemStockPK itemStockPK);
}
