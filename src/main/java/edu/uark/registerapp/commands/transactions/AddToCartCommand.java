package edu.uark.registerapp.commands.transactions;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.ConflictException;
import edu.uark.registerapp.models.api.TransactionEntry;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;

@Service
public class AddToCartCommand implements ResultCommandInterface<TransactionEntry>{
    @Override
    public TransactionEntry execute() {
        this.createTransactionEntryEntity();

        return this.apiTransactionEntry;
    }

    @Transactional
    private TransactionEntryEntity createTransactionEntryEntity() {
        final Optional<TransactionEntryEntity> queriedTransactionEntryEntity = 
            this.transactionEntryRepository
                .findByTransactionIdAndProductId(apiTransactionEntry.getTransactionId(), apiTransactionEntry.getProductId());

        if (queriedTransactionEntryEntity.isPresent()) {
            throw new ConflictException("productId");
        }

        return this.transactionEntryRepository.save(
            new TransactionEntryEntity(apiTransactionEntry));
    }

    // Properties
    private TransactionEntry apiTransactionEntry;
    public TransactionEntry getApiTransactionEntry() {
        return this.apiTransactionEntry;
    }
    public AddToCartCommand setApiTransactionEntry(final TransactionEntry apiTransactionEntry) {
        this.apiTransactionEntry = apiTransactionEntry;
        return this;
    }

    @Autowired
    private TransactionEntryRepository transactionEntryRepository;

}