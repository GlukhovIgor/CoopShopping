package com.coopshopping.service;

import com.coopshopping.model.Request;
import com.coopshopping.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestRepository requestRepository;

    @Override
    public Request findByRequestid(int id) {
        return requestRepository.findByRequestid(id);
    }

    @Override
    public Set<Request> findAllByRequestcollection(int reqcolid) {
        return requestRepository.findAllByRequestcollection(reqcolid);
    }

    @Override
    public void updateRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public void deleteRequest(int id) {
        requestRepository.delete(id);
    }
}
