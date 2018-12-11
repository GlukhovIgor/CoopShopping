package com.coopshopping.service;

import com.coopshopping.model.Request;

import java.util.Set;

public interface RequestService {

    Request findByRequestid(int id);

    Set<Request> findAllByRequestcollection(int reqcolid);

    void updateRequest(Request request);

    void deleteRequest(int id);

}
