package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.Item;
import br.com.deni.stock.core.domain.Stock;
import br.com.deni.stock.core.domain.StockBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface StockBaseRepository<T extends Stock>
    extends JpaRepository<Stock, Integer> {

}
