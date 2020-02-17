package br.com.deni.stock.core.domain;

import br.com.deni.stock.core.domain.dto.InvoiceNewDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "BRANCH")
@ApiModel(value = "Objeto de Estoque de Loja",subTypes = {StockBranch.class})
public class StockBranch extends Stock{
    public static final long serialVersionUID = 1L;

    @ApiModelProperty(dataType = "Inteiro", notes = "Código da Loja.", example = "10", position = 4)
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
