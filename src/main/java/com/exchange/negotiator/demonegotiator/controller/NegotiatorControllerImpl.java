package com.exchange.negotiator.demonegotiator.controller;

import com.exchange.negotiator.demonegotiator.model.Stock;
import com.exchange.negotiator.demonegotiator.service.NegotiatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class NegotiatorControllerImpl implements NegotiatorController {

    public static final Logger logger = LoggerFactory.getLogger(NegotiatorControllerImpl.class);

    @Autowired
    private NegotiatorService negotiatorService;



    @Override
    public String getStock(@PathVariable("id") String stockId) {
        logger.info("Inside NegotiatorControllerImpl getStock method");
        return negotiatorService.getStock(stockId);

    }

    @Override
    public String createStock(@RequestBody Stock stock) {
        logger.info("Inside NegotiatorControllerImpl createStock method");
        return negotiatorService.createStock(stock);
    }

    @Override
    public String updateStock(@RequestBody Stock stock) {
        logger.info("Inside NegotiatorControllerImpl updateStock method");
        return negotiatorService.updateStock(stock);
    }
}
