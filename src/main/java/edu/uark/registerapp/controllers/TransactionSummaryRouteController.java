package edu.uark.registerapp.controllers;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.controllers.enums.ViewModelNames;
import edu.uark.registerapp.commands.transactions.TransactionEntriesQuery;
import edu.uark.registerapp.models.api.TransactionEntry;


@Controller
@RequestMapping(value = "/transactionSummary")
public class TransactionSummaryRouteController extends BaseRouteController{
    @RequestMapping(value = "/{transactionId}", method = RequestMethod.GET)
    public ModelAndView showTransactionView(@PathVariable final UUID transactionId) {
        ModelAndView modelAndView =
            new ModelAndView(ViewNames.TRANSACTION_SUMMARY.getViewName());
        
            try {
                List<TransactionEntry> transactionEntries = this.transactionEntriesQuery.setTransactionId(transactionId).execute();
                double totalPrice = 0;
                int productNum = 0;
                for(int i = 0; i < transactionEntries.size(); i++)
                {
                    totalPrice += transactionEntries.get(i).getPrice() * transactionEntries.get(i).getQuantity();
                    productNum += transactionEntries.get(i).getQuantity();
                }
                modelAndView.addObject(
                    ViewModelNames.TRANSACTION_ENTRIES.getValue(),
                    transactionEntries);
                modelAndView.addObject(
                    ViewModelNames.TRANSACTION_ID.getValue(),
                    transactionId);
                modelAndView.addObject(
                    "TotalPrice", 
                    totalPrice);
                modelAndView.addObject(
                    "ProductNum",
                    productNum);  
            } catch (final Exception e) {
                modelAndView.addObject(
                    ViewModelNames.ERROR_MESSAGE.getValue(),
                    e.getMessage());
            }
        return modelAndView;
    }


    // Properties
	@Autowired
	private TransactionEntriesQuery transactionEntriesQuery;
}