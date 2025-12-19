package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constants.edusphereConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
@Slf4j, is a Lombok-provided annotation that will automatically generate an SLF4J
Logger static property in the class at compilation time.
* */
@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ContactService(){
        System.out.println("Contact Service Bean initialized");
    }

    /**
     * Save Contact Details into DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        contact.setStatus(edusphereConstants.OPEN);
        contact.setCreatedBy(edusphereConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if(result > 0){
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus(){
        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(edusphereConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String updateBy){
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId, edusphereConstants.CLOSE, updateBy);
        if(result > 0){
            isUpdated = true;
        }
        return isUpdated;
    }

}
