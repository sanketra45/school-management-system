package com.eazybytes.eazyschool.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Contact {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", mobileNum='" + mobileNum + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


//    Validations

    @NotBlank(message="name must not be blank")
    @Size(min = 3, message = "name should be atleast 3 character long")
    private String name;

    @NotBlank(message = "Mobile Number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})")
    private String mobileNum;

    @NotBlank(message = "email must not be blank")
    @Email(message = "please provide a valid email address")
    private String email;

    @NotBlank(message = "subject must not be blank")
    @Size(min = 5, message = "subject must be 5 character long")
    private String subject;

    @NotBlank(message = "message must not be blank")
    @Size(min = 10, message = "message must be 10 character long")
    private String message;
}
