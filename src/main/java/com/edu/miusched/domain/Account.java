package com.edu.miusched.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long Id;
    @NonNull
    private String username;
    @NonNull
    private String password;

    //@Enumerated(EnumType.ORDINAL)
    private Role role;
}
