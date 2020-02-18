package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {

    /*
    @Transactional(readOnly=true)
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
     new br.com.deni.stock.core.domain.dto.ProductDTO(obj.sku, obj.name)
     */

    @Transactional(readOnly=true)
    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN ProductStock ps ON obj.sku = ps.id.product WHERE obj.sku = :sku")
    Page<Product> findProductInStocksById(@Param("sku") Integer sku, Pageable pageRequest);

}
