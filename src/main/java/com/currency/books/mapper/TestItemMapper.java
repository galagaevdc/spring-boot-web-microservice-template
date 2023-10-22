package com.currency.books.mapper;

import com.currency.books.dto.TestItemEntity;
import com.currency.books.entity.TestItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestItemMapper {
    TestItemDto toDto(final TestItemEntity entity);

    List<TestItemDto> toDtos(final List<TestItemEntity> entity);

    TestItemEntity toEntity(final TestItemDto bookDto);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget TestItemEntity entity, TestItemDto updateDto);

}
