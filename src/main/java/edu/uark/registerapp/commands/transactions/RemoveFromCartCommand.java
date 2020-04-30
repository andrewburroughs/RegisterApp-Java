package edu.uark.registerapp.commands.transactions;

import java.util.Optional;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;

@Service
public class RemoveFromCartCommand implements VoidCommandInterface{
    @Transactional
    @Override
    public void execute() {
        System.out.println("yee");
        final Optional<TransactionEntryEntity> transactionEntryEntity =
            this.transactionEntryRepository.findById(this.transactionEntryId);
        if(!transactionEntryEntity.isPresent()) {
            throw new NotFoundException("TransactionEntry");
        }

        this.transactionEntryRepository.delete(transactionEntryEntity.get());
    }

    // Properties
    private UUID transactionEntryId;
    public UUID getTransactionEntryId() {
        return this.transactionEntryId;
    }
    public RemoveFromCartCommand setTransactionEntryId(final UUID transactionEntryId) {
        this.transactionEntryId = transactionEntryId;
        return this;
    }
    @Autowired
    private TransactionEntryRepository transactionEntryRepository;
}