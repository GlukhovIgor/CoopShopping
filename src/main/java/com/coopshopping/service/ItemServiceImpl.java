package com.coopshopping.service;

import com.coopshopping.model.*;
import com.coopshopping.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import static java.lang.Math.toIntExact;

import java.util.*;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestCollectionRepository requestCollectionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RequestRepository requestRepository;

    //private int itemcount = this.countItems() + 1;

    @Override
    public Item findById(int itemid) {
        return itemRepository.findByItemid(itemid);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    /*@Override
    public int countItems() {
        return toIntExact(itemRepository.count()) + 1;
    }*/

    @Override
    public void addItem(String name, String desc, int categoryid) {
        Category category = categoryRepository.findByCategoryid(categoryid);
        Item addedItem = new Item(/*toIntExact(itemRepository.count()) + 1,*/ name, desc, categoryid, category, userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUserid(), userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        itemRepository.save(addedItem);
        RequestCollection requestCollection = new RequestCollection(itemRepository.findByName(name).getItemid());
        Set<RequestCollection> requestCollections = new LinkedHashSet<>();
        requestCollections.add(requestCollection);
        addedItem.setRequestcollectionsByItemid(requestCollections);
        requestCollectionRepository.save(requestCollection);
        itemRepository.save(addedItem);
    }

    @Override
    public void updateItem(Item item, User user) {
        //item.setName(item.getName());
        item.setUsercreator(userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUserid());
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(int id) {
        for(RequestCollection rq : requestCollectionRepository.findAllByItem(id))
        {
            for(Request r : requestRepository.findAllByRequestcollection(rq.getRequestcollectionid())){
                requestRepository.delete(r);
            }
            requestCollectionRepository.delete(rq);
        }
        itemRepository.delete(id);
    }

    @Override
    public void addRequest(Item item, User user) {
        Iterator<RequestCollection> itr = requestCollectionRepository.findAllByItem(item.getItemid()).iterator();
        RequestCollection reqCol = itr.next();
        while(itr.hasNext()) {
            reqCol=itr.next();
        }
        Request req = new Request(user.getUserid(), reqCol.getRequestcollectionid());
        requestRepository.save(req);
        Set<Request> requestSet = reqCol.getRequestsByRequestcollectionid();
        requestSet.add(req);
        reqCol.setRequestsByRequestcollectionid(requestSet);
        requestCollectionRepository.save(reqCol);
    }
}
