package org.syswin.fences.webapp.controllers;

import javax.servlet.http.HttpSession;

import org.syswin.fences.core.User;

public class Utils {

    static User getUser(HttpSession session) {
        return (User) session.getAttribute(User.ID);
    }
    static void setUser(HttpSession session, User user) {
        session.setAttribute(User.ID, user);
    }

}
