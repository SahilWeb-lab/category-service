package com.ecom.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtil {

    public static Pageable createPageable(
            Integer pageNo,
            Integer pageSize,
            String sortBy,
            String sortDir) {

        Sort.Direction direction =
                sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortBy);

        return PageRequest.of(pageNo, pageSize, sort);
    }
}