package com.coopshopping.repository;

import com.coopshopping.model.RequestCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RequestCollectionRepository extends JpaRepository<RequestCollection, Integer> {
    RequestCollection findByItem(int itemid);
    Set<RequestCollection> findAllByItem(int itemid);
}
