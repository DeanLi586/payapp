package com.payapp.controllers;

import com.payapp.models.*;
import com.payapp.repositories.*;
import com.payapp.shared.AccountType;
import com.payapp.shared.Role;
import com.payapp.shared.RoleType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;

/**
 * Created by nexus on 12/8/17.
 */
@Controller
@RequestMapping("/payapp/parent")
public class ParentController {

//    @Autowired
    private ParentRepository parentRepository;
    private ParentAccountRepository parentAccountRepository;
    private RoleRepository roleRepository;
    private ChildRepository childRepository;
    private SchoolRepository schoolRepository;

    public ParentController(ParentRepository parentRepository, ParentAccountRepository parentAccountRepository,
                            RoleRepository roleRepository, ChildRepository childRepository,
                            SchoolRepository schoolRepository){
        this.parentRepository = parentRepository;
        this.parentAccountRepository = parentAccountRepository;
        this.roleRepository = roleRepository;
        this.childRepository = childRepository;
        this.schoolRepository = schoolRepository;
    }


    //-------------------- POST REQUESTS ---------------------------//

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public @ResponseBody String signUp(@RequestBody Parent parent){
        Parent p = parentRepository.findParentByEmail(parent.getEmail());
        Role r = new Role();
        r.setName(RoleType.ROLE_PARENT);

        if(p == null){
            p = new Parent();
            p.setFirstName(parent.getFirstName());
            p.setLastName(parent.getLastName());
            p.setEmail(parent.getEmail());
            p.setPassword(parent.getPassword());
            p.setPhone(parent.getPhone());
            p.setRole(r);
            parentRepository.save(p);
        }else {
            return "Unable to create Account";
        }

        return "Account Created Successfully";
    }

    @PostMapping("/login")
    public @ResponseBody String login(@RequestBody ParentLogin parent){
        Parent p = parentRepository.findParentByEmail(parent.getEmail());
        if(p == null){
            return "You do not have an account\nPlease Register";
        }else{
            if(!parent.getPassword().equals(p.getPassword())){
                return "Invalid username or password";
            }
            return "Hello "+p.getFirstName();
        }
    }

    @PostMapping("/addNewChild")
    @RolesAllowed("ROLE_PARENT")
    public @ResponseBody String addNewChild(@RequestBody Child child, @RequestParam Long id){

        Parent p = parentRepository.findOne(id);

        Child c = childRepository.findByLastNameAndParentId(child.getLastName(), id);

        if(c == null){
            c = new Child();
            c.setAge(child.getAge());
            c.setFirstName(child.getFirstName());
            c.setLastName(child.getLastName());
            c.setParent(p);
            c.setSchool(schoolRepository.findByName(child.getSchoolName()));
            childRepository.save(c);


            return "child added";
        }else{
            return "child already exists";
        }
    }

    @PostMapping("/addNewAccount")
    public @ResponseBody String addNewAccount(@RequestBody ParentAccount account, @RequestParam(name = "id") Long id){
        Parent p = parentRepository.findOne(id);
        ParentAccount parentAccount = account;

        switch(parentAccount.getAccountProvider()){
            case "MTN":
            case "mtn":
                parentAccount.setAccountType(AccountType.MTN_MOBILE_MONEY);
                break;
            case "Vodafone":
            case "vodafone":
                parentAccount.setAccountType(AccountType.VODAFONE_CASH);
                break;
            case "Airtel":
            case "airtel":
                parentAccount.setAccountType(AccountType.AIRTEL_MONEY);
                break;
            case "VISA":
            case "visa":
                parentAccount.setAccountType(AccountType.VISA);
                break;
            case "TIGO":
            case "tigo":
                parentAccount.setAccountType(AccountType.TIGO_CASH);
                break;
            default:
                return "Account type is not yet supported";
        }

        parentAccount.setParent(p);
        parentAccountRepository.save(parentAccount);

        return "Account Created";
    }


    //---------------------------GET REQUESTS -----------------------------//

    @GetMapping("/getAccounts")
    public @ResponseBody Collection<ParentAccount> getAccounts(@RequestParam(name = "id") Long id){
        Collection<ParentAccount> accounts = parentAccountRepository.findAllByParentId(id);
        if(accounts == null){
            return null;
        }
        System.out.println("Account is: {"+accounts+"}");
        return accounts;
    }

    @GetMapping("/getAccountById")
    public @ResponseBody ParentAccount getAccountById(@RequestParam(name = "accountId") Long id){
        return parentAccountRepository.findOne(id);
    }

    @GetMapping("/getChildren")
    public @ResponseBody Collection<Child> getChildren(@RequestParam(name = "id") Long id){
        return childRepository.findAllByParentId(id);
    }

    @GetMapping("/getChildById")
    public @ResponseBody Child getChildById(@RequestParam(name = "childId") Long id){
        return childRepository.findOne(id);
    }
}
