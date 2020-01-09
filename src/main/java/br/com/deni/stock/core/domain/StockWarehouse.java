package br.com.deni.stock.core.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "WAREHOUSE")
public class StockWarehouse extends Stock {
    public static final long serialVersionUID = 1L;

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
