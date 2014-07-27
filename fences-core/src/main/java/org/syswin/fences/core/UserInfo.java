package org.syswin.fences.core;

import java.util.Date;

public class UserInfo {

    private String           firstName;
    private String           lastName;
    private String           username;
    private String           phoneNumber;
    private String           email;
    private String           employeeId;
    private Permission permission;
    private Date             createdDate;

    public UserInfo () {
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPhoneNumber () {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getEmployeeId () {
        return employeeId;
    }

    public void setEmployeeId (String employeeId) {
        this.employeeId = employeeId;
    }

    public Permission getPermission () {
        return permission;
    }

    public void setPermission (Permission permission) {
        this.permission = permission;
    }

    public Date getCreatedDate () {
        return createdDate;
    }

    public void setCreatedDate (Date createdDate) {
        this.createdDate = createdDate;
    }
}
