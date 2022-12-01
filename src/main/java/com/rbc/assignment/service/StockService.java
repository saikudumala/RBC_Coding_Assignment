package com.rbc.assignment.service;

import com.rbc.assignment.model.Stock;
import com.rbc.assignment.repository.StocksRepo;
import com.rbc.assignment.util.CSVUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
/*
Class Name: StockService
Description: Service class for handling the business logic related to stocks
 */
@Service
@Log4j2
public class StockService {

    @Autowired
    StocksRepo repository;

    public void save(MultipartFile file) {
        try {
            List<Stock> stocks = CSVUtil.parseCsvToStockRecords(file.getInputStream());
            repository.saveAll(stocks);
            log.debug("stocks have been successfully persisted to the DB");
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public List<Stock> getAllStocks() {
        return repository.findAll();
    }

    public void save(Stock stock) {

        repository.save(stock);
    }


    public List<Stock> getStocksByLabel(String stockSticker) {

        return repository.findAll().stream()
                .filter(s -> s.getStockLabel().contentEquals(stockSticker))
                .collect(Collectors.toList());
    }


}

