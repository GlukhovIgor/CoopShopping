package com.coopshopping.service;

import com.coopshopping.model.RequestCollection;
import com.coopshopping.repository.RequestCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RequestCollectionServiceImpl implements RequestCollectionService {
    @Autowired
    RequestCollectionRepository requestCollectionRepository;

    @Override
    public RequestCollection findByItem(int itemid) {
        return requestCollectionRepository.findByItem(itemid);
    }

    @Override
    public Set<RequestCollection> findAllByItem(int itemid) {
        return requestCollectionRepository.findAllByItem(itemid);
    }
}
