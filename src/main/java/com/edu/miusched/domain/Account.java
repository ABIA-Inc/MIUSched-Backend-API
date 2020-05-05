package com.edu.miusched.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Account {

    @Id
    Long Id;
    @NonNull
    private String username;
    @NonNull
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Role role;
}
