package com.coopshopping.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item_usersliked", schema = "shop")
@IdClass(ItemUserslikedPK.class)
public class ItemUsersliked {
    private int user;
    private int item;
    private Item itemByItem;
    private User userByUser;

    @Id
    @Column(name = "user")
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Id
    @Column(name = "item")
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
        ItemUsersliked that = (ItemUsersliked) o;
        return user == that.user &&
                item == that.item;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, item);
    }

    @ManyToOne
    @JoinColumn(name = "item", referencedColumnName = "itemid", nullable = false, insertable = false, updatable = false)
    public Item getItemByItem() {
        return itemByItem;
    }

    public void setItemByItem(Item itemByItem) {
        this.itemByItem = itemByItem;
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    public User getUserByUser() {
        return userByUser;
    }

    public void setUserByUser(User userByUser) {
        this.userByUser = userByUser;
    }
}
