package edu.uark.registerapp.models.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import edu.uark.registerapp.models.entities.ProductEntity;

public class Product extends ApiResponse {
	private UUID id;
	public UUID getId() {
		return this.id;
	}
	public Product setId(final UUID id) {
		this.id = id;
		return this;
	}

	private String lookupCode;

	public String getLookupCode() {
		return this.lookupCode;
	}

	public Product setLookupCode(final String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}

	private int count;

	public int getCount() {
		return this.count;
	}

	public Product setCount(final int count) {
		this.count = count;
		return this;
	}

	private String createdOn;

	public String getCreatedOn() {
		return this.createdOn;
	}

	public Product setCreatedOn(final String createdOn) {
		this.createdOn = createdOn;
		return this;
	}

	public Product setCreatedOn(final LocalDateTime createdOn) {
		this.createdOn =
			createdOn.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

		return this;
	}

	private double price;

	public double getPrice() {
		return this.price;
	}

	public Product setPrice(final double price) {
		this.price = price;
		return this;
	}

	private int quantitySold;

	public int getQuantitySold(){
		return this.quantitySold;
	}

	public Product setQuantitySold(final int quantitySold){
		this.quantitySold = quantitySold;
		return this;
	}

	private double productSales;

	public double getProductSales(){
		return this.productSales;
	}

	public Product setProductSales(final double productSales){
		this.productSales = productSales;
		return this;
	}

	public Product() {
		super();

		this.count = -1;
		this.id = new UUID(0, 0);
		this.lookupCode = StringUtils.EMPTY;
		this.price = 0.00;

		this.setCreatedOn(LocalDateTime.now());
		this.quantitySold = 0;
		this.productSales = 0.00;
	}

	public Product(final ProductEntity productEntity) {
		super(false);

		this.id = productEntity.getId();
		this.count = productEntity.getCount();
		this.lookupCode = productEntity.getLookupCode();
		this.price = productEntity.getPrice();

		this.setCreatedOn(productEntity.getCreatedOn());
		this.quantitySold = productEntity.getQuantitySold();
		this.productSales = productEntity.getProductSales();
	}
}
