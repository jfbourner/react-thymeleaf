package com.jackbourner.reactthymeleaf.dto;

import com.jackbourner.reactthymeleaf.model.Role;
import com.jackbourner.reactthymeleaf.validation.PasswordMatches;
import com.jackbourner.reactthymeleaf.validation.ValidEmail;
import com.jackbourner.reactthymeleaf.validation.ValidPassword;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    String firstName;

    @NotNull
    @NotEmpty
    String  lastName;

    @NotNull
    @NotEmpty
    @ValidEmail
    /*@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)*/
    String email;

    @NotNull
    @NotEmpty
    String username;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;
    private String matchingPassword;

    private boolean enabled;

    private Set<Role> roles;

}
