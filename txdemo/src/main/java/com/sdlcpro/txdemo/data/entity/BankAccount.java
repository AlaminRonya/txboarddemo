package com.sdlcpro.txdemo.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sdlcpro.txdemo.enums.AccountType;
import com.sdlcpro.txdemo.enums.Bank;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "BANK_ACCOUNTS")
public class BankAccount extends BaseEntity{

    @Column(name = "ACCOUNT_NUMBER", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "ACCOUNT_TYPE", nullable = false)
    @Convert(converter = AccountType.AccountTypeConverter.class)
    private AccountType accountType;

    @Column(name = "BANK", nullable = false)
    @Convert(converter = Bank.BankConverter.class)
    private Bank bank;

    @Column(name = "BALANCE", nullable = false)
    private Double balance = 0.0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference
    @JoinColumn(name = "USER_INFO_ID", referencedColumnName = "id", nullable = false)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<AccountTransaction> accountTransactions;

    @Override
    protected void onPrePersist() {
        this.accountNumber = "ACC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}
