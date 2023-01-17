package com.jackbourner.reactthymeleaf.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "user_account", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
@Setter
@Getter
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ACCOUNT_ID")
    private Long id;

    @NotNull
    @Column(nullable = false, name="FIRSTNAME")
    private String firstName;

    @NotNull
    @Column(nullable = false , name="LASTNAME")
    private String lastName;

    @NotNull
    @Column(nullable = false, unique = true , name="USERNAME")
    private String username;

    @NotNull
    @Column(nullable = false, unique = true , name="EMAIL")
    private String email;

    @NotNull
    @Column(nullable = false , name="PASSWORD")
    private String password;

    @NotNull
    @Column(nullable = false , name="ENABLED")
    private Boolean enabled;

    @Column(nullable = true)
    private Boolean isUsing2FA;

    @Nullable
    private String secret;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ACCOUNT_ID"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
