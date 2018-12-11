package com.coopshopping.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import com.coopshopping.model.Item;
import com.coopshopping.model.Request;
import com.coopshopping.model.RequestCollection;
import com.coopshopping.model.User;
import com.coopshopping.repository.RequestCollectionRepository;
import com.coopshopping.repository.RequestRepository;
import com.coopshopping.service.RequestCollectionService;
import com.coopshopping.service.RequestService;
import com.coopshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.coopshopping.service.ItemService;

import javax.validation.Valid;

@Controller
@SessionAttributes("userid")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestCollectionService requestCollectionService;



    @InitBinder
    protected void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/list-items", method = RequestMethod.GET)
    public String showItemsList(ModelMap model) {
        String user = getLoggedInUserName();
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("userid",userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUserid());
        return "list-items";
    }

    @RequestMapping(value = "/add-item", method = RequestMethod.GET)
    public String showAddItemPage(ModelMap model) {
        model.addAttribute("item", new Item());
        return "item";
    }

    @RequestMapping(value = "/add-item", method = RequestMethod.POST)
    public String addItem(ModelMap model, @Valid Item item, BindingResult result) { //public String addItem(ModelMap model, @Valid Item item, BindingResult result) {

        /*if (result.hasErrors()) {
            System.out.println(result.toString());
            return "item";
        }*/
        itemService.addItem(item.getName(), item.getDescription(),item.getCategory());
        model.clear();// to prevent request parameter "name" to be passed
        return "redirect:/list-items";
    }

    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }

    @RequestMapping(value = "/update-item", method = RequestMethod.GET)
    public String showUpdateItemPage(ModelMap model, @RequestParam int id) {
        if (itemService.findById(id).getUsercreator() != userService.findByUsername(getLoggedInUserName()).getUserid())
            return "redirect:/list-items";
        model.addAttribute("item", itemService.findById(id));
        return "item";
    }

    @RequestMapping(value = "/update-item", method = RequestMethod.POST)
    public String updateItem(ModelMap model, @Valid Item item, BindingResult result) {
        itemService.updateItem(item, userService.findByUsername(getLoggedInUserName()));
        return "redirect:/list-items";
    }

    @RequestMapping(value = "/delete-item", method = RequestMethod.GET)
    public String deleteItem(@RequestParam int id, ModelMap model) {
        if (itemService.findById(id).getUsercreator() != userService.findByUsername(getLoggedInUserName()).getUserid())
            return "redirect:list-items";
        itemService.deleteItem(id);
        return "redirect:/list-items";
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("item", itemService.findById(id));
        model.addAttribute("reqcols", requestCollectionService.findAllByItem(id));
        return "showitem";
    }

    @RequestMapping(value = "/addRequest/{id}", method = RequestMethod.GET)
    public String addRequest(@PathVariable("id") int id, ModelMap model) {
        itemService.addRequest(itemService.findById(id), userService.findByUsername(getLoggedInUserName()));
        model.clear();
        return "redirect:/list-items";
    }

    @RequestMapping(value = "/showRequests/{id}", method = RequestMethod.GET)
    public String showRequests(@PathVariable("id") int id, ModelMap model) {
        if(userService.findByUsername(getLoggedInUserName()).getUserid() != itemService.findById(id).getUsercreator())
            return "accessdenied";
        Iterator<RequestCollection> itr = requestCollectionService.findAllByItem(id).iterator();
        RequestCollection reqCol = itr.next();
        while(itr.hasNext()) {
            reqCol=itr.next();
        }
        model.addAttribute("requests", requestService.findAllByRequestcollection(reqCol.getRequestcollectionid()));
        model.addAttribute("item", itemService.findById(id));
        return "listrequests";
    }
    @RequestMapping(value = "/update-request/{id}", method = RequestMethod.GET)
    public String showUpdateRequestPage(ModelMap model, @PathVariable("id") int id) {
        if (requestService.findByRequestid(id).getRequestcollectionByRequestcollection().getItemByItem().getUsercreator() != userService.findByUsername(getLoggedInUserName()).getUserid())
            return "accessdenied";
        model.addAttribute("request", requestService.findByRequestid(id));
        return "request";
    }

    @RequestMapping(value = "/update-request/{id}", method = RequestMethod.POST)
    public String updateRequest(Request request, ModelMap model, @PathVariable("id") int id) {
        requestService.updateRequest(request);
        model.clear();
        return "redirect:/showRequests/" + requestService.findByRequestid(id).getRequestcollectionByRequestcollection().getItem();
    }

    @RequestMapping(value = "/delete-request/{id}", method = RequestMethod.GET)
    public String deleteRequest(@PathVariable("id") int id, ModelMap model) {
        if (requestService.findByRequestid(id).getRequestcollectionByRequestcollection().getItemByItem().getUsercreator() != userService.findByUsername(getLoggedInUserName()).getUserid())
            return "accessdenied";
        int itemid = requestService.findByRequestid(id).getRequestcollectionByRequestcollection().getItem();
        requestService.deleteRequest(id);
        model.clear();
        return "redirect:/showRequests/" + itemid;
    }
}