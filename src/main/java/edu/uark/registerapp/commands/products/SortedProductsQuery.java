package edu.uark.registerapp.commands.products;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;

@Service
public class SortedProductsQuery implements ResultCommandInterface<List<Product>> {
	@Override
	public List<Product> execute() {
		final LinkedList<Product> products = new LinkedList<Product>();

		for (final ProductEntity productEntity : sortedProductRepository.findAll(Sort.by(Sort.Direction.ASC, "quantitySold"))) {
			products.addLast(new Product(productEntity));
		}
		
		return products;
	}

	@Autowired
	SortedProductRepository sortedProductRepository;
}
