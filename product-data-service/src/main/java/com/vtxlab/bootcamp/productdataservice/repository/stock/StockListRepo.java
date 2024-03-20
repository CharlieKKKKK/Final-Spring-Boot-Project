package com.vtxlab.bootcamp.productdataservice.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vtxlab.bootcamp.productdataservice.entity.StockList;

public interface StockListRepo extends JpaRepository<StockList, Long> {

}
