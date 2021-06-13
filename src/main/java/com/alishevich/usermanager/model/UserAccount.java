package com.alishevich.usermanager.model;

import lombok.*;
import org.springframework.data.util.ProxyUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @Size(min = 3, max = 16)
    @NotBlank
    private String userName;

    @Column(name = "password")
    @Size(max = 100)
    @NotBlank
    private String password;

    @Column(name = "first_name")
    @Size(min = 1, max = 16)
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 1, max = 16)
    @NotBlank
    private String lastName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at", columnDefinition = "timestamp default now()")
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
