package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.Item;
import br.com.deni.stock.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {
}
