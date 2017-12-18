package com.payapp.models;

import com.payapp.shared.Role;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by nexus on 12/17/17.
 */
@Entity
@Table(name = "school")
public class School{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "school_name", length = 50, unique = true)
    private String name;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    @Email
    @NotNull
    private String email;

    @Transient
    @OneToOne(mappedBy = "", fetch = FetchType.LAZY)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Child> child;

    @OneToMany(mappedBy = "school", fetch = FetchType.LAZY)
    @Column(name = "accounts")
    private Collection<SchoolAccount> schoolAccountList;

    @NotNull
    @Column(name = "password")
    private String password;

    public Collection<Child> getChild() {
        return child;
    }

    public void setChild(Collection<Child> child) {
        this.child = child;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<SchoolAccount> getSchoolAccountList() {
        return schoolAccountList;
    }

    public void setSchoolAccountList(Collection<SchoolAccount> schoolAccountList) {
        this.schoolAccountList = schoolAccountList;
    }
}
