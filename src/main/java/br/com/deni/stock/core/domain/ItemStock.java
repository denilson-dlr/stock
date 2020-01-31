package br.com.deni.stock.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;


@Entity
public class ItemStock implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private ItemStockPK id = new ItemStockPK();

    private Integer quantity;

    public ItemStock(){

    }

    public ItemStock(Item item, Stock stock, Integer quantity) {
        super();
        id.setItem(item);
        id.setStock(stock);
        this.id = id;
        this.quantity = quantity;
    }


    @JsonIgnore
    public Item getItem(){
        return id.getItem();
    }

    public void setItem(Item item){
        id.setItem(item);
    }

    public Stock getStock(){
        return id.getStock();
    }

    public void setStock(Stock stock){
        id.setStock(stock);
    }

    public ItemStockPK getId() {
        return id;
    }

    public void setId(ItemStockPK id) {
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
        ItemStock other = (ItemStock) obj;
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
        builder.append(getItem().getName());
        builder.append(", Quantidade: ");
        builder.append(getQuantity());
        builder.append(", Preço unitário: ");
        builder.append("\n");
        return builder.toString();
    }
}
