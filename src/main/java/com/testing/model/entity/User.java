package com.testing.model.entity;

import com.testing.model.entity.type.Role;
import com.testing.model.entity.type.Status;
import com.testing.model.validation.constraint.RoleSubset;
import com.testing.model.validation.constraint.StatusSubset;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class User {
    private Integer id;
    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "[A-Z][a-z]{1,20}$")
    private String name;
    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "[A-Z][a-z]{1,20}$")
    private String surname;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 6)
    private String password;
    @RoleSubset(anyOf = {Role.STUDENT, Role.ADMIN})
    private Role role;
    @StatusSubset(anyOf = {Status.BLOCKED, Status.UNBLOCKED})
    private Status status;

    public User() {
    }

    public User(String name, String surname, String email, String password, Role role, Status status) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && role == user.role && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, role, status);
    }
}
