package com.currency.books.service;

import com.currency.books.dto.TestItemEntity;
import com.currency.books.entity.TestItemDto;
import com.currency.books.exception.TestItemNotFoundException;
import com.currency.books.mapper.TestItemMapper;
import com.currency.books.repository.TestItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TestItemService {
    @Autowired
    private TestItemMapper testItemMapper;
    @Autowired
    private TestItemRepository testItemRepository;


    public TestItemDto create(final TestItemDto book) {
        final TestItemEntity testItemEntity = testItemMapper.toEntity(book);
        final TestItemEntity savedBook = testItemRepository.save(testItemEntity);
        return testItemMapper.toDto(savedBook);
    }

    public TestItemDto update(long id, final TestItemDto itemDto) {
        final TestItemEntity testItemToUpdate = getById(id);
        testItemMapper.update(testItemToUpdate, itemDto);
        final TestItemEntity savedBook = testItemRepository.save(testItemToUpdate);
        return testItemMapper.toDto(savedBook);
    }

    private TestItemEntity getById(final Long id) {
        return testItemRepository.findById(id).orElseThrow(() -> {
            throw new TestItemNotFoundException("Unable to find test item " + id);
        });
    }

    public TestItemDto findById(final Long id) {
        return testItemMapper.toDto(getById(id));
    }

    public Page<TestItemDto> findAll(final Pageable pageable) {
        return testItemRepository.findAll(pageable).map(testItemMapper::toDto);
    }

}
