package com.sdlcpro.txdemo.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sdlcpro.txdemo.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "ACCOUNTS_TRANSACTIONS")
public class AccountTransaction extends BaseEntity{

    @ManyToOne(optional = false)
    @JoinColumn(name = "BANK_ACCOUNT_ID", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private BankAccount bankAccount;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    @Convert(converter = TransactionType.TransactionTypeConverter.class)
    private TransactionType transactionType;

    @Column(name = "DESCRIPTION")
    private String description;

}
