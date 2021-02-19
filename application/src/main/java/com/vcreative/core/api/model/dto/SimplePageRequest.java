package com.vcreative.core.api.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Simple version of PageRequest that is easier to serialise and manage JSON properties
 */
@Slf4j
@EqualsAndHashCode
public class SimplePageRequest implements Pageable {

    private static final int DEFAULT_PAGE_SIZE = 20;

    @JsonIgnore private PageRequest pageRequest;

    @JsonCreator
    public SimplePageRequest(@JsonProperty("page_number") final int pageNumber,
                             @JsonProperty("page_size") final int pageSize) {
        this.pageRequest = PageRequest.of(pageNumber, pageSize);
    }

    public static SimplePageRequest of(final int pageNumber, final int pageSize) {
        return new SimplePageRequest(pageNumber, pageSize);
    }

    public static SimplePageRequest firstPage() {
        return new SimplePageRequest(0, DEFAULT_PAGE_SIZE);
    }

    @JsonProperty("page_number")
    @Override
    public int getPageNumber() {
        return this.pageRequest.getPageNumber();
    }

    @JsonProperty("page_size")
    @Override
    public int getPageSize() {
        return this.pageRequest.getPageSize();
    }

    @JsonIgnore
    @Override
    public long getOffset() {
        return this.pageRequest.getOffset();
    }

    @JsonIgnore
    @Override
    public Sort getSort() {
        return this.pageRequest.getSort();
    }

    @JsonIgnore
    @Override
    public Pageable next() {
        return this.pageRequest.next();
    }

    @JsonIgnore
    @Override
    public Pageable previousOrFirst() {
        return this.pageRequest.previousOrFirst();
    }

    @JsonIgnore
    @Override
    public Pageable first() {
        return this.pageRequest.first();
    }

    @JsonIgnore
    @Override
    public boolean hasPrevious() {
        return this.pageRequest.hasPrevious();
    }

    @JsonIgnore
    @Override
    public boolean isPaged() {
        return this.pageRequest.isPaged();
    }

    @JsonIgnore
    @Override
    public boolean isUnpaged() {
        return this.pageRequest.isUnpaged();
    }
}
