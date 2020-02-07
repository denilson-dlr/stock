package br.com.deni.stock;

import br.com.deni.stock.core.domain.*;
import br.com.deni.stock.core.repositories.*;
import br.com.deni.stock.core.services.InvoiceService;
import br.com.deni.stock.core.services.ItemService;
import br.com.deni.stock.core.services.StockBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.util.Arrays;

@SpringBootApplication
public class StockApplication implements CommandLineRunner {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private StockBranchRepository stockBranchRepository;

	@Autowired
	private StockShippingRepository stockShippingRepository;

	@Autowired
	private StockWarehouseRepository stockWarehouseRepository;

	@Autowired
	private StockBranchService stockBranchService;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ProductStockRepository productStockRepository;

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		StockBranch stockBranch1 = new StockBranch(null,0,"BRANCH", 1222);
		stockBranchRepository.saveAll(Arrays.asList(stockBranch1));

		StockWarehouse stockWarehouse1 = new StockWarehouse(null,0,"WAREHOUSE", 1000);
		stockWarehouseRepository.saveAll(Arrays.asList(stockWarehouse1));

		Invoice invoice2 = new Invoice(null, 1222);
		Item item2 = new Item(null, 4, "Amoxilina",200, invoice2);
		invoice2.getItems().addAll(Arrays.asList(item2));
		//invoiceRepository.saveAll(Arrays.asList(invoice2));
		//itemRepository.saveAll(Arrays.asList(item2));
		stockBranchService.creditItem(invoice2);

		Invoice invoice3 = new Invoice(null, 1222);
		Item item3 = new Item(null, 5, "Dipirona",244, invoice3);
		Item item4 = new Item(null, 4, "Amoxilina",133, invoice3);
		invoice3.getItems().addAll(Arrays.asList(item3,item4));
		//itemService.insertAll(invoice3.getItems());
		stockBranchService.creditItem(invoice3);

		/*
		Invoice invoice4 = new Invoice(null);
		Item item4 = new Item(null, 4, "Amoxilina",244, invoice3);
		invoice4.getItems().addAll(Arrays.asList(item4));
		//invoiceRepository.saveAll(Arrays.asList(invoice3));
		//itemRepository.saveAll(Arrays.asList(item3));
		stockWarehouseService.creditItem(invoice4, stockWarehouse1.getWarehouseCode());

		 */



	}
}