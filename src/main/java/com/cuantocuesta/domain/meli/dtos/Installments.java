
package com.cuantocuesta.domain.meli.dtos;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

public class Installments {

    private Integer quantity;
    private Double amount;
    private String currency_id;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
