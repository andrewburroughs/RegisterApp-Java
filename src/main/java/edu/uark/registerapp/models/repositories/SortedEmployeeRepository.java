package edu.uark.registerapp.models.repositories;

import java.util.UUID;
import java.lang.Iterable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import edu.uark.registerapp.models.entities.EmployeeEntity;

public interface SortedEmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, UUID> {
    Iterable<EmployeeEntity> findAll(Sort sort);
}