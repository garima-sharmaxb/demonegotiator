package com.exchange.negotiator.demonegotiator.service;

import com.exchange.negotiator.demonegotiator.model.Stock;

public interface NegotiatorService {

    String getStock(String stockId);

    String createStock(Stock stock);

    String updateStock(Stock stock);
}
