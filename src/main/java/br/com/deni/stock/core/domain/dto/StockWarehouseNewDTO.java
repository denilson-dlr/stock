package br.com.deni.stock.core.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "Objeto de Novo Centro de Distribui��o",subTypes = {StockWarehouseNewDTO.class})
public class StockWarehouseNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(dataType = "Inteiro", notes = "C�digo do Centro de Distribui��o.", example = "2111", position = 1)
    private Integer warehouseCode;
    @ApiModelProperty(dataType = "Inteiro", notes = "Quantidade de produtos que est�o no estoque do CD (estoque inicial).", example = "100", position = 2)
    private Integer quantity;

    public StockWarehouseNewDTO(){

    }

    public Integer getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(Integer warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
