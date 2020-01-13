package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.Stock;

import javax.transaction.Transactional;

@Transactional
public interface StockBranchRepository extends StockBaseRepository<Stock> {
}
