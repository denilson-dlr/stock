package br.com.deni.stock.core.domain.dto;

import br.com.deni.stock.core.domain.StockBranch;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "Objeto de Nova Loja",subTypes = {StockBranchNewDTO.class})
public class StockBranchNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(dataType = "Inteiro", notes = "Código da Loja.", example = "1222", position = 1)
    private Integer branchCode;
    @ApiModelProperty(dataType = "Inteiro", notes = "Quantidade de produtos que estão no estoque da loja (estoque inicial).", example = "100", position = 2)
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
