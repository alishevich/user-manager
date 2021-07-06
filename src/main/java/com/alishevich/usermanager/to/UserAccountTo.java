package com.alishevich.usermanager.to;

import com.alishevich.usermanager.model.Role;
import com.alishevich.usermanager.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"password"})
public class UserAccountTo {

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username must contain only Latin characters")
    @Size(min = 3, max = 16, message = "Username must be between 3 and 16 characters long")
    @NotBlank(message = "Username must not be empty")
    private String userName;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Password must contain only Latin characters and numbers")
    @Size(min = 1, max = 16, message = "Password must be between 1 and 16 characters long")
    @NotBlank(message = "Password must not be empty")
    private String password;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Firstname must contain only Latin characters")
    @Size(min = 1, max = 16,message = "Firstname must be between 1 and 16 characters long")
    @NotBlank(message = "Firstname must not be empty")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Lastname must contain only Latin characters")
    @Size(min = 1, max = 16, message = "Lastname must be between 1 and 16 characters long")
    @NotBlank(message = "Lastname must not be empty")
    private String lastName;

    private Role role;

    private Status status;
}
