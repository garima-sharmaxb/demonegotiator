package com.exchange.negotiator.demonegotiator.service;

import com.exchange.negotiator.demonegotiator.model.Stock;
import com.exchange.negotiator.demonegotiator.model.StockResponse;

public interface NegotiatorService {

    Stock getStock(String stockId);

    StockResponse createStock(Stock stock);

    Stock updateStock(Stock stock);
}
