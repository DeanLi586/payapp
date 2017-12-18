package com.payapp.controllers;

import com.payapp.models.School;
import com.payapp.models.SchoolLogin;
import com.payapp.repositories.RoleRepository;
import com.payapp.repositories.SchoolAccountRepository;
import com.payapp.repositories.SchoolRepository;
import com.payapp.shared.Role;
import com.payapp.shared.RoleType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by nexus on 12/17/17.
 */
@RestController
@RequestMapping(path = "/payapp/school")
public class SchoolController {

    private SchoolRepository schoolRepository;
    private SchoolAccountRepository schoolAccountRepository;
    private RoleRepository roleRepository;

    public SchoolController(SchoolAccountRepository schoolAccountRepository, SchoolRepository schoolRepository,
                            RoleRepository roleRepository){
        this.schoolAccountRepository = schoolAccountRepository;
        this.schoolRepository  = schoolRepository;
        this.roleRepository = roleRepository;
    }


    @PostMapping("/register")
    @ResponseBody public String register(@RequestBody School school){

        School s = schoolRepository.findByEmail(school.getEmail());
        Role r = roleRepository.findByName(RoleType.ROLE_SCHOOL);

        if(s == null){
            s = new School();
            s.setEmail(school.getEmail());
            s.setName(school.getName());
            s.setPhone(school.getPhone());
            s.setRole(r);
            s.setPassword(school.getPassword());
            schoolRepository.save(s);
        }else {
            return "You already have an account with us\nPlease log in";
        }

        return "Account Successfully created";
    }

    @PostMapping("/login")
    @ResponseBody public String login(@RequestBody SchoolLogin login){
        School s = schoolRepository.findByEmail(login.getEmail());
        if(s == null){
            return "Sorry, your email was not found in our database\nPlease create an account";
        }else{
            if(!s.getPassword().equals(login.getPassword())){
                return "Invalid password/username";
            }else{
                return "Login successful";
            }
        }
    }
}
