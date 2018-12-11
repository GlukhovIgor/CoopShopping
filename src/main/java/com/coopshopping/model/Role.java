package com.coopshopping.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Role {
    private int roleid;
    private String name;
    private Collection<UserRole> userRolesByRoleid;

    @Id
    @Column(name = "roleid")
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleid == role.roleid &&
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleid, name);
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<UserRole> getUserRolesByRoleid() {
        return userRolesByRoleid;
    }

    public void setUserRolesByRoleid(Collection<UserRole> userRolesByRoleid) {
        this.userRolesByRoleid = userRolesByRoleid;
    }
}
