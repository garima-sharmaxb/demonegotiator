package com.exchange.negotiator.demonegotiator.controller;

import com.exchange.negotiator.demonegotiator.model.Stock;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface NegotiatorController {

    /**
     *  This method interacts with stock exchange server
     *  and fetch stock details based on stock id.
     *
     * @param stockId
     * @return Stock
     */
    @RequestMapping( value = "/stock/{id}" , method = RequestMethod.GET)
    String getStock(String stockId);

    /**
     * This method interacts with stock exchange server
     * to add new stocks in DB
     *
     * @return status
     */
    @RequestMapping (value = "/stock" , method = RequestMethod.POST)
    String createStock(@RequestBody Stock stock);

    /**
     * This method interacts with stock exchange server
     * to updates entry of stock.
     * .
     * @return Stock
     */
    @RequestMapping (value = "/stock" , method = RequestMethod.PUT)
    String updateStock(@RequestBody Stock stock);


}
