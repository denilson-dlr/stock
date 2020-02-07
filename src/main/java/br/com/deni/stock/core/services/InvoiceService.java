package br.com.deni.stock.core.services;

import br.com.deni.stock.core.domain.Invoice;
import br.com.deni.stock.core.repositories.InvoiceRepository;
import br.com.deni.stock.core.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public Invoice insert(Invoice invoice){
        invoice = repository.save(invoice);
        return invoice;

    }

}
