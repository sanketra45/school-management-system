package com.eazybytes.eazyschool.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.eazybytes.eazyschool.service.ContactService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.eazybytes.eazyschool.model.Contact;


import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactController {

    private static Logger log = LoggerFactory.getLogger(ContactController.class);

    private final ContactService contactService;;

    @Autowired
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @RequestMapping(value = "/contact")
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

//
//    @RequestMapping(value = "saveMsg", method = POST)
//    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum, @RequestParam String email,
//                                    @RequestParam String subject, @RequestParam String message) {
//        log.info("Name : " + name);
//        log.info("Mobile No : " + mobileNum);
//        log.info("Email : " + email);
//        log.info("Subject : " + subject);
//        log.info("Message : " + message);
//
//        return new ModelAndView("redirect:/contact");
//    }




    @RequestMapping(value = "/saveMsg", method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors)
    {
        if(errors.hasErrors()){
            log.error("Contact form validations failed due to : " + errors.toString());
            return "contact";
        }
        contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }




}
