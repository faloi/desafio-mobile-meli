
package com.cuantocuesta.domain.meli.dtos;

import java.util.HashMap;
import java.util.Map;

public class Shipping {

    private Boolean free_shipping;
    private String mode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getFree_shipping() {
        return free_shipping;
    }

    public void setFree_shipping(Boolean free_shipping) {
        this.free_shipping = free_shipping;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
