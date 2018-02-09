package com.exchange.negotiator.demonegotiator.model;

public class Stock {

    /**
     * stockId
     */
    private String stockId;

    /**
     * price
     */
    private String price;

    /**
     * companyName
     */
    private String companyName;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    }
