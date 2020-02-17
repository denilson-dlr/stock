package br.com.deni.stock.core.domain.dto;


import br.com.deni.stock.core.domain.Item;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "Objeto de nova Nota Fiscal com Itens",subTypes = {InvoiceNewDTO.class})
public class InvoiceNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(dataType = "Inteiro", notes = "Campo de destino da nota fiscal.", example = "1222", position = 1)
    private Integer stockCode;

    private List<Item> items;

    public InvoiceNewDTO(){

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
}
