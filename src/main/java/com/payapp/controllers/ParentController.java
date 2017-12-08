package com.payapp.controllers;

import com.payapp.models.Parent;
import com.payapp.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nexus on 12/8/17.
 */
@Controller
@RequestMapping("/payapp")
public class ParentController {

//    @Autowired
    private ParentRepository parentRepository;

    public ParentController(ParentRepository parentRepository){
        this.parentRepository = parentRepository;
    }


    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public @ResponseBody String signUp(@RequestBody Parent parent){
        Parent p = parentRepository.findParentByParentEmail(parent.getParentEmail());

        if(p == null){
            p.setFirstName(parent.getFirstName());
            p.setLastName(parent.getLastName());
            p.setParentEmail(parent.getParentEmail());
            p.setPassword(parent.getPassword());

            parentRepository.save(p);
        }else {
            return "Parent not created";
        }

        return "Parent Created";
    }
}
