package com.edu.miusched.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @OneToOne
    private Address address;
    @OneToOne
    private Account account;

    private int active;

    public Admin(@NotEmpty Address address, Account account) {
        this.address = address;
        this.account = account;
        this.active = 1;
    }
}
