package com.rbc.assignment.repository;

import com.rbc.assignment.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/*
Interface Name: StocksRepo
Description: Repository interface for managing DB related operations of the POJO stock class
 */
@Repository
public interface StocksRepo extends JpaRepository<Stock, Long> {
}
