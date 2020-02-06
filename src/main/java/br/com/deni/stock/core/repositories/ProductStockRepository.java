package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.ProductStock;
import br.com.deni.stock.core.domain.ProductStockPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Integer> {

    //@org.springframework.transaction.annotation.Transactional(readOnly = true)
    //ProductStock findByItemStock();

    //@org.springframework.transaction.annotation.Transactional(readOnly = true)
    //ProductStock findAllByItemStock(ProductStockPK itemStockPK);

    @Transactional(readOnly=true)
    ProductStock findById(ProductStockPK productStockPK);
}
