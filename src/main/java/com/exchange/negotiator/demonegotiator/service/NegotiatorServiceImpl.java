package com.exchange.negotiator.demonegotiator.service;

import com.exchange.negotiator.demonegotiator.model.Stock;
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
    public String getStock(String stockId) {
        logger.info("Inside NegotiatorServiceImpl getStock method");
        try {
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
            StringBuilder stockDetails = new StringBuilder("");
            while ((output = br.readLine()) != null) {
                stockDetails.append(output);
            }
            conn.disconnect();
            logger.info("Exiting NegotiatorServiceImpl getStock method");
            return stockDetails.toString();
        } catch (MalformedURLException e) {
            logger.error("Exiting NegotiatorServiceImpl getStock method URL error " + e);
        } catch (IOException e) {
            logger.error("Exiting NegotiatorServiceImpl getStock method IO error " + e);
        }
        return null;
    }

    @Override
    public String createStock(Stock stock) {
        logger.info("Inside NegotiatorServiceImpl createStock method");
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            String userCredentials = "username:password";
            String basicAuth = "Basic " + new String(Base64.encodeBase64(userCredentials.getBytes()));
            conn.setRequestProperty ("Authorization", basicAuth);
            conn.setRequestMethod("POST");
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
            StringBuilder status = new StringBuilder("");
            while ((output = br.readLine()) != null) {
                status.append(output);
            }
            conn.disconnect();
            logger.info("Exiting NegotiatorServiceImpl createStock method");
            return status.toString();
        } catch (MalformedURLException e) {
            logger.error("Exiting NegotiatorServiceImpl createStock method URL error " + e);
        } catch (IOException e) {
            logger.error("Exiting NegotiatorServiceImpl createStock method IO error " + e);
        }
        return null;
    }

    @Override
    public String updateStock(Stock stock) {
        logger.info("Exiting NegotiatorServiceImpl updateStock method");
        try {
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
            StringBuilder stockDetails = new StringBuilder("");
            while ((output = br.readLine()) != null) {
                stockDetails.append(output);
            }
            conn.disconnect();
            logger.info("Exiting NegotiatorServiceImpl updateStock method");
            return  stockDetails.toString();
        } catch (MalformedURLException e) {
            logger.error("Exiting NegotiatorServiceImpl updateStock method URL error " + e);
        } catch (IOException e) {
            logger.error("Exiting NegotiatorServiceImpl updateStock method IO error " + e);
        }
        return null;
    }
}
