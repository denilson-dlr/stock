package br.com.deni.stock.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(dataType = "Inteiro", notes = "Campo de identificação do Produto (SKU).", example = "1111", position = 1)
    private Integer sku;
    @ApiModelProperty(dataType = "String", notes = "Nome do Produto.", example = "Dipirona Sódica", position = 2)
    private String name;

    @OneToMany(mappedBy="id.product")
    private Set<ProductStock> products = new HashSet<>();


    public Product(){

    }

    public Product( Integer sku, String name) {
        super();
        this.sku = sku;
        this.name = name;
    }

    @JsonIgnore
    public List<Stock> getStocks() {
        List<Stock> list = new ArrayList<>();
        for (ProductStock x : products) {
            list.add(x.getStock());
        }
        return list;
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

    public Set<ProductStock> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductStock> products) {
        this.products = products;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
        Product other = (Product) obj;
        if (sku == null) {
            if (other.sku != null)
                return false;
        } else if (!sku.equals(other.sku))
            return false;
        return true;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
