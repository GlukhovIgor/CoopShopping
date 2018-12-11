package com.coopshopping.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.*;

@Entity
@DynamicUpdate
public class Item {
    private int itemid;
    private String name;
    private Date postdate;
    private String description;
    private int category;
    private int usercreator;
    private Category categoryByCategory;
    private User userByUsercreator;
    private Collection<ItemUsersliked> itemUserslikedsByItemid;
    private Set<RequestCollection> requestcollectionsByItemid;

    public Item(/*int itemid,*/ String name, String description, int categoryid, Category category,int usercreator, User user/*, RequestCollection requestCollection*/) {
        //this.itemid = itemid;
        this.name = name;
        //java.util.Date utilDate = new java.util.Date();
        //this.postdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        this.postdate = new Date();
        this.description = description;
        this.category = categoryid;
        this.usercreator = usercreator;
        /*Set<RequestCollection> requestCollections = new HashSet<>();
        requestCollections.add(requestCollection);
        this.requestcollectionsByItemid = requestCollections;*/
        this.categoryByCategory = category;
    }

    public Item() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "itemid")
    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "postdate")
    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "category")
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Basic
    @Column(name = "usercreator")
    public int getUsercreator() {
        return usercreator;
    }

    public void setUsercreator(int usercreator) {
        this.usercreator = usercreator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemid == item.itemid &&
                category == item.category &&
                usercreator == item.usercreator &&
                Objects.equals(name, item.name) &&
                Objects.equals(postdate, item.postdate) &&
                Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemid, name, postdate, description, category, usercreator);
    }

    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "categoryid", nullable = false, insertable = false, updatable = false)
    public Category getCategoryByCategory() {
        return categoryByCategory;
    }

    public void setCategoryByCategory(Category categoryByCategory) {
        this.categoryByCategory = categoryByCategory;
    }

    @ManyToOne
    @JoinColumn(name = "usercreator", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    public User getUserByUsercreator() {
        return userByUsercreator;
    }

    public void setUserByUsercreator(User userByUsercreator) {
        this.userByUsercreator = userByUsercreator;
    }

    @OneToMany(mappedBy = "itemByItem")
    public Collection<ItemUsersliked> getItemUserslikedsByItemid() {
        return itemUserslikedsByItemid;
    }

    public void setItemUserslikedsByItemid(Collection<ItemUsersliked> itemUserslikedsByItemid) {
        this.itemUserslikedsByItemid = itemUserslikedsByItemid;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "itemByItem")
    public Set<RequestCollection> getRequestcollectionsByItemid() {
        return requestcollectionsByItemid;
    }

    public void setRequestcollectionsByItemid(Set<RequestCollection> requestcollectionsByItemid) {
        this.requestcollectionsByItemid = requestcollectionsByItemid;
    }
}
