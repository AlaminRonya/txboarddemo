package com.sdlcpro.txdemo.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public record PageableDto(Integer pageNo, Integer pageSize, String fieldBy, String direction) {
    public Pageable getPageable() {
        Sort.Direction sortDirection = (direction != null && !direction.isBlank())
                ? Sort.Direction.fromString(direction)
                : Sort.Direction.DESC; // Default to DESC
        return PageRequest.of((pageNo == null || pageNo < 1 ? 1 : pageNo) - 1,
                pageSize != null ? pageSize : 20,
                Sort.by(sortDirection, fieldBy != null ? fieldBy : "createdAt"));
    }
}