package br.com.deni.stock.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Item implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(dataType = "Inteiro", notes = "Campo de identificação do Produto (SKU).", example = "1111", position = 1)
    private Integer id;
    @ApiModelProperty(dataType = "Inteiro", notes = "Campo de identificação do Produto (SKU).", example = "1111", position = 1)
    private Integer sku;
    @ApiModelProperty(dataType = "String", notes = "Nome do Produto.", example = "Dipirona Sódica", position = 2)
    private String name;
    @ApiModelProperty(dataType = "Inteiro", notes = "Quantidade de itens do produto.", example = "10", position = 3)
    private Integer quantity;

    @OneToMany(mappedBy="id.item")
    private Set<ItemStock> items = new HashSet<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="invoice_id")
    private Invoice invoice;

    public Item(){

    }

    public Item(Integer id, Integer sku, String name, Integer quantity, Invoice invoice){
        super();
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.quantity = quantity;
        this.invoice = invoice;
    }

    @JsonIgnore
    public List<Stock> getPedidos() {
        List<Stock> list = new ArrayList<>();
        for (ItemStock x : items) {
            list.add(x.getStock());
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Set<ItemStock> getItems() {
        return items;
    }

    public void setItems(Set<ItemStock> items) {
        this.items = items;
    }

    @Override
    public int hashCode(){
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
        Item other = (Item) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
