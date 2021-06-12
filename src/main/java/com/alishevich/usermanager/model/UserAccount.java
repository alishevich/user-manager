package com.alishevich.usermanager.model;

import lombok.*;
import org.springframework.data.util.ProxyUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "user_name", name = "users_unique_username_idx"))
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"password"})
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username must contain only Latin characters")
    @Size(min = 3, max = 16, message = "Username must be between 3 and 16 characters long")
    @NotBlank
    private String userName;

    @Column(name = "password")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must contain only Latin characters and numbers")
    @Size(min = 1, max = 16)
    @NotBlank
    private String password;

    @Column(name = "first_name")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Firstname must contain only Latin characters")
    @Size(min = 1, max = 16,message = "Firstname must be between 1 and 16 characters long")
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Lastname must contain only Latin characters")
    @Size(min = 1, max = 16, message = "Lastname must be between 1 and 16 characters long")
    @NotBlank
    private String lastName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", columnDefinition = "timestamp default now()")
    //@NotNull
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
