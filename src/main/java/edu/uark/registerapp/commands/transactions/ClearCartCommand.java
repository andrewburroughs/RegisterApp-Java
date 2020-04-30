package edu.uark.registerapp.commands.transactions;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;

@Service
public class ClearCartCommand implements VoidCommandInterface{
    @Transactional
    @Override
    public void execute() {
        final List<TransactionEntryEntity> transactionEntryEntities =
            this.transactionEntryRepository.findByTransactionId(this.transactionId);
        for (TransactionEntryEntity transactionEntryEntity : transactionEntryEntities) {
            this.transactionEntryRepository.delete(transactionEntryEntity);
        }
    }

    // Properties
    private UUID transactionId;
    public UUID getTransactionId() {
        return this.transactionId;
    }
    public ClearCartCommand setTransactionId(final UUID transactionId) {
        this.transactionId = transactionId;
        return this;
    }
    @Autowired
    private TransactionEntryRepository transactionEntryRepository;
}