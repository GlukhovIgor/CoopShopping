package com.coopshopping.model;


import javax.persistence.*;
import java.util.*;

@Entity
public class RequestCollection {
    private int requestcollectionid;
    private Date startdate;
    private Date enddate;
    private int item;
    private String allowrequests;
    private Set<Request> requestsByRequestcollectionid;
    private Item itemByItem;

    public RequestCollection() {
    }

    public RequestCollection(int item) {
        java.util.Date utilDate = new java.util.Date();
        //this.startdate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        this.startdate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(utilDate);
        c.add(Calendar.MONTH, 1);
        this.enddate = c.getTime();
        this.item = item;
        this.allowrequests = "yes";
        this.requestsByRequestcollectionid = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "requestcollectionid")
    public int getRequestcollectionid() {
        return requestcollectionid;
    }

    public void setRequestcollectionid(int requestcollectionid) {
        this.requestcollectionid = requestcollectionid;
    }

    @Basic
    @Column(name = "startdate")
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @Basic
    @Column(name = "enddate")
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Basic
    @Column(name = "item")
    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    @Basic
    @Column(name = "allowrequests")
    public String getAllowrequests() {
        return allowrequests;
    }

    public void setAllowrequests(String allowrequests) {
        this.allowrequests = allowrequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestCollection that = (RequestCollection) o;
        return requestcollectionid == that.requestcollectionid &&
                item == that.item &&
                Objects.equals(startdate, that.startdate) &&
                Objects.equals(enddate, that.enddate) &&
                Objects.equals(allowrequests, that.allowrequests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestcollectionid, startdate, enddate, item, allowrequests);
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "requestcollectionByRequestcollection")
    public Set<Request> getRequestsByRequestcollectionid() {
        return requestsByRequestcollectionid;
    }

    public void setRequestsByRequestcollectionid(Set<Request> requestsByRequestcollectionid) {
        this.requestsByRequestcollectionid = requestsByRequestcollectionid;
    }

    @ManyToOne
    @JoinColumn(name = "item", referencedColumnName = "itemid", nullable = false, insertable = false, updatable = false)
    public Item getItemByItem() {
        return itemByItem;
    }

    public void setItemByItem(Item itemByItem) {
        this.itemByItem = itemByItem;
    }
}
