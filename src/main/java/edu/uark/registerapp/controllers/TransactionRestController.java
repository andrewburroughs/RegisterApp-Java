package edu.uark.registerapp.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.registerapp.commands.transactions.ClearCartCommand;
import edu.uark.registerapp.commands.transactions.StartTransactionCommand;
import edu.uark.registerapp.commands.transactions.TransactionCommitCommand;
import edu.uark.registerapp.controllers.enums.ViewNames;
import edu.uark.registerapp.models.api.ApiResponse;
import edu.uark.registerapp.models.api.Transaction;

@RestController
@RequestMapping(value = "/api/transaction")
public class TransactionRestController extends BaseRestController{
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody ApiResponse startTransaction(
        @RequestBody final Transaction transaction
    ) {
        Transaction createdTransaction = this.startTransactionCommand
            .setApiTransaction(transaction)
                .execute();
        createdTransaction.setRedirectUrl(
            ViewNames.PRODUCT_LISTING.getRoute().concat(
                "/" + createdTransaction.getId().toString()));
        return createdTransaction;
    }

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.PUT)
    public @ResponseBody ApiResponse commitTransaction(
        @PathVariable final UUID transactionId
    ) {
        this.transactionCommitCommand
            .setTransactionId(transactionId)
            .execute();
        
        return new ApiResponse();
    }

    @RequestMapping(value = "/{transactionId}", method = RequestMethod.DELETE)
    public @ResponseBody ApiResponse clearCart(
        @PathVariable final UUID transactionId
    ) {
        this.clearCartCommand
            .setTransactionId(transactionId)
            .execute();
        
        return new ApiResponse();
    }

    // Properties 
    @Autowired
    private StartTransactionCommand startTransactionCommand;

    @Autowired
    private TransactionCommitCommand transactionCommitCommand;

    @Autowired
    private ClearCartCommand clearCartCommand;
}