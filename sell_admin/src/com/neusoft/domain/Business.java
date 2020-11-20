package com.neusoft.domain;

public class Business {
    private Integer businessId;
    private String password;
    private String businessName;
    private String businessAddress;
    private String businessExplain;
    private Double startPrice;
    private Double deliveryPrice;

    public Business() {
    }

    public Business(Integer businessId, String password, String businessName, String businessAddress, String businessExplain, double starPrice, double deliveryPrice) {
        this.businessId = businessId;
        this.password = password;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessExplain = businessExplain;
        this.startPrice = starPrice;
        this.deliveryPrice = deliveryPrice;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessExplain() {
        return businessExplain;
    }

    public void setBusinessExplain(String businessExplain) {
        this.businessExplain = businessExplain;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    @Override
    public String toString() {
        return  "\n商家编号："+this.businessId+
                "\n商家名称："+this.businessName+
                "\n商家地址："+this.businessAddress+
                "\n商家介绍："+this.businessExplain+
                "\n起送费："+this.startPrice +
                "\n配送费："+this.deliveryPrice;
    }
}
