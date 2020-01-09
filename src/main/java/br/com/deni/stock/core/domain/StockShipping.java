package br.com.deni.stock.core.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SHIPPING")
public class StockShipping extends Stock{
    public static final long serialVersionUID = 1L;

    @ApiModelProperty(dataType = "Inteiro", notes = "Código do remetente dos produtos (Filial ou CD).", example = "1714", position = 4)
    private Integer sender;
    @ApiModelProperty(dataType = "Inteiro", notes = "Código do destinatário dos produtos (Filial ou CD).", example = "1744", position = 5)
    private Integer receiver;

    public StockShipping(){

    }

    public StockShipping(Integer id, Integer quantity, String type, Integer sender, Integer receiver){
        super(id, quantity, type);
        this.sender = sender;
        this.receiver = receiver;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
