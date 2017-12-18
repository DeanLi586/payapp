package com.payapp.models;

import com.payapp.shared.Account;
import com.payapp.shared.AccountType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by nexus on 12/17/17.
 */
@Table(name = "parent_account")
@Entity
public class ParentAccount{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;

    @NotNull
    private String accountName;

    @NotNull
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "account_number")
    @NotNull
    private String accountNumber;

    @OneToOne/*(mappedBy = "parent_account", fetch = FetchType.EAGER)*/
    private Parent parent;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
