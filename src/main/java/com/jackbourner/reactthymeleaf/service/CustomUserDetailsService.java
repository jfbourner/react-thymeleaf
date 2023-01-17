package com.jackbourner.reactthymeleaf.service;

import com.jackbourner.reactthymeleaf.dto.UserDto;
import com.jackbourner.reactthymeleaf.model.Role;
import com.jackbourner.reactthymeleaf.model.UserAccount;
import com.jackbourner.reactthymeleaf.repository.RoleRepository;
import com.jackbourner.reactthymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserAccount registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }
        if (usernameExists(userDto.getUsername())) {
            throw new UserAlreadyExistException("Username already taken or similar to an existing username");
        }
        final UserAccount newUser = new UserAccount();

        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        //Disable accounts until email approval
        newUser.setEnabled(false);
        // user.setUsing2FA(userDto.isUsing2FA());
        Role roles = roleRepository.findByName("ROLE_USER");
        newUser.setRoles(Collections.singleton(roles));
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserAccount userAccount = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));
        return new User(userAccount.getUsername(),
                userAccount.getPassword(),
                mapRolesToAuthorities(userAccount.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<UserDto> getAllUsers() {
        List<UserAccount> acc = userRepository.findAll();
        return acc.stream().map(this::MapModelToDtoUser).collect(Collectors.toList());
    }

    public UserDto getUserDetails(String username) {
        return userRepository.findByUsername(username)
                .map(this::MapModelToDtoUser).orElse(new UserDto());
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private UserDto MapModelToDtoUser(UserAccount user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setEnabled(user.getEnabled());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}


