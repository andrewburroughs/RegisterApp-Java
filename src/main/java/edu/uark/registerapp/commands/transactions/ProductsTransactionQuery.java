package edu.uark.registerapp.commands.transactions;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.Optional; //Ignore the warning, it is used when "isEmpty()" is called in line 25

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;

@Service
public class ProductsTransactionQuery implements ResultCommandInterface<List<Product>> {
	@Override
	public List<Product> execute() {

		final LinkedList<Product> products = new LinkedList<Product>();

		for (final ProductEntity productEntity : productRepository.findAll()) {
            if(transactionEntryRepository.findByTransactionIdAndProductId(transactionId, productEntity.getId()).isEmpty()) {
                products.addLast(new Product(productEntity));
            }
		}
		return products;
    }
    
    // Properties
    private UUID transactionId;
    public UUID getTransactionID() {
        return this.transactionId;
    }
    public ProductsTransactionQuery setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
        return this;
    }

	@Autowired
    ProductRepository productRepository;
    
    @Autowired
    TransactionEntryRepository transactionEntryRepository;
}