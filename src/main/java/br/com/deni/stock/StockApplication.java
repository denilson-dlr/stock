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
	private ProductStockRepository productStockRepository;

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		StockBranch stockBranch1 = new StockBranch(null,25,"BRANCH", 1222);
		stockBranchRepository.saveAll(Arrays.asList(stockBranch1));

		StockWarehouse stockWarehouse1 = new StockWarehouse(null,30,"WAREHOUSE", 1000);
		stockWarehouseRepository.saveAll(Arrays.asList(stockWarehouse1));

		/*
		Invoice invoice1 = new Invoice(null);
		Item item7 = new Item(null, 1, "Dipirona",100, invoice1);
		Item item8 = new Item(null, 2, "Novalgina", 50, invoice1);
		Item item9 = new Item(null, 3, "Powerade",5, invoice1);
		invoice1.getItems().addAll(Arrays.asList(item7,item8,item9));
		invoiceRepository.saveAll(Arrays.asList(invoice1));
		itemRepository.saveAll(Arrays.asList(item7,item8,item9));



		ProductStock productStock7 = new ProductStock(item7,stockBranch1,item7.getQuantity());
		ProductStock productStock8 = new ProductStock(item8,stockBranch1,item8.getQuantity());
		ProductStock productStock9 = new ProductStock(item9,stockBranch1,item9.getQuantity());

		stockBranch1.getProducts().addAll(Arrays.asList(productStock7, productStock8, productStock9));
		item7.getItems().addAll(Arrays.asList(productStock7));
		item8.getItems().addAll(Arrays.asList(productStock8));
		item9.getItems().addAll(Arrays.asList(productStock9));

		productStockRepository.saveAll(Arrays.asList(productStock7, productStock8, productStock9));

		//stockBranchService.creditItem(invoice1, stockBranch1.getBranchCode());

		 */



		Invoice invoice2 = new Invoice(null);
		Item item2 = new Item(null, 4, "Amoxilina",200, invoice2);
		invoice2.getItems().addAll(Arrays.asList(item2));
		//invoiceRepository.saveAll(Arrays.asList(invoice2));
		//itemRepository.saveAll(Arrays.asList(item2));
		stockBranchService.creditItem(invoice2, stockBranch1.getBranchCode());

		Invoice invoice3 = new Invoice(null);
		Item item3 = new Item(null, 5, "Dipirona",244, invoice3);
		invoice3.getItems().addAll(Arrays.asList(item3));
		//invoiceRepository.saveAll(Arrays.asList(invoice3));
		//itemRepository.saveAll(Arrays.asList(item3));
		stockBranchService.creditItem(invoice3, stockBranch1.getBranchCode());

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