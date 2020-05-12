package edu.uark.registerapp.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import edu.uark.registerapp.models.api.Product;

@Entity
@Table(name="product")
public class ProductEntity {
    @Id
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private final UUID id;

	public UUID getId() {
		return this.id;
	}

	@Column(name = "lookupcode")
	private String lookupCode;

	public String getLookupCode() {
		return this.lookupCode;
	}

	public ProductEntity setLookupCode(final String lookupCode) {
		this.lookupCode = lookupCode;
		return this;
	}

	@Column(name = "count")
	private int count;

	public int getCount() {
		return this.count;
	}

	public ProductEntity setCount(final int count) {
		this.count = count;
		return this;
	}

    @Column(name="price")
    private double price;

	public double getPrice() {
		return this.price;
	}

	public ProductEntity setPrice(final double price) {
		this.price = price;
		return this;
	}

	@Column(name = "createdon", insertable = false, updatable = false)
	@Generated(GenerationTime.INSERT)
	private LocalDateTime createdOn;

	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}

	@Column(name="quantitysold", updatable = true)
	private int quantitySold;

	public int getQuantitySold(){
		return this.quantitySold;
	}

	public ProductEntity setQuantitySold(final int quantitySold){
		this.quantitySold = quantitySold;
		return this;
	}

	@Column(name="productsales", updatable = true)
	private double productSales;

	public double getProductSales(){
		return this.productSales;
	}

	public ProductEntity setProductSales(final double productSales){
		this.productSales = productSales;
		return this;
	}

	public Product synchronize(final Product apiProduct) {
		this.setCount(apiProduct.getCount());
		this.setLookupCode(apiProduct.getLookupCode());
		this.setPrice(apiProduct.getPrice());

		apiProduct.setId(this.getId());
		apiProduct.setCreatedOn(this.getCreatedOn());

		this.setQuantitySold(apiProduct.getQuantitySold());
		this.setProductSales(apiProduct.getProductSales());
		return apiProduct;
	}

	public ProductEntity() {
		this.count = -1;
		this.price = 0.00;
		this.id = new UUID(0, 0);
		this.lookupCode = StringUtils.EMPTY;
		this.quantitySold = 0;
		this.productSales = 0;
	}

	public ProductEntity(final String lookupCode, final int count, final double price, final int quantitySold, final double productSales) {
		this.count = count;
		this.price = price;
		this.id = new UUID(0, 0);
		this.lookupCode = lookupCode;
		this.quantitySold = quantitySold;
		this.productSales = productSales;
	}

	public ProductEntity(final Product apiProduct) {
		this.id = new UUID(0, 0);
		this.count = apiProduct.getCount();
		this.lookupCode = apiProduct.getLookupCode();
		this.price = apiProduct.getPrice();
		this.quantitySold = apiProduct.getQuantitySold();
		this.productSales = apiProduct.getProductSales();
	}
}
