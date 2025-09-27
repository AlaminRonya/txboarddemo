package com.sdlcpro.txdemo.data.specification;

import com.sdlcpro.txdemo.data.entity.AccountTransaction;
import com.sdlcpro.txdemo.enums.TransactionType;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class AccountTransactionSpecification {
    public static Specification<AccountTransaction> byListOfTransactionTypes(Set<TransactionType> transactionTypes){
        if (transactionTypes == null || transactionTypes.isEmpty()){
            return null;
        }
        return (root, query, criteriaBuilder) -> root.get("transactionType").in(transactionTypes);
    }

    public static Specification<AccountTransaction> byListOfAccountNumbers(Set<String> accountNumbers){
        if (accountNumbers == null || accountNumbers.isEmpty()){
            return null;
        }
        return (root, query, criteriaBuilder) -> root.get("bankAccount").get("accountNumber").in(accountNumbers);
    }

    public static Specification<AccountTransaction> byTransactionRance(Double start, Double end){
        if (start == null || end == null){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), start),
                criteriaBuilder.lessThanOrEqualTo(root.get("amount"), end)
        );
    }
}
