package com.vtxlab.bootcamp.productdataservice.repository.coin;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vtxlab.bootcamp.productdataservice.entity.CoinList;

public interface CoinListRepo extends JpaRepository<CoinList, Long>{
  
}
