package edu.uark.registerapp.commands.transactions;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.api.TransactionEntry;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;

@Service
public class UpdateCartCommand implements ResultCommandInterface<TransactionEntry>{
    @Transactional
    @Override
    public TransactionEntry execute() {
        final Optional<TransactionEntryEntity> transactionEntryEntity =
            this.transactionEntryRepository.findById(this.transactionEntryId);
        if (!transactionEntryEntity.isPresent()) {
            throw new NotFoundException("TransactionEntry");
        }

        transactionEntryEntity.get().setQuantity(this.quantity);

        this.apiTransactionEntry = new TransactionEntry(transactionEntryEntity.get());
        
        this.transactionEntryRepository.save(transactionEntryEntity.get());

        return this.apiTransactionEntry;
    }

    // Properties
    private UUID transactionEntryId;
    public UUID getTransactionEntryId() {
        return this.transactionEntryId;
    }
    public UpdateCartCommand setTransactionEntryId(final UUID transactionEntryId) {
        this.transactionEntryId = transactionEntryId;
        return this;
    }

    private int quantity;
    public int getQuantity() {
        return this.quantity;
    }
    public UpdateCartCommand setQuantity(final int quantity) {
        this.quantity = quantity;
        return this;
    }

    private TransactionEntry apiTransactionEntry;
	public TransactionEntry getApiTransactionEntry() {
		return this.apiTransactionEntry;
	}
	public UpdateCartCommand setApiTransactionEntry(final TransactionEntry apiTransactionEntry) {
		this.apiTransactionEntry = apiTransactionEntry;
		return this;
	}

    @Autowired
    private TransactionEntryRepository transactionEntryRepository;
}