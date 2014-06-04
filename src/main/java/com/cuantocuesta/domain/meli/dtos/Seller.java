
package com.cuantocuesta.domain.meli.dtos;

import java.util.HashMap;
import java.util.Map;

public class Seller {

    private Integer id;
    private Object power_seller_status;
    private Boolean car_dealer;
    private Boolean real_estate_agency;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getPower_seller_status() {
        return power_seller_status;
    }

    public void setPower_seller_status(Object power_seller_status) {
        this.power_seller_status = power_seller_status;
    }

    public Boolean getCar_dealer() {
        return car_dealer;
    }

    public void setCar_dealer(Boolean car_dealer) {
        this.car_dealer = car_dealer;
    }

    public Boolean getReal_estate_agency() {
        return real_estate_agency;
    }

    public void setReal_estate_agency(Boolean real_estate_agency) {
        this.real_estate_agency = real_estate_agency;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
