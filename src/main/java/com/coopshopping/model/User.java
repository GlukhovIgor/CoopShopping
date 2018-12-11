package com.coopshopping.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class User {
    private int userid;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String passwordConfirm;
    private Collection<Item> itemsByUserid;
    private Collection<ItemUsersliked> itemUserslikedsByUserid;
    private Collection<Request> requestsByUserid;
    private Collection<UserRole> userRolesByUserid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userid == user.userid &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(lastname, user.lastname) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, firstname, lastname, username, password);
    }

    @OneToMany(mappedBy = "userByUsercreator")
    public Collection<Item> getItemsByUserid() {
        return itemsByUserid;
    }

    public void setItemsByUserid(Collection<Item> itemsByUserid) {
        this.itemsByUserid = itemsByUserid;
    }

    @OneToMany(mappedBy = "userByUser")
    public Collection<ItemUsersliked> getItemUserslikedsByUserid() {
        return itemUserslikedsByUserid;
    }

    public void setItemUserslikedsByUserid(Collection<ItemUsersliked> itemUserslikedsByUserid) {
        this.itemUserslikedsByUserid = itemUserslikedsByUserid;
    }

    @OneToMany(mappedBy = "userByUser")
    public Collection<Request> getRequestsByUserid() {
        return requestsByUserid;
    }

    public void setRequestsByUserid(Collection<Request> requestsByUserid) {
        this.requestsByUserid = requestsByUserid;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<UserRole> getUserRolesByUserid() {
        return userRolesByUserid;
    }

    public void setUserRolesByUserid(Collection<UserRole> userRolesByUserid) {
        this.userRolesByUserid = userRolesByUserid;
    }
}
