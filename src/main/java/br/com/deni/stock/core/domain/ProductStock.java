package br.com.deni.stock.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;


@Entity
public class ProductStock implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ProductStockPK id = new ProductStockPK();

    private Integer quantity;

    public ProductStock(){

    }

    public ProductStock(Product product, Stock stock, Integer quantity) {
        super();
        id.setProduct(product);
        id.setStock(stock);
        this.quantity = quantity;
    }


    @JsonIgnore
    public Product getProduct(){
        return id.getProduct();
    }

    public void setProduct(Product product){
        id.setProduct(product);
    }

    public Stock getStock(){
        return id.getStock();
    }

    public void setStock(Stock stock){
        id.setStock(stock);
    }

    public ProductStockPK getId() {
        return id;
    }

    public void setId(ProductStockPK id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductStock other = (ProductStock) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getProduct().getName());
        builder.append("\n");
        return builder.toString();
    }
}
