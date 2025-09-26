package com.sdlcpro.txdemo.data.repository;

import com.sdlcpro.txdemo.data.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, UUID> {

}