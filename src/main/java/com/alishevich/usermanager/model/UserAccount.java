package com.alishevich.usermanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.util.ProxyUtils;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"password"})
public class UserAccount {

    private Integer id;

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private Role role;

    private Status status;

    private Date createdAt = new Date();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(ProxyUtils.getUserClass(o))) {
            return false;
        }
        UserAccount that = (UserAccount) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
