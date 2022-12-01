package com.rbc.assignment.controller;

import com.rbc.assignment.model.Stock;
import com.rbc.assignment.service.StockService;
import com.rbc.assignment.util.CSVUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/*
Class Name: StockController
Description: Controller class providing REST API for all the end points related to Stocks
 */
@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/stocks")
public class StockController {
    private final Logger log = LoggerFactory.getLogger(StockController.class);
    @Autowired
    StockService stockService;


    //Endpoint method for uploading the file
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload bulk data set")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (file.isEmpty()) {
            return new ResponseEntity("You must select a file!", HttpStatus.OK);
        }
        if(CSVUtil.hasCSVFormat(file)){
            log.debug("Received file details: " + file.getOriginalFilename());
            try {
                stockService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body("\" message \": \" " + message + " \"");
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("\" message \": \" " + message + " \"");
            }
        }
        message = "Please Upload CSV file";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("\" message \": \" " + message + " \"");


    }

    //Endpoint method for retrieving the data based on stock label
    @GetMapping(value = "/label/{stockSticker}")
    @Operation(summary = "Find stock by label")
    public ResponseEntity<List<Stock>> getOrdersByStockSticker(@PathVariable String stockSticker) {
        try {
            log.debug("Stock sticker received as part of request: " + stockSticker);
            List<Stock> stocks = stockService.getStocksByLabel(stockSticker);
            log.debug("Number of stocks corresponding to stock sticker: " + stocks.size());
            if (stocks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(stocks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Endpoint method for adding new stocks to DB
    @PostMapping(value = "/add")
    @Operation(summary = "Add new stock")
    public ResponseEntity<String> addStocks(@RequestBody Stock stock) {
        try {

            stockService.save(stock);
            String message = "Stock has been added to the DB";
            log.debug(message);
            return ResponseEntity.status(HttpStatus.OK).body("\" message \": \" " + message + " \"");
        } catch (Exception e) {
            log.error("Unable to process the request due an exception ");
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
