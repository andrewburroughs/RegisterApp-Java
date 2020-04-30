package edu.uark.registerapp.commands.transactions;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.entities.ProductEntity;
import edu.uark.registerapp.models.entities.TransactionEntryEntity;
import edu.uark.registerapp.models.repositories.ProductRepository;
import edu.uark.registerapp.models.repositories.TransactionEntryRepository;

@Service
public class TransactionCommitCommand implements VoidCommandInterface{
    @Transactional
    @Override
    public void execute() {
        final List<TransactionEntryEntity> queriedTransactionEntryEntities =
            this.transactionEntryRepository.findByTransactionId(transactionId);
        for (TransactionEntryEntity transactionEntryEntity : queriedTransactionEntryEntities) {
            Optional<ProductEntity> productEntity = productRepository.findById(transactionEntryEntity.getProductId());
            if (!productEntity.isPresent()) {
                throw new NotFoundException("Product");
            }
            productEntity.get().setCount(productEntity.get().getCount() - transactionEntryEntity.getQuantity());

            this.productRepository.save(productEntity.get());
        }
    }

    // Properites
    private UUID transactionId;
    public UUID getTransactionId() {
        return this.transactionId;
    }
    public TransactionCommitCommand setTransactionId(final UUID transactionId) {
        this.transactionId = transactionId;;
        return this;
    }

    @Autowired
    private TransactionEntryRepository transactionEntryRepository;

    @Autowired
    private ProductRepository productRepository;
}