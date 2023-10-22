package com.currency.books;

import com.currency.books.entity.TestItemDto;
import com.currency.books.service.TestItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TestItemControllerTest {
    private static final String TITLE = "title";
    private static final String NEW_TITLE = "newTitle";
    private static final String DESCRIPTION = "description";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TestItemService testItemService;

    @Test
    void createTestItem() throws Exception {
        final TestItemDto testItemDto = createTestItemDto(TITLE);

        this.mockMvc.perform(post("/test-items")
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(testItemDto))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.title").value(TITLE))
                .andExpect(jsonPath("$.description").value(DESCRIPTION));
    }

    private static TestItemDto createTestItemDto(final String title) {
        final TestItemDto testItemDto = new TestItemDto();
        testItemDto.setTitle(title);
        testItemDto.setDescription(DESCRIPTION);
        return testItemDto;
    }

    @Test
    void createTestItemInvalid() throws Exception {
        final TestItemDto testItemDto = createTestItemDto(null);

        this.mockMvc.perform(post("/test-items")
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(testItemDto))).andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getTestItem() throws Exception {
        final TestItemDto testItemDto = createTestItemDto(TITLE);
        TestItemDto createdTestItem = testItemService.create(testItemDto);


        this.mockMvc.perform(get("/test-items/" + createdTestItem.getId())
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(testItemDto))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdTestItem.getId().toString()))
                .andExpect(jsonPath("$.title").value(TITLE))
                .andExpect(jsonPath("$.description").value(DESCRIPTION));
    }

    @Test
    void updateTestItem() throws Exception {
        final TestItemDto testItemDto = createTestItemDto(TITLE);
        TestItemDto createdTestItem = testItemService.create(testItemDto);

        this.mockMvc.perform(put("/test-items/" + createdTestItem.getId())
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(createTestItemDto(NEW_TITLE)))).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdTestItem.getId().toString()))
                .andExpect(jsonPath("$.title").value(NEW_TITLE))
                .andExpect(jsonPath("$.description").value(DESCRIPTION));
    }

    @Test
    void getItems() throws Exception {
        final TestItemDto testItemDto = createTestItemDto(TITLE);
        TestItemDto testItemDto1 = testItemService.create(testItemDto);
        testItemService.create(testItemDto);

        this.mockMvc.perform(get("/test-items?page=0&size=1")
                        .header("Content-Type", "application/json")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.content[0].id").value(testItemDto1.getId().toString()))
                .andExpect(jsonPath("$.content[0].title").value(TITLE))
                .andExpect(jsonPath("$.content[0].description").value(DESCRIPTION));
    }
}
