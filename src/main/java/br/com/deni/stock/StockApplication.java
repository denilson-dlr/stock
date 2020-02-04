package br.com.deni.stock;

import br.com.deni.stock.core.domain.*;
import br.com.deni.stock.core.repositories.*;
import br.com.deni.stock.core.services.StockBranchService;
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

	@Autowired
	private StockBranchRepository stockBranchRepository;

	@Autowired
	private StockShippingRepository stockShippingRepository;

	@Autowired
	private StockWarehouseRepository stockWarehouseRepository;

	@Autowired
	private StockBranchService stockBranchService;

	@Autowired
	private ItemStockRepository itemStockRepository;

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		StockBranch stockBranch1 = new StockBranch(null,25,"BRANCH", 1222);
		stockBranchRepository.saveAll(Arrays.asList(stockBranch1));

		Invoice invoice1 = new Invoice(null);
		Item item7 = new Item(null, 1, "Dipirona",100, invoice1);
		Item item8 = new Item(null, 2, "Novalgina", 50, invoice1);
		Item item9 = new Item(null, 3, "Powerade",5, invoice1);
		invoice1.getItems().addAll(Arrays.asList(item7,item8,item9));
		invoiceRepository.saveAll(Arrays.asList(invoice1));
		itemRepository.saveAll(Arrays.asList(item7,item8,item9));



		ItemStock itemStock7 = new ItemStock(item7,stockBranch1,item7.getQuantity());
		ItemStock itemStock8 = new ItemStock(item8,stockBranch1,item8.getQuantity());
		ItemStock itemStock9 = new ItemStock(item9,stockBranch1,item9.getQuantity());

		stockBranch1.getItems().addAll(Arrays.asList(itemStock7,itemStock8,itemStock9));
		item7.getItems().addAll(Arrays.asList(itemStock7));
		item8.getItems().addAll(Arrays.asList(itemStock8));
		item9.getItems().addAll(Arrays.asList(itemStock9));

		itemStockRepository.saveAll(Arrays.asList(itemStock7,itemStock8,itemStock9));

		//stockBranchService.creditItem(invoice1, stockBranch1.getBranchCode());



		Invoice invoice2 = new Invoice(null);
		Item item2 = new Item(null, 4, "Amoxilina",200, invoice2);
		invoice2.getItems().addAll(Arrays.asList(item2));
		invoiceRepository.saveAll(Arrays.asList(invoice2));
		itemRepository.saveAll(Arrays.asList(item2));
		stockBranchService.creditItem(invoice2, stockBranch1.getBranchCode());

		Invoice invoice3 = new Invoice(null);
		Item item3 = new Item(null, 4, "Amoxilina",244, invoice3);
		invoice3.getItems().addAll(Arrays.asList(item3));
		invoiceRepository.saveAll(Arrays.asList(invoice3));
		itemRepository.saveAll(Arrays.asList(item3));
		stockBranchService.creditItem(invoice3, stockBranch1.getBranchCode());


	}
}