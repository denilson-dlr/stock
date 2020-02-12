package br.com.deni.stock.core.domain.dto;


import br.com.deni.stock.core.domain.Item;

import java.io.Serializable;
import java.util.List;


public class InvoiceNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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
