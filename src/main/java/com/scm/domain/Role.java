package com.scm.domain;

import com.scm.domain.enums.RoleType;
import lombok.Data;

import javax.persistence.*;

@Data

@Table(name = "tbl_role")
@Entity
public class Role extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType type;

    @Override
    public String toString() {

        return "Role [type=" + type + "]";
    }
}
