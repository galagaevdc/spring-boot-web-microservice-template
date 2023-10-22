package com.currency.books.service;

import com.currency.books.entity.TestItemDto;
import com.currency.books.exception.TestItemNotFoundException;
import com.currency.books.repository.TestItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestItemServiceUnitTest {
    @InjectMocks
    private TestItemService testItemService;
    @Mock
    private TestItemRepository testItemRepository;

    @Test
    public void getTestItemWhenNotExistShouldThrowException() {
        TestItemNotFoundException testItemNotFoundException = Assertions.assertThrows(TestItemNotFoundException.class,
                () -> testItemService.findById(4L));


        assertEquals(testItemNotFoundException.getMessage(), "Unable to find test item 4");
    }

    @Test
    public void updateTestItemWhenNotExistShouldThrowException() {
        TestItemNotFoundException testItemNotFoundException = Assertions.assertThrows(TestItemNotFoundException.class,
                () -> testItemService.update(4L, new TestItemDto()));


        assertEquals(testItemNotFoundException.getMessage(), "Unable to find test item 4");
    }
}
