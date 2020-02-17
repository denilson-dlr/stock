package br.com.deni.stock.core.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(dataType = "Inteiro", notes = "Campo de destino da nota fiscal.", example = "1222", position = 1)
    private Integer sku;
    @ApiModelProperty(dataType = "String", notes = "Nome do Produto.", example = "Dipirona Sódica", position = 2)
    private String name;

    public ProductDTO(){

    }

    public ProductDTO(Integer sku, String name){
        super();
        this.sku = sku;
        this.name = name;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
