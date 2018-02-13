package com.exchange.negotiator.demonegotiator.model;

import lombok.Data;

@Data
@lombok.Getter
@lombok.Setter
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

    }
