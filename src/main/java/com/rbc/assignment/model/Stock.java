package com.rbc.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quarter;
    private String stockLabel;
    private String date;
    private String open;
    private String high;
    private String low;
    private String close;
    private long volume;

    public Stock(int quarter, String stockLabel, String date, String open, String high, String low, String close, long volume) {
        this.quarter = quarter;
        this.stockLabel = stockLabel;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }
}
