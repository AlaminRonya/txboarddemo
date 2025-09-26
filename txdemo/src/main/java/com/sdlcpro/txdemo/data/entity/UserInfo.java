package com.sdlcpro.txdemo.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "USER_INFORMATIONS")
public class UserInfo extends BaseEntity{

    @Column(name = "FISRT_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "PHONE_NUMBER", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<BankAccount> bankAccounts;

}
