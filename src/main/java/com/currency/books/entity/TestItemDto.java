package com.currency.books.entity;

import com.currency.books.dto.view.TestItemView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TestItemDto {

    @JsonView({TestItemView.OnGet.class, TestItemView.OnGetList.class})
    private Long id;
    @JsonView({TestItemView.OnGet.class, TestItemView.OnGetList.class})
    @NotBlank(message = "Title is mandatory",
            groups = {TestItemView.OnCreate.class, TestItemView.OnUpdate.class})
    private String title;
    @JsonView({TestItemView.OnGet.class})
    @NotBlank(message = "Description is mandatory",
            groups = {TestItemView.OnCreate.class, TestItemView.OnUpdate.class})
    private String description;
}
