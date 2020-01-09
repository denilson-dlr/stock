package br.com.deni.stock;

import br.com.deni.stock.core.domain.*;
import br.com.deni.stock.domain.*;
import br.com.deni.stock.core.repositories.InvoiceRepository;
import br.com.rd.stockApi.domain.*;
import br.com.deni.stock.core.repositories.ItemRepository;
import br.com.deni.stock.core.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class StockApplication implements CommandLineRunner {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private StockRepository stockRepository;

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		Invoice invoice1 = new Invoice(null);
		Item item1 = new Item(null, "Dipirona",10, invoice1);
		Item item2 = new Item(null, "Novalgina", 5, invoice1);
		invoice1.getItems().addAll(Arrays.asList(item1,item2));

		Invoice invoice2 = new Invoice(null);
		Item item3 = new Item(null, "Amoxilina",8, invoice2);
		Item item4 = new Item(null, "Benegrip", 3, invoice2);
		Item item5 = new Item(null, "Água Crystal", 20, invoice2);
		invoice2.getItems().addAll(Arrays.asList(item3,item4,item5));

		Invoice invoice3 = new Invoice(null);
		Item item6 = new Item(null, "Powerade",200, invoice3);
		invoice3.getItems().addAll(Arrays.asList(item6));

		StockWarehouse stockWarehouse1 = new StockWarehouse(null,150,"WAREHOUSE", 1714);
		StockBranch stockBranch1 = new StockBranch(null,25,"BRANCH", 1222);
		StockShipping stockShipping1 = new StockShipping(null, 0, "SHIPPING", 1744, 1222);

		item1.getStocks().addAll(Arrays.asList(stockBranch1));
		item2.getStocks().addAll(Arrays.asList(stockBranch1));
		item3.getStocks().addAll(Arrays.asList(stockWarehouse1));
		item4.getStocks().addAll(Arrays.asList(stockWarehouse1));
		item5.getStocks().addAll(Arrays.asList(stockWarehouse1));
		item6.getStocks().addAll(Arrays.asList(stockShipping1));

		stockBranch1.getItems().addAll(Arrays.asList(item1,item2));
		stockShipping1.getItems().addAll(Arrays.asList(item6));
		stockWarehouse1.getItems().addAll(Arrays.asList(item3,item4,item5));

		invoiceRepository.saveAll(Arrays.asList(invoice1, invoice2));
		itemRepository.saveAll(Arrays.asList(item1,item2,item3,item4,item5));
		stockRepository.saveAll(Arrays.asList(stockBranch1,stockShipping1,stockWarehouse1));

	}
}