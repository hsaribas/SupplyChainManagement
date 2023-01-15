package com.scm.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "tbl_user")
@Entity
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 120, nullable = false)
    private String password;

    @Column(length = 80, nullable = false, unique = true)
    private String email;

    @Column(length = 12, nullable = false, unique = true)
    private String phoneNumber;

    @ManyToMany
    @JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
