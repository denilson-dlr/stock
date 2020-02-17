package br.com.deni.stock.core.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "WAREHOUSE")
@ApiModel(value = "Objeto de Estoque Centro de Distribuição",subTypes = {StockWarehouse.class})
public class StockWarehouse extends Stock {
    public static final long serialVersionUID = 1L;

    @ApiModelProperty(dataType = "Inteiro", notes = "Código do Centro de Distribuição.", example = "10", position = 4)
    private Integer warehouseCode;

    public StockWarehouse(){

    }

    public StockWarehouse(Integer id, Integer quantity, String type, Integer warehouseCode){
        super(id, quantity, type);
        this.warehouseCode = warehouseCode;
    }

    public Integer getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(Integer warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
