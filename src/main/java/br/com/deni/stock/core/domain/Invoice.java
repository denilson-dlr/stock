package br.com.deni.stock.core.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@ApiModel(value = "Objeto de Nota Fiscal",subTypes = {Invoice.class})
public class Invoice implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(dataType = "Inteiro", notes = "Campo de identificação da Nota Fiscal.", example = "1111", position = 1)
    private Integer id;
    @ApiModelProperty(dataType = "Inteiro", notes = "Campo de destino da nota fiscal.", example = "1222", position = 2)
    private Integer stockCode;

    @OneToMany(mappedBy="invoice",cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    public Invoice(){

    }

    public Invoice(Integer id, Integer stockCode){
        super();
        this.id = id;
        this.stockCode = stockCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStockCode() {
        return stockCode;
    }

    public void setStockCode(Integer stockCode) {
        this.stockCode = stockCode;
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
        Invoice other = (Invoice) obj;
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
