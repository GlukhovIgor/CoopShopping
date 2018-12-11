package com.coopshopping.model;

import com.coopshopping.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Category {
    private int categoryid;
    private String name;
    private Collection<Item> itemsByCategoryid;


    public Category() {
    }

    public Category(int categoryid) {
        this.categoryid = categoryid;
    }

    @Id
    @Column(name = "categoryid")
    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
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
        Category category = (Category) o;
        return categoryid == category.categoryid &&
                Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryid, name);
    }

    @OneToMany(mappedBy = "categoryByCategory")
    public Collection<Item> getItemsByCategoryid() {
        return itemsByCategoryid;
    }

    public void setItemsByCategoryid(Collection<Item> itemsByCategoryid) {
        this.itemsByCategoryid = itemsByCategoryid;
    }
}
