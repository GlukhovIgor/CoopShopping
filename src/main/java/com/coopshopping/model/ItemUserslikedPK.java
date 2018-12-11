package com.coopshopping.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ItemUserslikedPK implements Serializable {
    private int user;
    private int item;

    @Column(name = "user")
    @Id
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Column(name = "item")
    @Id
    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemUserslikedPK that = (ItemUserslikedPK) o;
        return user == that.user &&
                item == that.item;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, item);
    }
}
