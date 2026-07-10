package com.herocycles.pricing.dto;

import java.util.ArrayList;
import java.util.List;

public class PriceBreakdownResponse {

    private String cycleName;

    private List<String> components = new ArrayList<>();

    private double totalPrice;

    public String getCycleName() {
        return cycleName;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}