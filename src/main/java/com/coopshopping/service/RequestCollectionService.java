package com.coopshopping.service;

import com.coopshopping.model.RequestCollection;

import java.util.Set;

public interface RequestCollectionService {
    RequestCollection findByItem(int itemid);
    Set<RequestCollection> findAllByItem(int itemid);
}
