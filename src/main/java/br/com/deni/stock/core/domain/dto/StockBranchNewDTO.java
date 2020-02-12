package br.com.deni.stock.core.domain.dto;

import java.io.Serializable;

public class StockBranchNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer branchCode;

    private Integer quantity;

    public StockBranchNewDTO(){

    }

    public Integer getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Integer branchCode) {
        this.branchCode = branchCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
