package com.payapp.models;

import com.payapp.shared.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;


/**
 * Created by nexus on 12/8/17.
 */
@Table(name = "parent")
@Entity
public class Parent {

    @Id
    @Column(name = "parent_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    @Email
    @NotNull
    private String email;

    @Column(name = "password")
    @Length(min = 6, max = 12)
    @NotNull
    private String password;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "phone")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column
    @OneToMany(mappedBy = "parent")
    private Collection<Child> child;

    @Transient
    @OneToOne(mappedBy = "", fetch = FetchType.LAZY)
    private Role role;

    @OneToMany(mappedBy = "parent")
    @Column(name = "accounts")
    private Collection<ParentAccount> parentAccountList;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<ParentAccount> getParentAccountList() {
        return parentAccountList;
    }

    public void setParentAccountList(Collection<ParentAccount> parentAccountList) {
        this.parentAccountList = parentAccountList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Child> getChild() {
        return child;
    }

    public void setChild(Collection<Child> childList) {
        this.child = childList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
