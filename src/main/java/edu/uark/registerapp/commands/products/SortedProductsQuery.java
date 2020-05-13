package edu.uark.registerapp.commands.products;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.models.api.Product;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.repositories.SortedProductRepository;

@Service
public class SortedProductsQuery implements ResultCommandInterface<List<Product>> {
    @Override
    public List<Product> execute() {
		final LinkedList<Product> products = new LinkedList<Product>();

		for (final ProductEntity productEntity : sortedProductRepository.findAll(Sort.by(Sort.Direction.DESC, "quantitySold"))) {
			products.addLast(new Product(productEntity));
		}
		
		return products;
	}
    
	public List<Product> execute(String config) {
		final LinkedList<Product> products = new LinkedList<Product>();
        Boolean direction;
        String columnName;
        switch(config){
            case "0":
                direction = true;
                columnName = "quantitySold";
                break;
            case "1":
                direction = false;
                columnName = "quantitySold";
                break;
            case "2":
                direction = true;
                columnName = "productSales";
                break;
            case "3":
                direction = false;
                columnName = "productSales";
                break;
            default:
                direction = true;
                columnName = "quantitySold";
                break;
        }
        if(direction == true){
            for (final ProductEntity productEntity : sortedProductRepository.findAll(Sort.by(Sort.Direction.DESC, columnName))) {
                products.addLast(new Product(productEntity));
            }
        }
        else{
            for (final ProductEntity productEntity : sortedProductRepository.findAll(Sort.by(Sort.Direction.ASC, columnName))) {
                products.addLast(new Product(productEntity));
            }
        }
		
		return products;
    }

	@Autowired
	SortedProductRepository sortedProductRepository;
}
