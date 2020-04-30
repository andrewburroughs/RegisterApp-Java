package edu.uark.registerapp.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.products.ProductsQuery;
import edu.uark.registerapp.commands.transactions.ProductsTransactionQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Product;

@Controller
@RequestMapping(value = "/productListing")
public class ProductListingRouteController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showProductListing() {
		ModelAndView modelAndView =
			new ModelAndView(ViewNames.PRODUCT_LISTING.getViewName());

		try {
			modelAndView.addObject(
				ViewModelNames.PRODUCTS.getValue(),
				this.productsQuery.execute());
		} catch (final Exception e) {
			modelAndView.addObject(
				ViewModelNames.ERROR_MESSAGE.getValue(),
				e.getMessage());
			modelAndView.addObject(
				ViewModelNames.PRODUCTS.getValue(),
				(new Product[0]));
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
	public ModelAndView startTransaction(@PathVariable final UUID transactionId) {
		ModelAndView modelAndView =
			new ModelAndView(ViewNames.PRODUCT_LISTING.getViewName());

		try {
			modelAndView.addObject(
				ViewModelNames.PRODUCTS.getValue(),
				this.productsTransactionQuery.setTransactionId(transactionId).execute());
			modelAndView.addObject(
				ViewModelNames.TRANSACTION_ID.getValue(),
				transactionId);
			modelAndView.addObject(
				ViewModelNames.TRANSACTION_STARTED.getValue(),
				true);
		} catch (final Exception e) {
			modelAndView.addObject(
				ViewModelNames.ERROR_MESSAGE.getValue(),
				e.getMessage());
			modelAndView.addObject(
				ViewModelNames.PRODUCTS.getValue(),
				(new Product[0]));
		}

		return modelAndView;
	}

	// Properties
	@Autowired
	private ProductsQuery productsQuery;

	@Autowired
	private ProductsTransactionQuery productsTransactionQuery;
}
