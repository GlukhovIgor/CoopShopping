package com.coopshopping.repository;

import com.coopshopping.model.Request;
import com.coopshopping.model.RequestCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    Request findByRequestid(int id);
    Set<Request> findAllByRequestcollection(int reqcolid);
}
