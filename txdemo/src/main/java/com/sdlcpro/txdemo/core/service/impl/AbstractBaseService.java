package com.sdlcpro.txdemo.core.service.impl;

import com.sdlcpro.txdemo.common.I18nService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractBaseService<T, ID> implements I18nService {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final JpaRepository<T, ID> repository;

    protected AbstractBaseService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    protected T save(T entity) {
        log.info("Saving entity: {}", entity);
        return repository.save(entity);
    }

    protected List<T> findAll() {
        log.debug("Fetching all entities");
        return repository.findAll();
    }
    protected List<T> findAll(Sort sort) {
        log.debug("Fetching all entities with sort: {}", sort);
        return repository.findAll(sort);
    }

    protected Page<T> findAll(Pageable pageable) {
        if (pageable == null) {
            pageable = PageRequest.of(0, 20); // default page 0, size 20
        }
        log.debug("Fetching all entities with pagination: {}", pageable);
        return repository.findAll(pageable);
    }

    protected Optional<T> findById(ID id) {
        log.debug("Finding entity by id: {}", id);
        return repository.findById(id);
    }

    protected void deleteById(ID id) {
        repository.deleteById(id);
    }

    protected void delete(T entity) {
        repository.delete(entity);
    }

    protected boolean existsById(ID id) {
        log.debug("Checking existence of entity by id: {}", id);
        return repository.existsById(id);
    }

    protected long count() {
        log.debug("Counting entities");
        return repository.count();
    }
}