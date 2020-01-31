package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.ItemStock;
import br.com.deni.stock.core.domain.Stock;
import br.com.deni.stock.core.domain.StockBranch;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface StockBranchRepository extends StockBaseRepository<Stock> {

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    StockBranch findByBranchCode(Integer branchCode);

    //@org.springframework.transaction.annotation.Transactional
    //List<ItemStock> findByItemSKU(Integer sku);
}
