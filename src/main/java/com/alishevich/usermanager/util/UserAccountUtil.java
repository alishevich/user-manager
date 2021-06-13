package com.alishevich.usermanager.util;

import com.alishevich.usermanager.model.UserAccount;
import com.alishevich.usermanager.to.UserAccountTo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public class UserAccountUtil {

    public static UserAccount createNewFromTo(UserAccountTo userTo, BCryptPasswordEncoder encoder) {
        return new UserAccount(null, userTo.getUserName(), encoder.encode(userTo.getPassword()),
                userTo.getFirstName(), userTo.getLastName(), userTo.getRole(), userTo.getStatus(), new Date());
    }

    public static UserAccount updateFromTo(UserAccount user, UserAccountTo userTo, BCryptPasswordEncoder encoder) {
        user.setUserName(userTo.getUserName());
        user.setPassword(encoder.encode(userTo.getPassword()));
        user.setFirstName(user.getFirstName());
        user.setLastName(userTo.getLastName());
        user.setRole(userTo.getRole());
        user.setStatus(userTo.getStatus());
        return user;
    }
}
