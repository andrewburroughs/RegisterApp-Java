package edu.uark.registerapp.models.repositories;

import java.util.UUID;
import java.lang.Iterable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import edu.uark.registerapp.models.entities.ProductEntity;

public interface SortedProductRepository extends PagingAndSortingRepository<ProductEntity, UUID> {
    Iterable<ProductEntity> findAll(Sort sort);
}