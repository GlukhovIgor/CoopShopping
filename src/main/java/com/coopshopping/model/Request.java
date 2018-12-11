package com.coopshopping.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Request {
    private int requestid;
    private Date requestdate;
    private int user;
    private int requestcollection;
    private String accepted;
    private User userByUser;
    private RequestCollection requestcollectionByRequestcollection;


    public Request() {
    }

    public Request(int user, int requestcollection) {
        this.requestdate = new Date();
        this.accepted = "no";
        this.user = user;
        this.requestcollection = requestcollection;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "requestid")
    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    @Basic
    @Column(name = "requestdate")
    public Date getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(Date requestdate) {
        this.requestdate = requestdate;
    }

    @Basic
    @Column(name = "user")
    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Basic
    @Column(name = "requestcollection")
    public int getRequestcollection() {
        return requestcollection;
    }

    public void setRequestcollection(int requestcollection) {
        this.requestcollection = requestcollection;
    }

    @Basic
    @Column(name = "accepted")
    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return requestid == request.requestid &&
                user == request.user &&
                requestcollection == request.requestcollection &&
                Objects.equals(requestdate, request.requestdate) &&
                Objects.equals(accepted, request.accepted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestid, requestdate, user, requestcollection, accepted);
    }

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    public User getUserByUser() {
        return userByUser;
    }

    public void setUserByUser(User userByUser) {
        this.userByUser = userByUser;
    }

    @ManyToOne
    @JoinColumn(name = "requestcollection", referencedColumnName = "requestcollectionid", nullable = false, insertable = false, updatable = false)
    public RequestCollection getRequestcollectionByRequestcollection() {
        return requestcollectionByRequestcollection;
    }

    public void setRequestcollectionByRequestcollection(RequestCollection requestcollectionByRequestcollection) {
        this.requestcollectionByRequestcollection = requestcollectionByRequestcollection;
    }
}
