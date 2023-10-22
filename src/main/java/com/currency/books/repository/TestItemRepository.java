package com.currency.books.repository;

import com.currency.books.dto.TestItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestItemRepository extends PagingAndSortingRepository<TestItemEntity, Long>, CrudRepository<TestItemEntity, Long> {
}
