package com.sdlcpro.txdemo.utils.url;

public interface UrlPath {
    String DASH = "-";
    String SEPARATOR = "/";
    String BASE_PATH = SEPARATOR + "api";
    String VERSION = SEPARATOR + "v1" + SEPARATOR;
    String OPEN_PARENTHESIS = "{";
    String CLOSE_PARENTHESIS = "}";
    String SWAGGER_PATH = SEPARATOR + "swagger-ui/**";
    String API_DOCS_PATH = SEPARATOR + "v3/api-docs/**";
    String IDENTIFIER_ID = SEPARATOR + OPEN_PARENTHESIS + "id" + CLOSE_PARENTHESIS;
    String PAGINATION = SEPARATOR + "pagination";

    interface BankAccount {
        String ROOT_PATH = BASE_PATH + VERSION + "bank" + DASH + "accounts";
        String BANK_ACCOUNT_IDENTIFIER = IDENTIFIER_ID;
        String BANK_ACCOUNT_PAGINATION = PAGINATION;
    }

    interface UserInfo {
        String ROOT_PATH = BASE_PATH + VERSION + "user" + DASH + "informations";
        String USER_INFO_IDENTIFIER = IDENTIFIER_ID;
        String USER_INFO_PAGINATION = PAGINATION;
    }

    interface Transaction {
        String ROOT_PATH = BASE_PATH + VERSION + "transactions";
        String TRANSACTION_IDENTIFIER = IDENTIFIER_ID;
        String TRANSACTION_PAGINATION = PAGINATION;
        String TRANSACTION_SEARCHING_PAGINATION = PAGINATION + SEPARATOR + "searching";
        String TRANSACTION_DEBIT= SEPARATOR + "debit";
        String TRANSACTION_CREDIT = SEPARATOR + "credit";
    }


}
