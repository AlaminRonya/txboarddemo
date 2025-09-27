package com.sdlcpro.txdemo.data.repository;

import com.sdlcpro.txdemo.data.entity.AccountTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, UUID> , JpaSpecificationExecutor<AccountTransaction> {

}