package com.currency.books.controller;

import com.currency.books.dto.view.TestItemView;
import com.currency.books.entity.TestItemDto;
import com.currency.books.service.TestItemService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestItemController {
    @Autowired
    private TestItemService testItemService;

    @JsonView(TestItemView.OnGet.class)
    @PostMapping("/test-items")
    public TestItemDto create(@Validated(TestItemView.OnCreate.class) @RequestBody TestItemDto testItemDto) {
        return testItemService.create(testItemDto);
    }

    @JsonView(TestItemView.OnGet.class)
    @PutMapping("/test-items/{id}")
    public TestItemDto update(@PathVariable long id, @Validated(TestItemView.OnUpdate.class) @RequestBody TestItemDto testItemDto) {
        return testItemService.update(id, testItemDto);
    }

    @JsonView(TestItemView.OnGet.class)
    @GetMapping("/test-items/{id}")
    public TestItemDto get(@PathVariable long id) {
        return testItemService.findById(id);
    }

    @GetMapping("/test-items")
    public Page<TestItemDto> getItems(final Pageable pageable) {
        return testItemService.findAll(pageable);
    }
}
