package br.com.deni.stock.core.services;

import br.com.deni.stock.core.domain.Item;
import br.com.deni.stock.core.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Transactional
    public List<Item> insertAll(List<Item> list ){
        list = repository.saveAll(list);
        return list;
    }
}
