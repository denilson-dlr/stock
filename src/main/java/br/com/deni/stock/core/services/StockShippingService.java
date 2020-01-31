package br.com.deni.stock.core.services;

import br.com.deni.stock.core.domain.StockShipping;
import br.com.deni.stock.core.repositories.StockShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockShippingService {

    @Autowired
    private StockShippingRepository  repository;

    public StockShipping  insert(StockShipping  shipping){
        shipping  =  repository.save(shipping);
        return shipping;
    }
}
