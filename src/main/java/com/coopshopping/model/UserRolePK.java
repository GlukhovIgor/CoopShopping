package com.coopshopping.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserRolePK implements Serializable {
    private int userId;
    private int roleId;

    @Column(name = "user_id")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "role_id")
    @Id
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolePK that = (UserRolePK) o;
        return userId == that.userId &&
                roleId == that.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
