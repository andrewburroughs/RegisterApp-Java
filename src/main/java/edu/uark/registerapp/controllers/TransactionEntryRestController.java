package edu.uark.registerapp.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uark.registerapp.commands.transactions.AddToCartCommand;
import edu.uark.registerapp.commands.transactions.RemoveFromCartCommand;
import edu.uark.registerapp.commands.transactions.UpdateCartCommand;
import edu.uark.registerapp.models.api.ApiResponse;
import edu.uark.registerapp.models.api.TransactionEntry;

@RestController
@RequestMapping(value = "/api/transactionEntry")
public class TransactionEntryRestController {
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody ApiResponse addToCart(
        @RequestBody final TransactionEntry transactionEntry
    ) {
        TransactionEntry createdTransactionEntry = this.addToCartCommand
            .setApiTransactionEntry(transactionEntry)
                .execute();
        return createdTransactionEntry;
    }

    @RequestMapping(value = "/{transactionEntryId}", method = RequestMethod.PUT)
    public @ResponseBody ApiResponse updateQuantity(
        @PathVariable final UUID transactionEntryId,
        @RequestBody final TransactionEntry transactionEntry
    ) {
        return this.updateCartCommand
            .setTransactionEntryId(transactionEntryId)
            .setQuantity(transactionEntry.getQuantity())
            .execute();
    }

    @RequestMapping(value = "/{transactionEntryId}", method = RequestMethod.DELETE)
    public @ResponseBody ApiResponse removeFromCart(
        @PathVariable final UUID transactionEntryId
    ) {
        System.out.println("yee2");
        this.removeFromCartCommand
            .setTransactionEntryId(transactionEntryId)
            .execute();

        return new ApiResponse();
    }

    // Properties
    @Autowired
    private AddToCartCommand addToCartCommand;

    @Autowired
    private UpdateCartCommand updateCartCommand;

    @Autowired
    private RemoveFromCartCommand removeFromCartCommand;
}