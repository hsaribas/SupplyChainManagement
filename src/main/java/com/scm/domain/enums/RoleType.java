package com.scm.domain.enums;

public enum RoleType {

    ROLE_RETAILER("Retailer"),
    ROLE_SUPPLIER("Supplier");

    private String name;

    private RoleType(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }
}
