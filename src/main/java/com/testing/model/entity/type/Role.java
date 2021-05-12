package com.testing.model.entity.type;

public enum Role {
    UNREGISTERED(0),
    STUDENT(1),
    ADMIN(2);

    private final int ROLE;

    Role(int ROLE) {
        this.ROLE = ROLE;
    }

    public int getROLE() {
        return ROLE;
    }

}
