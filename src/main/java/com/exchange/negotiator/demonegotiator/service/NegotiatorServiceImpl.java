package com.exchange.negotiator.demonegotiator.service;

import com.exchange.negotiator.demonegotiator.model.Stock;
import com.exchange.negotiator.demonegotiator.model.StockResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class NegotiatorServiceImpl implements NegotiatorService {

    public static final Logger logger = LoggerFactory.getLogger(NegotiatorServiceImpl.class);

    private String path = "http://localhost:8080/stock/";

    @Override
    public Stock getStock(String stockId) {
        logger.info("Inside NegotiatorServiceImpl getStock method");
        Stock stock = new Stock();
        try {
            ObjectMapper mapper = new ObjectMapper();
            StringBuilder baseUrl = new StringBuilder(path);
            URL url = new URL(baseUrl.append(stockId).toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String userCredentials = "username:password";
            String basicAuth = "Basic " + new String(Base64.encodeBase64(userCredentials.getBytes()));
            conn.setRequestProperty ("Authorization", basicAuth);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            //StringBuilder stockDetails = new StringBuilder("");
            while ((output = br.readLine()) != null) {
               // stockDetails.append(output);
                stock = mapper.readValue(output, Stock.class);
            }
            conn.disconnect();
            logger.info("Exiting NegotiatorServiceImpl getStock method");
            return stock;
        } catch (MalformedURLException e) {
            logger.error("Exiting NegotiatorServiceImpl getStock method URL error " + e);
        } catch (IOException e) {
            logger.error("Exiting NegotiatorServiceImpl getStock method IO error " + e);
        }
        return null;
    }

    @Override
    public StockResponse createStock(Stock stock) {
        logger.info("Inside NegotiatorServiceImpl createStock method");
        StockResponse stockResponse = new StockResponse();
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            String userCredentials = "username:password";
            String basicAuth = "Basic " + new String(Base64.encodeBase64(userCredentials.getBytes()));
            conn.setRequestProperty ("Authorization", basicAuth);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            /*String input = "{\"stockId\":\"" + stock.getStockId() + "\",\"price\":\""+ stock.getPrice() +"\",\"companyName\":\""+stock.getCompanyName()+"\"}\n" +
                    "\n";*/
            String input = mapper.writeValueAsString(stock);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());    }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
           // StringBuilder status = new StringBuilder("");
            while ((output = br.readLine()) != null) {
               // status.append(output);
                stockResponse = mapper.readValue(output, StockResponse.class);
            }
            conn.disconnect();
            logger.info("Exiting NegotiatorServiceImpl createStock method");
            return stockResponse;
        } catch (MalformedURLException e) {
            logger.error("Exiting NegotiatorServiceImpl createStock method URL error " + e);
        } catch (IOException e) {
            logger.error("Exiting NegotiatorServiceImpl createStock method IO error " + e);
            stockResponse.setStatusMessage(e.getMessage());
        }
        return stockResponse;
    }

    @Override
    public Stock updateStock(Stock stock) {
        logger.info("Exiting NegotiatorServiceImpl updateStock method");
        try {
            ObjectMapper mapper = new ObjectMapper();
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            String userCredentials = "username:password";
            String basicAuth = "Basic " + new String(Base64.encodeBase64(userCredentials.getBytes()));
            conn.setRequestProperty ("Authorization", basicAuth);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            String input = "{\"stockId\":\"" + stock.getStockId() + "\",\"price\":\""+ stock.getPrice() +"\",\"companyName\":\""+stock.getCompanyName()+"\"}\n" +
                    "\n";
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());    }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            //StringBuilder stockDetails = new StringBuilder("");
            while ((output = br.readLine()) != null) {
                //stockDetails.append(output);
                stock = mapper.readValue(output, Stock.class);
            }
            conn.disconnect();
            logger.info("Exiting NegotiatorServiceImpl updateStock method");
            return  stock;
        } catch (MalformedURLException e) {
            logger.error("Exiting NegotiatorServiceImpl updateStock method URL error " + e);
        } catch (IOException e) {
            logger.error("Exiting NegotiatorServiceImpl updateStock method IO error " + e);
        }
        return null;
    }
}
