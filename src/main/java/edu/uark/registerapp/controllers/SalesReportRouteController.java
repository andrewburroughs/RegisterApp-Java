package edu.uark.registerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uark.registerapp.commands.products.SortedProductsQuery;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.Product;

@Controller
@RequestMapping(value = "/salesReport")
public class SalesReportRouteController {
    @RequestMapping(value = "/")
    public ModelAndView showDescendingQuantity(){
        ModelAndView modelAndView = 
            new ModelAndView(ViewNames.SALES_REPORT.getViewName());
        try {
            modelAndView.addObject(
                ViewModelNames.PRODUCTS.getValue(),
                this.sortedProductsQuery.execute());
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

    @RequestMapping(value = "/ascending")
    public ModelAndView showAscendingQuantity(){
        new ModelAndView(ViewNames.SALES_REPORT.getViewName());
        try{
            modelAndView.addObject(
                ViewModelNames.PRODUCTS.getValue(),
                this.sortedProductsQuery.execute("quantitySold", false));
        } catch (final Exception e) {
            modelAndView.addObject(
                ViewModelNames.ERROR_MESSAGE.getValue(),
                e.getMessage());
            modelAndView.addObject(
                ViewModelNames.PRODUCTS.getValue(),
                (new Product[0]));
        }
    }

    @RequestMapping(value = "/productSales")
    public ModelAndView showDescendingSales(){
        new ModelAndView(ViewNames.SALES_REPORT.getViewName());
        try{
            modelAndView.addObject(
                ViewModelNames.PRODUCTS.getValue(),
                this.sortedProductsQuery.execute("productSales", true));
        } catch (final Exception e) {
            modelAndView.addObject(
                ViewModelNames.ERROR_MESSAGE.getValue(),
                e.getMessage());
            modelAndView.addObject(
                ViewModelNames.PRODUCTS.getValue(),
                (new Product[0]));
        }
    }

    @RequestMapping(value = "/ascendingProductSales")
    public ModelAndView showAscendingSales(){
        new ModelAndView(ViewNames.SALES_REPORT.getViewName());
        try{
            modelAndView.addObject(
                ViewModelNames.PRODUCTS.getValue(),
                this.sortedProductsQuery.execute("productSales", false));
        } catch (final Exception e) {
            modelAndView.addObject(
                ViewModelNames.ERROR_MESSAGE.getValue(),
                e.getMessage());
            modelAndView.addObject(
                ViewModelNames.PRODUCTS.getValue(),
                (new Product[0]));
        }
    }

    @Autowired
    private SortedProductsQuery sortedProductsQuery;
}