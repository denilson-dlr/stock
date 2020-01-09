package br.com.deni.stock.core.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "BRANCH")
public class StockBranch extends Stock{
    public static final long serialVersionUID = 1L;

    @ApiModelProperty(dataType = "Inteiro", notes = "Quantidade de itens armazenados em estoque.", example = "10", position = 4)
    private Integer branchCode;

    public StockBranch(){

    }

    public StockBranch(Integer id, Integer quantity, String type, Integer branchCode){
        super(id, quantity, type);
        this.branchCode = branchCode;
    }

    public Integer getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Integer branchCode) {
        this.branchCode = branchCode;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
