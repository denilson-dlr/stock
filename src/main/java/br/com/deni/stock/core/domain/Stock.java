package br.com.deni.stock.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 10, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("STOCK")
@ApiModel(value = "Objeto de Estoque", subTypes = {Stock.class})
public class Stock implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(dataType = "Inteiro", notes = "Identificação de estoque. Campo nulo quando houver request de nova inserção de estoque.", example = "01", position = 1)
    private Integer id;
    @ApiModelProperty(dataType = "Inteiro", notes = "Quantidade de itens armazenados em estoque.", example = "10", position = 2)
    private Integer quantity;
    @ApiModelProperty(dataType = "String", notes = "Tipo do Estoque: BRANCH (Filial), WAREHOUSE (CD) e SHIPPING(Transporte).", example = "BRANCH", position = 3)
    @Column(insertable=false, updatable=false)
    private String type;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "STOCK_ITEM",
            joinColumns = @JoinColumn(name = "stock_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items = new ArrayList<>();

    public Stock(){

    }

    public Stock (Integer id, Integer quantity, String type){
        super();
        this.id = id;
        this.quantity = quantity;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
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
        Stock other = (Stock) obj;
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
