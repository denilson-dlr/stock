package br.com.deni.stock.core.repositories;

import br.com.deni.stock.core.domain.Item;
import br.com.deni.stock.core.domain.Product;
import br.com.deni.stock.core.domain.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {

    /*
    @Transactional(readOnly=true)
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
     */

    /*
    @Transactional(readOnly=true)
    @Query("SELECT new br.com.deni.stock.core.domain.dto.ProductDTO(obj.sku, obj.name) FROM Product obj INNER JOIN ProductStock ps ON obj.sku = ps.id.product WHERE obj.sku = :sku")
    Page<ProductDTO> findProductInStocksById(@Param("sku") Integer sku, Pageable pageRequest);
    */

    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN ProductStock ps ON obj.sku = ps.id.product WHERE obj.sku = :sku")
    Page<Product> findProductTestById(@Param("sku") Integer sku, Pageable pageRequest);


    @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN ProductStock ps ON obj.sku = ps.id.product  WHERE obj.sku = :sku")
    List<Product> findProductTest();

}
