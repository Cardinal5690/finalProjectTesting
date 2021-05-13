package com.testing.model.entity;

import com.testing.model.entity.type.Role;
import com.testing.model.entity.type.Status;
import com.testing.model.validation.constraint.RoleSubset;
import com.testing.model.validation.constraint.StatusSubset;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class User {
    @NotNull
    private Integer id;
    @NotNull
    @Size(min = 2,max = 50)
    private String name;
    @NotNull
    @Size(max=50)
    private String surname;
    @NotNull
    @Email
    private String email;
    @NotNull
    private Integer password;
    @RoleSubset(anyOf = {Role.UNREGISTERED, Role.STUDENT,Role.ADMIN})
    private Role role;
    @StatusSubset(anyOf = {Status.BLOCKED, Status.UNBLOCKED})
    private Status status;
    private List<Subject> subjectList;

    public User() {
    }

    public User(String name, String surname, String email, Integer password, Role role, Status status) {
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

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
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

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && role == user.role && status == user.status && Objects.equals(subjectList, user.subjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, password, role, status, subjectList);
    }
}
